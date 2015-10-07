package au.com.interactivehippo.sweethome;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    public void GoTasksList (View view){
        final int result = 1;

        Intent changeScreens = new Intent(this, TaskListActivity.class);

        startActivityForResult(changeScreens, result);
    }

    public void CreateAccountPage (View view){
        final int result = 1;

        Intent changeScreens = new Intent(this, SignupFormActivity.class);

        startActivityForResult(changeScreens, result);
    }

    public void LogIn (View view){
        EditText usernameET = (EditText) findViewById(R.id.login_username);
        EditText passwordET = (EditText) findViewById(R.id.login_password);

        if(!isEmpty(usernameET)&&!isEmpty(passwordET)){
            Toast.makeText(this, "Getting into", Toast.LENGTH_SHORT).show();
            new LoginTask(this).execute();
        }
        else
        {
            Toast.makeText(this, "Please fill in your details", Toast.LENGTH_SHORT).show();
        }


        //final int result =1;

        //Intent changeScreens = new Intent(this, MainMenuGridActivity.class);

        //startActivityForResult(changeScreens, result);
    }

    protected boolean isEmpty(EditText editText){
        //convert to string, cut whitespaces, check length
        return editText.getText().toString().trim().length()==0;
    }

    public class LoginTask extends AsyncTask<Void, Void, Void> {
        private boolean success = false;
        private String error = "";

        private Activity CurrentActivity;

        public LoginTask(Activity CurrentActivity)
        {
            this.CurrentActivity = CurrentActivity;
        }

        @Override
        protected Void doInBackground(Void... params) {
            //Note, probably should pass these next 2 values in at constructor
            //too dependant on a particular layout
            EditText usernameET = (EditText) findViewById(R.id.login_username);
            EditText passwordET = (EditText) findViewById(R.id.login_password);

            JSONParser jsonParser = new JSONParser();

            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("user_email",usernameET.getText().toString()));
            parameters.add(new BasicNameValuePair("user_password", passwordET.getText().toString()));
            JSONObject json = jsonParser.getJSONFromUrl("http://iamlc.mobi/login.php", parameters);
            if(json != null)
            {
                try{
                    if(json.getString("success")!=null)
                    {
                        String result = json.getString("success");
                        if(Integer.parseInt(result) == 1)
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
            if(success)
            {
                Toast.makeText(CurrentActivity, "Login Successful!", Toast.LENGTH_SHORT).show();

                //go to next screen
                Intent nextScreen = new Intent(CurrentActivity, MainMenuGridActivity.class);
                startActivity(nextScreen);
            }
            else
            {
                Toast.makeText(CurrentActivity, "Failed: "+error, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
