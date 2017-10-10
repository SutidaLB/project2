package com.example.user.therailwaylinecheck;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.user.therailwaylinecheck.R.layout.fragment3;

public class Fragment3 extends Fragment {
      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //นำ XML Layout ที่จะใช้คู่กัน  มาใช้เป็นโครงร่างของ Fragment นี้
            View view =  inflater.inflate(fragment3, container, false);  //ใช้คู่กับ fragment3.xml

            /*Button Testbutton = (Button)view.findViewById(R.id.test);
            Testbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent L1  = new Intent(Fragment3.this,MapsActivity.class);
                  startActivity(L1);
            }*/
            return view;
      }
}