package com.example.user.therailwaylinecheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {
      private Context mContext;
      private ArrayList<MyItem> mArrayList;
      private int mLayout;

      //คอนสตรักเตอร์สำหรับรับข้อมูลเข้ามาในคลาส
      //MyItem คือคลาสที่สร้างไว้ในขั้นตอนก่อนนี้ โดยข้อมูลต่างๆ จะถูกกำหนดให้แก่คลาส MyItem
      //แล้วคลาส MyItem จะถูกกำหนดให้เป็นชนิดข้อมูลของ ArrayList เพื่อที่จะนำข้อมูลมาใส่ลงในรายการของ ListView
     public MyAdapter(Context context, int layout,
                       ArrayList<MyItem> arrayList ) {

            super(context, layout, arrayList);
            mContext = context;
            mLayout = layout;
            mArrayList = arrayList;
      }

      //ต้องโอเวอร์ไรด์เมธอด getView() ของคลาส ArrayAdapter เพื่อกำหนดข้อมูลให้กับรายการของ ListView แทนวิธีปกติ
      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
            //สร้าง Inflater เพื่อกระจายโครงสร้างของ XML ให้เป็นออบเจ็กต์ของจาวา
            View rowView = convertView;
            if(rowView == null) {
                  LayoutInflater inflater =
                          (LayoutInflater)mContext.getSystemService(
                                  Context.LAYOUT_INFLATER_SERVICE);

                  rowView = inflater.inflate(mLayout, parent, false);
            }
            //สร้างอินสแตนซ์เพื่อใช้อ้างถึงวิวที่เราใช้จัดโครงร่างของรายการของ ListView
            //ซึ่งวิวเหล่านี้เราได้เพิ่มเราได้วางลงใน Layout แล้วในขั้นตอนก่อนนี้ (ในที่นี้คือไฟล์ item_layout.xml)
            TextView textViewLine1 =
                    (TextView) rowView.findViewById(R.id.Station);
            //อ่านข้อมูลบรรทัดที่ 1 ที่เรากำหนดให้แก่คลาส MyItem จากเมธอด getTextLine1()
            //นำข้อมูลที่ได้ไปกำหนดเป็นข้อมูลบรรทัดแรกของรายการในลำดับนั้น (position)
            String textLine1 = mArrayList.get(position).getTextLine1();
            textViewLine1.setText(textLine1);

            return rowView; //ส่งรายการของ ListView แถวนั้นกลับไป
      }

}