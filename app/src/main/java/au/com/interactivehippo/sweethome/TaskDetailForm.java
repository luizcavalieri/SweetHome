package au.com.interactivehippo.sweethome;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


/**
 * Created by Admin on 9/18/2015.
 */
public class TaskDetailForm extends ActionBarActivity{

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView textViewDueDateTask;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_form);

        textViewDueDateTask = (TextView) findViewById(R.id.text_view_due_date_task);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setLogo(R.drawable.sweethome_logo_supersmall_white);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);


    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "calendar", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
             //arg1 = year;
             //arg2 = month;
             //arg3 = day;


            showDate(arg1, arg2 + 1, arg3);

           //datePicker.init(year, month, day, dateSetListener);




        }
    };

    //private DatePicker.OnDateChangedListener dateSetListener = new DatePicker.OnDateChangedListener(){

     //   public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

      //      textViewDueDateTask.setText(new StringBuilder().append(year).append("/").append(monthOfYear + 1).append("/").append(dayOfMonth));


     //   }
    //};

    private void showDate(int year, int month, int day) {
        textViewDueDateTask.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));



    }

    public void AddTask(View view) {
        EditText taskTitleET = (EditText) findViewById(R.id.task_title);
        EditText taskDescET = (EditText) findViewById(R.id.task_description);
        TextView dueDateET = (TextView) findViewById(R.id.text_view_due_date_task);

        final int result = 1;

        if(!isEmpty(taskTitleET)&&!isEmpty(taskDescET))
        {
            Toast.makeText(this, "Creating Task...", Toast.LENGTH_SHORT).show();
            new AddTaskDB(this).execute();
        }
        else
        {
            Toast.makeText(this, "Please fill in task details", Toast.LENGTH_SHORT).show();
        }

        Intent changeScreens = new Intent(this, TaskListActivity.class);

        startActivityForResult(changeScreens, result);
    }
    protected boolean isEmpty(EditText editText){
        //convert to string, cut whitespaces, check length
        return editText.getText().toString().trim().length()==0;
    }


    public class AddTaskDB extends AsyncTask<Void, Void, Void> {
        private boolean success = false;
        private String error = "";

        private Activity CurrentActivity;

        public AddTaskDB(Activity CurrentActivity)
        {
            this.CurrentActivity = CurrentActivity;
        }

        @Override
        protected Void doInBackground(Void... params) {
            //Note, probably should pass these next 2 values in at constructor
            //too dependant on a particular layout
            EditText taskTitleET = (EditText) findViewById(R.id.task_title);
            EditText taskDescET = (EditText) findViewById(R.id.task_description);
            TextView dueDateET = (TextView) findViewById(R.id.text_view_due_date_task);

            JSONParser jsonParser = new JSONParser();

            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("task_title",taskTitleET.getText().toString()));
            parameters.add(new BasicNameValuePair("task_description",taskDescET.getText().toString()));
            parameters.add(new BasicNameValuePair("text_view_due_date_task",dueDateET.getText().toString()));
            JSONObject json = jsonParser.getJSONFromUrl("http://iamlc.mobi/registerTask.php", parameters);
            if(json != null)
            {
                try{
                    if(json.getString("success")!=null)
                    {
                        String result = json.getString("success");
                        if(Integer.parseInt(result) == 0)
                        {
                            success = true;
                        }
                        else
                        {
                            success = false;
                            error = json.getString("error");
                        }
                    }
                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                success = false;
                error = "no response";
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //super.onPostExecute(aVoid);
            if(!success)
            {
                Toast.makeText(CurrentActivity, "Registration Successful!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(CurrentActivity, "Failed: "+error, Toast.LENGTH_SHORT).show();
            }
        }


    }

}
