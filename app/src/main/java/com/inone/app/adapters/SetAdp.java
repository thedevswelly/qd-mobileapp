package com.inone.app.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.inone.app.R;
import com.inone.app.dto.MenuData;
import com.inone.app.dto.SetDto;
import com.inone.app.interfaces.ItemClickListner;

import org.json.JSONArray;

import java.util.List;
/*
 * adapter for home categories grid
 * with image loading
 * */
public class SetAdp extends RecyclerView.Adapter<SetAdp.MyViewHolder> {

    /*
     * list and activity delaration
     * */
    private List<SetDto> l1;
    private Activity activity;
    private ItemClickListner listner;

    /*
     * view holder class for init View objects from xml layout file
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txt1,txt2;
        public RelativeLayout rl1;
        public ImageView imv;

        public MyViewHolder(View view) {
            super(view);
            txt1 = view.findViewById(R.id.txt1);
            txt2 = view.findViewById(R.id.txt2);
            rl1 = view.findViewById(R.id.rl1);
            imv = view.findViewById(R.id.imv);

        }
    }

    /*
     * constructor for taking data from activity classe
     * */
    public SetAdp(Activity activity, List<SetDto> l1) {
        this.l1 = l1;
        this.activity = activity;
        listner = (ItemClickListner) activity;
    }

    public SetAdp(Activity activity, Fragment fragment, List<SetDto> l1) {
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
                .inflate(R.layout.set_item, parent, false);

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

            SetDto menuData = l1.get(holder.getAdapterPosition());

            holder.imv.setImageResource(menuData.getIcon());

            holder.txt1.setText(menuData.getTitle());
            holder.txt2.setText(menuData.getDesc());

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
        return l1.size();
    }

}