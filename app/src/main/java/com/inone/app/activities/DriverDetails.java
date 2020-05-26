package com.inone.app.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inone.app.R;
import com.inone.app.adapters.SetAdp1;
import com.inone.app.dto.SetDto;
import com.inone.app.interfaces.ItemClickListner;
import com.inone.app.other.Const;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DriverDetails extends AppCompatActivity implements ItemClickListner {

    private Dialog loader;
    private JSONObject ldata = null;
    private JSONObject data = null;
    private List<SetDto> l2 = new ArrayList<>();
    private RecyclerView  lv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_details);
        loader = Const.getLoader(DriverDetails.this, getString(R.string.loading));


        initUI();
        setData();
    }





    private void setData() {
        try {

            l2 = new ArrayList<>();
            l2.add(new SetDto(getString(R.string.name), 0, "John Smith"));
            l2.add(new SetDto(getString(R.string.email), 0, "J@gmail.com"));
            l2.add(new SetDto(getString(R.string.mobile_number), 0, "34544434343"));
            l2.add(new SetDto(getString(R.string.password), 0, "23423"));
            l2.add(new SetDto(getString(R.string.dob), 0, "23-AUG-1995"));
            l2.add(new SetDto(getString(R.string.age), 0, "23"));
            l2.add(new SetDto(getString(R.string.city), 0, "Indore"));
            l2.add(new SetDto(getString(R.string.state), 0, "MP"));
            l2.add(new SetDto(getString(R.string.pincode), 0, "345543"));
            l2.add(new SetDto(getString(R.string.address_line1), 0, "ncjsdfsndjfdnj"));
            l2.add(new SetDto(getString(R.string.address_line2), 0, "ncdjnfkdfsm"));


            SetAdp1 mainAdp = new SetAdp1(DriverDetails.this, l2);

            LinearLayoutManager layoutManager2
                    = new LinearLayoutManager(DriverDetails.this, LinearLayoutManager.VERTICAL, false);
            lv1.setLayoutManager(layoutManager2);
            lv1.setItemAnimator(new DefaultItemAnimator());
            lv1.setNestedScrollingEnabled(false);
            lv1.setAdapter(mainAdp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void initUI() {
        lv1 = findViewById(R.id.lv1);


    }


    public void BackNow(View v) {
        finish();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemClick1(int position) {

    }

    @Override
    public void onItemClick2(int position) {

    }
}
