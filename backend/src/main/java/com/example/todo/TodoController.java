package com.example.todo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService service;
    public TodoController(TodoService service) { this.service = service; }
    @GetMapping
    public List<Todo> list() { return service.list(); }
    @GetMapping("/{id}")
    public Todo get(@PathVariable UUID id) { return service.get(id); }
    @PostMapping
    public ResponseEntity<Todo> create(@Valid @RequestBody TodoRequest request) {
        Todo todo = service.create(request);
        return ResponseEntity.created(URI.create("/api/todos/" + todo.getId())).body(todo);
    }
    @PutMapping("/{id}")
    public Todo update(@PathVariable UUID id, @Valid @RequestBody TodoRequest request) {
        return service.update(id, request);
    }
    @PatchMapping("/{id}/completion")
    public Todo complete(@PathVariable UUID id, @Valid @RequestBody CompletionRequest request) {
        return service.complete(id, request.completed());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    public record CompletionRequest(@NotNull(message = "Completed is required") Boolean completed) {}
}
