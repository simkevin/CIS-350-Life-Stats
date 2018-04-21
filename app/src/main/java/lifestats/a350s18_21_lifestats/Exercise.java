package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Exercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
    }

    public void openRunning(View view) {
        Intent intent = new Intent(this, ExerciseTimeSelector.class);
        intent.putExtra("Exercise", "Running");
        startActivity(intent);
    }

    public void openCycling(View view) {
        Intent intent = new Intent(this, ExerciseTimeSelector.class);
        intent.putExtra("Exercise", "Cycling");
        startActivity(intent);
    }

    public void openWalking(View view) {
        Intent intent = new Intent(this, ExerciseTimeSelector.class);
        intent.putExtra("Exercise", "Walking");
        startActivity(intent);
    }

    public void openSwimming(View view) {
        Intent intent = new Intent(this, ExerciseTimeSelector.class);
        intent.putExtra("Exercise", "Swimming");
        startActivity(intent);
    }

    public void openLifting(View view) {
        Intent intent = new Intent(this, ExerciseTimeSelector.class);
        intent.putExtra("Exercise", "Lifting");
        startActivity(intent);
    }

    public void openAerobics(View view) {
        Intent intent = new Intent(this, ExerciseTimeSelector.class);
        intent.putExtra("Exercise", "Aerobics");
        startActivity(intent);
    }

    public void openGraph(View view) {
        Intent intent = new Intent(this, ExerciseGraph.class);
        startActivity(intent);
    }

    public void openExerciseRating(View view) {
        Intent intent = new Intent(this, ExerciseRating.class);
        startActivity(intent);
    }

}
