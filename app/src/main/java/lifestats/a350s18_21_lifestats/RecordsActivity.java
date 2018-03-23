package lifestats.a350s18_21_lifestats;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.HashMap;

public class RecordsActivity extends AppCompatActivity {

    private HashMap<String, Float> happinessData;
    private HashMap<String, Float> productivityData;
    private HashMap<String, Float> stressData;

    private CaldroidFragment caldroidFragment;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        // dummy data
        happinessData = new HashMap<>();
        productivityData = new HashMap<>();
        stressData = new HashMap<>();

        happinessData.put("2018-03-11 00:00:00", (float) 5.0);
        productivityData.put("2018-03-11 00:00:00", (float) 2.0);
        stressData.put("2018-03-11 00:00:00", (float) 1.0);

        happinessData.put("2018-03-20 00:00:00", (float) 2.0);
        productivityData.put("2018-03-20 00:00:00", (float) 4.0);
        stressData.put("2018-03-20 00:00:00", (float) 5.0);


        // Setup caldroid fragment
        caldroidFragment = new CaldroidFragment();

        // Setup arguments


        // If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

            caldroidFragment.setArguments(args);
        }

        highlightDates();

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar, caldroidFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                // show data for selected date if date has records
                String key = formatter.format(date);
                if (happinessData.containsKey(key)) {
                    String records = "Happiness: " + happinessData.get(key) + "\n" + "Productivity: "
                            + productivityData.get(key) + "\n" + "Stress: " + stressData.get(key);
                    // show data from that date in toast
                    Toast.makeText(getApplicationContext(), records,
                            Toast.LENGTH_LONG).show();
                }
            }
        };

        // Setup Caldroid Listener
        caldroidFragment.setCaldroidListener(listener);
    }

    /**
     * Save current states of the Caldroid here
    */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (caldroidFragment != null) {
            caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
        }
    }

    // highlights dates in calendar with data records
    private void highlightDates() {
        for (String s: happinessData.keySet()) {
            Date record = null;
            try {
                record = formatter.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (caldroidFragment != null) {
                ColorDrawable green = new ColorDrawable(Color.GREEN);
                caldroidFragment.setBackgroundDrawableForDate(green, record);
                caldroidFragment.setTextColorForDate(R.color.caldroid_white, record);
            }
        }
    }
}
