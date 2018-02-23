package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // create spinner with appropriate options
        Spinner summarySpinner = (Spinner) findViewById(R.id.summary_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Summary.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.summary_options_array));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        summarySpinner.setAdapter(adapter);

        summarySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String summaryStr = (String) adapterView.getItemAtPosition(i);
                if (summaryStr.equals("All-time")) {
                    Toast.makeText(getApplicationContext(), "Now showing All-time summaries", Toast.LENGTH_SHORT).show();
                    // change text edit string to calculation of happiness average
                    // change text edit string to calculation of productivity avg
                    // change text edit string to calculation of stress avg
                    // change text edit string to calculation of sleep avg
                } else if (summaryStr.equals("Year")) {
                    Toast.makeText(getApplicationContext(), "Now showing Year summaries", Toast.LENGTH_SHORT).show();
                    // change text edit string to calculation of happiness average
                    // change text edit string to calculation of productivity avg
                    // change text edit string to calculation of stress avg
                    // change text edit string to calculation of sleep avg
                } else if (summaryStr.equals("Month")) {
                    Toast.makeText(getApplicationContext(), "Now showing Month summaries", Toast.LENGTH_SHORT).show();
                    // change text edit string to calculation of happiness average
                    // change text edit string to calculation of productivity avg
                    // change text edit string to calculation of stress avg
                    // change text edit string to calculation of sleep avg
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
