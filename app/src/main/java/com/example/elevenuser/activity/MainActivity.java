package com.example.elevenuser.activity;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.elevenuser.model.*;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.elevenuser.R;
import com.example.elevenuser.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.layout_dots)
    LinearLayout layout_dots;

   // private AdapterImageSlider adapterImageSlider;
    private Runnable runnable = null;
    private Handler handler = new Handler();
    ArrayList<ImageData> imageData;
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
        imageData=new ArrayList<ImageData>();



    }


}
