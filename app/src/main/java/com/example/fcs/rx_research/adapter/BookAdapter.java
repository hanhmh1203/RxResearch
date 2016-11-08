package com.example.fcs.rx_research.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fcs.rx_research.R;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hanhmai1203 on 11/3/16.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.CustomViewHolder> {
    private List<String> feedItemList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public BookAdapter(List<String> feedItemList, Context mContext) {
        this.feedItemList = feedItemList;
        this.mContext = mContext;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_book, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        String value = feedItemList.get(position);
        holder.tvTitle.setText(value);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position, value);
            }
        };
        holder.tvTitle.setOnClickListener(listener);
        holder.ivThumbnail.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        int count = (null != feedItemList ? feedItemList.size() : 0);
        return count;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbnail)
        ImageView ivThumbnail;
        @BindView(R.id.title)
        TextView tvTitle;

        public CustomViewHolder(View view) {
            super(view);
            this.ivThumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            this.tvTitle = (TextView) view.findViewById(R.id.title);

        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, String item);
    }
}
