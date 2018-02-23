package com.example.kevin.lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ControlPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);
    }

    public void openLifeStats(View view) {
        Intent intent = new Intent(this, LifeStatsActivity.class);
        startActivity(intent);
    }

    public void openDataEntry(View view) {
        Intent intent = new Intent(this, DataEntryActivity.class);
        startActivity(intent);
    }

    public void openDataAnalysis(View view) {
        Intent intent = new Intent(this, DataAnalysisActivity.class);
        startActivity(intent);
    }

    public void openGoals(View view) {
        Intent intent = new Intent(this, GoalsActivity.class);
        startActivity(intent);
    }

    public void openQuickView(View view) {
        Intent intent = new Intent(this, QuickViewActivity.class);
        startActivity(intent);
    }
}
