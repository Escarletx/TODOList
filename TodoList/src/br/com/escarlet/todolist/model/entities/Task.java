package br.com.escarlet.todolist.model.entities;

import br.com.escarlet.todolist.model.enums.TaskStatus;

import java.time.LocalDateTime;

public class Task {
    private static int counter = 1;
    private final int id;
    private final String name;
    private final String description;
    private final int priority;
    private final LocalDateTime dueDate;
    private final TaskStatus status;

    public Task(String name, String description, int priority, LocalDateTime dueDate, TaskStatus status) {
        this.id = counter++;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPriority() { return priority; }
    public LocalDateTime getDueDate() { return dueDate; }
    public TaskStatus getStatus() { return status; }
}
