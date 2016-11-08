package com.example.fcs.rx_research.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fcs.rx_research.R;
import com.example.fcs.rx_research.entity.Result;

import java.util.List;

import butterknife.BindView;

/**
 * Created by hanhmai1203 on 11/7/16.
 */
public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.MyViewHolder> {

    List<Result> items;
    Context mContext;

    public BestSellerAdapter(List<Result> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }

    public void setItems(List<Result> items) {
        this.items = items;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, author, description;

        public MyViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.tvTitle);
            this.description = (TextView) view.findViewById(R.id.tvDes);
            this.author = (TextView) view.findViewById(R.id.tvAuthor);
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result item = items.get(position);
        holder.title.setText("Title: " + item.getTitle());
        holder.description.setText("Des: " + item.getDescription());
        holder.author.setText("Author: " + item.getAuthor());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.book_item_row, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item_row, null);

        return new MyViewHolder(view);
    }
}