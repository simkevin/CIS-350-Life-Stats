package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonthRating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_rating);

        ExerciseWrapper exerciseWrapper = ExerciseWrapper.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        Date date = new Date();
        String currDate = dateFormat.format(date);

        String num = "";
        double totalTime = 0.0;
        TextView monthText = findViewById(R.id.monthMessage);


        DateFormat dayFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date dateDay = new Date();
        String currDay = dayFormat.format(dateDay);

        totalTime = 0.0;

        double totalHours = 0.0;
        double totalMin = 0.0;

        for (String loopDate : exerciseWrapper.keySet()) {
            String [] arr = loopDate.split("/");           //MM/dd/YYYY
            String [] currDateArr = currDate.split("/");   //MM/YYYY


            if (currDateArr[0].equals(arr[0])  && currDateArr[1].equals(arr[2])) {
                num = exerciseWrapper.get(loopDate);
                String[] exercisearr = num.split("&");
                for (int i = 1; i < exercisearr.length; i = i + 3) {
                    totalHours += Double.parseDouble(exercisearr[i]);

                }
                for (int i = 2; i < exercisearr.length; i = i + 3) {
                    totalMin += Double.parseDouble(exercisearr[i]);
                }
            }

        }

        double minOutofHour = totalMin / 60.0;
        totalTime += totalHours + minOutofHour;


        if (totalTime >= 150.0) {
            //Olympian
            monthText.setText("Your rating so far this month is: Olympian. Wow!");
        }

        else if (totalTime >= 90.0 && totalTime < 150.0) {
            //elite
            monthText.setText("Your rating so far this month is: Elite. Keep it up!");
        }

        else if (totalTime >= 30.0 && totalTime < 90.0)  {
            //healthy
            monthText.setText("Your rating so far this month is: Healthy. Nice job, keep pushing!");
        }

        else if (totalTime >= 15.0 && totalTime < 30.0){
            //mediocre
            monthText.setText("Your rating so far this month is: Mediocre. Keep going!");
        }
        else {
            //couch-potato
            monthText.setText("Your rating so far this month is: Couch-Potato. Come on, you can do better!");
        }


    }
}
