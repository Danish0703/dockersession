package com.example.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TodoRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 160, message = "Title must be 160 characters or fewer") String title,
        @Size(max = 1000, message = "Notes must be 1000 characters or fewer") String description,
        @NotNull(message = "Completed is required") Boolean completed) {}
