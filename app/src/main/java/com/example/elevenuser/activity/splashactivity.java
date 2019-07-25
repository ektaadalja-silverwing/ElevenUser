package com.example.elevenuser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import com.example.elevenuser.R;


public class splashactivity extends AppCompatActivity {

    private ImageView logo;
    private static int splashTimeOut=10000;
    private ImageView Hungry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo= findViewById(R.id.logo);
        Hungry = findViewById(R.id.hungry);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splashactivity.this,IntroSliderActivity.class);
                startActivity(i);
                finish();
            }
        },splashTimeOut);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mysplashanimation);
        logo.startAnimation(myanim);
        Animation myanim1 = AnimationUtils.loadAnimation(this,R.anim.mysplashanimation1);
        Hungry.startAnimation(myanim1);


    }
}