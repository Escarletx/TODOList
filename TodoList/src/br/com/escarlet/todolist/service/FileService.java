package br.com.escarlet.todolist.service;

import br.com.escarlet.todolist.model.dto.TaskDTO;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class FileService {
    public static void exportTasks(List<TaskDTO> tasks, String fileName) throws IOException {
        String finalName = fileName.toLowerCase().endsWith(".txt") ? fileName : fileName + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(finalName))) {
            for (TaskDTO t : tasks) {
                writer.write(t.toString());
                writer.newLine();
            }
        }
    }
}
