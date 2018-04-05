package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BudgetActivity extends AppCompatActivity {

    private double budget;
    private EditText budgetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        budgetText = findViewById(R.id.budgetEntry);
    }

    public void setBudget(View view) {
        String input = budgetText.getText().toString();
        budget = Double.parseDouble(input);
        Budget.setBudget(budget);
        Intent intent = new Intent(this, MoneySpent.class);
        startActivity(intent);
    }

}
