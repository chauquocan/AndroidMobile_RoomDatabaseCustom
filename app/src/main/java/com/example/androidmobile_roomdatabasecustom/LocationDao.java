package com.example.androidmobile_roomdatabasecustom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocationDao {
    @Query("select * from location")
    List<Location> getAll();
    @Insert
    void insertAll(Location... locations);
    @Delete
    void delete(Location location);
    @Update
    void update(Location location);
}
