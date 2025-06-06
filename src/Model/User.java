package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private String name;
    private List<Task> tasks;

    public User(String name, List<Task> tasks){
        this.name = name;
        this.tasks = tasks != null ? tasks : new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void deleteTask(Task task){
        tasks.remove(task);
    }

    public List<Task> getPendingTasks(){
        return tasks.stream()
                .filter(task -> !task.isComplete())
                .collect(Collectors.toList());
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public String getName(){
        return name;
    }
}
