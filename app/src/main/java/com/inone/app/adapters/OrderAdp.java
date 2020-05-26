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
import com.inone.app.other.Const;


import org.json.JSONArray;
import org.json.JSONObject;

/*
 * adapter for home categories grid
 * with image loading
 * */
public class OrderAdp extends RecyclerView.Adapter<OrderAdp.MyViewHolder> {

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

        public RecyclerView rlv1;
        public ImageView location_icon;
        public TextView txt1,txt2,txt3;
        public ProgressBar pb1;
        public RelativeLayout rl1;


        public MyViewHolder(View view) {
            super(view);
            rlv1 = view.findViewById(R.id.rlv1);
            location_icon = view.findViewById(R.id.location_icon);
            txt1 = view.findViewById(R.id.txt1);
            txt2 = view.findViewById(R.id.txt2);
            txt3 = view.findViewById(R.id.txt3);
            rl1 = view.findViewById(R.id.rl1);
            pb1 = view.findViewById(R.id.pb1);
        }
    }

    /*
     * constructor for taking data from activity classe
     * */
    public OrderAdp(Activity activity, JSONArray l1) {
        this.l1 = l1;
        this.activity = activity;
        listner = (ItemClickListner) activity;
    }

    public OrderAdp(Activity activity, Fragment fragment, JSONArray l1) {
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
                .inflate(R.layout.order_item, parent, false);

        return new MyViewHolder(itemView);
    }

    public JSONArray getL1() {
        return l1;
    }

    public void setL1(JSONArray l1) {
        this.l1 = l1;
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
                    listner.onItemClick(holder.getAdapterPosition());
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