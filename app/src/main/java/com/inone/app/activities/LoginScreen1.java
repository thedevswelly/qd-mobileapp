package com.inone.app.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inone.app.R;
import com.inone.app.other.Const;


public class LoginScreen1 extends AppCompatActivity {

    private Dialog loader = null;
    private EditText et1,et2,et3,et4,et5,et6;
    private TextView txt2;
    private String cc_digit1,mobile1,cname;
    private final Handler handler = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen1);

    loader = Const.getLoader(LoginScreen1.this,getString(R.string.loading));
        cc_digit1 = getIntent().getExtras().getString("cc_digit");
        mobile1 = getIntent().getExtras().getString("mobile");
        cname = getIntent().getExtras().getString("cname");


        initUI();
        txt2.setText(cc_digit1+mobile1);
        editListner();
    }
    private void editListner() {
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et1.getText().toString().length()>=1){
                    et2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et2.getText().toString().length()>=1){
                    et3.requestFocus();
                }
                else {
                    et1.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et3.getText().toString().length()>=1){
                    et4.requestFocus();
                }
                else {
                    et2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et4.getText().toString().length()>=1){
                    et5.requestFocus();
                }
                else {
                    et3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et5.getText().toString().length()>=1){
                    et6.requestFocus();
                }
                else {
                    et4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et6.getText().toString().length()>=1){

                }
                else {
                    et5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void initUI() {
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);
        txt2 = findViewById(R.id.txt2);
    }

    public void NextNow(View v){


        PopupMenu popup = new PopupMenu(LoginScreen1.this, et6);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.login_opt, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.pin1:
                        startActivity(new Intent(getApplicationContext(), DriverDash.class));
                        break;
                    case R.id.pin2:
                        startActivity(new Intent(getApplicationContext(), VendorDash.class));
                        break;
                    case R.id.pin3:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        break;

                }
                finish();
                return true;
            }
        });

        popup.show();
           }



    public void BackNow(View v){
        startActivity(new Intent(getApplicationContext(),LoginScreen.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        BackNow(null);
    }
}
