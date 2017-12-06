package com.example.user.therailwaylinecheck;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;


public class Fragment1 extends Fragment {
      AutoCompleteTextView autoCompTextView1,autoCompTextView2;
      ArrayAdapter<String> adapter1,adapter2;
      String station1,station2;

      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //นำ XML Layout ที่จะใช้คู่กัน  มาใช้เป็นโครงร่างของ Fragment นี้
            View view =  inflater.inflate(R.layout.fragment1, container, false);  //ใช้คู่กับ fragment1.xml

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            ArrayList<HashMap<String,String>> result = MysqlConnector.selectAllT_station();
            String[] enStation = new String[result.size()];
            String[] thStation = new String[result.size()];

            for(int i = 0;i<result.size();i++){
                  enStation[i] = result.get(i).get("name_en");
                  thStation[i] = result.get(i).get("name_th");
            }

            autoCompTextView1 = (AutoCompleteTextView)view.findViewById(R.id.AutoCompTextView1);
            adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, thStation);
            autoCompTextView1.setAdapter(adapter1);
            autoCompTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                        station1 = autoCompTextView1.getText().toString();
                  }
            });

            autoCompTextView2 = (AutoCompleteTextView)view.findViewById(R.id.AutoCompTextView2);
            adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, thStation);
            autoCompTextView2.setAdapter(adapter2);
            autoCompTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                        station2 = autoCompTextView2.getText().toString();
                  }
            });

            Button button1 = (Button)view.findViewById(R.id.Button1);
            button1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ListTrainSt.class);
                        intent.putExtra("station1", station1);
                        intent.putExtra("station2", station2);
                        startActivity(intent);
                  }
            });
            Button button2 = (Button)view.findViewById(R.id.Button2);
            button2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ListTrainSt2.class);
                        intent.putExtra("station1", station1);
                        intent.putExtra("station2", station2);
                        startActivity(intent);
                  }
            });

            return view;
      }

}