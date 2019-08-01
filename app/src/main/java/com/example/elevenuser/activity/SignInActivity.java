package com.example.elevenuser.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elevenuser.R;
import com.example.elevenuser.network.RestCall;
import com.example.elevenuser.network.RestClient;
import com.example.elevenuser.utils.PreferenceManager;
import com.example.elevenuser.utils.Tools;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements Validator.ValidationListener{

    @Email(message = "Enter Valid Email Id")
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @Password(message = "Minimum 6 to 15 Character Require")
    @BindView(R.id.edtPassword)
    EditText edtPassword;
   /* @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.tvRemeber)
    TextView tvRemeber;*/
    @BindView(R.id.tvForgot)
    TextView tvForgot;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.btn_sign_up_now)
    RelativeLayout btnSignUpNow;
    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;
    @BindView(R.id.tv_signed_up_now)
    TextView tvSignedUpNow;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.tvOr)
    TextView tvOr;
    @BindView(R.id.view2)
    View view2;

    Validator validator;
    PreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Tools.setSystemBarColor(this,R.color.black);
        initComponent();
    }

    private void initComponent() {
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        preferenceManager = new PreferenceManager(this);

    }

    @OnClick(R.id.btnSignIn)
    void btnSignIn()
    {
        validator.validate();
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

    @Override
    public void onValidationSucceeded() {
        Intent intent = new Intent(SignInActivity.this,DashboardActivity.class);
        startActivity(intent);

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }

    }

    }

