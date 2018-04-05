package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

    public void setBudget(View view) {
        String input = budgetText.getText().toString();
        Double value = Double.parseDouble(input);
        budget.put("budget", value);

        Intent intent = new Intent(this, MoneySpent.class);
        startActivity(intent);
    }

}
