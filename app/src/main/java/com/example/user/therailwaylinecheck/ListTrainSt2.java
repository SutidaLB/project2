package com.example.user.therailwaylinecheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.user.therailwaylinecheck.R.string.app_name;

public class ListTrainSt2 extends AppCompatActivity {
      String station1,station2;
      int num1=0,num2=0,num=0,num3=0;
      String Stmp,Ntmp;
      //public AutoCompleteTextView all_line_train,all_type_train,all_no_train;
      //public ArrayAdapter<String> adapter_line_nt,adapter_type_nt,adapter_no_nt;

      String Station1,Station2,NumTrain,TrainType,NumTrainType,time;
      int Station1_id,Station2_id;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.listtrainst2);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(app_name);
            setSupportActionBar(toolbar);

            /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                  }
            });*/
            Intent intent = getIntent();
            //station1 = intent.getExtras().getString("station1");
            station1 = intent.getExtras().getString("station1"); // สถานี 1
            station2 = intent.getExtras().getString("station2"); // สถานี 2

            final TextView s1 = (TextView)findViewById(R.id.sstation1);
            final TextView s2 = (TextView)findViewById(R.id.sstation2);

            s1.setText(station1);
            s2.setText(station2);

             //  Pull  Database ///////////
            ArrayList<HashMap<String,String>> result_route1 = MysqlConnectoTrain_line.selectAllT_train_line();

            ArrayList<HashMap<String,String>> result_route2 = MysqlConnectoTrain_type.selectAllT_train_type();

            ArrayList<HashMap<String,String>> result_route3 = MysqlConnectRoute.selectAllT_route();

            ArrayList<HashMap<String,String>> result_route4 = MysqlConnector.selectAllT_station();

            ArrayList<HashMap<String,String>> result_route5 = MysqlConnectSchedule.selectAllT_schedule();

            /*
               งานที่เสร็๗แล้ว
                        1.สร้าง ข้อมูลของหน้า MyItem พร้อมใช้งาน


               สิ่งที่ต้องทำต่อ  1.สร้างตัวแปรเก็บข้อมูล
                        2.ดึงข้อมูลมาเก็บในตัวแปร
                        3.เขียนอัลกอการดึงข้อมูลเฉพาะ station1 และ station2  นำมาเปรียบเทียบ
                        4.ส่งไปแสดงใน MyItem

               สิ่งที่สงสัย
                        1.จะเอาเวลา(Time)มาแสดงยังใง แล้วเวลาคำนวณจากใหน
                        2.ยังไม่ทำ POP UP ของปุ่ม

                        3.ยังมีงานอีกเยอะ วันที่ 8 จะทันพรีเซ้นมั๊ย
                        4.สู้ๆ ทันแน่นอน ถ้าตั้งใจ อิอิ
                        5.งานอื่นเยอะจัง ทำไม่ทันละ 55555++++
                        6. ณ เวลานี้ พะแอ้วนั่งร้องเพลงสบายใจเฉิบ การบ้านวิชา Internet Engineering ก็ยังไม่เสดเลย
                        7. บอมกำลังบ่นอยู่นะตอนนี้ อิอิ เผื่อผ่านเข้ามาอ่าน
                        8.ปล. คนป่วยอะไรแม่งดีดฉิบหาย 5555555555555++++++++++++
                        9.ปล.2 เวลานี้ 6.33น ณ วันที่ 1 ธันวาคม 2560

             */

            String[] time = new String[result_route1.size()];
            String[] TrainType = new String[result_route2.size()];

            String[] Station = new String[result_route3.size()];
            String[] NumTrain = new String[result_route3.size()];
            String[] NumTrainType = new String[result_route3.size()];

            String[] Station_St = new String[result_route4.size()];

            String[] Station_id = new String[result_route5.size()];
            String[] route_id = new String[result_route5.size()];
            String[] route_no = new String[result_route5.size()];
            String[] arrived = new String[result_route5.size()];
            String[] departed = new String[result_route5.size()];
            String[] sort = new String[result_route5.size()];



            String[] main_st_id = new String[238];
            String[] main_route_id = new String[238];
            String[] main_route_no = new String[238];




            //for(int i = 0;i<result.size();i++){
            //   thStation[i] = result.get(i).get("name_th");
            //}
            for(int i = 0;i<result_route5.size();i++){

                  Station_id[i] = result_route5.get(i).get("station_id"); //ขบวน
                  route_id[i] = result_route5.get(i).get("route_id");
                  route_no[i] = result_route5.get(i).get("route_no");
                  arrived[i] = result_route5.get(i).get("arrived");
                  departed[i] = result_route5.get(i).get("departed");
                  sort[i] = result_route5.get(i).get("sorting");
            }

            for(int i = 0;i<result_route4.size();i++){ // Station_table

                  Station_St[i] = result_route4.get(i).get("name_th"); //ขบวน
                 // route_id[i] = result_route4.get(i).get("route_id");
                 // route_no[i] = result_route4.get(i).get("route_no");
            }

            for(int i = 0;i<result_route1.size();i++){

                  time[i] = result_route1.get(i).get("name_th"); //ขบวน
            }
            for(int i = 0;i<result_route2.size();i++){

                  TrainType[i] = result_route2.get(i).get("name_th"); //ชนิด

            }

            for(int i = 0;i<result_route3.size();i++){
                  Station[i] = result_route3.get(i).get("name_th");
                  NumTrain[i] = result_route3.get(i).get("train_no");
                  NumTrainType[i] = result_route3.get(i).get("train_type");

            }

            //ส่วนของการค้นหาสถานี ให้ตรงกับที่ INPUT มา
            for(int i=0;i<Station_St.length;i++){  // บันทึกหมายเลขสถานี
                  if(Station_St[i].equals(station1)){
                        Station1_id = i+1;
                  }
                  if(Station_St[i].equals(station2)){
                        Station2_id = i+1;
                  }
            }

            for(int i=0;i<Station_id.length;i++){  //นำหมายเลขสถานีที่รับมา นำมาเปรียบเทียบแล้วเก็บค่า Station_id  route_id route_no ทั้ง ต้นทางและปลายทาง
                  if(Station_id[i].equals("" +Station1_id)){

                        num1++;
                  }
                  if(Integer.parseInt(Station_id[i])== Station2_id){

                        num2++;
                  }
            }
            String[] Station1_id_tmp = new String[num1];
            String[] route1_id_tmp = new String[num1];
            String[] route1_on_tmp = new String[num1];
            String[] arrived1_tmp = new String[num1];
            String[] departed1_tmp = new String[num1];
            String[] sort1_tmp = new String[num1];


            String[] Station2_id_tmp = new String[num2];
            String[] route2_id_tmp = new String[num2];
            String[] route2_on_tmp = new String[num2];
            String[] arrived2_tmp = new String[num2];
            String[] departed2_tmp = new String[num2];
            String[] sort2_tmp = new String[num2];

            num1=0;num2= 0;
            for(int i=0;i<Station_id.length;i++){  //นำหมายเลขสถานีที่รับมา นำมาเปรียบเทียบแล้วเก็บค่า Station_id  route_id route_no ทั้ง ต้นทางและปลายทาง
                  if(Station_id[i].equals("" +Station1_id)){
                        Station1_id_tmp[num1] = Station_id[i];
                        route1_id_tmp[num1] = route_id[i];
                        route1_on_tmp[num1] = route_no[i];
                        arrived1_tmp[num1] = arrived[i];
                        departed1_tmp[num1] = departed[i];
                        sort1_tmp[num1] = sort[i];
                        num1++;
                  }
                  if(Integer.parseInt(Station_id[i])== Station2_id){
                        Station2_id_tmp[num2] = Station_id[i];
                        route2_id_tmp[num2] = route_id[i];
                        route2_on_tmp[num2] = route_no[i];
                        arrived2_tmp[num2] = arrived[i];
                        departed2_tmp[num2] = departed[i];
                        sort2_tmp[num2] = sort[i];
                        num2++;
                  }
            }

            for(int i=0;i< Station1_id_tmp.length;i++){  //นำค่า Route_id มาเปรียบเทียบว่า มีอันใหนที่มีตารางเดินรถเหมือนกันหรือใม่
                  for(int j=0;j<Station2_id_tmp.length;j++){
                        if(Integer.parseInt(route1_id_tmp[i])==Integer.parseInt(route2_id_tmp[j])){
                              Ntmp = route1_on_tmp[i];
                              for(int k=0;k<Station.length;k++){
                                    if(Ntmp.equals(NumTrain[k])){
                                          Stmp = Station[k];

                                    }
                              }
                              //Station1 = Stmp.split(" ")[0];
                              if(Integer.parseInt(sort1_tmp[i]) < Integer.parseInt(sort2_tmp[j])) {
                                    num++;
                              }
                        }
                  }
            }
            String[] Station_tmp = new String[num];
            String[] NumTrain_tmp = new String[num];
            String[] NumTrainType_tmp = new String[num];
            String[] Train_type = new String[num];
            String[] arrived_tmp = new String[num];
            String[] departed_tmp = new String[num];
            num = 0;

            for(int i=0;i< Station1_id_tmp.length;i++){
                  for(int j=0;j<Station2_id_tmp.length;j++){
                        if(Integer.parseInt(route1_id_tmp[i])==Integer.parseInt(route2_id_tmp[j])){
                              Ntmp = route1_on_tmp[i];
                              for(int k=0;k<Station.length;k++){
                                    if(Ntmp.equals(NumTrain[k])){
                                          Stmp = Station[k];

                                    }
                              }
                              Station1 = Stmp.split(" ")[0];
                              if(Integer.parseInt(sort1_tmp[i]) < Integer.parseInt(sort2_tmp[j])){
                                    NumTrain_tmp[num] = Ntmp;
                                    /*for(int k=0;k<Station.length;k++){
                                          if(NumTrain_tmp[num].equals(NumTrain[k])){
                                                Station_tmp[num] = Station[k];
                                          }
                                    }*/
                                    Station_tmp[num] = Stmp;
                                    //Station_tmp[num]= Station_St[Integer.parseInt(Station1_id_tmp[i])-1] + " - " + Station_St[Integer.parseInt(Station2_id_tmp[j])-1];
                                    //NumTrain_tmp[num] = route1_on_tmp[i];
                                    for(int k=0;k<NumTrain.length;k++){//เพิ่มส่วนแสดง ชนิด รถไฟ Train_Type
                                          if(NumTrain[k].equals(NumTrain_tmp[num])){
                                                NumTrainType_tmp[num] = NumTrainType[k];
                                          }
                                    }
                                    departed_tmp[num] = departed1_tmp[i];
                                    arrived_tmp[num] = arrived2_tmp[j];


                                    num++;
                              }

                        }
                  }
            }



            // code เก่า
           /* for(int i=0;i<Station.length;i++){
                  Station1 = Station[i].split(" ")[0];
                  Station2 = Station[i].replaceAll(".*- ", "");
                  num1 = station1.length();
                  num2 = Station2.length();

                  if(station1.equals(Station1) &&  station2.equals(Station2)){
                        num++;
                  }
            }

            num = 0;
            for(int i=0;i<Station.length;i++){
                  Station1 = Station[i].split(" ")[0];
                  Station2 = Station[i].replaceAll(".*- ", "");
                  num1 = station1.length();
                  num2 = Station2.length();

                  if(station1.equals(Station1) &&  station2.equals(Station2)){
                       Station_tmp[num] = Station[i];
                       NumTrain_tmp[num] = NumTrain[i];
                       NumTrainType_tmp[num] = NumTrainType[i];
                       num++;
                  }

            }*/
            // จบ Code เก่า











            //จบส่วนของการค้นหาสถานี

            ///// End Pull Database  //////////////


            ArrayList<MyItem2> items = new ArrayList<>();
            String line1,line2,line3,line4;
            line1 = "Test";
            //MyItem test = new MyItem(line1);

            for(int i = 1; i <= Station_tmp.length; i++) {

                  //items.add(new MyItem("รหัสสถานี = " + Station1_id_tmp[i-1],"route_id = " +route1_id_tmp[i-1],"route_no = " + route1_on_tmp[i-1],line1));
                  items.add(new MyItem2(Station_tmp[i-1],NumTrain_tmp[i-1],TrainType[Integer.parseInt(NumTrainType_tmp[i-1])-1],departed_tmp[i-1] + " - " + arrived_tmp[i-1]));
            }

           //s1.setText("---"+ Station1 + "---" + Stmp + "---");
            //s1.setText(t[0].replaceAll(".*-", "")); // วิธีตัดคำข้างหน้าออก
            //s1.setText(t[0].split("-")[0]); // วิธีตัดคำข้างหลังออก
            //ใช้ MyAdapter ที่เราสร้างขึ้น  แทนคลาส ArrayAdapter ที่เคยใช้ตามปกติ
            MyAdapter2 adapter = new MyAdapter2(getBaseContext(),R.layout.item_layout2, items);

            ListView listView = (ListView)findViewById(R.id.selectst2);
            listView.setAdapter(adapter);


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
