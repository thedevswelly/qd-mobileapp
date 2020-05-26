package com.inone.app.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.inone.app.R;
import com.inone.app.interfaces.ItemClickListner;


import org.json.JSONArray;
import org.json.JSONObject;

/*
 * adapter for home categories grid
 * with image loading
 * */
public class SliderAdp extends RecyclerView.Adapter<SliderAdp.MyViewHolder> {

    /*
     * list and activity delaration
     * */
    private JSONArray l1;
    private Activity activity;

    /*
     * view holder class for init View objects from xml layout file
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imv;

        public MyViewHolder(View view) {
            super(view);
            imv = view.findViewById(R.id.imv);
        }
    }

    /*
     * constructor for taking data from activity classe
     * */
    public SliderAdp(Activity activity, JSONArray l1) {
        this.l1 = l1;
        this.activity = activity;

    }

    public SliderAdp(Activity activity, Fragment fragment, JSONArray l1) {
        this.l1 = l1;
        this.activity = activity;

    }
    /*
     * connecting layout xml file with java file
     * connecting item layout with adapter
     * */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slide_item, parent, false);

        return new MyViewHolder(itemView);
    }
    /*
     *
     * adding click listner to view
     * and setting values from list to view
     * */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return l1.length();
    }

}