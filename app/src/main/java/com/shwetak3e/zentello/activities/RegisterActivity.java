package com.shwetak3e.zentello.activities;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.user_activity.BookParcelDeliveryActivity;
import com.shwetak3e.zentello.utilities.Constants;
import com.shwetak3e.zentello.utilities.DetectNetworkConnectivity;
import com.shwetak3e.zentello.utilities.UserFormValidity;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    LinearLayout reg_layout;

    Button i_am_franchisee, i_am_customer;
    TextInputEditText fullNameEdit,  emailEdit, phoneEdit,passwordEdit;
    TextInputLayout fullName, email, phone, password;
    Button btnRegister;
    final String TAG= getClass().getSimpleName();

    CheckBox showPasswordButton;
    TextView joinUs;

    private static final String FULL_NAME_PARAM_KEY="fullName";
    private static final String EMAIL_PARAM_KEY="email";
    private static final String PASSWORD_PARAM_KEY="password";
    private static final String PHONE_PARAM_KEY="mobile";

    BroadcastReceiver nonetwork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_layout=(LinearLayout)findViewById(R.id.reg_layout);

        nonetwork=new DetectNetworkConnectivity() {
            @Override
            protected void onNetworkChange() {
                Snackbar snackbar=Snackbar.make(reg_layout, Constants.NO_NETWORK,Snackbar.LENGTH_INDEFINITE);
                snackbar.show();
            }
        };
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(nonetwork,filter);




        fullName=(TextInputLayout)findViewById(R.id.rTextFName);
        fullNameEdit = (TextInputEditText) findViewById(R.id.rEditFName);
        fullNameEdit.addTextChangedListener(new NewUserTextWatcher(fullNameEdit));


        i_am_customer=(Button)findViewById(R.id.i_am_customer);
        i_am_franchisee=(Button)findViewById(R.id.i_am_franchisee);

        i_am_customer.setSelected(true);
        i_am_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               fullNameEdit.setHint(getResources().getString(R.string.hint_name));
            }
        });
        i_am_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullNameEdit.setHint(getResources().getString(R.string.hint_franchisee_name));
            }
        });


        email=(TextInputLayout)findViewById(R.id.rTextEmail);
        emailEdit=(TextInputEditText) findViewById(R.id.rEditEmail);
        emailEdit.addTextChangedListener(new NewUserTextWatcher(emailEdit));


        phone=(TextInputLayout)findViewById(R.id.rTextPhone);
        phoneEdit=(TextInputEditText) findViewById(R.id.rEditPhone);
        phoneEdit.addTextChangedListener(new NewUserTextWatcher(phoneEdit));


        password=(TextInputLayout)findViewById(R.id.rTextPassword);
        passwordEdit=(TextInputEditText) findViewById(R.id.rEditPassword);
        passwordEdit.addTextChangedListener(new NewUserTextWatcher(passwordEdit));


        showPasswordButton=(CheckBox)findViewById(R.id.show_password_btn);
        showPasswordButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    showPasswordButton.setText("Hide Password");
                    passwordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordEdit.requestFocus();
                    passwordEdit.setSelection(passwordEdit.getText().toString().length());
                }else{
                    showPasswordButton.setText("Show Password");
                    passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordEdit.requestFocus();
                    passwordEdit.setSelection(passwordEdit.getText().toString().length());
                }
            }
        });


        btnRegister=(Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRegister.setEnabled(false);
                register();
            }
        });

        joinUs=(TextView)findViewById(R.id.reg_direction);
        Typeface tf= Typeface.createFromAsset(getAssets(),"fonts/allura_regular.ttf");
        joinUs.setTypeface(tf);

        Log.i(TAG,"onCreate");
    }


    public void register(){

        if(validateForm()) {
            Log.i(TAG,"register validated");
            try {

                Map<String, String> newUserDetails=new HashMap<>();
                newUserDetails.put(FULL_NAME_PARAM_KEY,fullNameEdit.getText().toString().trim());
                newUserDetails.put(EMAIL_PARAM_KEY,emailEdit.getText().toString().trim());
                newUserDetails.put(PHONE_PARAM_KEY,phoneEdit.getText().toString().trim());
                newUserDetails.put(PASSWORD_PARAM_KEY,passwordEdit.getText().toString().trim());
               // new RegistrationService(this).execute(newUserDetails);
                startActivity(new Intent(this, BookParcelDeliveryActivity.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Log.i(TAG,"registration invalidated");
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        btnRegister.setEnabled(true);
    }



    boolean validateForm(){

        Log.i(TAG,"validating Form");

        if(!UserFormValidity.isName(fullNameEdit, UserFormValidity.REQUIRED)) {
            fullNameEdit.requestFocus();
            fullNameEdit.setSelection(fullNameEdit.getText().toString().length());
            return false;
        }

        if(!UserFormValidity.isEmailAddress(emailEdit, UserFormValidity.REQUIRED)){
            emailEdit.requestFocus();
            emailEdit.setSelection(emailEdit.getText().toString().length());
            return false;
        }
        if(!UserFormValidity.isPhoneNumber(phoneEdit, UserFormValidity.REQUIRED)){
            phoneEdit.requestFocus();
            phoneEdit.setSelection(phoneEdit.getText().toString().length());
            return false;
        }
        if(!UserFormValidity.isPassword(passwordEdit, UserFormValidity.REQUIRED)){
            passwordEdit.requestFocus();
            passwordEdit.setSelection(passwordEdit.getText().toString().length());
            return false;
        }
        return true;
    }

    public void gotoLogin(View v){
        Log.i(TAG,"going to login Page");
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }



    class NewUserTextWatcher implements TextWatcher {

        private View view;

        private NewUserTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.rEditFName:
                    UserFormValidity.isName(fullNameEdit, UserFormValidity.REQUIRED);
                    break;
                case R.id.rEditEmail:
                    UserFormValidity.isEmailAddress(emailEdit, UserFormValidity.REQUIRED);
                    break;
                case R.id.rEditPhone:
                    UserFormValidity.isPhoneNumber(phoneEdit, UserFormValidity.REQUIRED);
                    break;
                case R.id.rEditPassword:
                    UserFormValidity.isPassword(passwordEdit, UserFormValidity.REQUIRED);
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }
}
