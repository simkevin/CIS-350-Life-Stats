package lifestats.a350s18_21_lifestats;

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
       // Intent intent = new Intent(this, LifeStatsActivity.class);
       // startActivity(intent);
    }

    public void openDataEntry(View view) {
        Intent intent = new Intent(this, QuantitativeDataEntry.class);
        startActivity(intent);
    }

    public void openMoney(View view) {
        Intent intent = new Intent(this, MoneySpent.class);
        startActivity(intent);
    }

    public void openDataAnalysis(View view) {
        //Intent intent = new Intent(this, DataAnalysisActivity.class);
        //startActivity(intent);
    }

    /*
     * This is the method called when the goals button is called. It opens the GoalsMain
     * activity.
     */
    public void openGoals(View view) {
        Intent intent = new Intent(this, GoalsMain.class);
        startActivity(intent);
    }

    /*
     *This is the method called when the summary button is pressed. It opnes the
     * summary activity.
     */
    public void openSummary(View view) {
        Intent intent = new Intent(this, Summary.class);
        startActivity(intent);
    }

    public void openSleep(View view) {
        Intent intent = new Intent(this, SleepScreen.class);
        startActivity(intent);
    }

    /*
     * This is the method called when the about button is called. It takes the user to the
     * about page.
     */
    public void openAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

}
