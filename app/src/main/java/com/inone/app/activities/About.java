package com.inone.app.activities;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inone.app.R;
import com.inone.app.other.Const;

import org.json.JSONObject;


/**
 * Created by Roshani
 * its about screen or we can say content screen where we will show content
 * category wise like terms and condition ,about us etc
 */

public class About extends AppCompatActivity {

    /*
    * init Ui in class
    *
    * */
    private WebView wv1;
    private TextView title_txt;
    public void BackNow(View v){
        finish();
    }
    private String title = "";

    /*
    * this method connects Ui xml file with java file
    * */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        title = getIntent().getExtras().getString(Const.title);
        initUI();
      setData(null);
    }


    private void setData(JSONObject jk) {
        try{
            title_txt.setText(title);

            String str = "<html>" +
                    "<body bgcolor=\"#ffffff\" text=\"#4a4949\" style=\"text-align: justify;\">" +
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English."+
                    "</body>" +
                    "</html>";

            wv1.loadDataWithBaseURL(null,str,"text/html", "utf-8", null);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    * loading content in Webview because text align justify doesnot works in android
    * and this code is supportable for all devices 4.4 to max
    * */
    private void initUI() {
        title_txt = findViewById(R.id.title_txt);
        wv1 =  findViewById(R.id.wv1);
        title_txt.setText(title);
    }

}
