package com.example.androidmobile_roomdatabasecustom;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Location.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LocationDao locationDao();
}
