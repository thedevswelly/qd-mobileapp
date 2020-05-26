package com.inone.app.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.inone.app.R;
import com.inone.app.activities.AddressScreen;
import com.inone.app.adapters.CartAdp;
import com.inone.app.interfaces.ItemClickListner;
import com.inone.app.other.Const;

import org.json.JSONArray;
import org.json.JSONObject;

public class Exchange extends Fragment implements ItemClickListner {

    private Activity activity;
    private View v = null;
    private RecyclerView lv1;
    private JSONArray l1 = new JSONArray();
    private JSONObject ldata = null;
    private CartAdp cartAdp = null;
    private SwipeRefreshLayout swipe1;
    private Button bt1;

    public Exchange() {
    }

    public Exchange(Activity activity) {
        this.activity = activity;

    }

    private void initUI() {
        lv1 = v.findViewById(R.id.lv1);
        swipe1 = v.findViewById(R.id.swipe1);
        bt1 = v.findViewById(R.id.bt1);

        swipe1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.getApplicationContext(), AddressScreen.class);
                intent.putExtra(Const.rtype,1);
                startActivity(intent);
            }
        });
    }
    private void addAdp(){
        if(swipe1!=null
                && swipe1.isRefreshing()){
            swipe1.setRefreshing(false);
        }
        if(l1!=null
        && l1.length()>0){
            bt1.setVisibility(View.VISIBLE);
        }
        else{

            bt1.setVisibility(View.GONE);
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

            cartAdp = new CartAdp(activity, this, l1);

            LinearLayoutManager layoutManager2
                    = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
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
            });
        }
        else{
            cartAdp.setL1(l1);
            cartAdp.notifyDataSetChanged();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_exch, container, false);


        initUI();
        addAdp();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onItemClick(int position) {
        try {
            if(cartAdp!=null) {


                l1.remove(position);
                cartAdp.setL1(l1);
                cartAdp.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick1(int position) {
        try {
            if(cartAdp!=null) {

                cartAdp.setL1(l1);
                cartAdp.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick2(int position) {
        try {
            if(cartAdp!=null) {
                cartAdp.setL1(l1);
                cartAdp.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
