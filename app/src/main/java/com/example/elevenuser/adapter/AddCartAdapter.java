package com.example.elevenuser.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elevenuser.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCartAdapter extends RecyclerView.Adapter<AddCartAdapter.AddCartViewHolder> {
    Context context;
    private OnClickListener onClickListener = null;

    public AddCartAdapter(Context context,OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public interface OnClickListener {

        void onCartClick(int position, ImageView imageView);
    }

    @NonNull
    @Override
    public AddCartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_add_to_cart, viewGroup, false);
        return new AddCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddCartViewHolder viewHolder, final int position) {

        viewHolder.tvAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onCartClick(position,viewHolder.imgMove);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public static class AddCartViewHolder extends RecyclerView.ViewHolder{
        /** ButterKnife Code **/
        @BindView(R.id.imgSet)
        ImageView imgSet;
        @BindView(R.id.imgMove)
        ImageView imgMove;
        @BindView(R.id.tvAddToCart)
        TextView tvAddToCart;
        /** ButterKnife Code **/

        public AddCartViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
