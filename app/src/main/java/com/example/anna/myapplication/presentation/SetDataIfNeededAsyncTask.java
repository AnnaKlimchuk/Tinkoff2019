package com.example.anna.myapplication.presentation;

import android.os.AsyncTask;

import com.example.anna.myapplication.domain.Person;

import java.util.List;

public class SetDataIfNeededAsyncTask extends AsyncTask<Void, Void, List<Person>> {

    @Override
    protected List<Person> doInBackground(Void... voids) {
        List<Person> personList = MyApplication.getPersonDao().loadAll();

        if (personList.size() == 0) {
            new ParseDataFromInternetAsyncTask().execute();
        }
        return personList;
    }

    @Override
    protected void onPostExecute(List<Person> personList) {
        super.onPostExecute(personList);
    }
}
