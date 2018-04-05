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

public class DataAnalysisActivity extends AppCompatActivity {

    private HappinessWrapper happinessData;
    private ProductivityWrapper productivityData;
    private StressWrapper stressData;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);

        happinessData = HappinessWrapper.getInstance();
        productivityData = ProductivityWrapper.getInstance();
        stressData = StressWrapper.getInstance();

        GraphView tGraph = (GraphView) findViewById(R.id.trend_graph);

        // need to write helper function that loads datapoints into array
        DataPoint[] dp1 = new DataPoint[happinessData.size()];
        int i = 0;
        for (String key: happinessData.keySet()) {
            try {
                dp1[i] = new DataPoint(dateFormat.parse(key).getTime(),
                        (double) happinessData.get(key));
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

        DataPoint[] dp2 = new DataPoint[productivityData.size()];
        int j = 0;
        for (String key: productivityData.keySet()) {
            try {
                dp2[j] = new DataPoint(dateFormat.parse(key).getTime(),
                        (double) productivityData.get(key));
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

        DataPoint[] dp3 = new DataPoint[stressData.size()];
        int k = 0;
        for (String key: stressData.keySet()) {
            try {
                dp3[k] = new DataPoint(dateFormat.parse(key).getTime(),
                        (double) stressData.get(key));
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
