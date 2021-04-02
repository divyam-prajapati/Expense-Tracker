package com.example.et;


import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void list_message(Context context, ArrayList<String> message) {

        StringBuilder builder = new StringBuilder();
        for(String i : message)
        {
            builder.append(" " + i + "\n");
        }

        Toast.makeText( context, builder, Toast.LENGTH_LONG).show();
    }
}