package com.example.animations;

import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    public void startAnimation(View view) {
        float dest = 0;
        ImageView aniView = (ImageView) findViewById(R.id.imageView1);
        switch (view.getId()) {

            case R.id.Button01:
                dest = 360;
                if (aniView.getRotation() == 360) {
                    System.out.println(aniView.getAlpha());
                    dest = 0;
                }
                ObjectAnimator animation1 = ObjectAnimator.ofFloat(aniView,
                        "rotation", dest);
                animation1.setDuration(2000);
                animation1.start();
                // Show how to load an animation from XML
                // Animation animation1 = AnimationUtils.loadAnimation(this,
                // R.anim.myanimation);
                // animation1.setAnimationListener(this);
                // animatedView1.startAnimation(animation1);
                break;

            case R.id.Button02:
                // shows how to define a animation via code
                // also use an Interpolator (BounceInterpolator)
                Paint paint = new Paint();
                TextView aniTextView = (TextView) findViewById(R.id.textView1);
                float measureTextCenter = paint.measureText(aniTextView.getText()
                        .toString());
                dest = 0 - measureTextCenter;
                if (aniTextView.getX() < 0) {
                    dest = 0;
                }
                ObjectAnimator animation2 = ObjectAnimator.ofFloat(aniTextView,
                        "x", dest);
                animation2.setDuration(2000);
                animation2.start();
                break;

            case R.id.Button03:
                // demonstrate fading and adding an AnimationListener

                dest = 1;
                if (aniView.getAlpha() > 0) {
                    dest = 0;
                }
                ObjectAnimator animation3 = ObjectAnimator.ofFloat(aniView,
                        "alpha", dest);
                animation3.setDuration(2000);
                animation3.start();
                break;

            case R.id.Button04:

                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(aniView, "alpha",
                        0f);
                fadeOut.setDuration(2000);
                ObjectAnimator mover = ObjectAnimator.ofFloat(aniView,
                        "translationX", -500f, 0f);
                mover.setDuration(2000);
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(aniView, "alpha",
                        0f, 1f);
                fadeIn.setDuration(2000);
                AnimatorSet animatorSet = new AnimatorSet();

                animatorSet.play(mover).with(fadeIn).after(fadeOut);
                animatorSet.start();
                break;

            default:
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        return true;
    }
}
