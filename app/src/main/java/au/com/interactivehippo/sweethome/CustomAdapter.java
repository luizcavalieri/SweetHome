package au.com.interactivehippo.sweethome;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Admin on 9/17/2015.
 */
public class CustomAdapter extends ArrayAdapter<TaskListFields>{
    private List<TaskListFields> tasks;

    public CustomAdapter(Context context, List<TaskListFields> tasks) {
        super(context, R.layout.task_list_rows, tasks);
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customViewTask = convertView;

        if(customViewTask == null){
            LayoutInflater taskInflater = LayoutInflater.from(getContext());
            customViewTask = taskInflater.inflate(R.layout.task_list_rows, null);
        }

        TaskListFields singleTaskItem = tasks.get(position);



        if(singleTaskItem != null){
            TextView taskName = (TextView) customViewTask.findViewById(R.id.taskName);
            TextView taskDueDate = (TextView) customViewTask.findViewById(R.id.dueDateTask);

            //ImageView profileImage = (ImageView) customViewTask.findViewById(R.id.profileImage);

            if(taskName != null) {
                taskName.setText(singleTaskItem.getTaskName());
            }
            //if(profileImage != null){
               // Resources res = getContext().getResources();
               // String sTaskUser = "au.com.interactivehippo.sweethome:drawable/"+singleTaskItem.getTaskUser();
               // profileImage.setImageDrawable(res.getDrawable(res.getIdentifier(sTaskUser, null, null)));
            //    profileImage.setImageResource(R.drawable.profile_pic_luiz);
                //taskUserAssigned.setText(singleTaskItem.getTaskDueDate());
            //}
            if(taskDueDate != null) {
                taskDueDate.setText(singleTaskItem.getTaskDueDate());

            }

        }


        return customViewTask;
    }


}
