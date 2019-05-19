package com.example.anna.myapplication.presentation;

import android.os.AsyncTask;

public class SetArticleToAdapterAsyncTask extends AsyncTask<DataAdapter, Void, Void> {

    @Override
    protected Void doInBackground(DataAdapter ... adapters){
        DataAdapter adapter = adapters[0];
        // ROOM
        adapter.setPersons(MyApplication.getPersonDao().loadAll());
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}
