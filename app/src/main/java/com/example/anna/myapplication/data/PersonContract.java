package com.example.anna.myapplication.data;

import android.provider.BaseColumns;

public interface PersonContract extends BaseColumns {


    String TABLE_NAME = "personsTable";

    String NAME = "name";
    String NOTE = "note";
}
