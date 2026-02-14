package br.com.escarlet.todolist.controller;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import br.com.escarlet.todolist.model.entities.Task;
import br.com.escarlet.todolist.model.enums.TaskStatus;

public class DataManager {
    private final List<Task> taskList = new ArrayList<>();

    public void addTask(String name, String description, int priority, LocalDateTime dueDate, TaskStatus status) {
        Task task = new Task(name, description, priority, dueDate, status);
        this.taskList.add(task);
        System.out.println("Tarefa " + name + " criada com sucesso!");
    }

    public List<Task> getTaskList() { return this.taskList; }

    public boolean removeTaskById(int id) {
        return taskList.removeIf(task -> task.getId() == id);
    }
}