package com.example.elevenuser.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elevenuser.R;

import butterknife.ButterKnife;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder>{

    Context context;

    public OfferAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_offer, viewGroup, false);
        return new OfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder offerViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class OfferViewHolder extends RecyclerView.ViewHolder{
        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
