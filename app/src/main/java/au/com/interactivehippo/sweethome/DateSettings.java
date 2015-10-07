package au.com.interactivehippo.sweethome;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.TooManyListenersException;

/**
 * Created by Admin on 9/18/2015.
 */
public class DateSettings implements DatePickerDialog.OnDateSetListener {

    Context context;
    public DateSettings (Context context){

        this.context = context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(context, "Selected date:" + dayOfMonth + "/" + monthOfYear + "/" + year, Toast.LENGTH_LONG).show();


    }
}
