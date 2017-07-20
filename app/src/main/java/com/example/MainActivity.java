package com.example;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.animations.AnimationExampleActivity;
import com.example.async.AsyncGet;
import com.example.data_passing_between_single_activity_to_multiple_fragments.TempActivity;
import com.example.kotlin.SplashActivity;
import com.example.simple_alert_dialog_with_list.SimpleAlertDialogActivity;

public class MainActivity extends AppCompatActivity {

    private final int PERMISSIONS_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] strings = {
                "Animations",
                "Simple Alert dialog with ListView",
                "Data passing between single activity to multiple fragments",
                "permission",
                "Async task",
                "kotlin for data passing with intent"
        };

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

                } else if (position == 3) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION,}, PERMISSIONS_CODE);

                    } else {

                        // You can do you operation directly. If you added permissions in AndroidManifest...
                        // Or visit following link to read about permission check.
                        // https://stackoverflow.com/questions/7203668/how-permission-can-be-checked-at-runtime-without-throwing-securityexception

                    }

                } else if (position == 4) {

                    new AsyncGet(new AsyncGet.AsyncGetResponse() {
                        @Override
                        public void processFinish(String result) {
                            // process result from Async task.
                            Toast.makeText(MainActivity.this, "Completed!!!", Toast.LENGTH_SHORT).show();
                        }
                    }).execute();

                } else if (position == 5) {

                    startActivity(new Intent(MainActivity.this, SplashActivity.class));

                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_CODE:
                if (grantResults.length > 0 && grantResults[grantResults.length - 1] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("permission", "accepted");
                } else {
                    Log.d("permission", "denied");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }
}
