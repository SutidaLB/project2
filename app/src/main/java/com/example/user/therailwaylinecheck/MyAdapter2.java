package com.example.user.therailwaylinecheck;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter2 extends ArrayAdapter {
      private Context mContext;
      private ArrayList<MyItem2> mArrayList;
      private int mLayout;

      //คอนสตรักเตอร์สำหรับรับข้อมูลเข้ามาในคลาส
      //MyItem คือคลาสที่สร้างไว้ในขั้นตอนก่อนนี้ โดยข้อมูลต่างๆ จะถูกกำหนดให้แก่คลาส MyItem
      //แล้วคลาส MyItem จะถูกกำหนดให้เป็นชนิดข้อมูลของ ArrayList เพื่อที่จะนำข้อมูลมาใส่ลงในรายการของ ListView
     public MyAdapter2(Context context, int layout,
                       ArrayList<MyItem2> arrayList ) {

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
            //ซึ่งวิวเหล่านี้เราได้เพิ่มเราได้วางลงใน Layout แล้วในขั้นตอนก่อนนี้ (ในที่นี้คือไฟล์ item_layout2.xml)
            TextView textViewLine1 = (TextView) rowView.findViewById(R.id.showStation2);
            TextView textViewLine2 = (TextView) rowView.findViewById(R.id.showNumTrain2);
            TextView textViewLine3 = (TextView) rowView.findViewById(R.id.showTrainType2);
            TextView textViewLine4 = (TextView) rowView.findViewById(R.id.showmtime2);
            TextView textViewLine5 = (TextView) rowView.findViewById(R.id.showStation3);
            TextView textViewLine6 = (TextView) rowView.findViewById(R.id.showNumTrain3);
            TextView textViewLine7 = (TextView) rowView.findViewById(R.id.showTrainType3);
            TextView textViewLine8 = (TextView) rowView.findViewById(R.id.showmtime3);
            //อ่านข้อมูลบรรทัดที่ 1 ที่เรากำหนดให้แก่คลาส MyItem2 จากเมธอด getTextLine1()
            //นำข้อมูลที่ได้ไปกำหนดเป็นข้อมูลบรรทัดแรกของรายการในลำดับนั้น (position)
          final String train_no1,train_name1,train_type1;
          final String train_no2,train_name2,train_type2;
          final String Source,MidStation,Dest;

          Source = mArrayList.get(position).getmSource();
          MidStation = mArrayList.get(position).getmMidStation();
          Dest = mArrayList.get(position).getmDest();


          String textLine1 = mArrayList.get(position).getmStation2();
          textViewLine1.setText(textLine1);
          train_name1 = textViewLine1.getText().toString();


          String textLine2 = mArrayList.get(position).getmNumTrain2();
          textViewLine2.setText(textLine2);
          train_no1 = textViewLine2.getText().toString();

          String textLine3 = mArrayList.get(position).getmTrainType2();
          textViewLine3.setText(textLine3);
          train_type1 = textViewLine3.getText().toString();


          String textLine4 = mArrayList.get(position).getMtime2();
          textViewLine4.setText(textLine4);



          String textLine5 = mArrayList.get(position).getmStation3();
          textViewLine5.setText(textLine5);
          train_name2 = textViewLine5.getText().toString();



          String textLine6 = mArrayList.get(position).getmNumTrain3();
          textViewLine6.setText(textLine6);
          train_no2 = textViewLine6.getText().toString();

          String textLine7 = mArrayList.get(position).getmTrainType3();
          textViewLine7.setText(textLine7);
          train_type2 = textViewLine7.getText().toString();

          String textLine8 = mArrayList.get(position).getMtime3();
          textViewLine8.setText(textLine8);

          Button price1 = (Button)rowView.findViewById(R.id.showmticked2);
          Button price2 = (Button)rowView.findViewById(R.id.showmticked3);


          textViewLine1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(getContext(), TableListTrainSt.class);
                  intent.putExtra("train_name",train_name1);
                  intent.putExtra("train_no",train_no1);
                  intent.putExtra("train_type",train_type1);

                  mContext.startActivity(intent);
              }
          });
           textViewLine5.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(getContext(), TableListTrainSt.class);
                  intent.putExtra("train_name",train_name2);
                  intent.putExtra("train_no",train_no2);
                  intent.putExtra("train_type",train_type2);
                  mContext.startActivity(intent);
              }
          });

           price1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String webUrl ="http://www.railway.co.th/Ticket/CheckPrice_TrainGo.asp?Sta=" + Source + "&Fin=" +MidStation + "&IdTrain=" +train_no1 + "&NFee=" +train_type1;;
                   Intent intent = new Intent(getContext(), Price.class);
                   intent.putExtra("webUrl",webUrl);
                   mContext.startActivity(intent);
               }
           });
          price2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String webUrl ="http://www.railway.co.th/Ticket/CheckPrice_TrainGo.asp?Sta=" + MidStation + "&Fin=" + Dest + "&IdTrain=" +train_no2 + "&NFee=" +train_type2;;
                  Intent intent = new Intent(getContext(), Price.class);
                  intent.putExtra("webUrl",webUrl);
                  mContext.startActivity(intent);
              }
          });

          return rowView; //ส่งรายการของ ListView แถวนั้นกลับไป
      }

}