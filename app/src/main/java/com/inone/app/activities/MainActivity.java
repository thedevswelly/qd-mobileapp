package com.inone.app.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import com.inone.app.R;
import com.inone.app.adapters.MenuAdp;
import com.inone.app.adapters.ViewPagerAdapter;
import com.inone.app.dto.MenuData;
import com.inone.app.fragments.Exchange;
import com.inone.app.fragments.Home;
import com.inone.app.fragments.Offers;
import com.inone.app.fragments.SettingFrag;
import com.inone.app.interfaces.ItemClickListner;
import com.inone.app.other.Const;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListner {

    private static final int LOCATION_REFRESH_TIME = 100;
    private static final int LOCATION_REFRESH_DISTANCE = 100;
    private RecyclerView menu_lv1;
    private List<MenuData> menus = new ArrayList<>();
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private JSONObject ldata = null;
    private ViewPager vp1;
    private LocationManager mLocationManager;
    private Location my_location = null;
    private TabLayout tabs;
    private Exchange fragment;
    private int str[] = {
            R.drawable.tab1,
            R.drawable.cart_icon,
            R.drawable.offer_icon,
            R.drawable.user_icon
    };
    private int str1[] = {
            R.string.home,
            R.string.cart,
            R.string.offers,
            R.string.account
    };
    String str2[] = new String[]{
            android.Manifest.permission.INTERNET
            , android.Manifest.permission.ACCESS_NETWORK_STATE
            , android.Manifest.permission.ACCESS_FINE_LOCATION
            , android.Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private void requestReadPhoneStatePermission() {
        ActivityCompat.requestPermissions(MainActivity.this, str2,
                1);
    }

    private boolean checkIfAlreadyhavePermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            return true;
        } else {
            return false;
        }
    }

    public void NavNow(View v) {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                LOCATION_REFRESH_DISTANCE, mLocationListener);

        initUI();
        setMenu();
        setupViewPager();
        tabs.setupWithViewPager(vp1);
        vp1.setOffscreenPageLimit(4);
        setupTabs();
    }
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    private void setMenu() {
        try {
            menus = new ArrayList<>();
            menus.add(new MenuData(getString(R.string.home), R.drawable.home_icon));
            menus.add(new MenuData(getString(R.string.profile), R.drawable.user_icon));
            menus.add(new MenuData(getString(R.string.orders), R.drawable.money_icon));
            menus.add(new MenuData(getString(R.string.favorites), R.drawable.fav_icon));
            menus.add(new MenuData(getString(R.string.address), R.drawable.location_icon));
            menus.add(new MenuData(getString(R.string.logout), R.drawable.logout_icon));


            MenuAdp catAdp = new MenuAdp(MainActivity.this, menus);

            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
            menu_lv1.setLayoutManager(layoutManager);
            menu_lv1.setItemAnimator(new DefaultItemAnimator());
            menu_lv1.setNestedScrollingEnabled(false);
            menu_lv1.setAdapter(catAdp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setupViewPager() {
        fragment = new Exchange(this);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Home(this), "home");
        adapter.addFragment(fragment, "exch");
        adapter.addFragment(new Offers(this), "offer");
        adapter.addFragment(new SettingFrag(this), "sett");

        vp1.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();


    }


    public void ProNow(View v){
        startActivity(new Intent(getApplicationContext(), ProfileScreen.class));
    }


    public void SrchNow(View v){
        startActivity(new Intent(getApplicationContext(), SearchScreen.class));
    }
    private void setupTabs() {
        for (int i = 0; i < str.length; i++) {
            RelativeLayout tabOne = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab1, null);
            ImageView imv = tabOne.findViewById(R.id.imv);
            ImageView imv1 = tabOne.findViewById(R.id.imv1);
            TextView txt = tabOne.findViewById(R.id.txt);
            txt.setText(getString(str1[i]));
            imv.setImageResource(str[i]);
            imv1.setImageResource(str[i]);

            imv.setVisibility(View.VISIBLE);
            imv1.setVisibility(View.GONE);

            tabs.getTabAt(i).setCustomView(tabOne);

        }

        setSelection(0);
    }



    public void setSelection(int selection) {
        try {
            for (int i = 0; i < str1.length; i++) {
                RelativeLayout tabOne = (RelativeLayout) tabs.getTabAt(i).getCustomView();
                ImageView imv = tabOne.findViewById(R.id.imv);
                ImageView imv1 = tabOne.findViewById(R.id.imv1);
                TextView txt = tabOne.findViewById(R.id.txt);
                if (i == selection) {
                    imv.setVisibility(View.GONE);
                    imv1.setVisibility(View.VISIBLE);
                    txt.setTextColor(getResources().getColor(R.color.app_color));
                } else {
                    imv.setVisibility(View.VISIBLE);
                    imv1.setVisibility(View.GONE);
                    txt.setTextColor(getResources().getColor(R.color.dgry));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    private void initUI() {
        tabs = findViewById(R.id.tabs);
        vp1 = findViewById(R.id.vp1);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        menu_lv1 = navigationView.getRootView().findViewById(R.id.menu_lv1);

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setSelection(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this,R.style.MyAlertDialogStyle)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")

                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })

                    .setNegativeButton(R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemClick1(int position) {
        try {
            MenuData menuData = menus.get(position);

            if (menuData.getTitle().equals(getString(R.string.home))) {

            } else if (menuData.getTitle().equals(getString(R.string.profile))) {
                startActivity(new Intent(getApplicationContext(), ProfileScreen.class));
            } else if (menuData.getTitle().equals(getString(R.string.orders))) {
                startActivity(new Intent(getApplicationContext(), Orders.class));
            }
            else if (menuData.getTitle().equals(getString(R.string.address))) {
                Intent intent = new Intent(getApplicationContext(), AddressScreen.class);
                intent.putExtra(Const.rtype,0);
                startActivity(intent);
            }
            else if (menuData.getTitle().equals(getString(R.string.favorites))) {
                startActivity(new Intent(getApplicationContext(), FavScreen.class));
            }
             else if (menuData.getTitle().equals(getString(R.string.logout))) {
                new AlertDialog.Builder(this,R.style.MyAlertDialogStyle)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to logout?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                                finish();
                            }
                        })

                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onItemClick2(int position) {

    }
}
