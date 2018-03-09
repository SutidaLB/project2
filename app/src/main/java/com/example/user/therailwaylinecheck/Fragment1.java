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

      // Search Value
      int num1=0,num2=0,num=0,num3=0;
      String Stmp,Ntmp,Statustmp;

      String Station1,Station2,NumTrain,TrainType,NumTrainType,time;
      int Station1_id,Station2_id;
      ArrayList<MyItem> items ;
      int numsize = 0;
      // End Search Value

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
                        items = new ArrayList<>();
                        num1=0;num2=0;num=0;num3=0;
                        //Stmp="",Ntmp""Statustmp;

                        //Station1,Station2,NumTrain,TrainType,NumTrainType,time;

                        ArrayList<HashMap<String,String>> result_route1 = MysqlConnectoTrain_line.selectAllT_train_line();
                        ArrayList<HashMap<String,String>> result_route2 = MysqlConnectoTrain_type.selectAllT_train_type();
                        ArrayList<HashMap<String,String>> result_route3 = MysqlConnectRoute.selectAllT_route();
                        ArrayList<HashMap<String,String>> result_route4 = MysqlConnector.selectAllT_station();
                        ArrayList<HashMap<String,String>> result_route5 = MysqlConnectSchedule.selectAllT_schedule();

                        String[] time = new String[result_route1.size()];
                        String[] TrainType = new String[result_route2.size()];

                        String[] Station = new String[result_route3.size()];
                        String[] NumTrain = new String[result_route3.size()];
                        String[] NumTrainType = new String[result_route3.size()];
                        String[] Status = new String[result_route3.size()];

                        String[] Station_St = new String[result_route4.size()];

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
                              Status[i] = result_route3.get(i).get("status");

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
                        // End //




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
                                                      Statustmp = Status[k];

                                                }
                                          }
                                          if(Integer.parseInt(sort1_tmp[i]) < Integer.parseInt(sort2_tmp[j]) && Integer.parseInt(Statustmp)==1) {
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
                                                      Statustmp = Status[k];

                                                }
                                          }
                                          if(Integer.parseInt(sort1_tmp[i]) < Integer.parseInt(sort2_tmp[j]) && Integer.parseInt(Statustmp)==1){
                                                NumTrain_tmp[num] = Ntmp;
                                                Station_tmp[num] = Stmp;
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
                        //จบส่วนของการค้นหาสถานี

                        ///// End Pull Database  //////////////


                        String line1,line2,line3,line4;
                        line1 = "Test";
                        //MyItem test = new MyItem(line1);

                        for(int i = 1; i <= Station_tmp.length; i++) {

                              items.add(new MyItem(i+ ": "+Station_tmp[i-1],NumTrain_tmp[i-1],TrainType[Integer.parseInt(NumTrainType_tmp[i-1])-1],departed_tmp[i-1] + " - " + arrived_tmp[i-1],station1,station2));
                        }

                        //numsize = items.size();
                        //System.out.println("itemSize() = " + items.size());







                        // End Search Station
                        if(Station_tmp.length > 0 ) {

                              Intent intent2 = new Intent(getContext(), ListTrainSt.class);
                              intent2.putExtra("station1", station1);
                              intent2.putExtra("station2", station2);
                              startActivity(intent2);

                        }
                        else{

                              Intent intent1 = new Intent(getContext(), ListTrainSt2.class);
                              intent1.putExtra("station1", station1);
                              intent1.putExtra("station2", station2);
                              startActivity(intent1);

                        }
                  }
            });
            /*Button button2 = (Button)view.findViewById(R.id.Button2);
            button2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ListTrainSt2.class);
                        intent.putExtra("station1", station1);
                        intent.putExtra("station2", station2);
                        startActivity(intent);
                  }
            });*/

            return view;
      }

}