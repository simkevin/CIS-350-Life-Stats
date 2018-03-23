package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

public class SleepScreen extends AppCompatActivity {
    // make SleepElement data type to put in LinkedList
    private class SleepElement {
        String date;
        Boolean sleepStatus;
        public SleepElement(String date, Boolean sleepStatus) {
            this.date = date;
            this.sleepStatus = sleepStatus;
        }
    }
    LinkedList<SleepElement> sleepTrack = new LinkedList<SleepElement>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_screen);

        // create spinner with appropriate options
        Spinner sleepSpinner = (Spinner) findViewById(R.id.sleep_status_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SleepScreen.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sleep_status_array));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sleepSpinner.setAdapter(adapter);

        sleepSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // set a boolean for sleeping. false means awake, true means asleep
            boolean sleeping = false;
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sleepStr = (String) adapterView.getItemAtPosition(i);
                // create and save date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(Calendar.getInstance().getTime());

                // if sleepTrack is not empty, obtain last sleep value
                // if it is empty, then use initialized "awake" value
                if (!sleepTrack.isEmpty()) {
                    sleeping = sleepTrack.getLast().sleepStatus;
                }

                if (sleepStr.equals("Awake") && !sleepTrack.isEmpty()) {
                    if(!sleeping) {
                        Toast.makeText(getApplicationContext(), "Sleep Status already awake.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(getApplicationContext(), "Sleep Status Set to Awake at " + date, Toast.LENGTH_SHORT).show();
                        sleeping = false;
                    }
                } else if (sleepStr.equals("Asleep")) {
                    if (sleeping) {
                        Toast.makeText(getApplicationContext(), "Sleep Status already sleeping.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(getApplicationContext(), "Sleep Status Set to Asleep at " + date, Toast.LENGTH_SHORT).show();
                        sleeping = true;
                    }
                }
                // save data in linkedlist
                SleepElement se = new SleepElement(date, sleeping);
                sleepTrack.add(se);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}