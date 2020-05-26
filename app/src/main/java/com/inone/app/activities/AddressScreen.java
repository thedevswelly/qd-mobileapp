package com.inone.app.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.inone.app.R;
import com.inone.app.adapters.AddAdp;
import com.inone.app.other.Const;

import org.json.JSONArray;
import org.json.JSONObject;


public class AddressScreen extends AppCompatActivity {

    private RecyclerView lv1;
    private JSONArray l1 = new JSONArray();
    private JSONObject ldata = null;
    private AddAdp cartAdp = null;
    private SwipeRefreshLayout swipe1;
    private Dialog loader;
    private int rtype = 0;
    private Dialog rdialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_screen);
        loader = Const.getLoader(AddressScreen.this,getString(R.string.loading));

        initUI();
        addAdp();
    }
    private void addAdp(){
        if(swipe1!=null
                && swipe1.isRefreshing()){
            swipe1.setRefreshing(false);
        }
        if(cartAdp==null) {
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

            cartAdp = new AddAdp(AddressScreen.this, l1);

            LinearLayoutManager layoutManager2
                    = new LinearLayoutManager(AddressScreen.this, LinearLayoutManager.VERTICAL, false);
            lv1.setLayoutManager(layoutManager2);
            lv1.setItemAnimator(new DefaultItemAnimator());
            lv1.setNestedScrollingEnabled(false);
            lv1.setAdapter(cartAdp);
            lv1.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    int topRowVerticalPosition =
                            (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();

                    swipe1.setEnabled(topRowVerticalPosition >= 0);
                }

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);


                }
            });
        }
        else{
            cartAdp.setL1(l1);
            cartAdp.notifyDataSetChanged();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initUI() {
        lv1 = findViewById(R.id.lv1);
        swipe1 = findViewById(R.id.swipe1);

//        swipe1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                makeApiCall();
//            }
//        });
    }

    public void BackNow(View v){
        finish();
    }



    public void AddNow(View v){
        try {
            Intent intent = new Intent(getApplicationContext(), AddAdress.class);
//            intent.putExtra(Const.data, l1.getJSONObject(position) + "");
            startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
