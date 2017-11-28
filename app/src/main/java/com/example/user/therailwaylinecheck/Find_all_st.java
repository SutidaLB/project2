package com.example.user.therailwaylinecheck;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Find_all_st extends AppCompatActivity {
    String station;
    AutoCompleteTextView allstation;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_all_st);

        //super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        //str = intent.getExtras().getString("bomkiki");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<HashMap<String,String>> result = MysqlConnector.selectAllT_station();

        String[] thStation = new String[result.size()];

        for(int i = 0;i<result.size();i++){
            thStation[i] = result.get(i).get("name_th");
        }

        ArrayList<HashMap<String,String>> result_route = MysqlConnectRoute.selectAllT_route(); // เราต้องดึงส่วนของ Route ใช่ป่าว ใช่ๆ
        String[] No_all_train = new String[result_route.size()];
        String[] Th_all_train = new String[result_route.size()];
        String[] Type_all_train = new String[result_route.size()];
        String[] Line_all_train = new String[result_route.size()];

        for(int i = 0;i<result_route.size();i++){
            Th_all_train[i] = result_route.get(i).get("name_th");
            No_all_train[i] = result_route.get(i).get("train_no");  //เลขขบวนที่ต้องการหา
            Type_all_train[i] = result_route.get(i).get("train_type"); //ชนิด
            Line_all_train[i] = result_route.get(i).get("train_line"); //ขบวน
        }

        allstation = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,  Th_all_train);
        allstation.setAdapter(adapter);

        allstation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                station = allstation.getText().toString();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}


