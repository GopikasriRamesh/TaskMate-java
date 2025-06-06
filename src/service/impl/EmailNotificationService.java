package service.impl;
import Model.Task;
import service.impl.NotificationService;

public class EmailNotificationService implements NotificationService {
    @Override
    public void notifyTaskCompleted(Task task) {
        System.out.println("📧 Email Sent:Task Completed-" + task.getTitle());
    }
}
