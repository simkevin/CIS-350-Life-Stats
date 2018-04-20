package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CalorieBudgetActivity extends AppCompatActivity {
    private Spinner weightLossGoalSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_budget);
        setSpinnerValues();
    }

    private void setSpinnerValues() {
        weightLossGoalSpinner = (Spinner) findViewById(R.id.spinnerWeightLoss);

        ArrayAdapter<CharSequence> weightLossGoals = ArrayAdapter.createFromResource(this,
                R.array.weight_loss_goals, android.R.layout.simple_spinner_item);
        weightLossGoals.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightLossGoalSpinner.setAdapter(weightLossGoals);
    }
}
