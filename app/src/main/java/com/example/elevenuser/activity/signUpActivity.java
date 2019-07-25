package com.example.elevenuser.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elevenuser.R;
import com.example.elevenuser.utils.PreferenceManager;
import com.example.elevenuser.utils.Tools;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class signUpActivity extends AppCompatActivity  implements Validator.ValidationListener {

    @NotEmpty(message = "First Name is Require")
    @BindView(R.id.edtFirstName)
    EditText edtFirstName;
    @NotEmpty(message = "Last Name is Require")
    @BindView(R.id.edtLastName)
    EditText edtLastName;
    @Email(message = "Enter Valid Email Id")
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @Password(message = "Minimum 6 to 15 Character Require")
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @ConfirmPassword
    @BindView(R.id.edtConfirmPassword)
    EditText edtConfirmPassword;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.tvOr)
    TextView tvOr;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.imgBackArrow)
    ImageView imgBackArrow;

    Validator validator;
    PreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Tools.setSystemBarColor(this,R.color.black);
        initComponent();

    }

    private void initComponent() {
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        preferenceManager = new PreferenceManager(this);
    }

    @OnClick(R.id.btnSignUp)
    void btnSignUp()
    {
        validator.validate();
    }
  @OnClick(R.id.imgBackArrow)
    void imgBackArrow()
  {
      Intent intent =  new Intent(signUpActivity.this,SignInActivity.class);
      startActivity(intent);
  }

    @Override
    public void onValidationSucceeded() {
        Intent intent =  new Intent(signUpActivity.this,MainActivity.class);
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
