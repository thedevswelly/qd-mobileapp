package com.inone.app.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.inone.app.R;
import com.inone.app.activities.ProductDetails;
import com.inone.app.other.CustomImageView;


import org.json.JSONArray;
import org.json.JSONObject;

/*
 * adapter for home categories grid
 * with image loading
 * */
public class HlAdp extends RecyclerView.Adapter<HlAdp.MyViewHolder> {

    /*
     * list and activity delaration
     * */
    private JSONArray l1;
    private Activity activity;
    private JSONObject ldata = null;

    /*
     * view holder class for init View objects from xml layout file
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_rt,txt1,txt01,txt2,txt3;
        public CustomImageView location_icon;
        public RelativeLayout rl1;
        public ImageView cart_icon;


        public MyViewHolder(View view) {
            super(view);

            txt_rt = view.findViewById(R.id.txt_rt);
            txt1 = view.findViewById(R.id.txt1);
            txt01 = view.findViewById(R.id.txt01);
            txt2 = view.findViewById(R.id.txt2);
            txt3 = view.findViewById(R.id.txt3);
            rl1 = view.findViewById(R.id.rl1);
            cart_icon = view.findViewById(R.id.cart_icon);
            location_icon = view.findViewById(R.id.location_icon);
        }
    }

    /*
     * constructor for taking data from activity classe
     * */
    public HlAdp(Activity activity, JSONArray l1) {
        this.l1 = l1;
        this.activity = activity;


    }

    public HlAdp(Activity activity, Fragment fragment, JSONArray l1) {
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
                .inflate(R.layout.hl_item, parent, false);

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





            holder.rl1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity.getApplicationContext(), ProductDetails.class);


                    activity.startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return l1.length();
    }

}