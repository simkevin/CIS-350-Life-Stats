package lifestats.a350s18_21_lifestats;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ExerciseGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_graph);


        ExerciseWrapper exerciseWrapper = ExerciseWrapper.getInstance();
        GraphView graph = (GraphView) findViewById(R.id.exerciseGraph);
        DataPoint[] dataArray = new DataPoint[exerciseWrapper.size()];
        String [] dateArray = new String[exerciseWrapper.size()];

        //dummy data
        exerciseWrapper.put("01/31/1997", "8&14");

        int z = 0;
        for (String date : exerciseWrapper.keySet()) {
            String num = exerciseWrapper.get(date);
            String[] numArray = num.split("&");
            double totalHours = 0.0;
            double totalMin = 0.0;
            double totalTime = 0.0;
            for (int i = 1; i < numArray.length; i = i + 3) {
                totalHours += Double.parseDouble(numArray[i]);
            }

            for (int i = 2; i < numArray.length; i = i + 3) {
                totalMin += Double.parseDouble(numArray[i]);
            }

            double minOutofHour = totalMin / 60.0;
            totalTime = totalHours + minOutofHour;
            DataPoint newData = new DataPoint(z, totalTime);
            dataArray[z] = newData;    //y
            dateArray[z] = date.toString();  //x
            z++;
        }


        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataArray);
        graph.addSeries(series);

        //labels
        if (dateArray.length > 1) {
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
            staticLabelsFormatter.setHorizontalLabels(dateArray);
            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Date");
            graph.getGridLabelRenderer().setVerticalAxisTitle("Hours of Exercise");

        }
    }
}
