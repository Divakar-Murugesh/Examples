package com.example.activities

import android.app.ActivityOptions
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.example.R
import com.example.adapters.ListViewAdapter
import com.example.interfaces.AsyncTaskResponseHandler
import com.example.models.SampleDataModel
import com.example.utils.AsyncTaskSample
import java.util.*


class MainActivity : AppCompatActivity() {

    private val PERMISSIONS_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonPageDetails = findViewById(R.id.button_pageDetails) as Button
        buttonPageDetails.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this@MainActivity).create()
            alertDialog.setTitle("Android UI Controls in this page")
            alertDialog.setMessage("" +
                    "1. RelativeLayout\n" +
                    "2. Button\n" +
                    "3. ListView\n" +
                    "Now you are seen all the above items in AlertDialog." +
                    "")
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "NEUTRAL Button") { dialog, which ->
                Toast.makeText(this@MainActivity, "NEUTRAL Button Clicked!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "POSITIVE Button") { dialog, which ->
                Toast.makeText(this@MainActivity, "POSITIVE Button Clicked!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NEGATIVE Button") { dialog, which ->
                Toast.makeText(this@MainActivity, "NEGATIVE Button Clicked!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            alertDialog.show()
        }

        val strings = arrayOf("Android UI Controls", "Animations", "Simple Alert dialog with ListView", "Data change listener for Activity with multiple fragments", "Requesting Permissions at Run Time", "Async task", "Data passing with Intent")

        val listView1 = findViewById(R.id.listView1) as ListView

        val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, strings)

        listView1.adapter = adapter

        listView1.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, l ->
            if (position == 0) {

                val intent = Intent(this@MainActivity, AndroidUIControlsActivity::class.java)
                val options = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.width, view.height)
                startActivity(intent, options.toBundle())

            } else if (position == 1) {

                val intent = Intent(this@MainActivity, AnimationExampleActivity::class.java)
                val options = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.width, view.height)
                startActivity(intent, options.toBundle())

            } else if (position == 2) {

                // Here I add static data...
                val models = ArrayList<SampleDataModel>()

                models.add(SampleDataModel(1, "ONE", "12 July 2017 06:30 PM"))
                models.add(SampleDataModel(2, "TWO", "12 July 2017 06:30 PM"))
                models.add(SampleDataModel(3, "THREE", "12 July 2017 06:30 PM"))
                models.add(SampleDataModel(4, "FOUR", "12 July 2017 06:30 PM"))
                models.add(SampleDataModel(5, "FIVE", "12 July 2017 06:30 PM"))
                models.add(SampleDataModel(6, "SIX", "12 July 2017 06:30 PM"))
                models.add(SampleDataModel(7, "SEVEN", "12 July 2017 06:30 PM"))

                val alertDialogBuilder = AlertDialog.Builder(this@MainActivity)
                alertDialogBuilder.setTitle("Awesome alert")
                        .setCancelable(false)
                        .setAdapter(ListViewAdapter(this@MainActivity, models)) { dialog, position ->
                            // on item click...
                            Toast.makeText(this@MainActivity, "You performed ListView item click event at index position : " + position, Toast.LENGTH_LONG).show()
                            dialog.cancel()
                        }
                        .setPositiveButton("Close") { dialog, id -> dialog.cancel() }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()

            } else if (position == 3) {

                val intent = Intent(this@MainActivity, FragmentsActivity::class.java)
                val options = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.width, view.height)
                startActivity(intent, options.toBundle())

            } else if (position == 4) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSIONS_CODE)

                } else {

                    Toast.makeText(this@MainActivity, "This device is lower version. So you can do your operation directly If you added permissions in AndroidManifest.", Toast.LENGTH_LONG).show()
                    // This device is lower version. So you can do your operation directly. If you added permissions in AndroidManifest...
                    // Or visit following link to read about permission check.
                    // https://stackoverflow.com/questions/7203668/how-permission-can-be-checked-at-runtime-without-throwing-securityexception

                }

            } else if (position == 5) {

                AsyncTaskSample(object : AsyncTaskResponseHandler {
                    override fun processFinish(result: String) {
                        // process result from Async task.
                        Toast.makeText(this@MainActivity, "Completed!!!", Toast.LENGTH_SHORT).show()
                    }
                }).execute()

            } else if (position == 6) {

                // Here I add static data...
                val models = ArrayList<SampleDataModel>()

                models.add(SampleDataModel(1, "ONE", "12 July 2017 06:30 PM"))
                models.add(SampleDataModel(2, "TWO", "12 July 2017 06:30 PM"))
                models.add(SampleDataModel(3, "THREE", "12 July 2017 06:30 PM"))

                val alertDialogBuilder = AlertDialog.Builder(this@MainActivity)
                alertDialogBuilder.setTitle("Select Item")
                        .setCancelable(false)
                        .setAdapter(ListViewAdapter(this@MainActivity, models)) { dialog, position ->
                            // on item click...
                            val intent = Intent(this@MainActivity, DataPreviewActivity::class.java)
                            intent.putExtra("KEY", models.get(position).notificationText)
                            val options = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.width, view.height)
                            startActivity(intent, options.toBundle())
                            dialog.cancel()
                        }
                        .setPositiveButton("Close") { dialog, id -> dialog.cancel() }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_CODE -> if (grantResults.isNotEmpty() && grantResults[grantResults.size - 1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "permission accepted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@MainActivity, "permission denied", Toast.LENGTH_LONG).show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}