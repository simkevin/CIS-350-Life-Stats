package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

// this class draws the charts for Life Stats
public class Charts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent thisIntent = getIntent();
        final ArrayList<String> goalList = thisIntent.getStringArrayListExtra("goalList");
        final ArrayList<String> ratingList = thisIntent.getStringArrayListExtra("ratingList");
        GraphView graph = (GraphView) findViewById(R.id.graph);
        DataPoint [] dataArray = new DataPoint[goalList.size()];
        int incrementer = 1;
        for (int i = 0; i < goalList.size(); i++) {
            String goal = goalList.get(i);
            String rating = ratingList.get(i);
            double numY = Double.parseDouble(rating);
            DataPoint newData = new DataPoint(incrementer, numY);
            dataArray[i] = newData;
            incrementer++;
        }

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataArray);
        graph.addSeries(series);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        String [] goalsArray = new String [goalList.size()];
        for (int i = 0; i < goalList.size(); i++) {
            goalsArray[i] = goalList.get(i);
        }

        //labels
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(goalsArray);
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Goals");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Difficulty Rating (out of 5)");

        //set spacing
        series.setSpacing(50);

        // draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);

    }
}
