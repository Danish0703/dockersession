package com.example.todo;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class TodoService {
    private final TodoRepository repository;
    public TodoService(TodoRepository repository) { this.repository = repository; }
    public List<Todo> list() { return repository.findAllByOrderByCreatedAtDescIdDesc(); }
    public Todo get(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }
    @Transactional
    public Todo create(TodoRequest request) {
        Todo todo = new Todo(request.title(), request.description());
        todo.setCompleted(request.completed());
        return repository.save(todo);
    }
    @Transactional
    public Todo update(UUID id, TodoRequest request) {
        Todo todo = get(id);
        todo.update(request.title(), request.description(), request.completed());
        return repository.save(todo);
    }
    @Transactional
    public Todo complete(UUID id, boolean completed) {
        Todo todo = get(id);
        todo.setCompleted(completed);
        return repository.save(todo);
    }
    @Transactional
    public void delete(UUID id) { repository.delete(get(id)); }
}
