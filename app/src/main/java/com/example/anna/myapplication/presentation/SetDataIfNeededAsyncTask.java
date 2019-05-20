package com.example.anna.myapplication.presentation;

import android.os.AsyncTask;

import com.example.anna.myapplication.domain.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SetDataIfNeededAsyncTask extends AsyncTask<Void, Void, List<Person>> {

    private static final String URL = "https://api.tinkoff.ru/v1/news";

    private HttpURLConnection urlConnection = null;
    private BufferedReader reader = null;
    private String resultJson = "";

    @Override
    protected List<Person> doInBackground(Void... voids) {
        List<Person> personList = MyApplication.getPersonDao().loadAll();

        if (personList.size() == 0) {
            try {
                java.net.URL url = new URL(URL);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                resultJson = stringBuilder.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            createPersonFromJson(resultJson);
            return null;
        }
        return personList;
    }

    @Override
    protected void onPostExecute(List<Person> personList) {
        super.onPostExecute(personList);
    }

    private void createPersonFromJson(String strJson) {

        JSONObject dataJsonObj = null;
        String name = "", note = "";

        try {
            dataJsonObj = new JSONObject(strJson);
            JSONArray persons = dataJsonObj.getJSONArray("payload");

            for (int i = 0; i < persons.length(); i++) {

                JSONObject newPerson = persons.getJSONObject(i);

                name = newPerson.getString("name");
                note = newPerson.getString("text");

                Person person = new Person();
                person.setName(name);
                person.setNote(note);

                // Room
                long personId = MyApplication.getPersonDao().loadAll().size() + 1;
                person.setId(personId);
                MyApplication.getPersonDao().insert(person);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
