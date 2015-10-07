package au.com.interactivehippo.sweethome;

/**
 * Created by Admin on 10/4/2015.
 */
public class TaskListFields {
    private String taskName;
    private String taskDueDate;
    private int taskUser;

    public String getTaskName(){
        return taskName;
    }

    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    public String getTaskDueDate(){
        return taskDueDate;
    }

    public void setTaskDueDate(String taskDueDate){
        this.taskDueDate = taskDueDate;
    }

   // public int getTaskUser(){
    //    return taskUser;
    //}

  //  public void setTaskUser(int taskUser){
   //     this.taskUser = taskUser;
   // }

}
