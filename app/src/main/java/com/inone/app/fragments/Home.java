package com.inone.app.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.inone.app.R;
import com.inone.app.activities.Vendors;
import com.inone.app.adapters.CatsAdp;
import com.inone.app.adapters.MainAdp;
import com.inone.app.adapters.SliderAdp;
import com.inone.app.interfaces.ItemClickListner;


import org.json.JSONArray;

import java.util.Timer;
import java.util.TimerTask;

public class Home extends Fragment implements ItemClickListner {

    private Activity activity;
    private View v = null;
    private RecyclerView slider,lv1,cats;
    private LinearLayout dots;
    private JSONArray l1 = new JSONArray();
    private JSONArray banners = new JSONArray();
    private JSONArray categories = new JSONArray();
    private Timer mTimer1;
    private TimerTask mTt1;
    private int pos = 0;
    private Handler mTimerHandler = new Handler();
    private NestedScrollView scroll1;
    private SwipeRefreshLayout swipe1;



    public Home() {
    }

    public Home(Activity activity) {
        this.activity = activity;

    }
    /*
     * Stoping slider animation
     * */
    private void stopTimer() {
        try {
            if (mTimer1 != null) {
                mTimer1.cancel();
                mTimer1.purge();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * Starting slider animation
     * */
    private void startTimer() {
        stopTimer();
        mTimer1 = new Timer();
        mTt1 = new TimerTask() {
            public void run() {
                mTimerHandler.post(new Runnable() {
                    public void run() {
                        if (pos < (l1.length() - 1)) {
                            pos++;
                        } else {
                            pos = 0;
                        }
                        setColor1();
                    }
                });
            }
        };

        mTimer1.schedule(mTt1, 1, 3000);
    }
    private void setColor1() {
        try {
            for (int i = 0; i < banners.length(); i++) {
                RelativeLayout rb1 = dots.findViewById(i).findViewById(R.id.rl1);
                if (i == pos) {
                    ViewCompat.setBackgroundTintList(rb1, ColorStateList.valueOf(activity.getResources().getColor(R.color.app_color)));
                } else {
                    ViewCompat.setBackgroundTintList(rb1, ColorStateList.valueOf(activity.getResources().getColor(R.color.gry)));
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        timerDelayRunForScroll(1000);
    }
    /*
     * changing image in slider slider animation
     * */
    public void timerDelayRunForScroll(long time) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    slider.smoothScrollToPosition(pos);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, time);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       v = inflater.inflate(R.layout.fragment_home, container, false);
        initUI();

        addAdp();

        addRadio();
        startTimer();
        addAdp1();
        addAdp2();
        return v;
    }





    private void addRadio() {
        try {
            dots.removeAllViews();
            for (int i = 0; i < banners.length(); i++) {
                RelativeLayout tabOne = (RelativeLayout)
                        LayoutInflater.from(activity).inflate(R.layout.dot_layout, null);

                tabOne.setId(i);
                dots.addView(tabOne);
                tabOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pos = v.getId();
                        timerDelayRunForScroll(1000);
                    }
                });
            }
            pos = 0;
            setColor1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        slider = v.findViewById(R.id.slider);
        cats = v.findViewById(R.id.cats);
        lv1 = v.findViewById(R.id.lv1);
        dots = v.findViewById(R.id.dots);
        scroll1 = v.findViewById(R.id.scroll1);
        swipe1 = v.findViewById(R.id.swipe1);
    }

    private void addAdp() {
        try {
            if(swipe1!=null && swipe1.isRefreshing()){
                swipe1.setRefreshing(false);
            }
            banners = new JSONArray();
            banners.put("");
            banners.put("");
            banners.put("");
            banners.put("");
            banners.put("");
            banners.put("");
            banners.put("");
            banners.put("");

            SliderAdp sliderAdp = new SliderAdp(activity,this,banners);

            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
            slider.setLayoutManager(layoutManager);
            slider.setItemAnimator(new DefaultItemAnimator());
            slider.setNestedScrollingEnabled(false);
            slider.setAdapter(sliderAdp);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addAdp1(){

        categories = new JSONArray();
        categories.put("");
        categories.put("");
        categories.put("");
        categories.put("");
        categories.put("");
        categories.put("");
        categories.put("");
        categories.put("");
        categories.put("");

        CatsAdp catsAdp = new CatsAdp(activity,this,categories);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        cats.setLayoutManager(layoutManager1);
        cats.setItemAnimator(new DefaultItemAnimator());
        cats.setNestedScrollingEnabled(false);
        cats.setAdapter(catsAdp);
    }

    private void addAdp2(){
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

        MainAdp mainAdp = new MainAdp(activity,this,l1);

        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        lv1.setLayoutManager(layoutManager2);
        lv1.setItemAnimator(new DefaultItemAnimator());
        lv1.setNestedScrollingEnabled(false);
        lv1.setAdapter(mainAdp);

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(activity.getApplicationContext(), Vendors.class);


        startActivity(intent);
    }

    @Override
    public void onItemClick1(int position) {

    }

    @Override
    public void onItemClick2(int position) {

    }
}
