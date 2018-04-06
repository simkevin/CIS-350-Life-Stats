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

// this class graphs happiness, productivity, and stress over time
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

        DataPoint[] happinessArray = new DataPoint[happinessData.size()];
        int incrementer = 0;
        for (String key: happinessData.keySet()) {
            try {
                happinessArray[incrementer] = new DataPoint(dateFormat.parse(key).getTime(),
                        (double) happinessData.get(key));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            incrementer++;
        }

        LineGraphSeries<DataPoint> happyGraph = new LineGraphSeries<>(happinessArray);
        PointsGraphSeries<DataPoint> happyPoints = new PointsGraphSeries<>(happinessArray);
        happyGraph.setColor(Color.RED);
        happyPoints.setColor(Color.RED);
        happyGraph.setTitle("Happiness");

        DataPoint[] productivityArray = new DataPoint[productivityData.size()];
        incrementer = 0;
        for (String key: productivityData.keySet()) {
            try {
                productivityArray[incrementer] = new DataPoint(dateFormat.parse(key).getTime(),
                        (double) productivityData.get(key));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            incrementer++;
        }

        LineGraphSeries<DataPoint> productiveGraph = new LineGraphSeries<>(productivityArray);
        PointsGraphSeries<DataPoint> productivePoints = new PointsGraphSeries<>(productivityArray);
        productiveGraph.setColor(Color.BLUE);
        productivePoints.setColor(Color.BLUE);
        productiveGraph.setTitle("Productivity");

        DataPoint[] stressArray = new DataPoint[stressData.size()];
        incrementer = 0;
        for (String key: stressData.keySet()) {
            try {
                stressArray[incrementer] = new DataPoint(dateFormat.parse(key).getTime(),
                        (double) stressData.get(key));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            incrementer++;
        }

        LineGraphSeries<DataPoint> stressfulGraph = new LineGraphSeries<>(stressArray);
        PointsGraphSeries<DataPoint> stressfulPoints = new PointsGraphSeries<>(stressArray);
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
