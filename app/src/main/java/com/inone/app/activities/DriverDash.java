package com.inone.app.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.inone.app.R;
import com.inone.app.adapters.MenuAdp;
import com.inone.app.dto.MenuData;
import com.inone.app.interfaces.ItemClickListner;
import com.inone.app.other.Const;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DriverDash extends AppCompatActivity  implements ItemClickListner {


    private RecyclerView menu_lv1;
    private List<MenuData> menus = new ArrayList<>();
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private JSONObject ldata = null;
    private TextView txt1,txt2,t0,t1,t2,txt11;
    private SwipeRefreshLayout swipe1;
    private ImageView imv;
    private LocationManager mLocationManager;
    private Location my_location = null;

    private ProgressDialog pd;
    String str[] = new String[]{
            android.Manifest.permission.INTERNET
            , android.Manifest.permission.ACCESS_NETWORK_STATE
            , android.Manifest.permission.ACCESS_FINE_LOCATION
            , android.Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private void requestReadPhoneStatePermission() {
        ActivityCompat.requestPermissions(DriverDash.this, str,
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

    private void initPd() {
        pd = new ProgressDialog(this);
        pd.setCancelable(true);
        pd.setMessage(getString(R.string.loading));
    }
    /*
     * this method connects Ui xml file with java file
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_dash1);

        initPd();
        initUI();
        setMenu();


    }


    public void EditNow(View v){
        startActivity(new Intent(getApplicationContext(),ProfileScreen.class));
    }
    public void ProNow(View v){
        startActivity(new Intent(getApplicationContext(),ProfileScreen.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
    }

    /*
     * navigation top left icon click listner
     * */
    public void BackNow(View v){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }
    private void setMenu() {
        try {
            menus = new ArrayList<>();
            menus.add(new MenuData(getString(R.string.home), R.drawable.home_icon));
            menus.add(new MenuData(getString(R.string.profile), R.drawable.user_icon));
            menus.add(new MenuData(getString(R.string.orders), R.drawable.money_icon));
            menus.add(new MenuData(getString(R.string.logout), R.drawable.logout_icon));


            MenuAdp catAdp = new MenuAdp(DriverDash.this, menus);

            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(DriverDash.this, LinearLayoutManager.VERTICAL, false);
            menu_lv1.setLayoutManager(layoutManager);
            menu_lv1.setItemAnimator(new DefaultItemAnimator());
            menu_lv1.setNestedScrollingEnabled(false);
            menu_lv1.setAdapter(catAdp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * Ui Initialization
     * */
    private void initUI() {

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        imv = findViewById(R.id.imv);
        menu_lv1 = navigationView.getRootView().findViewById(R.id.menu_lv1);
        swipe1 = findViewById(R.id.swipe1);
        t0 = findViewById(R.id.t0);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        txt11 = findViewById(R.id.txt11);


        navigationView = findViewById(R.id.nav_view);


        ((FloatingActionButton)findViewById(R.id.fab1)).setImageResource(R.drawable.nav_icon);

    }

    /*
     * back button click listner
     * */
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")

                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })

                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    public void PenNow(View v){
        Intent intent = new Intent(getApplicationContext(),Orders.class);
        intent.putExtra(Const.status,Const.pending);
        startActivity(intent);
    }
    public void ComNow(View v){
        Intent intent = new Intent(getApplicationContext(),Orders.class);
        intent.putExtra(Const.status,Const.delivered);
        startActivity(intent);
    }
    public void FailedNow(View v){
        Intent intent = new Intent(getApplicationContext(),Orders.class);
        intent.putExtra(Const.status,Const.canceled);
        startActivity(intent);
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
