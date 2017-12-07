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

public class TableListTrainSt extends AppCompatActivity {
      String ttrain_name,ttrain_no,ttrain_type,station1,station2;
      int num1=0,num2=0,num=0,num3=0;
      String Stmp,Ntmp;

      String Station1,Station2,NumTrain,TrainType,NumTrainType,time;
      int Station1_id,Station2_id;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.table_listtrainst);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(app_name);
            setSupportActionBar(toolbar);

            Intent intent = getIntent();
            ttrain_name = intent.getExtras().getString("train_name");
            ttrain_name = ttrain_name.replaceAll(".*:", "");

            ttrain_no = intent.getExtras().getString("train_no");
            ttrain_type = intent.getExtras().getString("train_type");
            //ttrain_route.replaceAll(".* ", "");

            final TextView text_train_route = (TextView)findViewById(R.id.train_route);
            final TextView text_train_no = (TextView)findViewById(R.id.train_no);
            final TextView text_train_type = (TextView)findViewById(R.id.train_type);


            text_train_route.setText(ttrain_name);
            text_train_no.setText(ttrain_no);
            text_train_type.setText(ttrain_type);

            ArrayList<HashMap<String,String>> result_route5 = MysqlConnectSchedule.selectAllT_schedule();
            ArrayList<HashMap<String,String>> result_route4 = MysqlConnector.selectAllT_station();

            String[] Station_id = new String[result_route5.size()];
            String[] route_id = new String[result_route5.size()];
            String[] route_no = new String[result_route5.size()];
            String[] arrived = new String[result_route5.size()];
            String[] departed = new String[result_route5.size()];

            String[] Station_St = new String[result_route4.size()];

            for(int i = 0;i<result_route4.size();i++){ // Station_table
                  Station_St[i] = result_route4.get(i).get("name_th"); //ขบวน

            }

            for(int i = 0;i<result_route5.size();i++){
                  Station_id[i] = result_route5.get(i).get("station_id"); //ขบวน
                  route_id[i] = result_route5.get(i).get("route_id");
                  route_no[i] = result_route5.get(i).get("route_no");
                  arrived[i] = result_route5.get(i).get("arrived");
                  departed[i] = result_route5.get(i).get("departed");

            }
            for(int i=0;i<route_no.length;i++){
                  if(route_no[i].equals(ttrain_no)){
                        num++;
                  }
            }

            String[] Station_tmp = new String[num];
            String[] Station_id_tmp = new String[num];
            String[] route_id_tmp = new String[num];
            String[] route_on_tmp = new String[num];
            String[] arrived_tmp = new String[num];
            String[] departed_tmp = new String[num];
            num=0;
            for(int i=0;i<route_no.length;i++){
                  if(route_no[i].equals(ttrain_no)){
                        Station_id_tmp[num] = Station_id[i];
                        route_id_tmp[num] = route_id[i];
                        route_on_tmp[num] = route_no[i];
                        arrived_tmp[num] = arrived[i];
                        departed_tmp[num] = departed[i];
                        num++;
                  }
            }

            for(int j = 0;j<Station_id_tmp.length;j++) {
                  for (int i = 1; i <= Station_St.length; i++) {
                        if (Station_id_tmp[j].equals(i+ "")) {
                              Station_tmp[j] = Station_St[i-1];
                        }
                  }
            }


           // text_train_route.setText(""+num +" " + Station_tmp[0]);

            ArrayList<TableMyItem> items = new ArrayList<>();
            String line1,line2,line3,line4;
            line1 = "Test";
            //MyItem test = new MyItem(line1);

            for(int i = 0; i <Station_id_tmp.length; i++) {

                  items.add(new TableMyItem(i+1+"",Station_tmp[i],arrived_tmp[i],departed_tmp[i]));
            }


            TableMyAdapter adapter = new TableMyAdapter(getBaseContext(),R.layout.table_item_layout,items);

            ListView listView = (ListView)findViewById(R.id.list_show_table);
            listView.setAdapter(adapter);


            /*
            //จบส่วนของการค้นหาสถานี

            ///// End Pull Database  //////////////

            ArrayList<MyItem> items = new ArrayList<>();
            String line1,line2,line3,line4;
            line1 = "Test";
            //MyItem test = new MyItem(line1);

            for(int i = 1; i <= Station_tmp.length; i++) {

                  items.add(new MyItem(i+ ":  "+Station_tmp[i-1],NumTrain_tmp[i-1],TrainType[Integer.parseInt(NumTrainType_tmp[i-1])-1],departed_tmp[i-1] + " - " + arrived_tmp[i-1]));
            }

           //s1.setText("---"+ Station1 + "---" + Stmp + "---");
            //s1.setText(t[0].replaceAll(".*-", "")); // วิธีตัดคำข้างหน้าออก
            //s1.setText(t[0].split("-")[0]); // วิธีตัดคำข้างหลังออก
            //ใช้ MyAdapter ที่เราสร้างขึ้น  แทนคลาส ArrayAdapter ที่เคยใช้ตามปกติ
            MyAdapter adapter = new MyAdapter(getBaseContext(),R.layout.item_layout, items);

            ListView listView = (ListView)findViewById(R.id.selectst);
            listView.setAdapter(adapter);

      */
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
