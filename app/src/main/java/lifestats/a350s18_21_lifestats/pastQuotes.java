package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class pastQuotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_quotes);

        final ListView archives = findViewById(R.id.quoteView);
        Intent thisIntent = getIntent();
        final ArrayList<String> dates = thisIntent.getStringArrayListExtra("dates");
        final ArrayAdapter thisAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1,  dates);
        archives.setAdapter(thisAdapter);
    }
}
