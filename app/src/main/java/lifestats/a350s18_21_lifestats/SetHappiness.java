package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SetHappiness extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_happiness);

        final TextView thresholdTextView = (TextView) findViewById(R.id.textViewSetThreshold);
        final RatingBar setThreshold = (RatingBar) findViewById(R.id.thresholdSet);
        Button submitButton = (Button) findViewById(R.id.submitThreshold);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(Calendar.getInstance().getTime());
                Toast.makeText(getApplicationContext()
                        , "RECORDED at " + date + " \n" +
                                "Happiness Threshold: " + setThreshold.getRating()
                        , Toast.LENGTH_LONG).show();

                double threshold = setThreshold.getRating();
                HappinessThresholdWrapper.getInstance().setThreshold(threshold);

                // reset all bars
                setThreshold.setRating(0F);
            }

        });

    }
}