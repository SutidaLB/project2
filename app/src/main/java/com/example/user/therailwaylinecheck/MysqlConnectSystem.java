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

public class MysqlConnectSystem {

    public static ArrayList<HashMap<String, String>> selectAllT_system() { //------------ Method to select data ---------------//

        InputStream is_system = null;
        String result_system = null;
        String address_system= "http://student.coe.phuket.psu.ac.th/~s5735512134/dbproject/connect_system.php";
        ArrayList<HashMap<String, String>> listSystem = new ArrayList<>();
        try
        {
            URL url=new URL(address_system);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            is_system=new BufferedInputStream(con.getInputStream());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is_system,"UTF-8"));
            StringBuilder sb_system = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb_system.append(line);
            }
            is_system.close();
            result_system = sb_system.toString();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        try {

            final JSONArray jArray = new JSONArray(result_system);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo = jArray.getJSONObject(i);
                HashMap<String, String> t_system = new HashMap<>();
                t_system.put("id", jo.get("id").toString());
                t_system.put("name_en", jo.get("name_en").toString());
                t_system.put("name_th", jo.get("name_th").toString());
                Log.e("test",jo.get("name_system").toString());
                listSystem.add(t_system);
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        return listSystem;
    }

}
