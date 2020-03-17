package com.example.ski.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;


import java.util.ArrayList;

import com.example.ski.Resorts;
import com.example.ski.R;
import com.example.ski.ResortActivity;
import com.example.ski.database.SqliteDatabase;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> implements Filterable{

    private Context context;
    private ArrayList<Resorts> listResorts;
    private ArrayList<Resorts> mArrayList;

    private SqliteDatabase mDatabase;

    public ContactAdapter(Context context, ArrayList<Resorts> listContacts) {
        this.context = context;
        this.listResorts = listContacts;
        this.mArrayList=listContacts;
        //mDatabase = new SqliteDatabase(context);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_layout, parent, false);
        final ContactViewHolder holder = new ContactViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ResortActivity.class);
                intent.putExtra("message_key", holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });

        return holder; //new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        final Resorts resorts = listResorts.get(position);
        Log.i("list", resorts.getResort());

        holder.resort.setText(resorts.getResort());
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    listResorts = mArrayList;
                } else {

                    ArrayList<Resorts> filteredList = new ArrayList<>();

                    for (Resorts resorts : mArrayList) {

                        if (resorts.getResort().toLowerCase().contains(charString)) {

                            filteredList.add(resorts);
                        }
                    }

                    listResorts = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listResorts;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listResorts = (ArrayList<Resorts>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    @Override
    public int getItemCount() {
        return listResorts.size();
    }

}
