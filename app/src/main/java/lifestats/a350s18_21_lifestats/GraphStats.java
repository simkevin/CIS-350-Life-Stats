package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

public class GraphStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_stats);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent thisIntent = getIntent();
        final ArrayList<String> goalList = thisIntent.getStringArrayListExtra("goalList");
        final ArrayList<String> ratingList = thisIntent.getStringArrayListExtra("ratingList");
        GraphView graph = (GraphView) findViewById(R.id.graph);
        DataPoint[] dataArray = new DataPoint[goalList.size()];
    }
}
