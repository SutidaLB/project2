package com.example.user.therailwaylinecheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class Fragment2 extends Fragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //นำ XML Layout ที่จะใช้คู่กัน  มาใช้เป็นโครงร่างของ Fragment นี้
            View view = inflater.inflate(R.layout.fragment2, container, false);  //ใช้คู่กับ fragment2.xml
            Button button2 = (Button)view.findViewById(R.id.button);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Find_all_st.class);
                    startActivity(intent);
                }
            });
            final ListView listView = (ListView) view.findViewById(R.id.selectst);

            final ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("ค้นหาขบวนรถไฟ");
            arrayList.add("ค้นหาสถานีรถไฟ");

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_single_choice, arrayList);
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            listView.setAdapter(adapter);

            Button button = (Button) view.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vf2) {

                    String str = "";
                    int st;
                    String st1="ค้นหาขบวนรถไฟ",st2="ค้นหาสถานีรถไฟ";
                    SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
                    for (int i = 0; i < listView.getCount(); i++) {
                        if (checkedItems.get(i) == true) {
                            if (!str.equals("")) {
                                str += "\n";
                            }
                            str += listView.getItemAtPosition(i).toString();
                        }
                    }
                    if(str.length() == st1.length()) {
                        Intent intent = new Intent(getContext(), Find_all_nt.class);
                        startActivity(intent);
                    }
                    else if(str.length() == st2.length()) {
                        Intent intent = new Intent(getContext(), Find_all_st.class);
                        startActivity(intent);
                    }
                    //str = "รายการที่เลือก: \n" + str + arrayList.get(0);
                    //Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
                }
            });

            return view;
        }

}
