package com.example.elevenuser.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.elevenuser.R;
import com.example.elevenuser.adapter.AddCartAdapter;
import com.example.elevenuser.adapter.OfferAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantActivity extends AppCompatActivity {
    @BindView(R.id.recyclerAddCart)
    RecyclerView recyclerAddCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_activity);
        initComponent();
    }

    private void initComponent() {
        ButterKnife.bind(this);
        setAddCartList();
        final CollapsingToolbarLayout collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
       /* ((AppBarLayout) findViewById(R.id.app_bar_layout)).addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int min_height = ViewCompat.getMinimumHeight(collapsing_toolbar) * 2;
                float scale = (float) (min_height + verticalOffset) / min_height;
                image.setScaleX(scale >= 0 ? scale : 0);
                image.setScaleY(scale >= 0 ? scale : 0);
            }
        });*/
    }




    private void setAddCartList() {
        recyclerAddCart.setLayoutManager(new GridLayoutManager(this, 2));
        AddCartAdapter addCartAdapter = new AddCartAdapter(this);
        recyclerAddCart.setAdapter(addCartAdapter);
    }
}
