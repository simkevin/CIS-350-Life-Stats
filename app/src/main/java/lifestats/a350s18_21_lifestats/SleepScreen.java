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

public class SleepScreen extends AppCompatActivity {

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
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sleepStr = (String) adapterView.getItemAtPosition(i);
                if (sleepStr.equals("Awake")) {
                    Toast.makeText(getApplicationContext(), "Sleep Status Set to Awake", Toast.LENGTH_SHORT).show();
                } else if (sleepStr.equals("Asleep")) {
                    Toast.makeText(getApplicationContext(), "Sleep Status Set to Asleep", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
