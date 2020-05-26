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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.inone.app.R;
import com.inone.app.interfaces.ItemClickListner;


import org.json.JSONArray;
import org.json.JSONObject;

/*
 * adapter for home categories grid
 * with image loading
 * */
public class MainAdp extends RecyclerView.Adapter<MainAdp.MyViewHolder> {

    /*
     * list and activity declaration
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
        public TextView txt1,txt2;
        public ProgressBar pb1;
        public RelativeLayout rl1;


        public MyViewHolder(View view) {
            super(view);
            rlv1 = view.findViewById(R.id.rlv1);
            location_icon = view.findViewById(R.id.location_icon);
            txt1 = view.findViewById(R.id.txt1);
            txt2 = view.findViewById(R.id.txt2);
            rl1 = view.findViewById(R.id.rl1);
            pb1 = view.findViewById(R.id.pb1);
        }
    }

    /*
     * constructor for taking data from activity classes
     * */
    public MainAdp(Activity activity, JSONArray l1) {
        this.l1 = l1;
        this.activity = activity;
        listner = (ItemClickListner) activity;
    }

    public MainAdp(Activity activity, Fragment fragment, JSONArray l1) {
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
                .inflate(R.layout.main_item, parent, false);

        return new MyViewHolder(itemView);
    }
    /*
     *
     * adding click listener to view
     * and setting values from list to view
     * */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        try {
            makeApiCall(holder.pb1,holder.rlv1,null);



            holder.txt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.location_icon.performClick();
                }
            });
            holder.txt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.location_icon.performClick();
                }
            });
            holder.location_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onItemClick(holder.getAdapterPosition());
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void makeApiCall(final ProgressBar pb1,final RecyclerView lv1,JSONObject jk) {
        try {

            JSONArray jarr = new JSONArray();
            jarr.put("");
            jarr.put("");
            jarr.put("");
            jarr.put("");
            jarr.put("");
            jarr.put("");
            jarr.put("");
            jarr.put("");

            addAdp(jarr,lv1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addAdp(JSONArray jarr, RecyclerView lv1) {
        try {
            HlAdp catsAdp = new HlAdp(activity, jarr);

            lv1.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
            lv1.setItemAnimator(new DefaultItemAnimator());
            lv1.setNestedScrollingEnabled(false);
            lv1.setAdapter(catsAdp);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return l1.length();
    }

}