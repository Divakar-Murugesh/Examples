package com.example.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.R;
import com.example.adapters.FragmentAdapter;
import com.example.interfaces.FragmentCommunicator;
import com.example.fragments.FirstFragment;
import com.example.fragments.SecondFragment;
import com.example.fragments.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

public class TempActivity extends AppCompatActivity {

    public List<FragmentCommunicator> fragmentCommunicators = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"Value One", "Value Two", "Value Three"}));
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                for (FragmentCommunicator fragmentCommunicator : fragmentCommunicators) {
                    fragmentCommunicator.passDataToFragment(spinner1.getSelectedItem().toString());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ViewPager viewPager1 = (ViewPager) findViewById(R.id.viewPager1);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment(), "FirstFragment");
        adapter.addFragment(new SecondFragment(), "SecondFragment");
        adapter.addFragment(new ThirdFragment(), "ThirdFragment");
        viewPager1.setAdapter(adapter);

        viewPager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (FragmentCommunicator fragmentCommunicator : fragmentCommunicators) {
                    fragmentCommunicator.passDataToFragment(spinner1.getSelectedItem().toString());
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
