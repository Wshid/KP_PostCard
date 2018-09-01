package com.example.jinsu.posters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jinsu on 2018-03-26.
 */

public class ChangeAdapter extends RecyclerView.Adapter<ChangeAdapter.ItemViewHolder>
{
    ArrayList<ChangeItem> myItems;
    public ChangeAdapter(ArrayList<ChangeItem> items)
    {
        myItems = items;
    }
    ArrayList<String> plus_gift = new ArrayList<>();
    ArrayList<String> minus_gift = new ArrayList<>();

    //새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_check,parent,false);
        return new ItemViewHolder(view);
    }

    //View의 내용을 해당 포지션의 데이터로 바꿈
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position)
    {
        holder.txt_name.setText(myItems.get(position).getCh_txt_name());
        holder.txt_detail.setText(myItems.get(position).getCh_txt_detail());
        holder.txt_detail2.setText(myItems.get(position).getCh_txt_detail2());
        holder.image_icon.setImageResource(myItems.get(position).getCh_drawable());
        holder.check.setChecked(myItems.get(position).isCheked());
        holder.check.setTag(myItems.get(position));
        if(myItems.get(position).isCheked())
        {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#BDBDBD"));
        }
        else
        {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.check.performClick();
            }
        });

        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox ch = (CheckBox) v;
                ChangeItem items = (ChangeItem) ch.getTag();

                items.setCheked(ch.isChecked());
                myItems.get(position).setCheked(ch.isChecked());

                if(ch.isChecked()) {
                    holder.linearLayout.setBackgroundColor(Color.parseColor("#BDBDBD"));
                }
                else
                {
                    holder.linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
                }

            }
        });
    }



    //데이터셋의 크기 반환
    @Override
    public int getItemCount() {
        return myItems.size();
    }

    //item의 view들을 바인딩
    class ItemViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image_icon;
        private TextView txt_name;
        private TextView txt_detail;
        private TextView txt_detail2;
        private CheckBox check;
        private LinearLayout linearLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            image_icon = (ImageView)itemView.findViewById(R.id.ch_image_icon);
            txt_name = (TextView)itemView.findViewById(R.id.ch_txt_name);
            txt_detail = (TextView)itemView.findViewById(R.id.ch_txt_detail);
            txt_detail2 = (TextView)itemView.findViewById(R.id.ch_txt_detail2);
            check = (CheckBox)itemView.findViewById(R.id.check);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.change_recycle_item);
        }
    }
}
