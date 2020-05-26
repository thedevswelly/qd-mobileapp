package com.inone.app.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.inone.app.R;
import com.inone.app.other.Const;

import org.json.JSONObject;


public class WalletScreen extends AppCompatActivity {

    private TextView txt3;

    private Dialog loader = null;
    private JSONObject ldata = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_screen);

        loader = Const.getLoader(WalletScreen.this,getString(R.string.loading));

        initUI();

    }


    private void initUI() {
        txt3 = findViewById(R.id.txt3);

        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextNow(null);
            }
        });
    }

    private void NextNow(View v) {


    }
    public void BackNow(View v){
        finish();
    }
}
