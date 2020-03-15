package com.example.ski.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;


import java.util.ArrayList;

import com.example.ski.Contacts;
import com.example.ski.R;
import com.example.ski.ResortActivity;
import com.example.ski.database.SqliteDatabase;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> implements Filterable{

    private Context context;
    private ArrayList<Contacts> listContacts;
    private ArrayList<Contacts> mArrayList;

    private SqliteDatabase mDatabase;

    public ContactAdapter(Context context, ArrayList<Contacts> listContacts) {
        this.context = context;
        this.listContacts = listContacts;
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
        final Contacts contacts = listContacts.get(position);
        Log.i("list", contacts.getResort());

        holder.resort.setText(contacts.getResort());
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    listContacts = mArrayList;
                } else {

                    ArrayList<Contacts> filteredList = new ArrayList<>();

                    for (Contacts contacts : mArrayList) {

                        if (contacts.getResort().toLowerCase().contains(charString)) {

                            filteredList.add(contacts);
                        }
                    }

                    listContacts = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listContacts;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listContacts = (ArrayList<Contacts>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    @Override
    public int getItemCount() {
        return listContacts.size();
    }

}
