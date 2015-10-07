package au.com.interactivehippo.sweethome;

import java.util.Date;

/**
 * Created by Admin on 9/18/2015.
 */
public class Task {

    public String taskName;
    public String taskDueDate;
    public String taskUser;

    public Task(){
        super();
    }

    public Task(String taskName,String taskUser, String taskDueDate) {
        super();
        this.taskName = taskName;
        this.taskUser = taskUser;
        this.taskDueDate = taskDueDate;

    }

}
