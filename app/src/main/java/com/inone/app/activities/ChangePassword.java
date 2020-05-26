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


public class ChangePassword extends AppCompatActivity {

    private EditText et2,et3,et4;
    private Dialog loader;
    private JSONObject ldata = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch_pass);
        loader = Const.getLoader(ChangePassword.this,getString(R.string.loading));

        initUI();
    }
    private void initUI() {
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
    }
    public void NextNow(View v){
        finish();
    }


    public void BackNow(View v){
        finish();
    }
}
