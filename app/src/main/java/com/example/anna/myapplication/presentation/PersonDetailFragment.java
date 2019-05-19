package com.example.anna.myapplication.presentation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anna.myapplication.R;
import com.example.anna.myapplication.domain.Person;

public class PersonDetailFragment extends Fragment {

    private TextView textDescription, personTextView;
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_PERSON_ID = "personId";
    private long personId = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view =  inflater.inflate(R.layout.person_detail, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            personId = bundle.getLong(ARG_PERSON_ID, 1);
        }

        textDescription = view.findViewById(R.id.person_description);
        personTextView = view.findViewById(R.id.person_name);

        new LoadChanges().execute();
        return view;
    }

    private class LoadChanges extends AsyncTask<Void, Void, Person> {

        @Override
        protected Person doInBackground(Void ... voids) {
            // ROOM
            Person person = MyApplication.getPersonDao().getById(personId);
            return person;
        }

        @Override
        protected void onPostExecute(final Person person) {
            super.onPostExecute(person);

            personTextView.setText(person.getName());
            textDescription.setText(person.getNote());
        }
    }
}