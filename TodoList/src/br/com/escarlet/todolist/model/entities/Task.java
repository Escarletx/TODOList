package br.com.escarlet.todolist.model.entities;

import br.com.escarlet.todolist.model.enums.TaskStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static int counter = 1;
    private final int id;
    private String name;
    private String description;
    private int priority;
    private LocalDateTime dueDate;
    private TaskStatus status;

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
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    public TaskStatus getStatus() { return status; }
    public void setStatus (TaskStatus status) { this.status = status; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return """
           +-------------------------------------------+
           | ID: %d
           | Tarefa: %s
           | Descrição: %s
           | Data de término: %s
           | Nível de prioridade: %s
           | Status: %s
           +-------------------------------------------+
           """.formatted(id, name, description, dueDate.format(fmt), priority, status);
    }
}
