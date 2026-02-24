package br.com.escarlet.todolist.model.entities;

import br.com.escarlet.todolist.model.enums.TaskStatus;

import java.time.LocalDateTime;

public class Task implements Comparable<Task>{
    private static int counter = 1;
    private final int id;
    private final String name;
    private final String description;
    private final int priority;
    private final LocalDateTime dueDate;
    private final TaskStatus status;
    private final String category;
    private final int alarm;

    public Task(String name, String description, int priority, LocalDateTime dueDate, TaskStatus status, String category, int alarm) {
        this.id = counter++;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = status;
        this.category = category;
        this.alarm = alarm;
    }

    @Override
    public int compareTo (Task other) {
        return Integer.compare(this.priority, other.getPriority());
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPriority() { return priority; }
    public LocalDateTime getDueDate() { return dueDate; }
    public TaskStatus getStatus() { return status; }
    public String getCategory() { return category; }
    public int getAlarm() { return alarm; }
}
