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
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void addTask(String name, String description, int priority, LocalDateTime dueDate, String statusInput) {
        try {
            TaskStatus status = TaskStatus.valueOf(statusInput.toUpperCase().trim());
            this.taskList.add(new Task(name, description, priority, dueDate, status));

            Collections.sort(taskList);
        } catch (IllegalArgumentException e) {
            this.taskList.add(new Task(name, description, priority, dueDate, TaskStatus.TODO));

            Collections.sort(taskList);
        }

        System.out.println("Tarefa " + name + " criada com sucesso!");
    }

    public List<TaskDTO> getTaskDTO() {
        return taskList.stream()
                .map(this::mapToDTO)
                .toList();
    }

    public boolean removeTaskById(int id) {
        return taskList.removeIf(task -> task.getId() == id);
    }

    public List<TaskDTO> filterByStatus (String statusInput) {
        try {
            TaskStatus statusTarget = TaskStatus.valueOf(statusInput.toUpperCase().trim());
            return taskList.stream()
                    .filter(t -> t.getStatus() == statusTarget)
                    .map(this::mapToDTO)
                    .toList();
        } catch (IllegalArgumentException e) {
            System.out.println("\n Nenhuma tarefa encontrada com esse status.\n");
            return new ArrayList<>();
        }
    }

    public List<TaskDTO> filterByPriority (int inputPriority) {
        return taskList.stream()
                .filter(t -> t.getPriority() == inputPriority)
                .map(this::mapToDTO)
                .toList();
    }

    public TaskDTO mapToDTO(Task t) {
        return new TaskDTO(
                        t.getId(),
                        t.getName(),
                        t.getDescription(),
                        t.getPriority(),
                        t.getDueDate().format(fmt),
                        t.getStatus().name()
                );
    }
}