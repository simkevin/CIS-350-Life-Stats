package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

// this activity displays past recorded goals
public class PastGoals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_goals);

        final ListView archives = findViewById(R.id.ArchiveList);

        Intent thisIntent = getIntent();
        final ArrayList<String> goalList = thisIntent.getStringArrayListExtra("goalList");
        final ArrayAdapter thisAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1,  goalList);
        archives.setAdapter(thisAdapter);

        // Database singleton wrapper
        final GoalsToDifficultyWrapper goalsDataBase = GoalsToDifficultyWrapper.getInstance();


        final Button addGoal = findViewById(R.id.goalAdd);


        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pastScreen = new Intent(PastGoals.this, GoalsMain.class);
                startActivity(pastScreen);
            }
        });



        archives.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String picked = archives.getItemAtPosition(i).toString();
                thisAdapter.remove(picked);
                goalsDataBase.remove(picked.split(",")[0]);
                thisAdapter.notifyDataSetChanged();
            }
        });


    }
}
