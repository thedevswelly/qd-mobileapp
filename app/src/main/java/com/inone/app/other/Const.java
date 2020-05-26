package com.inone.app.other;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;


import com.inone.app.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Const {
    private static final int INTSTRIAL = 0;
    private static final int REWARDED = 1;
    private static final int NATIVE = 2;
    private static final int DAILYREWARD = 3;
    public static String spName = "my_sp_dfsdkgdkmg231";
    public static String api_version = "api_version";
    public static String api_secrate = "45JHJHJHJ";
    public static String baseUrl = "http://inone.co.in/index.php/apis/";
    public static String imgBaseUrl = "http://inone.co.in/";
    public static String mob_details = "mob_details";
    public static String resendCode = "resendCode";
    public static String singupUrl = "signup_now";
    public static String status = "status";
    public static String message = "message";
    public static String user_data = "user_data";
    public static String ldata = "ldata";
    public static String loginUrl = "login";
    public static String loginUrl1 = "login1";
    public static String records = "records";
    public static String getBannersUrl = "get_banners";
    public static String getCatsUrl = "get_cats";
    public static String getProByCatUrl = "get_products";
    public static String limit = "limit";
    public static String title = "title";
    public static String rtype = "rtype";
    public static String updateTokenUrl = "token_update";
    public static String getOffersUrl = "get_offers";
    public static String proUpUrl = "profile_update";
    public static String addToCartUrl = "add_to_cart";
    public static String getCartUrl = "get_cart";
    public static String delCartItemUrl = "del_cart_fav";
    public static String getOrderUrl = "get_orders";
    public static String getAddUrl =  "get_addresses";
    public static String data = "data";
    public static String addAddressUrl = "add_address";
    public static String cheoutUrl = "checkout_now";
    public static String cod = "Cash On Delivery";
    public static String pay_pending = "Payment Pending";
    public static String pay_completed = "Payment Completed";
    public static String searchQueryUrl = "search_products";
    public static String search_query = "search_query";
    public static String is_update = "is_update";
    public static String getDrDashUrl = "load_dash_driver";
    public static String pending = "Proceed";
    public static String delivered = "Delivered";
    public static String canceled = "Canceled";
    public static String driver = "biker";
    public static String updateOrderDtUrl = "update_order_st";
    public static String getUsersUrl = "get_Users";
    public static String updateUserStUrl = "update_user_st";
    public static String vendor = "vendor";
    public static String updateOrderDriverUrl = "update_order_driver";
    public static String getVenDashUrl = "load_vendor_driver";
    public static String drivers = "Drivers";
    public static String getVensUrl = "get_vendors";
    public static String vendorId = "vendorId";
    public static String updateLocUrl = "update_lat_lon";
    public static String show_cart = "show_cart";
    public static String parcel_received = "Parcel Received";
    public static String pay_settings = "pay_settings";
    public static String getOfferUrl = "get_offers";
    public static String getVenCatsUrl = "get_vendor_cat";
    public static String offer_dt = "offer_dt";

    public static Dialog getLoader(Activity activity,String msg){
        Dialog dialog = null;
        try{
            dialog = new Dialog(activity);
            dialog.setCancelable(true);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.my_pro_bar);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            TextView txt_msg = dialog.findViewById(R.id.txt_msg);
            txt_msg.setText(msg);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return dialog;
    }

    public static String getStrAddress(Activity activity, Location location) {
        try{
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(activity, Locale.getDefault());

            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String str = "";

            String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();

            if(knownName!=null){
                str = str+""+knownName;
            }

            if(address!=null){
                str = str+", "+address;
            }

            if(city!=null){
                str = str+", "+city;
            }

            if(state!=null){
                str = str+", "+state;
            }

            if(country!=null){
                str = str+", "+country;
            }

            if(postalCode!=null){
                str = str+", "+postalCode;
            }

            return str;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
    public static boolean isOnline(Activity activity) {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            Toast.makeText(activity.getApplicationContext(),activity.getString(R.string.intr_msg),5).show();
            return false;
        }
    }

    public static byte[] getBytes(File file) {
        byte[] bytes = null;
        try {
            int size = (int) file.length();
            bytes = new byte[size];
            try {
                BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
                buf.read(bytes, 0, bytes.length);
                buf.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;

    }

    public static JSONArray addInList(JSONArray jsonArray, JSONArray products) {
        try{
            for(int i=0;i<jsonArray.length();i++){
                products.put(jsonArray.getJSONObject(i));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static String getBase64(String str) {
        try {
            String data = Base64.encodeToString(str.getBytes("UTF-8"), Base64.DEFAULT).replaceAll("\n", "");
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getLocalTime(long time) {
        try {
            Date local = new Date((long) time);
            final CharSequence cur_date2 = DateFormat.format("dd-MM-yyyy", local.getTime());

            return cur_date2.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static long getUtcTime() {

        Date date = new Date();
        return date.getTime();
    }

    public static void playNotificationSound(Context context,int noti_type) {
        try {
            Uri soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + context.getPackageName() + "/" + noti_type);

            try {
                Ringtone r = RingtoneManager.getRingtone(context, soundUri);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
                Ringtone r = RingtoneManager.getRingtone(context, soundUri);
                r.play();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getStrFrmBase64(String str) {
        try {
            return new String(Base64.decode(str, Base64.DEFAULT), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        }
        return str;
    }

    public static File getFile(Activity activity, String path,String filename) {
        File file = null;
        try{
            Bitmap bmThumbnail = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Images.Thumbnails.MINI_KIND);
            file = new File(activity.getCacheDir(), filename);
            file.createNewFile();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bmThumbnail.compress(Bitmap.CompressFormat.PNG, 100 , bos);
            byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return file;
    }
    public static String getDeviceId(Activity activity){
        String android_id = Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        return android_id;
    }



    public static String[] getImg(String string) {
        String str[] = {string};
        try{
            if(string.contains(",")){
                str = string.trim().split(",");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return str;
    }


}
