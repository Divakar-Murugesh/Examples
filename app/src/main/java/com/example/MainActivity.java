package com.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Array of strings...
        String[] strings = {"Animations", "Simple Alert dialog with ListView"};

        ListView listView1 = (ListView) findViewById(R.id.listView1);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);

        listView1.setAdapter(adapter);
    }
}
