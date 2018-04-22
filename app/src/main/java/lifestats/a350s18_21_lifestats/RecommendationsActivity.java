package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RecommendationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);
    }

    public void openStress(View view) {
        Intent intent = new Intent(this, StressRecommendationsActivity.class);
        startActivity(intent);
    }

    public void openHappiness(View view) {
        Intent intent = new Intent(this, HappinessRecommendationsActivity.class);
        startActivity(intent);
    }

    public void openProductivity(View view) {
        Intent intent = new Intent(this, ProductivityRecommendationsActivity.class);
        startActivity(intent);
    }
}
