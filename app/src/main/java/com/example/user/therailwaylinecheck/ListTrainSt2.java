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
      String Junction = "กรุงเทพ",St_Pachee = "ชุมทางบ้านภาชี",St_Pladuk = "ชุมทางหนองปลาดุก";
      String St_bkk;
      int num1=0,num2=0,num=0,num3=0,num0=0,num_st1=0,num_st2=0;
      String Stmp,Ntmp,Statustmp;

      //String Station1,Station2,NumTrain,TrainType,NumTrainType,time;
      int Station1_id,Station2_id,Station_bkk_id;
      String St1_route_no,St2_route_no;
      String St1_line,St2_line;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.listtrainst2);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(app_name);
            setSupportActionBar(toolbar);

            Intent intent = getIntent();
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

            //ตาราง Train_line
            String[] time = new String[result_route1.size()];
            // Train_type
            String[] TrainType = new String[result_route2.size()];
            // Route
            String[] Station = new String[result_route3.size()];
            String[] NumTrain = new String[result_route3.size()];
            String[] NumTrainType = new String[result_route3.size()];
            String[] Train_line = new String[result_route3.size()];
            String[] Status = new String[result_route3.size()];

            // Station
            String[] Station_St = new String[result_route4.size()];
            // Schedule
            String[] Station_id = new String[result_route5.size()];
            String[] route_id = new String[result_route5.size()];
            String[] route_no = new String[result_route5.size()];
            String[] arrived = new String[result_route5.size()];
            String[] departed = new String[result_route5.size()];
            String[] sort = new String[result_route5.size()];

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
                  Train_line[i] = result_route3.get(i).get("train_line");
                  Status[i] = result_route3.get(i).get("status");
            }

            // ส่วนของ  Algor การหาทางผ่านแต่ละชุมทาง
            for(int i=0;i<Station_St.length;i++){  // บันทึกหมายเลขสถานี
                  if(Station_St[i].equals(station1)){
                        Station1_id = i+1;
                  }
                  if(Station_St[i].equals(station2)){
                        Station2_id = i+1;
                  }
            }
            for(int i=0;i<Station_id.length;i++){
                  if(Station1_id == Integer.parseInt(Station_id[i])){
                        St1_route_no = route_no[i];
                        break;
                  }
            }
            for(int i=0;i<Station_id.length;i++){
                  if(Station2_id == Integer.parseInt(Station_id[i])){
                        St2_route_no = route_no[i];
                        break;
                  }
            }

            for(int i=0;i<NumTrain.length;i++){
                  if(NumTrain[i].equals(St1_route_no)){
                        St1_line = Train_line[i];
                  }
                  else if(NumTrain[i].equals(St2_route_no)){
                        St2_line = Train_line[i];
                  }
            }


            if((St1_line.equals("3") && St2_line.equals("5")) || (St1_line.equals("5") && St2_line.equals("3"))) // ภาคใต้ -- ตะวันตก || ตะวันตก---ภาคใต้
            {
                  St_bkk = St_Pladuk;
            }
            else  if((St1_line.equals("1") && St2_line.equals("2")) || (St1_line.equals("2") && St2_line.equals("1"))) // ภาคเหนือ -- อีสาน || อีสาน---เหนือ
            {
                  St_bkk = St_Pachee;
            }
            else  // กรุงเทพมหานคร
            {
                  St_bkk = Junction;
            }

            // จบส่วน Algor การหาทางผ่านแต่ละชุมทาง

            //ส่วนของการค้นหาสถานี ให้ตรงกับที่ INPUT มา
            for(int i=0;i<Station_St.length;i++){  // บันทึกหมายเลขสถานี
                  if(Station_St[i].equals(station1)){
                        Station1_id = i+1;
                  }
                  if(Station_St[i].equals(St_bkk))
                  {
                        Station_bkk_id = i+1;
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
                  if(Integer.parseInt(Station_id[i])== Station_bkk_id){

                        num3++;
                  }
            }
            String[] Station1_id_tmp = new String[num1];
            String[] route1_id_tmp = new String[num1];
            String[] route1_on_tmp = new String[num1];
            String[] arrived1_tmp = new String[num1];
            String[] departed1_tmp = new String[num1];
            String[] sort1_tmp = new String[num1];
            //String[] status1_tmp = new String[num1];

            String[] Station_id_bkk = new String[num3];
            String[] route_id_bkk = new String[num3];
            String[] route_on_bkk = new String[num3];
            String[] arrived_bkk = new String[num3];
            String[] departed_bkk = new String[num3];
            String[] sort_bkk = new String[num3];
            //String[] status_bkk = new String[num3];

            String[] Station2_id_tmp = new String[num2];
            String[] route2_id_tmp = new String[num2];
            String[] route2_on_tmp = new String[num2];
            String[] arrived2_tmp = new String[num2];
            String[] departed2_tmp = new String[num2];
            String[] sort2_tmp = new String[num2];
            //String[] status2_tmp = new String[num2];

            // เทียบต้นทางกับ กรุงเทพ
            num1=0;num2=0;num3=0;
            for(int i=0;i<Station_id.length;i++){  //นำหมายเลขสถานีที่รับมา นำมาเปรียบเทียบแล้วเก็บค่า Station_id  route_id route_no ทั้ง ต้นทางและปลายทาง
                  if(Station_id[i].equals("" +Station1_id)){
                        Station1_id_tmp[num1] = Station_id[i];
                        route1_id_tmp[num1] = route_id[i];
                        route1_on_tmp[num1] = route_no[i];
                        arrived1_tmp[num1] = arrived[i];
                        departed1_tmp[num1] = departed[i];
                        sort1_tmp[num1] = sort[i];
                        //status1_tmp[num1] = Status[i];
                        num1++;
                  }
                  if(Integer.parseInt(Station_id[i])== Station2_id){
                        Station2_id_tmp[num2] = Station_id[i];
                        route2_id_tmp[num2] = route_id[i];
                        route2_on_tmp[num2] = route_no[i];
                        arrived2_tmp[num2] = arrived[i];
                        departed2_tmp[num2] = departed[i];
                        sort2_tmp[num2] = sort[i];
                        //status2_tmp[num2] = Status[i];
                        num2++;
                  }
                  if(Integer.parseInt(Station_id[i])== Station_bkk_id){
                        Station_id_bkk[num3] = Station_id[i];
                        route_id_bkk[num3] = route_id[i];
                        route_on_bkk[num3] = route_no[i];
                        arrived_bkk[num3] = arrived[i];
                        departed_bkk[num3] = departed[i];
                        sort_bkk[num3] = sort[i];
                        //status_bkk[num3] = Status[i];
                        num3++;
                  }
            }

            // Station1 เทียบ กรุงเทพ
            for(int i=0;i< Station1_id_tmp.length;i++){  //นำค่า Route_id มาเปรียบเทียบว่า มีอันใหนที่มีตารางเดินรถเ หมือนกันหรือใม่
                  for(int j=0;j<Station_id_bkk.length;j++){
                        if(Integer.parseInt(route1_id_tmp[i])==Integer.parseInt(route_id_bkk[j])){
                              Ntmp = route1_on_tmp[i];
                              for(int k=0;k<Station.length;k++){
                                    if(Ntmp.equals(NumTrain[k])){
                                          Stmp = Station[k];
                                          Statustmp = Status[k];

                                    }
                              }
                              if(Integer.parseInt(sort1_tmp[i]) < Integer.parseInt(sort_bkk[j]) && (Integer.parseInt(Statustmp) == 1)){
                                    num++;
                              }
                        }
                  }
            }
            for(int i=0;i< Station_id_bkk.length;i++){  //นำค่า Route_id มาเปรียบเทียบว่า มีอันใหนที่มีตารางเดินรถเ หมือนกันหรือใม่
                  for(int j=0;j<Station2_id_tmp.length;j++){
                        if(Integer.parseInt(route_id_bkk[i])==Integer.parseInt(route2_id_tmp[j])){
                              Ntmp = route_on_bkk[i];
                              for(int k=0;k<Station.length;k++){
                                    if(Ntmp.equals(NumTrain[k])){
                                          Stmp = Station[k];
                                          Statustmp = Status[k];

                                    }
                              }
                              if(Integer.parseInt(sort_bkk[i]) < Integer.parseInt(sort2_tmp[j]) && (Integer.parseInt(Statustmp) == 1)) {
                                    num0++;
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
            String[] status_tmp = new String[num];


            String[] Station_tmp_1 = new String[num0];
            String[] NumTrain_tmp_1 = new String[num0];
            String[] NumTrainType_tmp_1 = new String[num0];
            String[] Train_type_1 = new String[num0];
            String[] arrived_tmp_1 = new String[num0];
            String[] departed_tmp_1 = new String[num0];
            String[] status_tmp_1 = new String[num0];



            num = 0;
            for(int i=0;i< Station1_id_tmp.length;i++){
                  for(int j=0;j<Station_id_bkk.length;j++){
                        if(Integer.parseInt(route1_id_tmp[i])==Integer.parseInt(route_id_bkk[j])){
                              Ntmp = route1_on_tmp[i];
                              for(int k=0;k<Station.length;k++){
                                    if(Ntmp.equals(NumTrain[k])){
                                          Stmp = Station[k];
                                          Statustmp = Status[k];

                                    }
                              }
                              if(Integer.parseInt(sort1_tmp[i]) < Integer.parseInt(sort_bkk[j]) && (Integer.parseInt(Statustmp) == 1)){
                                    NumTrain_tmp[num] = Ntmp;

                                    Station_tmp[num] = Stmp;
                                    for(int k=0;k<NumTrain.length;k++){//เพิ่มส่วนแสดง ชนิด รถไฟ Train_Type
                                          if(NumTrain[k].equals(NumTrain_tmp[num])){
                                                NumTrainType_tmp[num] = NumTrainType[k];
                                          }
                                    }
                                    departed_tmp[num] = departed1_tmp[i];
                                    arrived_tmp[num] = arrived_bkk[j];
                                    num++;
                              }
                        }
                  }
            }
            // เทียบ กรุงเทพ - ปลายทาง
            num0 = 0;
            for(int i=0;i< Station_id_bkk.length;i++){
                  for(int j=0;j<Station2_id_tmp.length;j++){
                        if(Integer.parseInt(route_id_bkk[i])==Integer.parseInt(route2_id_tmp[j])){
                              Ntmp = route_on_bkk[i];
                              for(int k=0;k<Station.length;k++){
                                    if(Ntmp.equals(NumTrain[k])){
                                          Stmp = Station[k];
                                          Statustmp = Status[k];
                                    }
                              }
                              if(Integer.parseInt(sort_bkk[i]) < Integer.parseInt(sort2_tmp[j]) && (Integer.parseInt(Statustmp) == 1)){
                                    NumTrain_tmp_1[num0] = Ntmp;

                                    Station_tmp_1[num0] = Stmp;
                                    for(int k=0;k<NumTrain.length;k++){//เพิ่มส่วนแสดง ชนิด รถไฟ Train_Type
                                          if(NumTrain[k].equals(NumTrain_tmp_1[num0])){
                                                NumTrainType_tmp_1[num0] = NumTrainType[k];
                                          }
                                    }
                                    departed_tmp_1[num0] = departed_bkk[i];
                                    arrived_tmp_1[num0] = arrived2_tmp[j];
                                    num0++;
                              }
                        }
                  }
            }

            //จบส่วนของการค้นหาสถานี

            // ส่วนของการต่อเวลา อิอิ









            // จบส่วนการต่อเวลา อิอิ
            ///// End Pull Database  //////////////

            ArrayList<MyItem2> items = new ArrayList<>();
            String line1,line2,line3,line4;
            line1 = "Test";
            if(Station_tmp.length < Station_tmp_1.length){
                  for(int i = 1; i <= Station_tmp.length; i++) {
                        items.add(new MyItem2(i + ":"+Station_tmp[i-1],""+NumTrain_tmp[i-1],""+TrainType[Integer.parseInt(NumTrainType_tmp[i-1])-1],
                                departed_tmp[i-1] + " - " + arrived_tmp[i-1]
                                ,i + ":"+Station_tmp_1[i-1],""+NumTrain_tmp_1[i-1],""+TrainType[Integer.parseInt(NumTrainType_tmp_1[i-1])-1],
                                departed_tmp_1[i-1] + " - " + arrived_tmp_1[i-1]));
                  }
            }
            else {
                  for (int i = 1; i <= Station_tmp_1.length; i++) {
                        items.add(new MyItem2(i + ":" + Station_tmp[i - 1], "" + NumTrain_tmp[i - 1], "" + TrainType[Integer.parseInt(NumTrainType_tmp[i - 1]) - 1],
                                departed_tmp[i - 1] + " - " + arrived_tmp[i - 1]
                                , i + ":" + Station_tmp_1[i - 1], "" + NumTrain_tmp_1[i - 1], "" + TrainType[Integer.parseInt(NumTrainType_tmp_1[i - 1]) - 1],
                                departed_tmp_1[i - 1] + " - " + arrived_tmp_1[i - 1]));
                  }
            }

            //s1.setText(Station_tmp.length + "  " + Station_tmp_1.length + " " + St_bkk);
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
