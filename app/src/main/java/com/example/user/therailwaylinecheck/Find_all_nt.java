package com.example.user.therailwaylinecheck;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.HashMap;


public class Find_all_nt extends AppCompatActivity {
    String no_trian,line_train,type_train;
    public AutoCompleteTextView all_line_train,all_type_train,all_no_train;
    public ArrayAdapter<String> adapter_line_nt,adapter_type_nt,adapter_no_nt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.find_all_nt);

        Intent intent = getIntent();

       /* ArrayAdapter < String > dataAdapter = new ArrayAdapter < String > (getBaseContext(), android.R.layout.simple_dropdown_item_1line, list );

        AutoCompleteTextView autocomplete = ( AutoCompleteTextView ) this.findViewById ( R.id.autoComp_line );

        autocomplete.setAdapter ( dataAdapter );*/


        //super.onCreate(savedInstanceState);

        //Intent intent = getIntent();
        //str = intent.getExtras().getString("bomkiki");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

       // ArrayList<HashMap<String,String>> result = MysqlConnector.selectAllT_station();
        //String[] thStation = new String[result.size()];

        ArrayList<HashMap<String,String>> result_route1 = MysqlConnectoTrain_line.selectAllT_train_line();

        ArrayList<HashMap<String,String>> result_route2 = MysqlConnectoTrain_type.selectAllT_train_type();

        ArrayList<HashMap<String,String>> result_route3 = MysqlConnectRoute.selectAllT_route();



        String[] No_all_train = new String[result_route3.size()];
        String[] Th_all_train = new String[result_route3.size()];
        String[] Type_all_train = new String[result_route2.size()];
        String[] Line_all_train = new String[result_route1.size()];

        //for(int i = 0;i<result.size();i++){
         //   thStation[i] = result.get(i).get("name_th");
        //}
        for(int i = 0;i<result_route1.size();i++){

            Line_all_train[i] = result_route1.get(i).get("name_th"); //ขบวน
        }
        for(int i = 0;i<result_route2.size();i++){

            Type_all_train[i] = result_route2.get(i).get("name_th"); //ชนิด

        }

        for(int i = 0;i<result_route3.size();i++){
            Th_all_train[i] = result_route3.get(i).get("name_th");
            No_all_train[i] = result_route3.get(i).get("train_no");  //เลขขบวนที่ต้องการหา

        }

        all_line_train = (AutoCompleteTextView)findViewById(R.id.autoComp_line);
        adapter_line_nt = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_dropdown_item_1line,Line_all_train);
        all_line_train.setAdapter(adapter_line_nt);
        all_line_train.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                line_train = all_line_train.getText().toString();
            }
        });

        all_type_train = (AutoCompleteTextView)findViewById(R.id.autoComp_type);
        adapter_type_nt = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_dropdown_item_1line, Type_all_train);
        all_type_train.setAdapter(adapter_type_nt);
        all_type_train.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                type_train = all_type_train.getText().toString();
            }
        });



        all_no_train = (AutoCompleteTextView)findViewById(R.id.autoComp_no);
        adapter_no_nt = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_dropdown_item_1line, No_all_train);
        all_no_train.setAdapter(adapter_no_nt);
        all_no_train.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                no_trian = all_no_train.getText().toString();
            }
        });


        //test = allline_train.getText().toString();
       // t_1.setText(adapter_line_nt.getItem());
       // t_2.setText(Th_alltrain[1]);
        //t_3.setText(Line_alltrain[2]);
        /*
        allline_train.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // WORKS IF USED ON TOUCH
                allline_train.showDropDown();
                return false;
            }
        });
        */











        /*

        allline_train = (AutoCompleteTextView)findViewById(R.id.autoComp_line);
        adapter_line_nt = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_dropdown_item_1line, Line_alltrain);
        allline_train.setAdapter(adapter_line_nt);
        allline_train.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                line_train = allline_train.getText().toString();
            }
        });
        alltype_train = (AutoCompleteTextView)findViewById(R.id.autoComp_type);
         adapter_type_nt = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_dropdown_item_1line, Type_alltrain);
        alltype_train.setAdapter(adapter_type_nt);
        alltype_train.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                type_train = alltype_train.getText().toString();
            }
        });
        allno_train = (AutoCompleteTextView)findViewById(R.id.autoComp_no);
        adapter_no_nt = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_dropdown_item_1line, No_alltrain);
        allno_train.setAdapter(adapter_no_nt);
        allno_train.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                no_trian = allno_train.getText().toString();
            }
        });*/
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


