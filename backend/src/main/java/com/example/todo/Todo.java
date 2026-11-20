package com.example.todo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    private UUID id;
    @Column(nullable = false, length = 160)
    private String title;
    @Column(nullable = false, length = 1000)
    private String description;
    @Column(nullable = false)
    private boolean completed;
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
    @Column(nullable = false)
    private Instant updatedAt;

    protected Todo() {}
    public Todo(String title, String description) {
        this.id = UUID.randomUUID();
        this.title = title.strip();
        this.description = description == null ? "" : description.strip();
        this.createdAt = Instant.now();
        this.updatedAt = createdAt;
    }
    public void update(String title, String description, boolean completed) {
        this.title = title.strip();
        this.description = description == null ? "" : description.strip();
        this.completed = completed;
        this.updatedAt = Instant.now();
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
        this.updatedAt = Instant.now();
    }
    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
