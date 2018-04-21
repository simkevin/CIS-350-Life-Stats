package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class SleepAnalysis extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> seriesGarbage;

    int numDataPoints = SleepScreen.sleepTrack.size();
    long[] sleepRecord = new long[numDataPoints];

    // get the date difference in hours from simpleDateFormat
    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.HOURS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_analysis);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        // this is the array that will contain the data to be graphed
        DataPoint [] data = new DataPoint[numDataPoints];

        // this is the array that will hold the dates in String form
        String dates[] = new String[numDataPoints];

        // store the dates in the sleepTrack into a dates array
        int i = 0;
        // pull data from sleepTrack
        while (SleepScreen.sleepTrack.iterator().hasNext() && i < numDataPoints) {
            dates[i] = SleepScreen.sleepTrack.iterator().next().date;
            i++;
        }

        // get sleepAmount
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // know where to begin keeping track of sleep
        int j = 0;
        if(SleepScreen.sleepTrack.peekFirst().sleepStatus == false) {
            j = 1;
        } else {
            j = 0;
        }

        for (int q = j; q+1 < dates.length; q++) {
            long sleepAmount = getDateDiff(sdf, dates[q], dates[q+1]);
            sleepRecord[q] = sleepAmount;
        }

        // SleepScreen.sleepTrack.iterator().hasNext()
        for(int n = 0; n < sleepRecord.length; n++) {
            data[n] = new DataPoint(n, sleepRecord[n]);
        }

        series = new LineGraphSeries<>(data);
        graph.addSeries(series);

        // add garbage data to test if graph works
        DataPoint[] dataGarbage = new DataPoint[5];
        dataGarbage[0] = new DataPoint(0, 1);
        dataGarbage[1] = new DataPoint(1, 2);
        dataGarbage[2] = new DataPoint(2, 4);
        dataGarbage[3] = new DataPoint(3, 5);
        dataGarbage[4] = new DataPoint(4, 6);
        seriesGarbage = new LineGraphSeries<>(dataGarbage);
        graph.addSeries(seriesGarbage);
    }
}