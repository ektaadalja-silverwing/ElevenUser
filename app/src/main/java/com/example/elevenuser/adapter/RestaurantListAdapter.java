package com.example.elevenuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.elevenuser.R;
import com.example.elevenuser.activity.RestaurantActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantListViewholder> {
    Context context;

    private OnClickListener onClickListener = null;

    public RestaurantListAdapter(Context context, OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public RestaurantListViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_restaurant_list, viewGroup, false);
        return new RestaurantListViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListViewholder viewholder, final int position) {
        viewholder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class RestaurantListViewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.linear)
        LinearLayout linear;
        @BindView(R.id.tvResturentName)
        TextView tvResturentName;
        @BindView(R.id.tvFoodType)
        TextView tvFoodType;
        @BindView(R.id.tvCityName)
        TextView tvCityName;
        @BindView(R.id.tvRating)
        TextView tvRating;

        public RestaurantListViewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
