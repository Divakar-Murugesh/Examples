package com.example.utils

import android.os.AsyncTask
import com.example.interfaces.AsyncTaskResponseHandler


class AsyncTaskSample(val responseHandler: AsyncTaskResponseHandler) : AsyncTask<Void, Void, String>() {

    override fun onPreExecute() {
        super.onPreExecute()

        // show progress dialog. If you need
    }

    override fun doInBackground(vararg voids: Void): String {

        var result: String? = null

        // do your background work here. Like API calls.
        result = "assign API response"

        return result
    }

    override fun onProgressUpdate(vararg values: Void) {
        super.onProgressUpdate(*values)

        // update progress percentage. If you need.
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)

        // hide progress dialog. If showing.

        // pass result value.
        responseHandler.processFinish(result)
    }

}