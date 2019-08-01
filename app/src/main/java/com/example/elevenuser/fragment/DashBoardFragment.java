package com.example.elevenuser.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.elevenuser.R;
import com.example.elevenuser.activity.RestaurantActivity;
import com.example.elevenuser.adapter.OfferAdapter;
import com.example.elevenuser.adapter.RestaurantListAdapter;
import com.example.elevenuser.model.ImageData;
import com.example.elevenuser.utils.Mediator;
import com.example.elevenuser.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class DashBoardFragment extends Fragment implements RestaurantListAdapter.OnClickListener{

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.layout_dots)
    LinearLayout layout_dots;
    @BindView(R.id.recyclerOffer)
    RecyclerView recyclerOffer;
    @BindView(R.id.recyclerRestaurantsList)
    RecyclerView recyclerRestaurantList;


    public AdapterImageSlider adapterImageSlider;
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



    @SuppressLint("ValidFragment")
    public DashBoardFragment(Context context, ArrayList<ImageData> imageData) {
        // Required empty public constructor

       /* Fragment fragment = new RestaurantFragment();
        ((DashboardActivity)getActivity()).addFragment(fragment);*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_dash_board, container, false);
        adapterImageSlider=new AdapterImageSlider(getContext(),new ArrayList<ImageData>());

        initcomponent(view);


        return view;
    }

    private void initcomponent(final View view) {
        ButterKnife.bind(this,view);
        setRecyclerOffer();
        setRestaurantList();
        final List<ImageData> items = new ArrayList<>();

        for (int i = 0; i < array_image_place.length; i++) {
            ImageData obj = new ImageData();
            obj.image = array_image_place[i];
            obj.imageDrw = getResources().getDrawable(obj.image);
            obj.name = array_title_place[i];
            obj.brief = array_brief_place[i];
            items.add(obj);
            Log.e("@@@",items.get(i).name);
        }
        adapterImageSlider.setItems(items);
        pager.setAdapter(adapterImageSlider);

        // displaying selected image first
        pager.setCurrentItem(0);
        addBottomDots(layout_dots, adapterImageSlider.getCount(), 0);
        ((TextView) view.findViewById(R.id.title)).setText(items.get(0).name);
        ((TextView) view.findViewById(R.id.brief)).setText(items.get(0).brief);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                ((TextView) view.findViewById(R.id.title)).setText(items.get(pos).name);
                ((TextView) view.findViewById(R.id.brief)).setText(items.get(pos).brief);
                addBottomDots(layout_dots, adapterImageSlider.getCount(), pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        startAutoSlider(adapterImageSlider.getCount());
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

    private void addBottomDots(LinearLayout layout_dots, int count, int pos) {
        ImageView[] dots = new ImageView[count];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getContext());
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle_outline);
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[pos].setImageResource(R.drawable.shape_circle);
        }
    }


    private void setRestaurantList() {
        recyclerRestaurantList.setLayoutManager(new LinearLayoutManager(getContext()));
        RestaurantListAdapter restaurantListAdapter = new RestaurantListAdapter(getContext(),this);
        recyclerRestaurantList.setAdapter(restaurantListAdapter);

    }

    private void setRecyclerOffer() {
        recyclerOffer.setLayoutManager(new GridLayoutManager(getContext(),2));
        OfferAdapter offerAdapter = new OfferAdapter(getContext());
        recyclerOffer.setAdapter(offerAdapter);
    }

    @Override
    public void onClick(int position) {
        RestaurantActivity restaurantActivity = new RestaurantActivity();
        Mediator.dashboardActivity.loadFragment(restaurantActivity);
    }

    private static class AdapterImageSlider extends PagerAdapter {

        private Context context;
        private List<ImageData> items;

        private DashBoardFragment.AdapterImageSlider.OnItemClickListener onItemClickListener;

        interface OnItemClickListener {
            void onItemClick(View view, ImageData obj);
        }

        public void setOnItemClickListener(DashBoardFragment.AdapterImageSlider.OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        // constructor
        private AdapterImageSlider(Context context, ArrayList<ImageData> items) {
            this.context =context ;
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item_slider_image, container, false);

            ImageView image = (ImageView) v.findViewById(R.id.image);
            MaterialRippleLayout lyt_parent = (MaterialRippleLayout) v.findViewById(R.id.lyt_parent);
            Tools.displayImageOriginal(context, image, o.image);
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


}
