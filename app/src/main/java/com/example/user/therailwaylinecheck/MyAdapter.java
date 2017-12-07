package com.example.user.therailwaylinecheck;

import android.content.Context;
import android.content.Intent;
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
            TextView textViewLine1 = (TextView) rowView.findViewById(R.id.Station);
            TextView textViewLine2 = (TextView) rowView.findViewById(R.id.NumTrain);
            TextView textViewLine3 = (TextView) rowView.findViewById(R.id.TrainType);
            TextView textViewLine4 = (TextView) rowView.findViewById(R.id.mtime);
            //อ่านข้อมูลบรรทัดที่ 1 ที่เรากำหนดให้แก่คลาส MyItem จากเมธอด getTextLine1()
            //นำข้อมูลที่ได้ไปกำหนดเป็นข้อมูลบรรทัดแรกของรายการในลำดับนั้น (position)
          final String train_no,train_name,train_type;

          String textLine1 = mArrayList.get(position).getmStation();
          textViewLine1.setText(textLine1);
          train_name = textViewLine1.getText().toString();


          String textLine2 = mArrayList.get(position).getmNumTrain();
          textViewLine2.setText(textLine2);
          train_no = textViewLine2.getText().toString();

          String textLine3 = mArrayList.get(position).getmTrainType();
          textViewLine3.setText(textLine3);
          train_type = textViewLine3.getText().toString();

          String textLine4 = mArrayList.get(position).getMtime();
          textViewLine4.setText(textLine4);

          textViewLine1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(getContext(), TableListTrainSt.class);
                  intent.putExtra("train_name",train_name);
                  intent.putExtra("train_no",train_no);
                  intent.putExtra("train_type",train_type);

                  mContext.startActivity(intent);
              }
          });

          return rowView; //ส่งรายการของ ListView แถวนั้นกลับไป
     }

}