package com.inone.app.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.inone.app.R;
import com.inone.app.adapters.ProAdp;
import com.inone.app.interfaces.ItemClickListner;
import com.inone.app.other.Const;

import org.json.JSONArray;
import org.json.JSONObject;


public class SearchScreen extends AppCompatActivity implements ItemClickListner {

    private RecyclerView lv1;
    private Dialog loader;
    private JSONObject ldata = null;
    private JSONArray l1 = new JSONArray();
    private ImageView search_icon2;
    private EditText et2;
    private ProAdp proAdp = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);

        loader = Const.getLoader(SearchScreen.this, getString(R.string.loading));

        initUI();
        addAdp();
    }

    private void addAdp() {
        try {
            l1 = new JSONArray();
            l1.put("");
            l1.put("");
            l1.put("");
            l1.put("");
            l1.put("");
            l1.put("");
            l1.put("");
            l1.put("");
            l1.put("");

            proAdp = new ProAdp(SearchScreen.this, l1);

            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(SearchScreen.this, LinearLayoutManager.VERTICAL, false);
            lv1.setLayoutManager(layoutManager);
            lv1.setItemAnimator(new DefaultItemAnimator());
            lv1.setNestedScrollingEnabled(false);
            lv1.setAdapter(proAdp);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        lv1 = findViewById(R.id.lv1);
        et2 = findViewById(R.id.et2);
        search_icon2 = findViewById(R.id.search_icon2);

        search_icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et2.setText("");
            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (et2.getText().toString().length() > 0) {
                        search_icon2.setVisibility(View.VISIBLE);
                    } else {
                        search_icon2.setVisibility(View.GONE);
                        lv1.setAdapter(null);
                        l1 = new JSONArray();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
    }


    public void BackNow(View v) {
        finish();
    }

    @Override
    public void onItemClick(int position) {
       
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onItemClick1(int position) {
        
    }

    @Override
    public void onItemClick2(int position) {

    }
}
