package com.inone.app.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.inone.app.R;
import com.inone.app.activities.LoginScreen;
import com.inone.app.activities.OffersAc;
import com.inone.app.activities.ProfileScreen;
import com.inone.app.adapters.SetAdp;
import com.inone.app.dto.SetDto;
import com.inone.app.interfaces.ItemClickListner;
import com.inone.app.other.Const;

import java.util.ArrayList;
import java.util.List;

public class SettingFrag extends Fragment implements ItemClickListner {

    private Activity activity;
    private View v = null;
    private RecyclerView lv1;
    private LinearLayout dots;
    private List<SetDto> l1 = new ArrayList<>();

    public SettingFrag() {
    }

    public SettingFrag(Activity activity) {
        this.activity = activity;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_sett, container, false);
        initUI();

        addAdp();
        return v;
    }

    private void initUI() {
        lv1 = v.findViewById(R.id.lv1);
    }

    private void addAdp() {
        try {

           l1 = new ArrayList<>();
            l1.add(new SetDto(getString(R.string.profile),R.drawable.user_icon,getString(R.string.profile_msg)));
//            l1.add(new SetDto(getString(R.string.change_pass),R.drawable.password_icon,getString(R.string.change_pass_msg)));
            l1.add(new SetDto(getString(R.string.offers),R.drawable.offer_icon,getString(R.string.offers_msg)));
            l1.add(new SetDto(getString(R.string.feedback),R.drawable.fav_icon,getString(R.string.feedback_msg)));
            l1.add(new SetDto(getString(R.string.logout),R.drawable.logout_icon,getString(R.string.logout_msg)));



            SetAdp mainAdp = new SetAdp(activity,this,l1);

            LinearLayoutManager layoutManager2
                    = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
            lv1.setLayoutManager(layoutManager2);
            lv1.setItemAnimator(new DefaultItemAnimator());
            lv1.setNestedScrollingEnabled(false);
            lv1.setAdapter(mainAdp);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int position) {
        if(l1.get(position).getTitle().equals(getString(R.string.profile))){
            startActivity(new Intent(activity.getApplicationContext(), ProfileScreen.class));
        }
        else if(l1.get(position).getTitle().equals(getString(R.string.offers))){
            startActivity(new Intent(activity.getApplicationContext(), OffersAc.class));
        }
        else if(l1.get(position).getTitle().equals(getString(R.string.feedback))){

        }
        else if(l1.get(position).getTitle().equals(getString(R.string.logout))){
            new AlertDialog.Builder(activity)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")

                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(new Intent(activity.getApplicationContext(), LoginScreen.class));
                            activity.finish();
                        }
                    })

                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    @Override
    public void onItemClick1(int position) {

    }

    @Override
    public void onItemClick2(int position) {

    }
}
