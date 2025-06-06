package util;

import Model.Task;
import Model.User;

import java.io.*;
import java.util.List;

public class TaskStorage {

    public static void saveTasksToFile(User user) {
        String fileName = "tasks_" + user.getName() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Task task : user.getTasks()) {
                writer.write(task.getId() + "|" + task.getTitle() + "|" + task.getPriority() + "|" + task.isComplete());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving tasks: " + e.getMessage());
        }
    }

    public static void loadTasksFromFile(User user) {
        String fileName = "tasks_" + user.getName() + ".txt";
        File file = new File(fileName);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String priority = parts[2];
                    boolean completed = Boolean.parseBoolean(parts[3]);

                    Task task = new Task(title, priority, user);
                    task.setID(id); // You’ll need to add this setter
                    if (completed) task.markAsCompleted();
                    user.getTasks().add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading tasks: " + e.getMessage());
        }
    }
}
