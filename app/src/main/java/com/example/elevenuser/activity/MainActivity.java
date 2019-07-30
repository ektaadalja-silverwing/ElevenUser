package com.example.elevenuser.activity;

import android.app.Activity;
import android.content.Context;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
import com.example.elevenuser.adapter.OfferAdapter;
import com.example.elevenuser.adapter.RestaurantListAdapter;
import com.example.elevenuser.model.*;
import com.example.elevenuser.R;
import com.example.elevenuser.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.layout_dots)
    LinearLayout layout_dots;
    @BindView(R.id.recyclerOffer)
    RecyclerView recyclerOffer;
    @BindView(R.id.recyclerRestaurantsList)
    RecyclerView recyclerRestaurantList;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    boolean isNavigationHide = false;

    // private AdapterImageSlider adapterImageSlider;
    private AdapterImageSlider adapterImageSlider;
    private Runnable runnable = null;
    private Handler handler = new Handler();



    List<ImageData> imageData;

    private static int[] array_image_place = {
            R.drawable.image_two,
            R.drawable.image_three,
            R.drawable.image_four,
            R.drawable.image_five,
    };
    private static String[] array_title_place = {
            "Dui fringilla ornare finibus, orci odio",
            "Mauris sagittis non elit quis fermentum",
            "Mauris ultricies augue sit amet est sollicitudin",
            "Suspendisse ornare est ac auctor pulvinar",
            "Vivamus laoreet aliquam ipsum eget pretium",
    };
    private static String[] array_brief_place = {
            "Eleven Eleven",
            "Cafe Coffee Day",
            "Danny Coffee Bar",
            "Mocha",
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    private void initComponent() {
        ButterKnife.bind(this);
        setRecyclerOffer();
        setRestaurantList();


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_restaurants:
                       // mTextMessage.setText(item.getTitle());
                        navigation.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
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
        });




        adapterImageSlider = new AdapterImageSlider(this, new ArrayList<ImageData>());

        final List<ImageData> items = new ArrayList<>();
        for (int i = 0; i < array_image_place.length; i++) {
            ImageData obj = new ImageData();
            obj.image = array_image_place[i];
            obj.imageDrw = getResources().getDrawable(obj.image);
            obj.name = array_title_place[i];
            obj.brief = array_brief_place[i];
            items.add(obj);
        }

        adapterImageSlider.setItems(items);
        pager.setAdapter(adapterImageSlider);

        // displaying selected image first
        pager.setCurrentItem(0);
        addBottomDots(layout_dots, adapterImageSlider.getCount(), 0);
        ((TextView) findViewById(R.id.title)).setText(items.get(0).name);
        ((TextView) findViewById(R.id.brief)).setText(items.get(0).brief);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                ((TextView) findViewById(R.id.title)).setText(items.get(pos).name);
                ((TextView) findViewById(R.id.brief)).setText(items.get(pos).brief);
                addBottomDots(layout_dots, adapterImageSlider.getCount(), pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        startAutoSlider(adapterImageSlider.getCount());
    }

    private void animateNavigation(final boolean hide) {
        if (isNavigationHide && hide || !isNavigationHide && !hide) return;
        isNavigationHide = hide;
        int moveY = hide ? (2 * navigation.getHeight()) : 0;
        navigation.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }


    private void setRestaurantList() {
        recyclerRestaurantList.setLayoutManager(new LinearLayoutManager(this));
        RestaurantListAdapter restaurantListAdapter = new RestaurantListAdapter(this);
        recyclerRestaurantList.setAdapter(restaurantListAdapter);
    }

    private void setRecyclerOffer() {
        recyclerOffer.setLayoutManager(new GridLayoutManager(this,2));
        OfferAdapter offerAdapter = new OfferAdapter(this);
        recyclerOffer.setAdapter(offerAdapter);
    }

    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle_outline);
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setImageResource(R.drawable.shape_circle);
        }
    }

    private void startAutoSlider(final int count) {
        runnable = new Runnable() {
            @Override
            public void run() {
                int pos = pager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                pager.setCurrentItem(pos);
                handler.postDelayed(runnable, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
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


    private static class AdapterImageSlider extends PagerAdapter {

        private Activity act;
        private List<ImageData> items;

        private AdapterImageSlider.OnItemClickListener onItemClickListener;

        private interface OnItemClickListener {
            void onItemClick(View view, ImageData obj);
        }

        public void setOnItemClickListener(AdapterImageSlider.OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        // constructor
        private AdapterImageSlider(Activity activity, ArrayList<ImageData> items) {
            this.act = activity;
            this.items = items;
        }

        @Override
        public int getCount() {
            return this.items.size();
        }

        public ImageData getItem(int pos) {
            return items.get(pos);
        }

        public void setItems(List<ImageData> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final ImageData o = items.get(position);
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item_slider_image, container, false);

            ImageView image = (ImageView) v.findViewById(R.id.image);
            MaterialRippleLayout lyt_parent = (MaterialRippleLayout) v.findViewById(R.id.lyt_parent);
            Tools.displayImageOriginal(act, image, o.image);
            lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, o);
                    }
                }
            });

            ((ViewPager) container).addView(v);

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((RelativeLayout) object);

        }

    }

    @Override
    public void onDestroy() {
        if (runnable != null) handler.removeCallbacks(runnable);
        super.onDestroy();
    }

}
