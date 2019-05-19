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

public class ParseDataFromInternetAsyncTask extends AsyncTask<Void, Void, Void> {

    private static final String URL = "https://api.tinkoff.ru/v1/news";

    private HttpURLConnection urlConnection = null;
    private BufferedReader reader = null;
    private String resultJson = "";

    @Override
    protected Void doInBackground(Void... params) {

        try {
            URL url = new URL(URL);

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

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
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