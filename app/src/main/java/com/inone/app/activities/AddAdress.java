package com.inone.app.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inone.app.R;
import com.inone.app.other.Const;

import org.json.JSONObject;


public class AddAdress extends AppCompatActivity {

    private EditText et1,et2,et3,et4,et5,et7;
    private TextView et6;
    private Dialog loader = null;
    private JSONObject ldata = null,data = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address);

//        try {
//            data = new JSONObject(getIntent().getExtras().getString(Const.data));
//        } catch (Exception e) {
//            e.printStackTrace();
//            data = null;
//        }
        loader = Const.getLoader(AddAdress.this,getString(R.string.loading));
        initUI();
        setData();
    }

    private void setData() {

    }

    private void initUI() {
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);
        et7 = findViewById(R.id.et7);

        et6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(AddAdress.this, et6);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.pin_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.pin1:
                               et6.setText("731101");
                                break;
                            case R.id.pin2:
                                et6.setText("731102");
                                break;
                            case R.id.pin3:
                                et6.setText("731103");
                                break;

                        }
                        return true;
                    }
                });

                popup.show();
            }
        });
    }
    public void NextNow(View v){
        finish();
    }

    public void BackNow(View v){
        finish();
    }

    @Override
    public void onBackPressed() {
        BackNow(null);
    }
}
