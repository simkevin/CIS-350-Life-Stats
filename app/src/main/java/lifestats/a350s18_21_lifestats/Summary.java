package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Summary extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        // create all necessary parts
        TextView summary_welcome_message = (TextView) findViewById(R.id.summary_welcome_message);
        TextView choose_summary = (TextView) findViewById(R.id.choose_summary);
        final TextView happiness_average = (TextView) findViewById(R.id.happiness_average);
        final TextView productivity_average = (TextView) findViewById(R.id.productivity_average);
        final TextView stress_average = (TextView) findViewById(R.id.stress_average);
        final TextView sleep_average = (TextView) findViewById(R.id.sleep_average);
        final EditText happinessEditText = (EditText) findViewById(R.id.happiness);
        final EditText stressEditText = (EditText) findViewById(R.id.stress);
        final EditText productivityEditText = (EditText) findViewById(R.id.productivity);
        final EditText sleepEditText = (EditText) findViewById(R.id.sleep);
        Button submitButton = (Button) findViewById(R.id.submit);

        // create spinner with appropriate options
        Spinner summarySpinner = (Spinner) findViewById(R.id.summary_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Summary.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.summary_options_array));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        summarySpinner.setAdapter(adapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (happinessEditText.getText() != null &&
                        stressEditText.getText() != null &&
                        productivityEditText.getText() != null &&
                        sleepEditText.getText() != null) {
                    String inputHappy = happinessEditText.getText().toString();
                    String inputProductivity = productivityEditText.getText().toString();
                    String inputStress = stressEditText.getText().toString();
                    String inputSleep = sleepEditText.getText().toString();

                    // date format
                    SimpleDateFormat sdfToday = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdfToday.format(Calendar.getInstance().getTime());

                    Toast.makeText(getApplicationContext()
                            , "RECORDED at " + date + " \n" +
                                    "Happiness: " + inputHappy + "\n" +
                                    "Productivity: " + inputProductivity + "\n" +
                                    "Stress: " + inputStress + "\n" +
                                    "Sleep: " + inputSleep
                            , Toast.LENGTH_LONG).show();

                    double inputHappyNum = Double.parseDouble(inputHappy);
                    double inputProductivityNum = Double.parseDouble(inputProductivity);
                    double inputStressNum = Double.parseDouble(inputStress);
                    double inputSleepNum = Double.parseDouble(inputSleep);

                    if(avgStore.containsKey(date)) {
                        // get previous amounts
                        Summaries prevAmount = avgStore.get(date);
                        Double happyVal = Double.parseDouble(prevAmount.happiness);
                        Double productivityVal = Double.parseDouble(prevAmount.productivity);
                        Double stressVal = Double.parseDouble(prevAmount.stress);
                        Double sleepVal = Double.parseDouble(prevAmount.sleep);
                        int summariesAmount = prevAmount.amountOfSummaries;

                        // increment summariesAmount
                        summariesAmount++;

                        // use current amounts to get totals




                    } else {

                    }
/*
                    if (alltimeStore.containsKey("1")) {
                        // get previous amounts
                        Summaries prevAmountString = alltimeStore.get("1");
                        String happinessAvgString = prevAmountString.happiness;
                        String productivityAvgString = prevAmountString.productivity;
                        String stressAvgString = prevAmountString.stress;
                        String sleepAvgString = prevAmountString.sleep;
                        int divider = prevAmountString.amountOfSummaries;
                        divider++;

                        double happinessAvg = Double.parseDouble(happinessAvgString);
                        double stressAvg = Double.parseDouble(stressAvgString);
                        double productivityAvg = Double.parseDouble(productivityAvgString);
                        double sleepAvg = Double.parseDouble(sleepAvgString);

                        double happyTotal = (inputHappyNum + happinessAvg) / divider;
                        double stressTotal = (inputStressNum + stressAvg) / divider;
                        double productivityTotal = (inputProductivityNum + productivityAvg) / divider;
                        double sleepTotal = (inputSleepNum + sleepAvg) / divider;

                        Summaries newInput = new Summaries("" + happyTotal,
                                "" + productivityTotal, "" + stressTotal,
                                "" + sleepTotal, divider);

                        alltimeStore.put("1", newInput);
                    } else {
                        Summaries newInput = new Summaries("" + inputHappyNum,
                                "" + inputProductivityNum, "" + inputStressNum,
                                "" + inputSleepNum, 1);
                        alltimeStore.put("1", newInput);
                    }

                    // reset edit texts
                    happinessEditText.setText(null);
                    productivityEditText.setText(null);
                    sleepEditText.setText(null);
                    stressEditText.setText(null);
*/
                }

            }

        });

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
