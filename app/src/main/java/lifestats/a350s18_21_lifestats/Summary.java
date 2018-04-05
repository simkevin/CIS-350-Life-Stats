package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Summary extends AppCompatActivity {
    // is this class necessary?
    private class Summaries {
        String happiness;
        String productivity;
        String stress;
        String sleep;
        int amountOfSummaries;
        public Summaries(String happiness, String productivity, String stress, String sleep, int amountOfSummaries) {
            this.happiness = happiness;
            this.productivity = productivity;
            this.stress = stress;
            this.sleep = sleep;
            this.amountOfSummaries = amountOfSummaries;
        }
    }
    HashMap<String, Summaries> avgStore = new HashMap<String, Summaries>();

    private HappinessWrapper happinessData = HappinessWrapper.getInstance();
    private ProductivityWrapper productivityData = ProductivityWrapper.getInstance();
    private StressWrapper stressData = StressWrapper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        // create all necessary parts
        final TextView happiness_average = (TextView) findViewById(R.id.happiness_average);
        final TextView productivity_average = (TextView) findViewById(R.id.productivity_average);
        final TextView stress_average = (TextView) findViewById(R.id.stress_average);
        //final TextView sleep_average = (TextView) findViewById(R.id.sleep_average);

        // create spinner with appropriate options
        Spinner summarySpinner = (Spinner) findViewById(R.id.summary_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Summary.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.summary_options_array));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        summarySpinner.setAdapter(adapter);
        summarySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                double happinessAvg;
                double productivityAvg;
                double stressAvg;
                double sleepAvg;
                String summaryStr = (String) adapterView.getItemAtPosition(i);
                if (summaryStr.equals("All-time")) {
                    happinessAvg = calculateAlltimeAverage("happiness");
                    productivityAvg = calculateAlltimeAverage("productivity");
                    stressAvg = calculateAlltimeAverage("stress");
                    sleepAvg = calculateAlltimeAverage("");
                    // change text edit string to calculation of happiness average
                    happiness_average.setText("Happiness Average: " + happinessAvg);
                    // change text edit string to calculation of productivity avg
                    productivity_average.setText("Productivity Average: " + productivityAvg);
                    // change text edit string to calculation of stress avg
                    stress_average.setText("Stress Average: " + stressAvg);
                    // change text edit string to calculation of sleep avg
                    //sleep_average.setText("Sleep Average: " + sleepAvg);
                    Toast.makeText(getApplicationContext(), "Now showing All-time summaries", Toast.LENGTH_SHORT).show();
                }

                if (summaryStr.equals("Year")) {
                    happinessAvg = calculateYearAverage("happiness");
                    productivityAvg = calculateYearAverage("productivity");
                    stressAvg = calculateYearAverage("stress");
                    sleepAvg = calculateYearAverage("");
                    // change text edit string to calculation of happiness average
                    happiness_average.setText("Happiness Average: " + happinessAvg);
                    // change text edit string to calculation of productivity avg
                    productivity_average.setText("Productivity Average: " + productivityAvg);
                    // change text edit string to calculation of stress avg
                    stress_average.setText("Stress Average: " + stressAvg);
                    // change text edit string to calculation of sleep avg
                    //sleep_average.setText("Sleep Average: " + sleepAvg);
                    Toast.makeText(getApplicationContext(), "Now showing Year summaries", Toast.LENGTH_SHORT).show();
                }

                if (summaryStr.equals("Month")) {
                    happinessAvg = calculateMonthAverage("happiness");
                    productivityAvg = calculateMonthAverage("productivity");
                    stressAvg = calculateMonthAverage("stress");
                    sleepAvg = calculateMonthAverage("");
                    // change text edit string to calculation of happiness average
                    happiness_average.setText("Happiness Average: " + happinessAvg);
                    // change text edit string to calculation of productivity avg
                    productivity_average.setText("Productivity Average: " + productivityAvg);
                    // change text edit string to calculation of stress avg
                    stress_average.setText("Stress Average: " + stressAvg);
                    // change text edit string to calculation of sleep avg
                    //sleep_average.setText("Sleep Average: " + sleepAvg);
                    Toast.makeText(getApplicationContext(), "Now showing Month summaries", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private double calculateAlltimeAverage(String stat) {
        double average = 0.0;
        if (stat.equals("happiness")) {
            for (String key: happinessData.keySet()) {
                average += happinessData.get(key);
            }
        }
        if (stat.equals("productivity")) {
            for (String key: productivityData.keySet()) {
                average += productivityData.get(key);
            }
        }
        if (stat.equals("stress")) {
            for (String key: stressData.keySet()) {
                average += stressData.get(key);
            }
        }

        return average / happinessData.size() ;
    }

    private double calculateYearAverage(String stat) {
        double average = 0.0;
        int numYearlyEntries = 1;
        if (stat.equals("happiness")) {
            for (String key: happinessData.keySet()) {
                average += happinessData.get(key);
            }
        }
        if (stat.equals("productivity")) {
            for (String key: productivityData.keySet()) {
                average += productivityData.get(key);
            }
        }
        if (stat.equals("stress")) {
            for (String key: stressData.keySet()) {
                average += stressData.get(key);
            }
        }

        return average / numYearlyEntries ;
    }

    private double calculateMonthAverage(String stat) {
        double average = 0.0;
        int numMonthlyEntries = 1;
        if (stat.equals("happiness")) {
            for (String key: happinessData.keySet()) {
                average += happinessData.get(key);
            }
        }
        if (stat.equals("productivity")) {
            for (String key: productivityData.keySet()) {
                average += productivityData.get(key);
            }
        }
        if (stat.equals("stress")) {
            for (String key: stressData.keySet()) {
                average += stressData.get(key);
            }
        }

        return average / numMonthlyEntries ;
    }
}
