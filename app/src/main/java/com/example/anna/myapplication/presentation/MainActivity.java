package com.example.anna.myapplication.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import com.example.anna.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonProfile = findViewById(R.id.open_profile);
        buttonProfile.setOnClickListener(view -> {
            Intent startActivity = new Intent(MainActivity.this, ArticleListActivity.class);
            startActivity(startActivity);
        });
    }
}