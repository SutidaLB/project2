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

public class MysqlConnectoTrain_line {

    public static ArrayList<HashMap<String, String>> selectAllT_train_line() { //------------ Method to select data ---------------//

        InputStream is_train_line = null;
        String result_train_line = null;
        String address_train_line= "http://student.coe.phuket.psu.ac.th/~s5735512134/dbproject/connect_train_line.php";
        ArrayList<HashMap<String, String>> listTrain_line = new ArrayList<>();
        try
        {
            URL url=new URL(address_train_line);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            is_train_line = new BufferedInputStream(con.getInputStream());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is_train_line,"UTF-8"));
            StringBuilder sb_train_line = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb_train_line.append(line);
            }
            is_train_line.close();
            result_train_line = sb_train_line.toString();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        try {

            final JSONArray jArray = new JSONArray(result_train_line);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo = jArray.getJSONObject(i);
                HashMap<String, String> t_train_line = new HashMap<>();
                t_train_line.put("id", jo.get("id").toString());
                t_train_line.put("name_en", jo.get("name_en").toString());
                t_train_line.put("name_th", jo.get("name_th").toString());
                t_train_line.put("system", jo.get("system").toString());
                Log.e("test",jo.get("name_en").toString());
                listTrain_line.add(t_train_line);
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        return listTrain_line;
    }

}
