package com.example.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.R;
import com.example.adapters.NotificationListAdapter;
import com.example.models.NotificationModel;
import com.example.utils.AsyncGet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int PERMISSIONS_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPageDetails = (Button) findViewById(R.id.button_pageDetails);
        buttonPageDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Android UI Controls in this page");
                alertDialog.setMessage("" +
                        "1. RelativeLayout\n" +
                        "2. Button\n" +
                        "3. ListView\n" +
                        "Now you are seen all the above items in AlertDialog." +
                        "");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "NEUTRAL Button", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "NEUTRAL Button Clicked!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "POSITIVE Button", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "POSITIVE Button Clicked!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NEGATIVE Button", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "NEGATIVE Button Clicked!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                alertDialog.show();

            }
        });

        String[] strings = {
                "Android UI Controls",
                "Animations",
                "Simple Alert dialog with ListView",
                "Data passing between single activity to multiple fragments",
                "permission",
                "Async task",
                "Data passing with intent in \"kotlin\""
        };

        ListView listView1 = (ListView) findViewById(R.id.listView1);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);

        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (position == 0) {

                    startActivity(new Intent(MainActivity.this, AndroidUIControlsActivity.class));

                } else if (position == 1) {

                    startActivity(new Intent(MainActivity.this, AnimationExampleActivity.class));

                } else if (position == 2) {

                    // Here I add static data...
                    List<NotificationModel> notificationModels = new ArrayList<NotificationModel>();

                    notificationModels.add(new NotificationModel(1, "ONE", "12 July 2017 06:30 PM"));
                    notificationModels.add(new NotificationModel(2, "TWO", "12 July 2017 06:30 PM"));
                    notificationModels.add(new NotificationModel(3, "THREE", "12 July 2017 06:30 PM"));
                    notificationModels.add(new NotificationModel(4, "FOUR", "12 July 2017 06:30 PM"));
                    notificationModels.add(new NotificationModel(5, "FIVE", "12 July 2017 06:30 PM"));
                    notificationModels.add(new NotificationModel(6, "SIX", "12 July 2017 06:30 PM"));
                    notificationModels.add(new NotificationModel(7, "SEVEN", "12 July 2017 06:30 PM"));

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setTitle("Awesome alert")
                            .setCancelable(false)
                            .setAdapter(new NotificationListAdapter(MainActivity.this, notificationModels), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int position) {

                                    // on item click...
                                    Toast.makeText(MainActivity.this, "You performed ListView item click event at index position : " + position, Toast.LENGTH_LONG).show();
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    dialog.cancel();

                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                } else if (position == 3) {

                    startActivity(new Intent(MainActivity.this, TempActivity.class));

                } else if (position == 4) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION,}, PERMISSIONS_CODE);

                    } else {

                        Toast.makeText(MainActivity.this, "This device is lower version. So you can do your operation directly. If you added permissions in AndroidManifest...", Toast.LENGTH_LONG).show();
                        // This device is lower version. So you can do your operation directly. If you added permissions in AndroidManifest...
                        // Or visit following link to read about permission check.
                        // https://stackoverflow.com/questions/7203668/how-permission-can-be-checked-at-runtime-without-throwing-securityexception

                    }

                } else if (position == 5) {

                    new AsyncGet(new AsyncGet.AsyncGetResponse() {
                        @Override
                        public void processFinish(String result) {
                            // process result from Async task.
                            Toast.makeText(MainActivity.this, "Completed!!!", Toast.LENGTH_SHORT).show();
                        }
                    }).execute();

                } else if (position == 6) {

                    startActivity(new Intent(MainActivity.this, KotlinFirstActivity.class));

                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_CODE:
                if (grantResults.length > 0 && grantResults[grantResults.length - 1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "permission accepted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "permission denied", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }
}
