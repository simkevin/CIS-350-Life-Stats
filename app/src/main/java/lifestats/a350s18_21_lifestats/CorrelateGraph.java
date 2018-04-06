package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Set;

public class CorrelateGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correlate_graph);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent thisIntent = getIntent();
        String statOne = thisIntent.getStringExtra("statOne");
        String statTwo = thisIntent.getStringExtra("statTwo");
        LineGraphSeries statOneLine = statLine(statOne);
        //LineGraphSeries statTwoLine = statLine(statTwo);
        GraphView graph = (GraphView) findViewById(R.id.graph);


        graph.addSeries(statOneLine);
        //graph.addSeries(statTwoLine);
        graph.getLegendRenderer().setVisible(true);
        graph.getGridLabelRenderer().setLabelFormatter(new
                DateAsXAxisLabelFormatter(this));
        //double correlation = getCorrelation(statOne, statTwo);
        //TextView correlateText = (TextView)findViewById(R.id.textViewCorrelation);
        //correlateText.setText("Sample Covariance: " + correlation);
    }

    private double getCorrelation(String statOneName, String statTwoName) {
        ArrayList moodStats = new ArrayList<String>(3);
        moodStats.add("Happiness");
        moodStats.add("Stress");
        moodStats.add("Productivity");


        /*
         *In this case, as they are recorded in pairs, we can do a proper statistical
         * comparison
         */
        if (moodStats.contains(statOneName) && moodStats.contains(statTwoName)) {
            Set<Map.Entry<String, Float>> statOneValues;
            Set<Map.Entry<String, Float>> statTwoValues;

            if (statOneName.equals("Happiness")) {
               statOneValues = HappinessWrapper.getInstance().entrySet();
            } else if (statOneName.equals("Productivity")) {
                statOneValues = ProductivityWrapper.getInstance().entrySet();
            } else if (statOneName.equals("Stress")) {
                statOneValues = StressWrapper.getInstance().entrySet();
            } else {
                throw new IllegalArgumentException("Invalid stat name");
            }

            if (statTwoName.equals("Happiness")) {
                statTwoValues = HappinessWrapper.getInstance().entrySet();
            } else if (statTwoName.equals("Productivity")) {
                statTwoValues = ProductivityWrapper.getInstance().entrySet();
            } else if (statTwoName.equals("Stress")) {
                statTwoValues = StressWrapper.getInstance().entrySet();
            } else {
                throw new IllegalArgumentException("Invalid stat name");
            }

            double statOneSum = 0;
            double statTwoSum = 0;
            double[] statOneValueArr = new double[statOneValues.size()];
            double[] statTwoValueArr = new double[statTwoValues.size()];

            // If the arrays are not equal length, something messed up, so we return
            if (statOneValueArr.length != statTwoValueArr.length) {
                return -1;
            }

            if (statOneValueArr.length <=1 ) {
                return -1;
            }

            // We finally will calculate the coveriance
            int i = 0;
            for (Map.Entry<String, Float> entry : statOneValues) {
                statOneName += entry.getValue();
                statOneValueArr[i] = entry.getValue();
                i++;
            }

            int j = 0;
            for (Map.Entry<String, Float> entry : statTwoValues) {
                statTwoSum += entry.getValue();
                statTwoValueArr[j] = entry.getValue();
                j++;
            }

            double statOneMean = statOneSum / statOneValues.size();
            double statTwoMean = statTwoSum / statTwoValues.size();

            double sampleCovereanceSum = 0;

            for (int k = 0; k < statOneValueArr.length; k++ ) {
                sampleCovereanceSum += (statOneValueArr[k] - statOneMean) *
                        (statTwoValueArr[k] - statTwoMean);
            }
            return sampleCovereanceSum / statOneValueArr.length;
        }

        // More correlations to go here
        return -1;
    }

    private LineGraphSeries statLine(String stat) {
        // We match the stat to get the databases
        if (stat.equals("Happiness")) {
           return GraphStats.getHappiness();
        } else if (stat.equals("Productivity")) {
            return GraphStats.getProductivity();
        } else if (stat.equals("Stress")) {
            return GraphStats.getStress();
        } else if (stat.equals("Exercise")) {
            return GraphStats.getExercise();
        }
        else {
            throw new IllegalArgumentException("Invalid stat argument. " + stat + " is not a"
            + " valid stat.");
        }
    }
}
