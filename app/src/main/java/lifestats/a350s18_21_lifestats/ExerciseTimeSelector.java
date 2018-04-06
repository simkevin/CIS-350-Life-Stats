package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Context;

import java.util.Set;
import android.view.View;
import java.util.Date;
import android.util.Log;
import android.widget.Toast;


public class ExerciseTimeSelector extends AppCompatActivity {
    private Spinner hours ;
    private Spinner minutes ;
    private String exerciseType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_time_selector);

        Intent thisIntent = getIntent();
        exerciseType = thisIntent.getStringExtra("Exercise");


        hours = (Spinner) findViewById(R.id.spinnerHours);
        minutes = (Spinner) findViewById(R.id.spinnerMinutes);


        ArrayAdapter<CharSequence> hoursAdapter = ArrayAdapter.createFromResource(this,
                R.array.hours, android.R.layout.simple_spinner_item);
        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hours.setAdapter(hoursAdapter);

        ArrayAdapter<CharSequence> minutesAdapter = ArrayAdapter.createFromResource(this,
                R.array.minutes, android.R.layout.simple_spinner_item);
        minutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minutes.setAdapter(minutesAdapter);
    }

    public void submitExercise(View view) {
        int hoursValue = Integer.parseInt(hours.getSelectedItem().toString());
        int minutesValue = Integer.parseInt(minutes.getSelectedItem().toString());
        String currentTime = "" + (new Date()).getTime();
        String toStore = exerciseType + "&" + hoursValue + "&" + minutesValue;
        ExerciseWrapper exerciseWrapper = ExerciseWrapper.getInstance();
        exerciseWrapper.put(currentTime, toStore);
        CharSequence alertMessage = "Exercise Recorded!";
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, alertMessage, duration);
        toast.show();
    }
}
