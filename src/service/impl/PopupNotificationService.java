package service.impl;
import Model.Task;
import service.impl.NotificationService;

public class PopupNotificationService implements NotificationService{
    @Override
    public void notifyTaskCompleted(Task task){
        System.out.println("🔔 Popup:Task" + task.getTitle()+"completed!!");
    }
}
