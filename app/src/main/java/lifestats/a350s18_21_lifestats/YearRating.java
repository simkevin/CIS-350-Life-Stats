package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YearRating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_rating);

        ExerciseWrapper exerciseWrapper = ExerciseWrapper.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String currDate = dateFormat.format(date);

        String num = "";
        double totalTime = 0.0; 
        TextView monthText = findViewById(R.id.yearMessage);


        totalTime = 0.0;

        double totalHours = 0.0; 
        double totalMin = 0.0;
        for (String loopDate : exerciseWrapper.keySet()) {
            String [] arr = loopDate.split("/");           //MM/dd/YYYY
            String currYear = currDate;   //YYYY


            if (currYear.equals(arr[2])) {    //comparing years
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



        if (totalTime >= 1750.0) {
            //Olympian
            monthText.setText("Your rating so far this year is: Olympian. Wow!");
        }

        else if (totalTime >= 1000.0 && totalTime < 1750.0) {
            //elite
            monthText.setText("Your rating so far this year is: Elite. Keep it up!");
        }

        else if (totalTime >= 350.0 && totalTime < 1000.0)  {
            //healthy
            monthText.setText("Your rating so far this year is: Healthy. Nice job, keep pushing!");
        }

        else if (totalTime >= 150.0 && totalTime < 350.0){
            //mediocre
            monthText.setText("Your rating so far this year is: Mediocre. Keep going!");
        }
        else {
            //couch-potato
            monthText.setText("Your rating so far this year is: Couch-Potato. Come on, you can do better!");
        }


    }
}
