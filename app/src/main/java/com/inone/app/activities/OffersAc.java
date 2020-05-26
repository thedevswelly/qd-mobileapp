package com.inone.app.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.inone.app.R;
import com.inone.app.adapters.OffAdp;
import com.inone.app.interfaces.ItemClickListner;
import com.inone.app.other.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class OffersAc extends AppCompatActivity implements ItemClickListner {

    private RecyclerView lv1;
    private Dialog loader;
    private JSONObject ldata = null;
    private JSONArray l1 = new JSONArray();
    private SwipeRefreshLayout swipe1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_offers);

        loader = Const.getLoader(OffersAc.this,getString(R.string.loading));


        initUI();
       addAdp();
    }

    private void addAdp() {
        try {
            if(swipe1.isRefreshing()){
                swipe1.setRefreshing(false);
            }
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

            OffAdp catAdp = new OffAdp(OffersAc.this,l1);

            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(OffersAc.this, LinearLayoutManager.VERTICAL, false);
            lv1.setLayoutManager(layoutManager);
            lv1.setItemAnimator(new DefaultItemAnimator());
            lv1.setNestedScrollingEnabled(false);
            lv1.setAdapter(catAdp);
            lv1.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    int topRowVerticalPosition =
                            (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                    swipe1.setEnabled(topRowVerticalPosition >= 0);

                }
            });

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void initUI() {
        lv1 = findViewById(R.id.lv1);
        swipe1 = findViewById(R.id.swipe1);
    }


    public void BackNow(View v){
        finish();
    }

    @Override
    public void onItemClick(int position) {
        try {
            JSONObject jk = l1.getJSONObject(position);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick1(int position) {

    }

    @Override
    public void onItemClick2(int position) {

    }
}
