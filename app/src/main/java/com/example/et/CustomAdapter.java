package com.example.et;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    ArrayList<String> amount;
    ArrayList<String> category;
    ArrayList<String> description;
    ArrayList<String> date;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        private final TextView amount, category, description, date;

        public ViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            amount = (TextView) itemView.findViewById(R.id.card_view_amount);
            category = (TextView) itemView.findViewById(R.id.card_view_category);
            description = (TextView) itemView.findViewById(R.id.card_view_title);
            date = (TextView) itemView.findViewById(R.id.card_view_date);

        }
    }

    public CustomAdapter(Context context, ArrayList<String> amount, ArrayList<String> category, ArrayList<String> description, ArrayList<String> date) {
        this.context = context;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // inflate the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_expense_list, parent, false);
        // set the view's size, margins, padding and layout parameters

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.amount.setText((String) amount.get(position));
        holder.category.setText((String) category.get(position));
        holder.description.setText((String) description.get(position));
        holder.date.setText((String) date.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, EditExpense.class );
                intent.putExtra("amount", amount.get(position));
                intent.putExtra("category", category.get(position));
                intent.putExtra("description", description.get(position));
                intent.putExtra("date", date.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return amount.size();
    }

}
