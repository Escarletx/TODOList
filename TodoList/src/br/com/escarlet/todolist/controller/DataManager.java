package br.com.escarlet.todolist.controller;

import br.com.escarlet.todolist.model.dto.TaskDTO;
import br.com.escarlet.todolist.model.entities.Task;
import br.com.escarlet.todolist.model.enums.TaskStatus;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataManager {
    private final List<Task> taskList = new ArrayList<>();
    private final List<String> categories = new ArrayList<>(List.of("Trabalho", "Estudos", "Pessoal"));
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void addTask(String name, String description, int priority, LocalDateTime dueDate, String statusInput, String category) {
        TaskStatus status;
        try {
            status = TaskStatus.valueOf(statusInput.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            status = TaskStatus.TODO;
        }
        this.taskList.add(new Task(name, description, priority, dueDate, status, category));
        Collections.sort(taskList);
        System.out.println("Tarefa " + name + " criada com sucesso!");
    }

    public boolean removeTaskById(int id) {
        return taskList.removeIf(task -> task.getId() == id);
    }

    public List<TaskDTO> getAllTasksDTO() {
        return taskList.stream()
                .map(t -> TaskDTO.fromEntity(t, fmt))
                .toList();
    }

    public List<TaskDTO> filterByStatus (String statusInput) {
        try {
            TaskStatus statusTarget = TaskStatus.valueOf(statusInput.toUpperCase().trim());
            return taskList.stream()
                    .filter(t -> t.getStatus() == statusTarget)
                    .map(t -> TaskDTO.fromEntity(t, fmt))
                    .toList();
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    public List<TaskDTO> filterByPriority (int inputPriority) {
        return taskList.stream()
                .filter(t -> t.getPriority() == inputPriority)
                .map(t -> TaskDTO.fromEntity(t, fmt))
                .toList();
    }

    public List<TaskDTO> filterByCategory(String categoryInput) {
        return taskList.stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(categoryInput.trim()))
                .map(t -> TaskDTO.fromEntity(t, fmt))
                .toList();
    }

    public void addCategory(String category) {
        if (category == null) return;
        String formatted = category.trim();

        if(!formatted.isEmpty() && !categories.contains(formatted)) {
            categories.add(formatted);
            System.out.println("Categoria incluída com sucesso!");
        }
    }

    public List<String> getCategories() {
        return new ArrayList<>(categories);
    }
}