package com.example.user.therailwaylinecheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


public class Fragment1 extends Fragment {
      AutoCompleteTextView autoCompTextView1,autoCompTextView2;
      ArrayAdapter<String> adapter1,adapter2;

      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
      }
//////123123
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //นำ XML Layout ที่จะใช้คู่กัน  มาใช้เป็นโครงร่างของ Fragment นี้
            View view =  inflater.inflate(R.layout.fragment1, container, false);  //ใช้คู่กับ fragment1.xml

            String[] textSource = {"กบินทร์บุรี","กบินทร์เก่า","กระสัง","กระเบียด","กรุงเทพ","กลางดง","กะปาง","กะแด๊ะ","กระโดนค้อ","สีคิ้ว","มวกเหล็ก","เชียงใหม่"};
            autoCompTextView1 = (AutoCompleteTextView)view.findViewById(R.id.AutoCompTextView1);
            adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,textSource);
            autoCompTextView1.setAdapter(adapter1);


            autoCompTextView2 = (AutoCompleteTextView)view.findViewById(R.id.AutoCompTextView2);
            adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,textSource);
            autoCompTextView2.setAdapter(adapter2);

            Button button1 = (Button)view.findViewById(R.id.Button1);
            button1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ListTrainSt.class);
                        startActivity(intent);
                  }
            });
            Button button2 = (Button)view.findViewById(R.id.Button2);
            button2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ListTrainSt.class);
                        startActivity(intent);
                  }
            });

            return view;
      }

}