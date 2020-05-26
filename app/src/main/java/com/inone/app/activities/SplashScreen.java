package com.inone.app.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.inone.app.R;
import com.inone.app.other.Const;

import org.json.JSONObject;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);



    }


    public void nextNow(View v) {
        try {
            startActivity(new Intent(getApplicationContext(), LoginScreen.class));
            finish();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
