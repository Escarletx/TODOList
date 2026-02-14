package br.com.escarlet.todolist.model.dto;

public record TaskDTO(
        int id,
        String name,
        String description,
        int priority,
        String dueDate,
        String status,
        String category) {

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
