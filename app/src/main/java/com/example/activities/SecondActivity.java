package com.example.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.R;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    private ObjectAnimator animation1;
    private ObjectAnimator animation2;
    private Button button;
    private Random random;
    private AnimatorSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        random = new Random();

        set = createAnimation();
        set.start();
        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                int nextX = random.nextInt(500);
                int nextY = random.nextInt(500);
                animation1 = ObjectAnimator.ofFloat(button, "x", button.getX(), nextX);
                animation1.setDuration(1400);
                animation2 = ObjectAnimator.ofFloat(button, "y", button.getY(), nextY);
                animation2.setDuration(1400);
                set.playTogether(animation1, animation2);
                set.start();
            }
        });
    }

    public void onClick(View view) {
        String string = button.getText().toString();
        int hitTarget = Integer.valueOf(string) + 1;
        button.setText(String.valueOf(hitTarget));

        startActivity(new Intent(SecondActivity.this, FirstImageActivity.class));
    }

    private AnimatorSet createAnimation() {
        int nextX = random.nextInt(500);
        int nextY = random.nextInt(500);
        button = (Button) findViewById(R.id.button1);
        animation1 = ObjectAnimator.ofFloat(button, "x", nextX);
        animation1.setDuration(1400);
        animation2 = ObjectAnimator.ofFloat(button, "y", nextY);
        animation2.setDuration(1400);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animation1, animation2);
        return set;
    }
}