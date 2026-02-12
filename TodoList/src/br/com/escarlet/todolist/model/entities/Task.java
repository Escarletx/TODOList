package br.com.escarlet.todolist.model.entities;

import java.util.UUID;

public class Task {
    private final UUID id = UUID.randomUUID();
    private String name;
    private String description;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return """
           Tarefa: %s
           Descrição: %s
           """.formatted(name, description);
    }
}
