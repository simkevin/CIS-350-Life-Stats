package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

// this class handles all money-related user stories
public class MoneySpent extends AppCompatActivity {

    private DateToMoneyWrapper moneyPerDay = DateToMoneyWrapper.getInstance();
    //key = date, value = moneySpent
    private double weeklyExpenses;
    private BudgetWrapper budgetWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_spent);

        TextView dailySpending = findViewById(R.id.amountSpent);
        TextView remainingBudget = findViewById(R.id.remainingBudget);
        Button submitButton = findViewById(R.id.moneyButton);
        Button latestSpending = findViewById(R.id.latestSpending);
        Button moneyGraph = findViewById(R.id.moneyGraph);
        final EditText moneyText = findViewById(R.id.moneyText);


        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String currDate = dateFormat.format(date);
        if (!moneyPerDay.containsKey(currDate)) {
            dailySpending.setText("Amount spent today: $" + 0.00);
        }
        else {
            dailySpending.setText("Amount spent today: $" + moneyPerDay.get(currDate));
        }

        budgetWrapper = BudgetWrapper.getInstance();
        weeklyExpenses = calculateWeeklyTotal();
        double remainder;
        if (!budgetWrapper.containsKey("budget")) {
            remainingBudget.setText("Weekly budget left: $" + 0.00);
        }
        else {
            remainder = budgetWrapper.get("budget") - weeklyExpenses;
            remainingBudget.setText("Weekly budget left: $" + remainder);
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the current date and the amount spent
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date date = new Date();
                String currDate = dateFormat.format(date);
                String text = moneyText.getText().toString();
                double newEntrySpent = Double.parseDouble(text);
                DecimalFormat two = new DecimalFormat("#.00");
                newEntrySpent = Double.valueOf(two.format(newEntrySpent));

                //if more is being added to a current day
                if (moneyPerDay.containsKey(currDate)) {
                    double newMoney = moneyPerDay.get(currDate);
                    newMoney += newEntrySpent;
                    newMoney = Double.valueOf(two.format(newMoney));
                    moneyPerDay.put(currDate, newMoney);
                    moneyText.setText("");
                }

                //if its a new day
                else {
                    moneyPerDay.put(currDate, newEntrySpent);
                    moneyText.setText("");
                }
            }
        });


        latestSpending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> days = new ArrayList<String>();
                ArrayList<Double>  money = new ArrayList<Double>();
                for (String s : moneyPerDay.keySet()) {
                    days.add(s + ": \t\t\t\t$" + moneyPerDay.get(s));
                    money.add(moneyPerDay.get(s));
                }
                Intent latestScreen = new Intent(MoneySpent.this, PastMoney.class);
                latestScreen.putExtra("dateList", days);
                latestScreen.putExtra("moneyList", money);
                startActivity(latestScreen);


            }
        });

        moneyGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> days = new ArrayList<String>();
                ArrayList<String>  money = new ArrayList<String>();
                for (String s : moneyPerDay.keySet()) {
                    days.add(s);
                    money.add(Double.toString(moneyPerDay.get(s)));
                }
                Intent graphScreen = new Intent(MoneySpent.this, MoneyChartChooser.class);
                graphScreen.putExtra("dateList", days);
                graphScreen.putExtra("moneyList", money);
                startActivity(graphScreen);
            }
        });


    }

    public void openSetBudget(View view) {
        Intent intent = new Intent(this, BudgetActivity.class);
        startActivity(intent);
    }

    private double calculateWeeklyTotal() {
        double sum = 0.0;
        Calendar cal = Calendar.getInstance();
        int today = cal.get(Calendar.DAY_OF_WEEK);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String currDate = dateFormat.format(date);

        switch(today) {
            case Calendar.SUNDAY:
                sum = moneyPerDay.get(currDate);
                break;
            case Calendar.MONDAY:
                cal.add(Calendar.DATE, -2);
                for (int i = 0; i < 2; i++) {
                    cal.add(Calendar.DATE, 1);
                    date = cal.getTime();
                    currDate = dateFormat.format(date);
                }

                if (moneyPerDay.containsKey(currDate)) {
                    sum += moneyPerDay.get(currDate);
                }
                break;
            case Calendar.TUESDAY:
                cal.add(Calendar.DATE, -3);
                for (int i = 0; i < 3; i++) {
                    cal.add(Calendar.DATE, 1);
                    date = cal.getTime();
                    currDate = dateFormat.format(date);
                }

                if (moneyPerDay.containsKey(currDate)) {
                    sum += moneyPerDay.get(currDate);
                }
                break;
            case Calendar.WEDNESDAY:
                cal.add(Calendar.DATE, -4);
                for (int i = 0; i < 4; i++) {
                    cal.add(Calendar.DATE, 1);
                    date = cal.getTime();
                    currDate = dateFormat.format(date);
                }

                if (moneyPerDay.containsKey(currDate)) {
                    sum += moneyPerDay.get(currDate);
                }
                break;
            case Calendar.THURSDAY:
                cal.add(Calendar.DATE, -5);
                for (int i = 0; i < 5; i++) {
                    cal.add(Calendar.DATE, 1);
                    date = cal.getTime();
                    currDate = dateFormat.format(date);
                    Log.d("DATE", currDate);
                }

                if (moneyPerDay.containsKey(currDate)) {
                    sum += moneyPerDay.get(currDate);
                }

                break;
            case Calendar.FRIDAY:
                cal.add(Calendar.DATE, -6);
                for (int i = 0; i < 6; i++) {
                    cal.add(Calendar.DATE, 1);
                    date = cal.getTime();
                    currDate = dateFormat.format(date);
                }

                if (moneyPerDay.containsKey(currDate)) {
                    sum += moneyPerDay.get(currDate);
                }
                break;
            case Calendar.SATURDAY:
                cal.add(Calendar.DATE, -7);
                for (int i = 0; i < 7; i++) {
                    cal.add(Calendar.DATE, 1);
                    date = cal.getTime();
                    currDate = dateFormat.format(date);
                }

                if (moneyPerDay.containsKey(currDate)) {
                    sum += moneyPerDay.get(currDate);
                }
                break;
        }

        return sum;
    }

}
