package com.inone.app.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.inone.app.R;
import com.inone.app.other.Const;

import org.json.JSONObject;


public class ProfileScreen extends AppCompatActivity {

    private EditText et1,et2,et3;
    private Dialog loader = null;
    private JSONObject ldata = null;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);


        loader = Const.getLoader(ProfileScreen.this,getString(R.string.loading));
        initUI();
    }




    private void initUI() {
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
    }
    public void NextNow(View v){
        finish();
    }


    public void BackNow(View v){
        finish();
    }

    @Override
    public void onBackPressed() {
        BackNow(null);
    }
}
