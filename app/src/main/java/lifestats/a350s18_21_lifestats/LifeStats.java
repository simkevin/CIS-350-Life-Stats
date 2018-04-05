package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.Set;
import android.util.Log;

public class LifeStats extends AppCompatActivity {
    private Spinner spinner;
    private Spinner spinnerStatOne;
    private Spinner spinnerStatTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_stats);

        spinner = (Spinner) findViewById(R.id.spinner);
        Set<String> goals = GoalsToDifficultyWrapper.getInstance().keySet();
        String[] goalStrings = new String[goals.size()];
        int i = 0;
        for(String str : goals) {
            goalStrings[i] = str;
            i++;
        }

        // We make and populate the goal spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, goalStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinnerStatOne = (Spinner) findViewById(R.id.spinnerStatOne);
        spinnerStatTwo = (Spinner) findViewById(R.id.spinnerStatTwo);
        ArrayAdapter<CharSequence> statsOne = ArrayAdapter.createFromResource(this,
                R.array.stat_names, android.R.layout.simple_spinner_item);
        statsOne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> statsTwo = ArrayAdapter.createFromResource(this,
                R.array.stat_names, android.R.layout.simple_spinner_item);
        statsTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerStatOne.setAdapter(statsOne);
        spinnerStatTwo.setAdapter(statsTwo);

    }

    public void openGraphStats(View view) {
        String goalSelected = spinner.getSelectedItem().toString();
        String goalAssociatedValues = GoalsToDifficultyWrapper.getInstance().get(goalSelected);

        Intent intent = new Intent(this, GraphStats.class);
        intent.putExtra("goalValue", goalAssociatedValues);
        startActivity(intent);
    }

    public void openCorrelateGraph(View view) {
        String firstStat = spinnerStatOne.getSelectedItem().toString();
        String secondStat = spinnerStatTwo.getSelectedItem().toString();
        Intent intent = new Intent(this, CorrelateGraph.class);
        intent.putExtra("statOne", firstStat);
        intent.putExtra("statTwo", secondStat);
        startActivity(intent);
    }

}
