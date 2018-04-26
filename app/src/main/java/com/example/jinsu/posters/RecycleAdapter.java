package com.example.jinsu.posters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jinsu on 2018-03-26.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ItemViewHolder>
{
    ArrayList<HomeItem> myItems;
    public RecycleAdapter(ArrayList<HomeItem> items)
    {
        myItems = items;
    }

    //새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        return new ItemViewHolder(view);
    }

    //View의 내용을 해당 포지션의 데이터로 바꿈
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position)
    {
        holder.txt_name.setText(myItems.get(position).getTxt_name());
        holder.txt_detail.setText(myItems.get(position).getTxt_detail());
        holder.txt_detail2.setText(myItems.get(position).getTxt_detail2());
        holder.image_icon.setImageResource(myItems.get(position).getDrawable());
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
        public ItemViewHolder(View itemView) {
            super(itemView);
            image_icon = (ImageView)itemView.findViewById(R.id.image_icon);
            txt_name = (TextView)itemView.findViewById(R.id.txt_name);
            txt_detail = (TextView)itemView.findViewById(R.id.txt_detail);
            txt_detail2 = (TextView)itemView.findViewById(R.id.txt_detail2);
        }
    }
}
