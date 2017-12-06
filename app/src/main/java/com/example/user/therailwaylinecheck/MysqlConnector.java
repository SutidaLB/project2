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

public class MysqlConnector {

    public static ArrayList<HashMap<String, String>> selectAllT_station() { //------------ Method to select data ---------------//

        InputStream is = null;
        String result = null;
        String address= "http://student.coe.phuket.psu.ac.th/~s5735512134/dbproject/connect_station.php";
        //String address="http://10.0.2.2/dbproject/connect_station.php";
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try
        {
            URL url=new URL(address);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            result = sb.toString();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        try {

            final JSONArray jArray = new JSONArray(result);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo = jArray.getJSONObject(i);
                HashMap<String, String> t_station = new HashMap<>();
                t_station.put("id", jo.get("id").toString());
                t_station.put("name_en", jo.get("name_en").toString());
                t_station.put("name_th", jo.get("name_th").toString());
                t_station.put("distance", jo.get("distance").toString());

                Log.e("test",jo.get("name_en").toString());
                list.add(t_station);
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

}
