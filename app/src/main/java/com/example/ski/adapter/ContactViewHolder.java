package com.example.ski.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ski.R;
import com.example.ski.ResortActivity;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    public TextView resort,status;
    public ImageView deleteContact;
    public  ImageView editContact;

    public ContactViewHolder(View itemView) {
        super(itemView);
        resort = (TextView)itemView.findViewById(R.id.resortTextView);
        //status = (TextView)itemView.findViewById(R.id.status);
        //deleteContact = (ImageView)itemView.findViewById(R.id.delete_contact);
        //editContact = (ImageView)itemView.findViewById(R.id.edit_contact);
    }


}
