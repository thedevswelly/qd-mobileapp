package com.inone.app.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inone.app.R;
import com.inone.app.adapters.SetAdp1;
import com.inone.app.adapters.SliderAdp2;
import com.inone.app.dto.SetDto;
import com.inone.app.interfaces.ItemClickListner;
import com.inone.app.other.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class OrderDetails extends AppCompatActivity implements ItemClickListner {

    private Dialog loader;
    private JSONObject ldata = null;
    private JSONObject data = null,data3 = null;

    private JSONArray l1 = new JSONArray();
    private JSONArray drivers = new JSONArray();
    private JSONObject pay_settings = null, offer_dt = null;
    private List<SetDto> l2 = new ArrayList<>();
    private RecyclerView slider, lv1;
    private LinearLayout dots;
    private Timer mTimer1;
    private TimerTask mTt1;
    private Button bt1;
    private int pos = 0;
    private Handler mTimerHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);

        loader = Const.getLoader(OrderDetails.this, getString(R.string.loading));
        try {
            data = new JSONObject(getIntent().getExtras().getString(Const.user_data));
            pay_settings = new JSONObject(getIntent().getExtras().getString(Const.pay_settings));
            offer_dt = new JSONObject(getIntent().getExtras().getString(Const.offer_dt));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        initUI();
        setData();

    }



    private void setColor1() {
        try {
            for (int i = 0; i < l1.length(); i++) {
                RelativeLayout rb1 = dots.findViewById(i).findViewById(R.id.rl1);
                if (i == pos) {
                    ViewCompat.setBackgroundTintList(rb1, ColorStateList.valueOf(getResources().getColor(R.color.app_color)));
                } else {
                    ViewCompat.setBackgroundTintList(rb1, ColorStateList.valueOf(getResources().getColor(R.color.gry)));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        timerDelayRunForScroll(1000);
    }

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


    private void addRadio() {
        try {
            dots.removeAllViews();
            for (int i = 0; i < l1.length(); i++) {
                RelativeLayout tabOne = (RelativeLayout)
                        LayoutInflater.from(this).inflate(R.layout.dot_layout, null);

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

    private void stopTimer() {
        try {
            if (mTimer1 != null) {
                mTimer1.cancel();
                mTimer1.purge();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    private void setData() {
        try {
            try {
                l1 = new JSONArray();
                l1.put("");
                l1.put("");
                l1.put("");
                l1.put("");
                l1.put("");



                addAdp();
                addRadio();
                startTimer();
            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println("srdf----------45---------"+data3);

            l2 = new ArrayList<>();
            l2.add(new SetDto(getString(R.string.order_id), 0, "#JHHDJD89889"));
            l2.add(new SetDto(getString(R.string.delivery_charge), 0, "200 Inr"));
            l2.add(new SetDto(getString(R.string.enter_code), 0, "#jnjjnj"));
            l2.add(new SetDto(getString(R.string.offer_dis), 0,"50 Inr."));
            l2.add(new SetDto(getString(R.string.price), 0, "100 Inr"));
            l2.add(new SetDto(getString(R.string.quantity), 0, "3"));

            l2.add(new SetDto(getString(R.string.payment_st), 0, "COD"));
            l2.add(new SetDto(getString(R.string.address), 0, "fsnfje, sdhabshd, Indore MP 451001"));

            SetAdp1 mainAdp = new SetAdp1(OrderDetails.this, l2);

            LinearLayoutManager layoutManager2
                    = new LinearLayoutManager(OrderDetails.this, LinearLayoutManager.VERTICAL, false);
            lv1.setLayoutManager(layoutManager2);
            lv1.setItemAnimator(new DefaultItemAnimator());
            lv1.setNestedScrollingEnabled(false);
            lv1.setAdapter(mainAdp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addAdp() {
        try {

            SliderAdp2 sliderAdp = new SliderAdp2(this, l1);

            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            slider.setLayoutManager(layoutManager);
            slider.setItemAnimator(new DefaultItemAnimator());
            slider.setNestedScrollingEnabled(false);
            slider.setAdapter(sliderAdp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        slider = findViewById(R.id.slider);
        dots = findViewById(R.id.dots);
        lv1 = findViewById(R.id.lv1);
        bt1 = findViewById(R.id.bt1);

        findViewById(R.id.fab2).setVisibility(View.VISIBLE);
        findViewById(R.id.fab2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(OrderDetails.this, bt1);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                      switch (item.getItemId()){
                          case R.id.pending:

                              break;
                          case R.id.parcel_received:

                              break;
                          case R.id.canceled:

                              break;
                          case R.id.delivered:

                              break;
                      }
                        return true;
                    }
                });

                popup.show();
            }
        });

        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    public void onItemClick1(int position) {
        Intent intent = new Intent(getApplicationContext(), ProductDetails.class);

        startActivity(intent);
    }

    @Override
    public void onItemClick2(int position) {

    }
}
