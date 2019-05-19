package com.example.anna.myapplication.presentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anna.myapplication.R;

public class ArticleListFragment extends Fragment {

    private DataAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.person_list_fragment, container, false);

        recyclerView = view.findViewById(R.id.persons_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DataAdapter(personId ->
                ((ArticleListActivity) getActivity()).changeFragment2(personId));
        new SetArticleToAdapterAsyncTask().execute(adapter);

        recyclerView.setAdapter(adapter);
        return view;
    }
}