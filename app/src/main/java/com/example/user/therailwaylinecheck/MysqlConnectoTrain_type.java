package com.example.user.therailwaylinecheck;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MysqlConnectoTrain_type {

    public static ArrayList<HashMap<String, String>> selectAllT_train_type() { //------------ Method to select data ---------------//

        InputStream is_train_type = null;
        String result_train_type = null;
        String address_train_type= "http://student.coe.phuket.psu.ac.th/~s5735512134/dbproject/connect_train_type.php";
        ArrayList<HashMap<String, String>> listTrain_type = new ArrayList<>();
        try
        {
            URL url=new URL(address_train_type);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            is_train_type = new BufferedInputStream(con.getInputStream());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is_train_type,"UTF-8"));
            StringBuilder sb_train_type = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb_train_type.append(line);
            }
            is_train_type.close();
            result_train_type = sb_train_type.toString();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        try {

            final JSONArray jArray = new JSONArray(result_train_type);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo = jArray.getJSONObject(i);
                HashMap<String, String> t_train_type = new HashMap<>();
                t_train_type.put("id", jo.get("id").toString());
                t_train_type.put("name_en", jo.get("name_en").toString());
                t_train_type.put("name_th", jo.get("name_th").toString());
                t_train_type.put("system", jo.get("system").toString());
                Log.e("test",jo.get("name_en").toString());
                listTrain_type.add(t_train_type);
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        return listTrain_type;
    }

}
