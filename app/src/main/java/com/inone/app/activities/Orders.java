package com.inone.app.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.inone.app.R;
import com.inone.app.adapters.OrderAdp;
import com.inone.app.interfaces.ItemClickListner;
import com.inone.app.other.Const;

import org.json.JSONArray;
import org.json.JSONObject;


public class Orders extends AppCompatActivity implements ItemClickListner {

    private RecyclerView lv1;
    private JSONArray l1 = new JSONArray();
    private JSONObject ldata = null;
    private OrderAdp cartAdp = null;
    private SwipeRefreshLayout swipe1;
    private String status = "";
    private JSONObject pay_settings = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_screen);

        try {
            status = getIntent().getExtras().getString(Const.status);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

            cartAdp = new OrderAdp(Orders.this, l1);

            LinearLayoutManager layoutManager2
                    = new LinearLayoutManager(Orders.this, LinearLayoutManager.VERTICAL, false);
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


    private void initUI() {
        lv1 = findViewById(R.id.lv1);
        swipe1 = findViewById(R.id.swipe1);
        findViewById(R.id.fab2).setVisibility(View.VISIBLE);

    }

    public void BackNow(View v){
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onItemClick(int position) {
        try {
            Intent intent = new Intent(getApplicationContext(), OrderDetails.class);
            intent.putExtra(Const.user_data,l1.getJSONObject(position)+"");
            intent.putExtra(Const.pay_settings,pay_settings+"");
            startActivity(intent);
        } catch (Exception e) {
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
