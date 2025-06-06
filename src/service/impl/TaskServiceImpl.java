package service.impl;
import service.impl.TaskService;
import service.impl.NotificationService;
import Model.Task;
import Model.User;

import java.util.List;

public class TaskServiceImpl implements TaskService{
    public NotificationService notificationService;
    //constructor injection
    public TaskServiceImpl(NotificationService notificationService){
       this.notificationService=notificationService;
    }

    @Override
    public void addTask(User user, Task task) {
        user.addTask(task);
    }

    @Override
    public void deleteTask(User user,Task task){
        user.deleteTask(task);
    }

    @Override
    public void completeTask(User user,Task task){
        task.markAsCompleted();
        notificationService.notifyTaskCompleted(task);
    }

    @Override
    public List<Task> getPendingTasks(User user){
        return user.getPendingTasks();
    }

    @Override
    public List<Task> getAllTasks(User user){
        return user.getTasks();
    }
}
