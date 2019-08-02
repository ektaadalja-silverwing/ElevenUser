package com.example.elevenuser.activity;

import android.animation.Animator;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elevenuser.R;
import com.example.elevenuser.adapter.AddCartAdapter;
import com.example.elevenuser.utils.Mediator;
import com.example.elevenuser.utils.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantActivity extends Fragment implements AddCartAdapter.OnClickListener {
    @BindView(R.id.recyclerAddCart)
    RecyclerView recyclerAddCart;


    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    @BindView(R.id.tvVeg)
    TextView tvVeg;
    AddCartAdapter addCartAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.restaurant_fragment, null);
        return root;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        initComponent(view);
    }


    private void initComponent(View view) {
        ButterKnife.bind(this, view);
        Tools.setSystemBarColor(getActivity(), R.color.black);
        setAddCartList();
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
        recyclerAddCart.setLayoutManager(new GridLayoutManager(getContext(), 2));

        addCartAdapter = new AddCartAdapter(getContext(), this);
        recyclerAddCart.setAdapter(addCartAdapter);
        addCartAdapter.setOnClickListener(this);
    }



    @Override
    public void onCartClick(int position, ImageView imageView) {
        moveToCart(imageView);
        Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show();
    }

    public void moveToCart(ImageView imageView) {
        new CircleAnimationUtil()
                .attachActivity(getActivity())
                .setTargetView(imageView)
                .setMoveDuration(500)
                .setDestView(Mediator.dashboardActivity.navigation.findViewById(R.id.navigation_cart))
                .setBorderColor(R.color.colorPrimaryDark)
                .setBorderWidth(2)
                .setAnimationListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //addItemToCart();
                        //Mediator.dashboardActivity.setUpBadge();

                        Toast.makeText(getContext(), "Item added", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).startAnimation();


    }
}
