package com.example.et;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class GraphView extends AppCompatActivity {

    PieChart pieChart;
    private myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view);

        helper = new myDbAdapter(this);

        TextView total_amount = (TextView) findViewById(R.id.total_amount);
        total_amount.setText(helper.getTotalAmount());

        TextView ba_amount = (TextView) findViewById(R.id.ba_amount);
        ba_amount.setText(helper.getBankAmount());

        TextView cc_amount = (TextView) findViewById(R.id.cc_amount);
        cc_amount.setText(helper.getCreditCardAmount());

        TextView ca_amount = (TextView) findViewById(R.id.ca_amount);
        ca_amount.setText(helper.getCashAmount());

        pieChart = findViewById(R.id.pie_chart);
        //ToolBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.graph_view_toolbar);
        TextView mTitle = (TextView) myToolbar.findViewById(R.id.graph_view_toolbar_title);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setData();
    }

    private void setData()
    {
        pieChart.addPieSlice(
                new PieModel(
                        "Bank Account",
                        Integer.parseInt(helper.getBankAmount()),
                        Color.parseColor("#A254F2")));
        pieChart.addPieSlice(
                new PieModel(
                        "Credit Card",
                        Integer.parseInt(helper.getCreditCardAmount()),
                        Color.parseColor("#FF8993")));
        pieChart.addPieSlice(
                new PieModel(
                        "Cash",
                        Integer.parseInt(helper.getCashAmount()),
                        Color.parseColor("#34EBE9")));
        pieChart.setLegendColor(R.color.colorPrimaryDark);
        pieChart.startAnimation();
    }
}