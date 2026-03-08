package br.com.escarlet.todolist.view;

import br.com.escarlet.todolist.model.dto.TaskDTO;
import br.com.escarlet.todolist.controller.CategoryController;
import br.com.escarlet.todolist.controller.TaskController;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final TaskController taskManager = new TaskController();
    private static final CategoryController categoryManager = new CategoryController();
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        while(true) {
            checkAlarms();
            mainMenu();
            try {
                int option = Integer.parseInt(input.nextLine());
                switch (option) {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        retrieveTasks(taskManager.getAllTasksDTO());
                        break;
                    case 3:
                        removeTask();
                        retrieveTasks(taskManager.getAllTasksDTO());
                        break;
                    case 4:
                        System.out.print("=== Cadastre uma categoria ===");
                        processCategory();
                        break;
                    case 5:
                        filterByStatus();
                        break;
                    case 6:
                        filterByPriority();
                        break;
                    case 7:
                        filterByCategory();
                        break;
                    case 8: {
                        System.out.println("Encerrando programa");
                        return;
                    }
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
        System.out.println("1. Adicionar tarefa");
        System.out.println("2. Listar tarefas");
        System.out.println("3. Remover tarefas da lista");
        System.out.println("4. Cadastrar categoria de tarefa");
        System.out.println("5. Filtar por status");
        System.out.println("6. Filtar por prioridade");
        System.out.println("7. Filtar por categoria");
        System.out.println("9. Sair");
        System.out.println("Escolha uma opção: ");
    }

    public static void addTask() {
        System.out.println(" === Cadastro da Tarefa ===");
        String name = readString("Nome: ");
        String description = readString("Descrição: ");
        int priority = readPriority();
        LocalDateTime dueDate = readDateTime();
        String status = readStatus();
        System.out.println("== Escolha ou cadastre uma categoria ==");
        String category = processCategory();
        int alarm = 0;
        String hasAlarm = readString("Deseja agendar um lembrete? ");
        String alarmActive = hasAlarm.toLowerCase();
        if(alarmActive.equals("sim")) {
            alarm = readAlarm();
        }
        taskManager.addTask(name, description, priority, dueDate, status, category, alarm);
    }

    public static void removeTask() {
        if (taskManager.getAllTasksDTO().isEmpty()) {
            System.out.println("Não há itens na lista para remover.");
            return;
        }

        System.out.println("Digite o ID da tarefa que deseja remover: ");
        try {
            int id = Integer.parseInt(input.nextLine());
            if(taskManager.removeTaskById(id)) {
                System.out.println("Tarefa " + id + " removida!");
            } else  {
                System.out.println("Tarefa " + id + "não encontrada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }

    private static void filterByStatus() {
        System.out.println("=== Filtar por status ===");
        String status = readStatus();
        List<TaskDTO> result = taskManager.filterByStatus(status);
        retrieveTasks(result);
    }

    private static void filterByPriority() {
        System.out.println("=== Filtar por prioridade ===");
        int priority = readPriority();

        List<TaskDTO> result = taskManager.filterByPriority(priority);
        retrieveTasks(result);
    }

    private static void filterByCategory() {
        System.out.println("=== Filtar por categoria ===");
        System.out.println("\nCategorias disponíveis: " + categoryManager.getCategories());
        System.out.print("Digite o nome da categoria para busca: ");
        String category = input.nextLine().trim();

        List<TaskDTO> result = taskManager.filterByCategory(category);
        retrieveTasks(result);
    }

    private static void checkAlarms() {
        List<TaskDTO> alarms = taskManager.getActiveAlarm();
        if(!alarms.isEmpty()) {
            System.out.println("\n=== ALERTA DE TAREFAS PRÓXIMAS ===");
            alarms.forEach(dto -> System.out.println("-> " + dto.name() + " vence em breve!" + "\n Data de termino: " + dto.dueDate() + "\n"));
            System.out.println("=====================================\n");
        }
    }

    private static void retrieveTasks(List<TaskDTO> tasks) {
        if(tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            System.out.println("\n=== Lista de Tarefas ===");
            tasks.forEach(System.out::println);
            System.out.println("Total: " + tasks.size() + " tarefa(s).");
        }
    }

    private static String processCategory() {
        System.out.println("\nCategorias disponíveis: " + categoryManager.getCategories());
        String inputCategory = readString("Digite o nome da categoria: ").trim();
        categoryManager.addCategory(inputCategory);
        return inputCategory;
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

    private static String readStatus() {
        System.out.println("Status (TODO, DOING, DONE): ");
        return input.nextLine().trim();
    }

    private static int readAlarm() {
        while (true) {
            try {
                System.out.println("Agendar lembrete para quantos minutos?");
                int time = Integer.parseInt(input.nextLine());
                if (time > 0) return time;
                System.out.println("Agendamento registrado");
            } catch (Exception ignore) {
                System.out.println("Agendamento inválido!");
            }
        }
    }
}