package com.example.et;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class myDbAdapter
{
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String amount, String date, String category, String description)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.AMOUNT, amount);
        contentValues.put(myDbHelper.DATE, date);
        contentValues.put(myDbHelper.CATEGORY, category);
        contentValues.put(myDbHelper.DESCRIPTION, description);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }// End of insert data

    /*
    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.AMOUNT,myDbHelper.DATE,myDbHelper.CATEGORY,myDbHelper.DESCRIPTION};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String amount =cursor.getString(cursor.getColumnIndex(myDbHelper.AMOUNT));
            String date =cursor.getString(cursor.getColumnIndex(myDbHelper.DATE));
            String category =cursor.getString(cursor.getColumnIndex(myDbHelper.CATEGORY));
            String description =cursor.getString(cursor.getColumnIndex(myDbHelper.DESCRIPTION));
            buffer.append(cid+ "   " + amount + "   " + date + "  " + category + "  " + description +" \n");
        }
        return buffer.toString();
    }// End of getData
    */

    public ArrayList<String> getAmount()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.AMOUNT};
        String sort = myDbHelper.DATE + " DESC" ;
        ArrayList<String> amount_list = new ArrayList<String>();
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,sort);
        while (cursor.moveToNext())
        {
            String amount = cursor.getString(cursor.getColumnIndex(myDbHelper.AMOUNT));
            amount_list.add(amount);
        }
        return amount_list;
    }// End of getAmount

    public ArrayList<String> getCategory()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.CATEGORY};
        String sort = myDbHelper.DATE + " DESC" ;
        ArrayList<String> category_list = new ArrayList<String>();
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,sort);
        while (cursor.moveToNext())
        {
            String category = cursor.getString(cursor.getColumnIndex(myDbHelper.CATEGORY));
            category_list.add(category);
        }
        return category_list;
    }// End of getCategory

    public ArrayList<String> getDescription()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.DESCRIPTION};
        String sort = myDbHelper.DATE + " DESC" ;
        ArrayList<String> description_list = new ArrayList<String>();
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,sort);
        while (cursor.moveToNext())
        {
            String description =cursor.getString(cursor.getColumnIndex(myDbHelper.DESCRIPTION));
            description_list.add(description);
        }
        return description_list;
    }// End of getDescription

    public ArrayList<String> getDate()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.DATE};
        String sort = myDbHelper.DATE + " DESC" ;
        ArrayList<String> date_list = new ArrayList<String>();
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,sort);
        while (cursor.moveToNext())
        {
            String description =cursor.getString(cursor.getColumnIndex(myDbHelper.DATE));
            date_list.add(description);
        }
        return date_list;
    }// End of getDate

    public String getTotalAmount()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.AMOUNT};
        int totalAmount = 0;
        Cursor cursor =db.query(myDbHelper.TABLE_NAME, columns,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            String amount = cursor.getString(cursor.getColumnIndex(myDbHelper.AMOUNT));
            totalAmount += Integer.parseInt(amount);
        }
        return String.valueOf(totalAmount);
    }

    public String getBankAmount()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.AMOUNT, myDbHelper.CATEGORY};
        int totalAmount = 0;
        Cursor cursor =db.query(myDbHelper.TABLE_NAME, columns,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            if("Bank Account".equals( cursor.getString(cursor.getColumnIndex(myDbHelper.CATEGORY)) ) ) {
                String amount = cursor.getString(cursor.getColumnIndex(myDbHelper.AMOUNT));
                totalAmount += Integer.parseInt(amount);
            }
        }
        return String.valueOf(totalAmount);
    }

    public String getCreditCardAmount()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.AMOUNT, myDbHelper.CATEGORY};
        int totalAmount = 0;
        Cursor cursor =db.query(myDbHelper.TABLE_NAME, columns,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            if("Credit Card".equals( cursor.getString(cursor.getColumnIndex(myDbHelper.CATEGORY)) ) ) {
                String amount = cursor.getString(cursor.getColumnIndex(myDbHelper.AMOUNT));
                totalAmount += Integer.parseInt(amount);
            }
        }
        return String.valueOf(totalAmount);
    }

    public String getCashAmount()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.AMOUNT, myDbHelper.CATEGORY};
        int totalAmount = 0;
        Cursor cursor =db.query(myDbHelper.TABLE_NAME, columns,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            if("Cash".equals( cursor.getString(cursor.getColumnIndex(myDbHelper.CATEGORY)) ) ) {
                String amount = cursor.getString(cursor.getColumnIndex(myDbHelper.AMOUNT));
                totalAmount += Integer.parseInt(amount);
            }
        }
        return String.valueOf(totalAmount);
    }

    public  int delete(String amount, String date)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={amount, date};

        int count = db.delete(myDbHelper.TABLE_NAME ,myDbHelper.AMOUNT + " = ?" + " AND " + myDbHelper.DATE + " = ?" , whereArgs);
        return  count;
    }// End of delete

    public int update(String newAmount , String newDate, String  newCategory, String newDescription,
                      String oldAmount , String oldDate, String oldCategory , String oldDescription)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.AMOUNT, newAmount);
        contentValues.put(myDbHelper.DATE, newDate);
        contentValues.put(myDbHelper.CATEGORY, newCategory);
        contentValues.put(myDbHelper.DESCRIPTION, newDescription);

        String[] whereArgs= {oldAmount, oldDate, oldCategory, oldDescription};

        int count = db.update(myDbHelper.TABLE_NAME, contentValues,
                myDbHelper.AMOUNT + " = ? " + " AND " +
                        myDbHelper.DATE + " = ? " + " AND " +
                        myDbHelper.CATEGORY + " = ? " + " AND " +
                        myDbHelper.DESCRIPTION + " = ? "
                ,whereArgs );
        return count;
    }// End of update Amount

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "expense.db";
        private static final String TABLE_NAME = "expense_table";
        private static final int DATABASE_Version = 1;
        private static final String UID = "_id";
        private static final String AMOUNT = "AMOUNT";
        private static final String DATE = "DATE";
        private static final String CATEGORY = "CATEGORY";
        private static final String DESCRIPTION = "DESCRIPTION";
        private static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + AMOUNT + " INTEGER ," + DATE +
                " DATE ," + CATEGORY + " VARCHAR(255) ," + DESCRIPTION + " VARCHAR(225));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
                Message.message(context, "Database Created!!!");
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }
    }// End of myDbHelper

}//End of Adapter

