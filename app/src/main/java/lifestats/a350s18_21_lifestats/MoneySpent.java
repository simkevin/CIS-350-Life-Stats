package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MoneySpent extends AppCompatActivity {
    HashMap<String, String> moneyTrack = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_spent);

        final TextView spentToday = (TextView) findViewById(R.id.spentToday);
        final EditText moneyInput = (EditText) findViewById(R.id.moneyInput);
        TextView enterCashSpent = (TextView) findViewById(R.id.enterCashSpent);
        Button submitButton = (Button) findViewById(R.id.submit);
        TextView title = (TextView) findViewById(R.id.moneySpentTitle);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (moneyInput.getText() != null) {
                    String input = moneyInput.getText().toString();
                    // date format
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(Calendar.getInstance().getTime());
                    Toast.makeText(getApplicationContext()
                            , "RECORDED at " + date + " \n" +
                                    "AMOUNT: " + input
                            , Toast.LENGTH_LONG).show();

                    if(moneyTrack.containsKey(date)) {
                        String prevAmountString = moneyTrack.get(date);
                        double prevAmountValue = Double.parseDouble(prevAmountString);
                        double currAmountValue = Double.parseDouble(input);
                        double todayTotal = currAmountValue + prevAmountValue;
                        moneyTrack.put(date, "" + todayTotal);
                        spentToday.setText("You spent $" + todayTotal + " today.");
                    } else {
                        moneyTrack.put(date, input);
                        spentToday.setText("You spent $" + input + " today.");
                    }

                    // reset edit text
                    moneyInput.setText(null);
                } else {
                    Toast.makeText(getApplicationContext()
                            , "Please write something! Cannot submit a blank amount."
                            , Toast.LENGTH_SHORT).show();
                    return;
                }

            }

        });

    }
}
