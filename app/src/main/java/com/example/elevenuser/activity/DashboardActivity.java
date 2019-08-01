package com.example.elevenuser.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.elevenuser.R;
import com.example.elevenuser.adapter.OfferAdapter;
import com.example.elevenuser.adapter.RestaurantListAdapter;
import com.example.elevenuser.fragment.DashBoardFragment;
import com.example.elevenuser.model.ImageData;
import com.example.elevenuser.utils.Mediator;
import com.example.elevenuser.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {


    private ActionBar toolbar;

    @BindView(R.id.navigation)
    public BottomNavigationView navigation;
    // private AdapterImageSlider adapterImageSlider;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Tools.setSystemBarColor(this, R.color.black);
        initcomponent();

        Fragment fragment = new DashBoardFragment(getApplicationContext(), new ArrayList<ImageData>());
        loadFragment(fragment);
        navigation.setSelectedItemId(R.id.navigation_restaurants);


    }

    private void initcomponent() {
        ButterKnife.bind(this);
        Mediator.dashboardActivity = this;

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_restaurants:
                    // mTextMessage.setText(item.getTitle());
                    navigation.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    fragment = new DashBoardFragment(getApplicationContext(), new ArrayList<ImageData>());
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_deal:
                    //  mTextMessage.setText(item.getTitle());
                    navigation.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    return true;
                case R.id.navigation_order:
                    //mTextMessage.setText(item.getTitle());
                    navigation.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    return true;
                case R.id.navigation_cart:
                    // mTextMessage.setText(item.getTitle());
                    navigation.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    return true;
                case R.id.navigation_account:
                    // mTextMessage.setText(item.getTitle());
                    navigation.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setUpBadge() {

        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) navigation.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(3);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        View badge = LayoutInflater.from(this)
                .inflate(R.layout.card_badge_layout, itemView, true);
        TextView textView = (badge.findViewById(R.id.notificationsBadge));
        if (count > 0) {
            badge.setVisibility(View.VISIBLE);
        } else {
            badge.setVisibility(View.GONE);
        }
        textView.setText("" + count);
        //itemView.addView(badge);
    }
}
