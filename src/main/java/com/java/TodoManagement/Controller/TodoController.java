package com.java.TodoManagement.Controller;

import com.java.TodoManagement.Dto.TodoDto;
import com.java.TodoManagement.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoController {

    private final TodoService todoService;
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @PostMapping("/add")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo,HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TodoDto>> getTodo(){
        return new ResponseEntity<>(todoService.getTodo(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id){
        return new ResponseEntity<>(todoService.getTodo(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long id){
        return new ResponseEntity<>(todoService.updateTodo(todoDto,id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id){
        return ResponseEntity.ok(todoService.completeTodo(id));
    }

    @PatchMapping("/{id}/incomplete")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable Long id){
        return ResponseEntity.ok(todoService.incompleteTodo(id));
    }
}
