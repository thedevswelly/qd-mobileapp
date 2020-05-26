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


public class LoginScreen extends AppCompatActivity {

    private EditText et1,et2;
    private Dialog loader = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        loader = Const.getLoader(LoginScreen.this,getString(R.string.loading));
        initUI();
    }



    private void initUI() {
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);

    }
    public void SignUpNow(View v){
        startActivity(new Intent(getApplicationContext(),SignupScreen.class));
    }


    public void ForgotNow(View v){
        startActivity(new Intent(getApplicationContext(),ForgotPassword.class));
    }


    public void NextNow(View v){
        PopupMenu popup = new PopupMenu(LoginScreen.this,  findViewById(R.id.bt1));
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
}
