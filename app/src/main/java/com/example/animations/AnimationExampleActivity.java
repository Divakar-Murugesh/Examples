package com.example.animations;

import android.animation.LayoutTransition;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.R;

public class AnimationExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        LayoutTransition l = new LayoutTransition();
        l.enableTransitionType(LayoutTransition.CHANGING);
        final LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.layout1);
        linearLayout1.setLayoutTransition(l);

        final TextView textView1 = (TextView) findViewById(R.id.textView1);
        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);

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

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                linearLayout1.addView(new Button(AnimationExampleActivity.this));

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AnimationExampleActivity.this, SecondActivity.class);
                ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.getWidth(), view.getHeight());
                startActivity(intent, options.toBundle());

            }
        });

    }

}
