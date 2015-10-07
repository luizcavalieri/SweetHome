package au.com.interactivehippo.sweethome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

/**
 * Created by Admin on 9/18/2015.
 */
public class MainMenuGridActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_grid);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setLogo(R.drawable.logo_white_small);
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
        final int result = 1;

        Intent changeScreens = new Intent(this, TaskListActivity.class);

        startActivityForResult(changeScreens, result);
    }

    public void UnderConstructionPage (View view){
        final int result = 1;

        Intent changeScreens = new Intent(this, UnderConstructionActivity.class);

        startActivityForResult(changeScreens, result);
    }
}
