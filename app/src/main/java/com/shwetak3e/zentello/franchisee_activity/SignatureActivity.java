package com.shwetak3e.zentello.franchisee_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.models.DrawSignature;

public class SignatureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        DrawSignature drawSignature=new DrawSignature(this);
        setContentView(drawSignature);
    }
}
