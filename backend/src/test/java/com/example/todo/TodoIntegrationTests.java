package com.example.todo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import jakarta.validation.Validator;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class TodoIntegrationTests {
    @Autowired TodoService service;
    @Autowired TodoRepository repository;
    @Autowired Validator validator;

    @Test
    void taskLifecyclePersistsInPostgres() {
        Todo created = service.create(new TodoRequest("  Learn Spring Boot  ", "  Use PostgreSQL  ", false));
        repository.flush();
        assertThat(created.getId()).isNotNull();
        assertThat(service.get(created.getId()).getTitle()).isEqualTo("Learn Spring Boot");
        assertThat(service.list()).extracting(Todo::getId).contains(created.getId());
        Todo updated = service.update(created.getId(), new TodoRequest("Build the app", "Use a migration", false));
        repository.flush();
        assertThat(updated.getDescription()).isEqualTo("Use a migration");
        service.complete(created.getId(), true);
        repository.flush();
        assertThat(service.get(created.getId()).isCompleted()).isTrue();
        service.delete(created.getId());
        repository.flush();
        assertThat(repository.findById(created.getId())).isEmpty();
    }

    @Test
    void missingTasksReturnNotFound() {
        assertThatThrownBy(() -> service.get(UUID.randomUUID()))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("404");
    }

    @Test
    void validatesTitleNotesAndCompletion() {
        assertThat(validator.validate(new TodoRequest(" ", "", false))).isNotEmpty();
        assertThat(validator.validate(new TodoRequest("x".repeat(161), "", false))).isNotEmpty();
        assertThat(validator.validate(new TodoRequest("A task", "x".repeat(1001), false))).isNotEmpty();
        assertThat(validator.validate(new TodoRequest("A task", "", null))).isNotEmpty();
        assertThat(validator.validate(new TodoRequest("A task", null, false))).isEmpty();
    }
}
