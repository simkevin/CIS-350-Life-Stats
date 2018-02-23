package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class pastGoals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_goals);

        final ListView archives = (ListView) findViewById(R.id.ArchiveList);
        Intent thisIntent = getIntent();
        ArrayList<String> goalList = thisIntent.getStringArrayListExtra("goalList");
        ArrayList<String> ratingList = thisIntent.getStringArrayListExtra("ratingList");
        final ArrayAdapter thisAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1,  goalList);
        archives.setAdapter(thisAdapter);


        Button addGoal = (Button) findViewById(R.id.goalAdd);
        Button removeGoal = (Button) findViewById(R.id.goalDelete);


        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pastScreen = new Intent(pastGoals.this, GoalsMain.class);
                startActivity(pastScreen);
            }
        });


        archives.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String picked = archives.getItemAtPosition(i).toString();
                thisAdapter.remove(picked);
                thisAdapter.notifyDataSetChanged();
            }
        });
    }
}
