package au.com.interactivehippo.sweethome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by Admin on 9/18/2015.
 */
public class MainMenuGridActivity extends ActionBarActivity {

    User userLogged = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_grid);

        Intent intent = getIntent();

        userLogged = (User) intent.getSerializableExtra("user");

        TextView txtView = (TextView)findViewById(R.id.greetingUser);

        txtView.setText("Good'day, "+userLogged.getUserFirstname());

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setLogo(R.drawable.sweethome_logo_supersmall_white);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }


    public void GoTasksList (View view){

        //Toast.makeText(this, "user Logged: "+userLogged.getUserEmail(), Toast.LENGTH_SHORT).show();

        final int result = 1;

//        Intent intent = getIntent();
       // userLogged = (User) intent.getSerializableExtra("user");

        Intent changeScreens = new Intent(this, TaskListActivity.class);

        changeScreens.putExtra("user", userLogged);

        startActivityForResult(changeScreens, result);

        //startActivity(changeScreens);

    }

    public void UnderConstructionPage (View view){
        final int result = 1;

        Intent changeScreens = new Intent(this, UnderConstructionActivity.class);

        startActivityForResult(changeScreens, result);
    }
}
