import Model.*;
import service.impl.*;
import util.TaskStorage;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<User> allUsers = new ArrayList<>();
    private static final Map<String, User> userMap = new HashMap<>();
    private static TaskService taskService;

    public static void main(String[] args) {
        // Choose notification type
        System.out.println("Choose notification type (1 = Email, 2 = Popup):");
        int choice = Integer.parseInt(scanner.nextLine());
        NotificationService notificationService = (choice == 1)
                ? new EmailNotificationService()
                : new PopupNotificationService();

        taskService = new TaskServiceImpl(notificationService);

        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        User currentUser = new User(username,new ArrayList<>());
        TaskStorage.loadTasksFromFile(currentUser);
        userMap.put(username, currentUser);
        allUsers.add(currentUser);

        boolean exit = false;

        while (!exit) {
            printMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    addTask(currentUser);
                    break;
                case "2":
                    completeTask(currentUser);
                    break;
                case "3":
                    deleteTask(currentUser);
                    break;
                case "4":
                    showPendingTasks(currentUser);
                    break;
                case "5":
                    filterByPriority(currentUser);
                    break;
                case "6":
                    TaskStorage.saveTasksToFile(currentUser);
                    exit = true;
                    break;
                default:
                    System.out.println("❌ Invalid Choice... Try Again.");
            }
        }
        System.out.println("👋 Exiting... Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\n============== TaskMate Menu ==============");
        System.out.println("1. ➕ Add Task");
        System.out.println("2. ✅ Complete Task");
        System.out.println("3. ❌ Delete Task");
        System.out.println("4. 📝 Show Pending Tasks");
        System.out.println("5. 🔍 Filter By Priority");
        System.out.println("6. 🚪 Exit");
    }

    private static void addTask(User user) {
        System.out.println("Enter task title:");
        String title = scanner.nextLine();

        if (title.trim().isEmpty()) {
            System.out.println("❗ Task title cannot be empty!");
            return;
        }

        System.out.println("Enter Priority level (High/Medium/Low):");
        String priority = scanner.nextLine().trim();

        if (!Arrays.asList("High", "Medium", "Low").contains(priority)) {
            System.out.println("❗ Invalid Priority Level");
            return;
        }

        Task task = new Task(title, priority, user);
        taskService.addTask(user, task);
        System.out.println("✅ Task added: " + task);
    }

    private static void completeTask(User user) {
        System.out.println("Enter task ID to complete:");
        int id = Integer.parseInt(scanner.nextLine());

        Task found = findTaskById(user.getTasks(), id);
        if (found != null) {
            taskService.completeTask(user, found);
        } else {
            System.out.println("❌ Task not found");
        }
    }

    private static void deleteTask(User user) {
        System.out.println("Enter task ID to delete:");
        int id = Integer.parseInt(scanner.nextLine());

        Task found = findTaskById(user.getTasks(), id);
        if (found != null) {
            taskService.deleteTask(user, found);
            System.out.println("🗑️ Task deleted: " + found.getTitle());
        } else {
            System.out.println("❌ Task not found");
        }
    }

    private static void showPendingTasks(User user) {
        List<Task> pending = taskService.getPendingTasks(user);
        if (pending.isEmpty()) {
            System.out.println("📭 No pending tasks");
        } else {
            System.out.println("📝 Pending Tasks:");
            for (Task task : pending) {
                System.out.println(task);
            }
        }
    }

    private static void filterByPriority(User user) {
        System.out.println("Enter priority to filter (High/Medium/Low):");
        String filter = scanner.nextLine().trim();

        List<Task> tasks = user.getTasks();
        boolean found = false;

        for (Task t : tasks) {
            if (t.getPriority().equalsIgnoreCase(filter)) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("📭 No tasks found with priority: " + filter);
        }
    }

    private static Task findTaskById(List<Task> tasks, int id) {
        for (Task task : tasks) {
            if (task.getId() == id) return task;
        }
        return null;
    }
}
