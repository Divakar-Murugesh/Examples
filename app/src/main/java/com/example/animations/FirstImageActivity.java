package com.example.animations;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.R;

public class FirstImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_image);

        final ImageView sharedImage = (ImageView) findViewById(R.id.sharedimage);
        sharedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This is where the magic happens.
                // makeSceneTransitionAnimation takes a context, view,
                // a name for the target view.
                ActivityOptions options = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation(FirstImageActivity.this, sharedImage, "sharedImage");
                }
                Intent intent = new Intent(FirstImageActivity.this, SecondImageActivity.class);
                if (options != null) {
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
    }
}
