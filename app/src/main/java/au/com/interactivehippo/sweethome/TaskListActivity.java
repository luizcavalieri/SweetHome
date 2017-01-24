package au.com.interactivehippo.sweethome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Admin on 9/18/2015.
 */
public class TaskListActivity extends AppCompatActivity implements FetchDataListener {

    private ProgressDialog dialog;
    private CustomAdapter customAdapter;
    private CustomAdapter listAdapter;

    User userLogged = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        userLogged = (User) intent.getSerializableExtra("user");

        Toast.makeText(this, "user Logged: "+userLogged.getUserEmail(), Toast.LENGTH_SHORT).show();


        setContentView(R.layout.task_list);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setLogo(R.drawable.sweethome_logo_supersmall_white);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        initView();

    }

    private void initView() {
        dialog = ProgressDialog.show(this, "", "Loading...");

        Toast.makeText(this, "user Logged: "+userLogged.getUserId(), Toast.LENGTH_SHORT).show();

        String url = "http://iamlc.mobi/taskList.php?user_id="+userLogged.getUserId();
        FetchDataTask task = new FetchDataTask(this);
        task.execute(url);

    }

    @Override
    public void onFetchComplete(List<TaskListFields> data) {
        // dismiss the progress dialog
        if (dialog != null) dialog.dismiss();
        // create new adapter

        ListView theListView = (ListView) findViewById(R.id.tasksListView);

        CustomAdapter adapter = new CustomAdapter(this, data);
        // set the adapter to list
        theListView.setAdapter(adapter);
        //setCustomAdapter(adapter);

    }



    // private void setCustomAdapter(CustomAdapter adapter) {
    //}

    @Override
    public void onFetchFailure(String msg) {
        // dismiss the progress dialog
        if(dialog != null)  dialog.dismiss();
        // show failure message
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }


    public void AddNewTask (View view){
        final int result = 1;

        Intent changeScreens = new Intent(this, TaskDetailForm.class);

        startActivityForResult(changeScreens, result);
    }

}
