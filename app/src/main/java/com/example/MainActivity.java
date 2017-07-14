package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.animations.AnimationExampleActivity;
import com.example.data_passing_between_single_activity_to_multiple_fragments.TempActivity;
import com.example.simple_alert_dialog_with_list.SimpleAlertDialogActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Array of strings...
        String[] strings = {"Animations", "Simple Alert dialog with ListView", "Data passing between single activity to multiple fragments"};

        ListView listView1 = (ListView) findViewById(R.id.listView1);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);

        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (position == 0) {
                    startActivity(new Intent(MainActivity.this, AnimationExampleActivity.class));
                } else if (position == 1) {
                    startActivity(new Intent(MainActivity.this, SimpleAlertDialogActivity.class));
                } else if (position == 2) {
                    startActivity(new Intent(MainActivity.this, TempActivity.class));
                }

            }
        });
    }
}
