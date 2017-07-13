package com.example.animations;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.R;

public class AnimationExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        Button button1 = (Button) findViewById(R.id.button1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // Using hardware layer
            textView1.animate().translationX(400).withLayer();
        } else {
            textView1.animate().translationX(400);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // Using hardware layer
            button1.animate().translationY(400).withLayer();
        } else {
            button1.animate().translationY(400);
        }
    }
}
