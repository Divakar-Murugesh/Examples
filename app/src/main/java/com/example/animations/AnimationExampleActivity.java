package com.example.animations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.R;

public class AnimationExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        final LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.layout1);
        final TextView textView1 = (TextView) findViewById(R.id.textView1);
        final Button button1 = (Button) findViewById(R.id.button1);

        // Start Action
        button1.animate().translationX(350).setDuration(1000).withStartAction(new Runnable() {
            public void run() {
                textView1.animate().translationX(500 - (textView1.getWidth() / 2)).setDuration(1000).withLayer();

                // End Action
                button1.animate().alpha(0).withEndAction(new Runnable() {
                    public void run() {
                        // Remove view from parent layout
                        linearLayout1.removeView(button1);
                    }
                });
            }
        });

    }
}
