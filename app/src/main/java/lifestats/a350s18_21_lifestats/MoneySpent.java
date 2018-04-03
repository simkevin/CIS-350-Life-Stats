package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
 
public class MoneySpent extends AppCompatActivity {

    private DateToMoneyWrapper moneyPerDay = DateToMoneyWrapper.getInstance();
    //key = date, value = moneySpent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_spent);

        Button submitButton = findViewById(R.id.moneyButton);
        Button latestSpending = findViewById(R.id.latestSpending);
        Button moneyGraph = findViewById(R.id.moneyGraph);
        final EditText moneyText = findViewById(R.id.moneyText);
        

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
                Intent latestScreen = new Intent(MoneySpent.this, pastMoney.class);
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
                Intent graphScreen = new Intent(MoneySpent.this, moneyChartChooser.class);
                graphScreen.putExtra("dateList", days);
                graphScreen.putExtra("moneyList", money);
                startActivity(graphScreen);
            }
        });


    }
}
