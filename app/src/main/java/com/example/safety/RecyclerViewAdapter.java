package com.example.safety;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<DisasterNews> mData;


    public RecyclerViewAdapter(Context mContext, List lst) {


        this.mContext = mContext;
        this.mData = lst;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_row, parent, false);
        // click listener here
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.dsname.setText(mData.get(position).getName());
        holder.ds_description.setText(mData.get(position).getDescription());
        holder.dsdate.setText(mData.get(position).getDate());
        holder.dslink.setText(holder.dslink.getText()+" "+mData.get(position).getLink());
        holder.ds_latitude.setText(holder.ds_latitude.getText() + String.valueOf(mData.get(position).getLatitude()));
        holder.ds_longitude.setText(holder.ds_longitude.getText() + String.valueOf(mData.get(position).getLongitude()));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView dsname, ds_description, dsdate,dslink,
                ds_latitude, ds_longitude;


        public MyViewHolder(View itemView) {
            super(itemView);
            dsname = itemView.findViewById(R.id.rowname);
            ds_description = itemView.findViewById(R.id.description);
            dsdate = itemView.findViewById(R.id.date);
            ds_latitude = itemView.findViewById(R.id.latitude);
            ds_longitude = itemView.findViewById(R.id.longitude);
            dslink = itemView.findViewById(R.id.link);

        }
    }
}