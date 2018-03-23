package com.example.rgokhale.lifestatsapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.HashMap;

public class YearMoneyChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_money_chart);



        Intent thisIntent = getIntent();
        final ArrayList<String> dateList = thisIntent.getStringArrayListExtra("dateList");
        final ArrayList<String> moneyList = thisIntent.getStringArrayListExtra("moneyList");
        final ArrayList<String> yearList = thisIntent.getStringArrayListExtra("yearList");
        final ArrayList<String> yearAmountList = thisIntent.getStringArrayListExtra("yearAmountList");

        HashMap<String, String> dateAndMoney = new HashMap<String, String>();
        for (int i = 0; i < dateList.size(); i++) {
            dateAndMoney.put(dateList.get(i), moneyList.get(i));
        }


        GraphView graph = (GraphView) findViewById(R.id.moneyGraphPicYear);
        DataPoint[] dataArray = new DataPoint[yearList.size()];
        int z = 1;

        for (int i = 0; i < yearList.size(); i++) {
            String x = yearList.get(i);
            String y = yearAmountList.get(i);
            double numY = Double.parseDouble(y);
            DataPoint newData = new DataPoint(z, numY);
            dataArray[i] = newData;
            z++;
        }


        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataArray);
        graph.addSeries(series);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });


        String [] dateArray = new String [yearList.size()];
        for (int i = 0; i < yearList.size(); i++) {
            dateArray[i] = yearList.get(i);
            //System.out.println(dateList.get(i));
        }


        //labels

        if (dateArray.length > 1) {
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
            staticLabelsFormatter.setHorizontalLabels(dateArray);
            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Year");
            graph.getGridLabelRenderer().setVerticalAxisTitle("MoneySpent");

        }

        //set spacing
        //series.setSpacing(200);
        // draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
    }
}
