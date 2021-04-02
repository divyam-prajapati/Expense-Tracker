package com.example.et;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Transactions extends AppCompatActivity {

    private myDbAdapter helper;
    private ImageView noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        helper = new myDbAdapter(this);

        ArrayList<String> amount = helper.getAmount();
        ArrayList<String> category = helper.getCategory();
        ArrayList<String> description = helper.getDescription();
        ArrayList<String> date = helper.getDate();

        noData = (ImageView) findViewById(R.id.empty_view);
        if(helper.getAmount().size() == 0) {
            noData.setVisibility(View.VISIBLE);
        }else {
            noData.setVisibility(View.GONE);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_expenses);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        final CustomAdapter customAdapter = new CustomAdapter( Transactions.this, amount, category, description, date);
        recyclerView.setAdapter(customAdapter);// set the Adapter to RecyclerView


        //ToolBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.transactions_toolbar);
        TextView mTitle = (TextView) myToolbar.findViewById(R.id.transactions_toolbar_title);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}