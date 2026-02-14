package br.com.escarlet.todolist.view;

import br.com.escarlet.todolist.controller.DataManager;
import br.com.escarlet.todolist.model.enums.TaskStatus;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final DataManager manager = new DataManager();
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        while(true) {
            try {
                mainMenu();
                int option = Integer.parseInt(input.nextLine());
                switch (option) {
                    case 1:
                        addTask();
                        break;
                    case 2: {
                        var tasks = manager.getTaskList();
                        if (tasks.isEmpty()) {
                            System.out.println("\n Nenhuma tarefa encontrada.");
                        } else {
                            System.out.println("\n=== Lista de tarefas ===");
                            tasks.forEach(System.out::println);
                        }
                        break;
                    }
                    case 3:
                        System.out.println("Encerrando programa");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas os números do menu.");
            }
        }
    }

    public static void mainMenu() {
        System.out.println(" === TODO LIST ===");
        System.out.println("1. Adicionar Tarefa");
        System.out.println("2. Listar Tarefas");
        System.out.println("3. Sair");
        System.out.println("Escolha uma opção: ");
    }

    public static void addTask() {
        System.out.println(" === Cadastro da Tarefa ===");
        String name = readString("Nome: ");
        String description = readString("Descrição: ");
        int priority = readPriority();
        LocalDateTime dueDate = readDateTime();
        TaskStatus status = readStatus();
        manager.addTask(name, description, priority, dueDate, status);
        System.out.println("\nTarefas cadastradas: ");
        manager.getTaskList().forEach(System.out::println);
        System.out.println();
    }

    private static String readString(String label) {
        System.out.println(label);
        return input.nextLine();
    }

    private static int readPriority() {
        while (true) {
            try {
                System.out.println("Nível de prioridade de 1 a 5: ");
                int p = Integer.parseInt(input.nextLine());
                if (p >= 1 && p <= 5) return p;
                System.out.println("Digite um nível de prioridade entre 1 e 5");
            } catch (NumberFormatException e) {
                System.out.println("Nivel de prioridade inválido!");
            }
        }
    }

    private static LocalDateTime readDateTime() {
        while (true) {
            try {
                System.out.println("Data de término (dd/MM/yyyy HH:mm): ");
                return LocalDateTime.parse(input.nextLine(), fmt);
            } catch (Exception e) {
                System.out.println("Formato inválido! Ex: 20/02/2026 14:00");
            }
        }
    }

    private static TaskStatus readStatus() {
        while(true) {
            try {
                System.out.println("Status (TODO, DOING, DONE): ");
                String inputStatus = input.nextLine();
                return TaskStatus.valueOf(inputStatus.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Status inválido.");
            }
        }
    }
}