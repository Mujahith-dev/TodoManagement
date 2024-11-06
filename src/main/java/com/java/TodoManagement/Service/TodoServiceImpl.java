package com.java.TodoManagement.Service;

import com.java.TodoManagement.Dto.TodoDto;
import com.java.TodoManagement.Entity.Todo;
import com.java.TodoManagement.Exception.ResourceNotFoundException;
import com.java.TodoManagement.Repository.TodoRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoRepo repo;
    private final ModelMapper modelMapper;


    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        //convert TodoDto into Todo jpa entity
//        Todo todo = new Todo();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.isCompleted());
          Todo todo = modelMapper.map(todoDto, Todo.class);

        //Todo Jpa Entity
        Todo savedTodo =repo.save(todo);
        //convert saved Todo into TodoDto
//        TodoDto savedTodoDto = new TodoDto();
//        savedTodoDto.setId(savedTodo.getId());
//        savedTodoDto.setTitle(savedTodo.getTitle());
//        savedTodoDto.setDescription(savedTodo.getDescription());
//        savedTodoDto.setCompleted(savedTodo.isCompleted());
            return modelMapper.map(savedTodo, TodoDto.class);
//            return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = repo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo not found in id "+id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getTodo() {
        List<Todo> todo = repo.findAll();
        return todo.stream()
                .map(todos -> modelMapper.map(todos, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = repo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todo not found in id "+id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo updatedTodo = repo.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
       Todo todo = repo.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("Todo not found with id "+id));
       repo.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = repo.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Todo no available in the given id "+id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = repo.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo = repo.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("Todo not available for the given id "+id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = repo.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
