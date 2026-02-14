package br.com.escarlet.todolist.model.dto;

import br.com.escarlet.todolist.model.entities.Task;

import java.time.format.DateTimeFormatter;

public record TaskDTO(
        int id,
        String name,
        String description,
        int priority,
        String dueDate,
        String status,
        String category)
{
    public static TaskDTO fromEntity (Task t, DateTimeFormatter fmt) {
        return new TaskDTO(
                t.getId(),
                t.getName(),
                t.getDescription(),
                t.getPriority(),
                t.getDueDate().format(fmt),
                t.getStatus().name(),
                t.getCategory());
    }

    @Override
    public String toString() {
        return """
           +-------------------------------------------+
           | ID: %d
           | Tarefa: %s
           | Descrição: %s
           | Data de término: %s
           | Nível de prioridade: %s
           | Status: %s
           | Categoria: %s
           +-------------------------------------------+
           """.formatted(id, name, description, dueDate, priority, status, category);
    }
}
