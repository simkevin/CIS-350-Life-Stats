package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

// this class handles the budget screen
public class BudgetActivity extends AppCompatActivity {

    private BudgetWrapper budget;
    private EditText budgetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        budget = BudgetWrapper.getInstance();
        budgetText = findViewById(R.id.budgetEntry);
    }

    // this method sets the weekly budget based on user input
    public void setBudget(View view) {
        String input = budgetText.getText().toString();
        Double value = Double.parseDouble(input);
        budget.put("budget", value);

        Intent intent = new Intent(this, MoneySpent.class);
        startActivity(intent);
    }

}
