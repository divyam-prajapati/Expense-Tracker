package com.example.et;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;


public class AddExpense extends AppCompatActivity {

    private EditText date, amount, description;
    private Spinner category;
    private myDbAdapter helper;
    DatePickerDialog datePickerDialog;

    private String mCategory = "Enter Category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        amount = findViewById(R.id.editAmount);
        date = findViewById(R.id.editDate);
        category = findViewById(R.id.editCategory);
        description = findViewById(R.id.editDescription);

        setupSpinner();

        //Db Helper
        helper = new myDbAdapter(this);

        //ToolBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.add_expense_toolbar);
        TextView mTitle = (TextView) myToolbar.findViewById(R.id.add_toolbar_title);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AddExpense.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                // set day of month , month and year value in the edit text
                                String d = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                date.setText(d);
                            }

                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter categorySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_category_options, R.layout.spinner_list);

        // Specify dropdown layout style - simple list view with 1 item per line
        categorySpinnerAdapter.setDropDownViewResource(R.layout.spinner_list);

        // Apply the adapter to the spinner
        category.setAdapter(categorySpinnerAdapter);

        // Set the integer mSelected to the constant values
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.ca))) {
                        mCategory = "Cash";
                    } else if (selection.equals(getString(R.string.cc))) {
                        mCategory = "Credit Card";
                    } else if (selection.equals(getString(R.string.ba))) {
                        mCategory = "Bank Account";
                    } else {
                        mCategory = "Enter Category";
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCategory = "Enter Category";
            }
        });
    }

    public void save_details(View view)
    {
        String amt = amount.getText().toString();
        String dt = date.getText().toString();
        String ctg = mCategory;
        String des = description.getText().toString();
        if( amt.isEmpty() || dt.isEmpty() || ctg.isEmpty() || des.isEmpty() )
        {
            Message.message(getApplicationContext(),"Please Enter all the Details!!");
        }
        else
        {
            long id = helper.insertData( amt ,dt ,ctg ,des );
            if ( id <= 0 )
            {
                Message.message(getApplicationContext(),"Insertion Unsuccessful");
            } else
            {
                Message.message(getApplicationContext(),"Insertion Successful");
            }
            amount.setText("");
            date.setText("");
            category.setSelection(0);
            description.setText("");
        }
    }

}