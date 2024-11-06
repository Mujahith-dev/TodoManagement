package com.java.TodoManagement.Service;

import com.java.TodoManagement.Dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {
    public TodoDto addTodo(TodoDto todoDto);

    public List<TodoDto> getTodo();

    public TodoDto getTodo(Long id);

    public TodoDto updateTodo(TodoDto todoDto, Long id);

    public void deleteTodo(Long id);

    public TodoDto completeTodo(Long id);

    public TodoDto incompleteTodo(Long id);
}
