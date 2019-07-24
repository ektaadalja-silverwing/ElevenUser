package com.example.elevenuser.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.elevenuser.R;
import com.example.elevenuser.utils.Tools;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Tools.setSystemBarColor(this,R.color.white);
        initComponent();

    }

    private void initComponent() {
        ButterKnife.bind(this);
    }
    @OnClick(R.id.imgBackArrow)
    void imgBackArrow()
    {
        Intent intent =  new Intent(ForgotPasswordActivity.this,SignInActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btnSendPass)
    void btnSendPass()
    {
        Intent intent =  new Intent(ForgotPasswordActivity.this,IntroSliderActivity.class);
        startActivity(intent);
    }
}
