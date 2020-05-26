package com.inone.app.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.inone.app.R;
import com.inone.app.interfaces.ItemClickListner;


import org.json.JSONArray;

/*
 * adapter for home categories grid
 * with image loading
 * */
public class CatsAdp1 extends RecyclerView.Adapter<CatsAdp1.MyViewHolder> {

    /*
     * list and activity delaration
     * */
    private JSONArray l1;
    private Activity activity;
    private ItemClickListner listner;


    /*
     * view holder class for init View objects from xml layout file
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txt1;
        public ImageView right_icon;
        public RelativeLayout rl1;
        public RecyclerView lv1;
        public RelativeLayout lv1_rl;
        public ProgressBar pb1;

        public MyViewHolder(View view) {
            super(view);
            txt1 = view.findViewById(R.id.txt1);
            rl1 = view.findViewById(R.id.rl1);
            right_icon = view.findViewById(R.id.right_icon);
            lv1 = view.findViewById(R.id.lv1);
            lv1_rl = view.findViewById(R.id.lv1_rl);
            pb1 = view.findViewById(R.id.pb1);
        }
    }

    /*
     * constructor for taking data from activity classe
     * */
    public CatsAdp1(Activity activity, JSONArray l1) {
        this.l1 = l1;
        this.activity = activity;
        listner = (ItemClickListner) activity;

    }

    public CatsAdp1(Activity activity, Fragment fragment, JSONArray l1) {
        this.l1 = l1;
        this.activity = activity;
        listner = (ItemClickListner) fragment;
    }
    /*
     * connecting layout xml file with java file
     * connecting item layout with adapter
     * */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cats_item1, parent, false);

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