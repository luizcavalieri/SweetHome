package au.com.interactivehippo.sweethome;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;

import java.util.Calendar;

/**
 * Created by Admin on 9/18/2015.
 */
public class PickerDialogs extends android.support.v4.app.DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        DateSettings dateSettings = new DateSettings(getActivity());

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog;

        dialog = new DatePickerDialog(getActivity(),dateSettings,year,month,day);

        return dialog;
    }


}
