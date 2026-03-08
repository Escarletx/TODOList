package br.com.escarlet.todolist.controller;

import java.util.List;
import java.util.ArrayList;

public class CategoryController {
    private final List<String> categories = new ArrayList<>(List.of("Trabalho", "Estudos", "Pessoal"));

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