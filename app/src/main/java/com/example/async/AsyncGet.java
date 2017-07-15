package com.example.async;

import android.os.AsyncTask;

public class AsyncGet extends AsyncTask<Void, Void, String> {

    public interface AsyncGetResponse {
        void processFinish(String result);
    }

    private AsyncGetResponse asyncGetResponse = null;

    public AsyncGet(AsyncGetResponse asyncGetResponse) {
        this.asyncGetResponse = asyncGetResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // show progress dialog. If you need
    }

    @Override
    protected String doInBackground(Void... voids) {

        String result = null;

        // do your background work here. Like API calls.

        return result;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

        // update progress percentage. If you need.
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        // hide progress dialog. If showing.

        // pass result value.
        if (asyncGetResponse != null) {
            asyncGetResponse.processFinish(result);
        }
    }

}
