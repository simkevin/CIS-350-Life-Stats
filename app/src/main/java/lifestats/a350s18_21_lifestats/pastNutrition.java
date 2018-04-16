package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class pastNutrition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_nutrition);
        final ListView archives = findViewById(R.id.foodView);
        Intent thisIntent = getIntent();
        final ArrayList<String> dateList = thisIntent.getStringArrayListExtra("dateList");
        final ArrayList<String> foodList = thisIntent.getStringArrayListExtra("foodList");
        final ArrayAdapter thisAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1,  dateList);
        archives.setAdapter(thisAdapter);


    }
}
