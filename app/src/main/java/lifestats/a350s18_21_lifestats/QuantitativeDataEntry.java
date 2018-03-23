package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.StreamCorruptedException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class QuantitativeDataEntry extends AppCompatActivity {

    private static HappinessWrapper storeHappiness = HappinessWrapper.getInstance();
    private static ProductivityWrapper storeProductivity = ProductivityWrapper.getInstance();
    private static StressWrapper storeStress = StressWrapper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantitative_data_entry);

        final RatingBar happinessBar = (RatingBar) findViewById(R.id.happinessBar);
        final RatingBar productivityBar = (RatingBar) findViewById(R.id.productivityBar);
        final RatingBar stressBar = (RatingBar) findViewById(R.id.stressBar);
        Button submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(Calendar.getInstance().getTime());
                Toast.makeText(getApplicationContext()
                        , "RECORDED at " + date + " \n" +
                                "Happiness Rating: " + happinessBar.getRating() + "\n" +
                                "Productivity Rating: " + productivityBar.getRating() + "\n" +
                                "Stress Rating: " + stressBar.getRating()
                        , Toast.LENGTH_LONG).show();

                storeHappiness.put(date, happinessBar.getRating());
                storeProductivity.put(date, productivityBar.getRating());
                storeStress.put(date, stressBar.getRating());

                // reset all bars
                happinessBar.setRating(0F);
                productivityBar.setRating(0F);
                stressBar.setRating(0F);
            }

        });
    }


}
