package com.smotteh.milestone6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    private ArrayList<String> nameData = new ArrayList<>();
    private ArrayList<Integer> idData = new ArrayList<>();

    private ArrayList<String> nameDataFull;
    private ArrayList<Integer> idDataFull;
    private Context context;


    public MyAdapter(Context ct, ArrayList<String> nameData, ArrayList<Integer> idData) {
        context = ct;
        this.nameData = nameData;
        this.idData = idData;

        nameDataFull = new ArrayList<>(nameData);
        idDataFull = new ArrayList<>(idData);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.person_row, parent, false);

        final MyViewHolder holder = new MyViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPersonView(holder.myText.getText().toString(), Integer.parseInt(holder.idText.getText().toString()));
            }
        });

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText.setText(nameData.get(position));
        holder.idText.setText(String.format(Locale.getDefault(), "%d", idData.get(position)));
    }

    @Override
    public int getItemCount() {
        return nameData.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<String> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(nameDataFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (String item : nameDataFull) {
                    if (item.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            nameData.clear();
            nameData.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText;
        TextView idText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText = itemView.findViewById(R.id.person_text);
            idText = itemView.findViewById(R.id.id_text);
        }
    }

    public void openPersonView(String name, int id) {
        Toast.makeText(context, name + " id: " + id, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(context, ViewPersonActivity.class);
        Bundle b = new Bundle();
        b.putInt("key", id);
        intent.putExtras(b);
        context.startActivity(intent);
    }
}
