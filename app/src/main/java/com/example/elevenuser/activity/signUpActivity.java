package com.example.elevenuser.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.elevenuser.R;
import com.example.elevenuser.utils.Tools;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class signUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Tools.setSystemBarColor(this,R.color.black);
        initComponent();

    }

    private void initComponent() {
        ButterKnife.bind(this);
    }
  @OnClick(R.id.imgBackArrow)
    void imgBackArrow()
  {
      Intent intent =  new Intent(signUpActivity.this,SignInActivity.class);
      startActivity(intent);
  }
}
