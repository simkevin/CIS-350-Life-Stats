package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayRating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_rating);

        ExerciseWrapper exerciseWrapper = ExerciseWrapper.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String currDate = dateFormat.format(date);
        String num = "";
        double totalTime = 0.0;
        TextView dayText = findViewById(R.id.dayMessage);

        if (exerciseWrapper.containsKey(currDate)) {

            num = exerciseWrapper.get(currDate);
            String[] arr = num.split("&");
            double totalHours = 0.0;
            double totalMin = 0.0;
            for (int i = 1; i < arr.length; i = i + 3) {
                totalHours += Double.parseDouble(arr[i]);

            }
            for (int i = 2; i < arr.length; i = i + 3) {
                totalMin += Double.parseDouble(arr[i]);
            }
            double minOutofHour = totalMin / 60.0;
            totalTime = totalHours + minOutofHour;

        }
        else {
            totalTime = 0.0;
        }


        if (totalTime >= 5.0) {
            //Olympian
            dayText.setText("Your rating so far today is: Olympian. Wow!");
        }

        else if (totalTime >= 3.0 && totalTime < 5.0) {
            //elite
            dayText.setText("Your rating today so far is: Elite. Keep it up!");
        }

        else if (totalTime >= 1.0 && totalTime < 3.0)  {
            //healthy
            dayText.setText("Your rating today so far is: Healthy. Nice job, keep pushing!");
        }

        else if (totalTime >= 0.5 && totalTime < 1.0){
            //mediocre
            dayText.setText("Your rating today so far is: Mediocre. Keep going!");
        }
        else {
            //couch-potato
            dayText.setText("Your rating today so far is: Couch-Potato. Come on, you can do better!");
        }

    }
}
