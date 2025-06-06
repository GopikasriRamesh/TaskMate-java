package service.impl;

import Model.Task;
import Model.User;
import java.util.List;

public interface TaskService {
     void addTask(User user, Task task);
     void deleteTask(User user,Task task);
     void completeTask(User user,Task task);
     List<Task> getPendingTasks(User user);
     List<Task> getAllTasks(User user);
}
