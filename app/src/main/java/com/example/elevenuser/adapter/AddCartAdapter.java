package com.example.elevenuser.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elevenuser.R;

import butterknife.ButterKnife;

public class AddCartAdapter extends RecyclerView.Adapter<AddCartAdapter.AddCartViewHolder> {
    Context context;

    public AddCartAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AddCartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_add_to_cart, viewGroup, false);
        return new AddCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddCartViewHolder addCartViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public static class AddCartViewHolder extends RecyclerView.ViewHolder{
        public AddCartViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
