package com.example.et;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private myDbAdapter helper;
    private ImageView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Db Helper
        helper = new myDbAdapter(this);

        TextView ba_amount =  (TextView) findViewById(R.id.total_ba_amount);
        ba_amount.setText(helper.getBankAmount());

        TextView cc_amount =  (TextView) findViewById(R.id.total_cc_amount);
        cc_amount.setText(helper.getCreditCardAmount());

        TextView ca_amount =  (TextView) findViewById(R.id.total_ca_amount);
        ca_amount.setText(helper.getCashAmount());

        TextView b_amount =  (TextView) findViewById(R.id.balance_amount);
        b_amount.setText(helper.getTotalAmount());

        no_data = (ImageView) findViewById(R.id.empty_view_ms);
        if(helper.getAmount().size() == 0) {
            no_data.setVisibility(View.VISIBLE);
        } else {
            no_data.setVisibility(View.GONE);
        }

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddExpense.class);
                startActivity(intent);
            }
        });

        //Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        TextView mTitle = (TextView) myToolbar.findViewById(R.id.my_toolbar_title);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //RECENT TRANSACTIONS
        ArrayList<String> amount = helper.getAmount();
        ArrayList<String> category = helper.getCategory();
        ArrayList<String> description = helper.getDescription();
        ArrayList<String> date = helper.getDate();
        if (amount.size() > 5) {
            amount.subList(6,amount.size()).clear();
            category.subList(6,category.size()).clear();
            description.subList(6,description.size()).clear();
            date.subList(6,date.size()).clear();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_screen_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter( MainActivity.this, amount, category, description, date);
        recyclerView.setAdapter(customAdapter);// set the Adapter to RecyclerView

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Transactions" menu option
            case R.id.transactions:
                Intent intent = new Intent(this, Transactions.class);
                this.startActivity(intent);
                break;
            // Respond to a click on the "Graph View" menu option
            case R.id.graph_view:
                Intent i = new Intent(this, GraphView.class);
                this.startActivity(i);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}