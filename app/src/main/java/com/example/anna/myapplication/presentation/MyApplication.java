package com.example.anna.myapplication.presentation;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.anna.myapplication.data.AppDatabase;
import com.example.anna.myapplication.data.PersonDao;

public class MyApplication extends Application {

    private static AppDatabase database;

    public static PersonDao getPersonDao(){
        return database.PersonDao();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // ROOM
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();

        setInitialData();
    }

    public static void setInitialData(){
        new SetDataIfNeededAsyncTask().execute();
    }
}