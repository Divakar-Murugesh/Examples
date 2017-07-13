package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                            public void onClick(DialogInterface dialog, int id) {

                                // on item click...

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

            }
        });
    }
}
