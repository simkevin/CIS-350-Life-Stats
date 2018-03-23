package lifestats.a350s18_21_lifestats;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class DataAnalysisActivity extends AppCompatActivity {

    private HashMap<String, Float> happiness;
    private HashMap<String, Float> productivity;
    private HashMap<String, Float> stress;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);

        happiness = new HashMap<>();
        productivity = new HashMap<>();
        stress = new HashMap<>();

        happiness.put("2018-03-10 00:00:00", (float) 3.0);
        happiness.put("2018-03-11 00:00:00", (float) 5.0);
        happiness.put("2018-03-20 00:00:00", (float) 2.0);

        productivity.put("2018-03-10 00:00:00", (float) 3.0);
        productivity.put("2018-03-11 00:00:00", (float) 2.0);
        productivity.put("2018-03-20 00:00:00", (float) 4.0);

        stress.put("2018-03-10 00:00:00", (float) 3.0);
        stress.put("2018-03-11 00:00:00", (float) 1.0);
        stress.put("2018-03-20 00:00:00", (float) 5.0);


        GraphView tGraph = (GraphView) findViewById(R.id.trend_graph);

        // need to write helper function that loads datapoints into array
        DataPoint[] dp1 = new DataPoint[happiness.size()];
        int i = 0;
        for (String key: happiness.keySet()) {
            try {
                dp1[i] = new DataPoint(dateFormat.parse(key).getTime(),
                        (double) happiness.get(key));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            i++;
        }

        LineGraphSeries<DataPoint> happyGraph = new LineGraphSeries<>(dp1);
        PointsGraphSeries<DataPoint> happyPoints = new PointsGraphSeries<>(dp1);
        happyGraph.setColor(Color.RED);
        happyPoints.setColor(Color.RED);
        happyGraph.setTitle("Happiness");

        DataPoint[] dp2 = new DataPoint[productivity.size()];
        int j = 0;
        for (String key: productivity.keySet()) {
            try {
                dp2[j] = new DataPoint(dateFormat.parse(key).getTime(),
                        (double) productivity.get(key));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            j++;
        }

        LineGraphSeries<DataPoint> productiveGraph = new LineGraphSeries<>(dp2);
        PointsGraphSeries<DataPoint> productivePoints = new PointsGraphSeries<>(dp2);
        productiveGraph.setColor(Color.BLUE);
        productivePoints.setColor(Color.BLUE);
        productiveGraph.setTitle("Productivity");

        DataPoint[] dp3 = new DataPoint[stress.size()];
        int k = 0;
        for (String key: stress.keySet()) {
            try {
                dp3[k] = new DataPoint(dateFormat.parse(key).getTime(),
                        (double) stress.get(key));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            k++;
        }

        LineGraphSeries<DataPoint> stressfulGraph = new LineGraphSeries<>(dp3);
        PointsGraphSeries<DataPoint> stressfulPoints = new PointsGraphSeries<>(dp3);
        stressfulGraph.setColor(Color.YELLOW);
        stressfulPoints.setColor(Color.YELLOW);
        stressfulGraph.setTitle("Stress");

        tGraph.addSeries(happyGraph);
        tGraph.addSeries(happyPoints);
        tGraph.addSeries(productiveGraph);
        tGraph.addSeries(productivePoints);
        tGraph.addSeries(stressfulGraph);
        tGraph.addSeries(stressfulPoints);

        tGraph.getLegendRenderer().setVisible(true);
        tGraph.getGridLabelRenderer().setLabelFormatter(new
                DateAsXAxisLabelFormatter(this));
    }
}
