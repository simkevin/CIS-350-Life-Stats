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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, goalStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void openGraphStats(View view) {
        String goalSelected = spinner.getSelectedItem().toString();
        String goalAssociatedValues = GoalsToDifficultyWrapper.getInstance().get(goalSelected);

        Intent intent = new Intent(this, GraphStats.class);
        intent.putExtra("goalValue", goalAssociatedValues);
        startActivity(intent);
    }

}
