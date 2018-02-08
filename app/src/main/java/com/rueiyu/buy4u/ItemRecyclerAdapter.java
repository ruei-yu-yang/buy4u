package com.rueiyu.buy4u;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by RueiYu on 2018/2/8.
 */

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder> {
    private final List<Item> items;

    public ItemRecyclerAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item.getPhotoPath())
                .override(300, 250)
                .into(holder.ivPhoto);
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(item.getPrice() + "");
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }else{
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivPhoto;
        private final TextView tvName;
        private final TextView tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.row_photo);
            tvName = itemView.findViewById(R.id.row_name);
            tvPrice = itemView.findViewById(R.id.row_price);
        }
    }

    public List<Item> getItems() {
        return items;
    }
}
