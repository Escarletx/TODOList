package br.com.escarlet.todolist.controller;

import java.util.List;
import java.util.ArrayList;
import br.com.escarlet.todolist.model.entities.Task;

public class DataManager {
    private final List<Task> taskList = new ArrayList<>();

    public void addTask(String name, String description) {
        Task task = new Task(name, description);
        this.taskList.add(task);
        System.out.println("Tarefa " + name + " criada com sucesso!");
    }

    public List<Task> getTaskList() { return this.taskList; }
}