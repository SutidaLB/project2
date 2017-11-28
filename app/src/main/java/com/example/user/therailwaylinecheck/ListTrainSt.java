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

import static com.example.user.therailwaylinecheck.R.string.app_name;

public class ListTrainSt extends AppCompatActivity {
      String station1,station2;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.listtrainst);
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
            station1 = intent.getExtras().getString("station1");
            station2 = intent.getExtras().getString("station2");
            final TextView s1 = (TextView)findViewById(R.id.Dstation1);
            final TextView s2 = (TextView)findViewById(R.id.Dstation2);

            s1.setText(station1);
            s2.setText(station2);

            ArrayList<MyItem> items = new ArrayList<>();
            String line1;
            for(int i = 1; i <= 5; i++) {
                  line1= i+" "+"สถานีxxx - สถานีxxx";
                  items.add(new MyItem(line1));
            }
            //ใช้ MyAdapter ที่เราสร้างขึ้น  แทนคลาส ArrayAdapter ที่เคยใช้ตามปกติ
            MyAdapter adapter = new MyAdapter(getBaseContext(),
                    R.layout.item_layout, items);

            ListView listView = (ListView)findViewById(R.id.selectst);
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
