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

public class MysqlConnectSchedule {

    public static ArrayList<HashMap<String, String>> selectAllT_schedule() { //------------ Method to select data ---------------//

        InputStream is_schedule = null;
        String result_schedule = null;
        String address_schedule= "http://student.coe.phuket.psu.ac.th/~s5735512134/dbproject/connect_schedule.php";
        ArrayList<HashMap<String, String>> listSchedule = new ArrayList<>();

        try
        {
            URL url=new URL(address_schedule);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            is_schedule = new BufferedInputStream(con.getInputStream());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        try {


            BufferedReader reader = new BufferedReader(new InputStreamReader(is_schedule,"UTF-8"));
            StringBuilder sb_schedule = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb_schedule.append(line);
            }
            is_schedule.close();
            result_schedule = sb_schedule.toString();
        } catch(Exception e)
        {
            e.printStackTrace();
        }

        try {

            final JSONArray jArray = new JSONArray(result_schedule);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo = jArray.getJSONObject(i);
                HashMap<String, String> t_schedule = new HashMap<>();

                t_schedule.put("id", jo.get("id").toString());
                t_schedule.put("route_id", jo.get("route_id").toString());
                t_schedule.put("route_no", jo.get("route_no").toString());
                t_schedule.put("sorting", jo.get("sorting").toString());
                t_schedule.put("station_id", jo.get("station_id").toString());
                t_schedule.put("arrived", jo.get("arrived").toString());
                t_schedule.put("departed", jo.get("departed").toString());
                Log.e("test",jo.get("id").toString());
                listSchedule.add(t_schedule);
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        }

        return listSchedule;
    }

}
