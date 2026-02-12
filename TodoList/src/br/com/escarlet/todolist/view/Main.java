package br.com.escarlet.todolist.view;

import java.util.Scanner;
import br.com.escarlet.todolist.controller.DataManager;

public class Main {
    private static final DataManager manager = new DataManager();
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            try {
                mainMenu();
                int option = Integer.parseInt(input.nextLine());
                switch (option) {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        System.out.println("Encerrando programa");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException var2) {
                System.out.println("Digite apenas os números do menu.");
            }
        }
    }

    public static void mainMenu() {
        System.out.println(" === TODO LIST ===");
        System.out.println("1. Adicionar Tarefa");
        System.out.println("2. Sair");
        System.out.println("Escolha uma opção: ");
    }

    public static void addTask() {
        System.out.println(" === Cadastro da Tarefa ===");
        System.out.println("Nome da tarefa: ");
        String name = input.nextLine();
        System.out.println("Descrição: ");
        String description = input.nextLine();
        manager.addTask(name, description);
        System.out.println("\nTarefas cadastradas: ");
        manager.getTaskList().forEach(System.out::println);
        System.out.println();
    }
}