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

    public TextView resort;

    public ContactViewHolder(View itemView) {
        super(itemView);
        resort = (TextView)itemView.findViewById(R.id.resortTextView);
    }


}
