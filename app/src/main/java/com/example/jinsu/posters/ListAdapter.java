package com.example.jinsu.posters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {
    ArrayList<Listitem> myItems;

    public ListAdapter(ArrayList<Listitem> items)
    {
        myItems = items;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        holder.txt_date.setText(myItems.get(position).getDate());
        holder.txt_place.setText(myItems.get(position).getPlace());
        holder.txt_sum.setText(myItems.get(position).getSum());
        holder.txt_rest.setText(myItems.get(position).getRest());
        if (myItems.get(position).isMoney())
        {
            holder.txt_sum.setTextColor(Color.parseColor("#0048ff"));
        }
        else
        {
            holder.txt_sum.setTextColor(Color.parseColor("#f72727"));
        }
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txt_date;
        private TextView txt_place;
        private TextView txt_sum;
        private TextView txt_rest;
        public ItemViewHolder(View itemView) {
            super(itemView);
            txt_date = (TextView)itemView.findViewById(R.id.txt_date);
            txt_place = (TextView)itemView.findViewById(R.id.txt_place);
            txt_sum = (TextView)itemView.findViewById(R.id.txt_sum);
            txt_rest = (TextView)itemView.findViewById(R.id.txt_rest);
        }
    }
}
