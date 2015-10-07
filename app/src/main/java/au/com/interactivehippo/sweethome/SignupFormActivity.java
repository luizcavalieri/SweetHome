package au.com.interactivehippo.sweethome;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.isEmpty;

/**
 * Created by Admin on 9/18/2015.
 */
public class SignupFormActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);


    }

    public void CreateUser(View view) {
        EditText usernameET = (EditText) findViewById(R.id.user_email);
        EditText firstnameET = (EditText) findViewById(R.id.user_firstname);
        EditText passwordET = (EditText) findViewById(R.id.user_password);

        if(!isEmpty(usernameET)&&!isEmpty(passwordET)&&!isEmpty(firstnameET))
        {
            Toast.makeText(this, "Registering...", Toast.LENGTH_SHORT).show();

            new CreateTask(this).execute();
        }
        else
        {
            Toast.makeText(this, "Please fill in your details", Toast.LENGTH_SHORT).show();
        }
    }

    protected boolean isEmpty(EditText editText){
        //convert to string, cut whitespaces, check length
        return editText.getText().toString().trim().length()==0;
    }

    public class CreateTask extends AsyncTask<Void, Void, Void> {
        private boolean success = false;
        private String error = "";

        private Activity CurrentActivity;

        public CreateTask(Activity CurrentActivity)
        {
            this.CurrentActivity = CurrentActivity;
        }

        @Override
        protected Void doInBackground(Void... params) {
            //Note, probably should pass these next 2 values in at constructor
            //too dependant on a particular layout
            EditText usernameET = (EditText) findViewById(R.id.user_email);
            EditText firstnameET = (EditText) findViewById(R.id.user_firstname);
            EditText passwordET = (EditText) findViewById(R.id.user_password);

            JSONParser jsonParser = new JSONParser();

            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("user_email",usernameET.getText().toString()));
            parameters.add(new BasicNameValuePair("user_firstname",firstnameET.getText().toString()));
            parameters.add(new BasicNameValuePair("user_password", passwordET.getText().toString()));
            JSONObject json = jsonParser.getJSONFromUrl("http://iamlc.mobi/register.php", parameters);
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
                Toast.makeText(CurrentActivity, "Registration Successful!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(CurrentActivity, "Failed: "+error, Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void BackLoginPage (View view){
        final int result = 1;

        Intent changeScreens = new Intent(this, MainActivity.class);

        startActivityForResult(changeScreens, result);
    }

}