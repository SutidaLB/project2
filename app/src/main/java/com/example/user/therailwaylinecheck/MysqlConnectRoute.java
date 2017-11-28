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

public class MysqlConnectRoute {

    public static ArrayList<HashMap<String, String>> selectAllT_route() { //------------ Method to select data ---------------//

        InputStream is_route = null;
        String result_route = null;
        String address_route= "http://student.coe.phuket.psu.ac.th/~s5735512134/dbproject/connect_route.php";


        ArrayList<HashMap<String, String>> listRoute = new ArrayList<>();

        try
        {
            URL url=new URL(address_route);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            is_route = new BufferedInputStream(con.getInputStream());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        try {


            BufferedReader reader = new BufferedReader(new InputStreamReader(is_route,"UTF-8"));
            StringBuilder sb_route = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb_route.append(line);
            }
            is_route.close();
            result_route = sb_route.toString();
        } catch(Exception e)
        {
            e.printStackTrace();
        }

        try {

            final JSONArray jArray = new JSONArray(result_route);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo = jArray.getJSONObject(i);
                HashMap<String, String> t_route = new HashMap<>();

                t_route.put("id", jo.get("id").toString());
                t_route.put("status", jo.get("status").toString());
                t_route.put("train_no", jo.get("train_no").toString());
                t_route.put("name_en", jo.get("name_en").toString());
                t_route.put("name_th", jo.get("name_th").toString());
                t_route.put("train_type", jo.get("train_type").toString());
                t_route.put("train_line", jo.get("train_line").toString());
                t_route.put("systems_id", jo.get("systems_id").toString());
                t_route.put("class_1_1", jo.get("class_1_1").toString());
                t_route.put("class_2_1", jo.get("class_2_1").toString());
                t_route.put("class_2_2", jo.get("class_2_2").toString());
                t_route.put("class_2_3", jo.get("class_2_3").toString());
                t_route.put("class_2_4", jo.get("class_2_4").toString());
                t_route.put("class_3_1", jo.get("class_3_1").toString());
                t_route.put("class_3_2", jo.get("class_3_2").toString());
                t_route.put("remark_name_en", jo.get("remark_name_en").toString());
                t_route.put("remark_name_th", jo.get("remark_name_th").toString());
                Log.e("test",jo.get("name_th").toString());
                listRoute.add(t_route);
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        }

        return listRoute;
    }

}
