package com.example.elevenuser.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.elevenuser.R;
import com.example.elevenuser.adapter.IntroViewPagerAdapter;
import com.example.elevenuser.utils.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroSliderActivity extends AppCompatActivity {

    @BindView(R.id.view_pagers1)
    ViewPager viewPager1;

    private IntroViewPagerAdapter introViewPagerAdapter;
    private static final int MAX_STEP = 3;

    private String about_title_array[] = {
            "BREAKFAST",
            "SOUP & SALAD",
            "APPETIZER",
    };
    private String about_description_array[] =
            {
            "A wide variety of breakfast dishes that will keep you going all day & make you have it again for the next bright morning.\n" +
                    "\n" +
                    "\n",
            "Luscious soup recipes that any bowl will be blessed to receive & salads to give a boost to your mood.",
            "Appetizers to leave you spell bounded & titillate your taste buds accompanied with scrumptiousness.",
    };
    private int about_images_array[] =
            {
            R.drawable.fastfood,
            R.drawable.fastfood,
            R.drawable.fastfood,
            R.drawable.fastfood
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);
        initComponent();
    }

    private void initComponent() {
        ButterKnife.bind(this);

        // adding bottom dots
        bottomProgressDots(0);
        introViewPagerAdapter = new IntroViewPagerAdapter();
        viewPager1.setAdapter(introViewPagerAdapter);
        viewPager1.addOnPageChangeListener(viewPagerPageChangeListener);

        Tools.setSystemBarColor(this, R.color.overlay_light_80);
        Tools.setSystemBarLight(this);
    }

    private void bottomProgressDots(int current_index) {
        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        ImageView[] dots = new ImageView[MAX_STEP];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        }
    }
    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            bottomProgressDots(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    /**
     * View pager adapter
     */
    public class IntroViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        private Button btnNext;

        public IntroViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.intro_slide_one, container, false);

                ((TextView) view.findViewById(R.id.title)).setText(about_title_array[position]);
                ((TextView) view.findViewById(R.id.description)).setText(about_description_array[position]);
                ((ImageView) view.findViewById(R.id.image)).setImageResource(about_images_array[position]);
                Button ok = view.findViewById(R.id.btn_ok);

                if (position == 2)
                {
                    Log.e("@@", "instantiateItem: "+position);
                    ok.setVisibility(View.VISIBLE);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(IntroSliderActivity.this,SignInActivity.class);
                            startActivity(intent);

                        }
                    });

                }else {
                    Log.e("@@", "instantiate: "+position);

                    ok.setVisibility(View.GONE);

                }

                    container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return about_title_array.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
