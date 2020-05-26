package com.inone.app.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inone.app.R;
import com.inone.app.other.Const;


public class ForgotPassword extends AppCompatActivity {

    private EditText et1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_screen);
        initUI();
    }



    private void initUI() {
        et1 = findViewById(R.id.et1);

    }





    public void NextNow(View v){
       finish();
    }
}
