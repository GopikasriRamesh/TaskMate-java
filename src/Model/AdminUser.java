package Model;

import java.util.ArrayList;
import java.util.List;

public class AdminUser extends User {

    public AdminUser(String name,List<Task> tasks) {
        super(name,tasks);
    }

    public List<Task> getAllTasks(List<User> users) {
        // Will collect tasks from all users
        return new ArrayList<>();
    }
}
