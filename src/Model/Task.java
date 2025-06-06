package Model;
import Model.User;

public class Task {
    private static int idCounter=1;
    private int id;
    private String title;
    private boolean isComplete;
    private String priority; //high,med,low
    private User owner;

    public Task(String title,String priority,User owner){
        this.id=idCounter++;
        this.title=title;
        this.priority=priority;
        this.owner=owner;
        this.isComplete=false;
    }

    public void markAsCompleted(){
        this.isComplete=true;
    }

    @Override
    public String toString(){
        return "[" +priority+"]" + title +(isComplete?"(Done)":"(Pending)");
    }
    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public boolean isComplete(){
        return isComplete;
    }

    public String getPriority(){
        return priority;
    }

    public User getOwner(){
        return owner;
    }


    public void setID(int id) {
        this.id=id;
    }
}
