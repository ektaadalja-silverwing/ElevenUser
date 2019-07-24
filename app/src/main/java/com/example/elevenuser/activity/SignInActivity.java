package com.example.elevenuser.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.elevenuser.R;
import com.example.elevenuser.utils.Tools;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Tools.setSystemBarColor(this,R.color.black);
        initComponent();
    }

    private void initComponent() {
        ButterKnife.bind(this);
    }
    @OnClick(R.id.tv_signed_up_now)
    void tvSignUp()
    {
        Intent intent = new Intent(SignInActivity.this,signUpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvForgot)
    void tvForgot()
    {
        Intent intent = new Intent(SignInActivity.this,ForgotPasswordActivity.class);
        startActivity(intent);
    }

}
