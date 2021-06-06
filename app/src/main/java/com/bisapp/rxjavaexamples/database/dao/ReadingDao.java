package com.bisapp.rxjavaexamples.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.bisapp.rxjavaexamples.database.Readings;

import java.util.List;

@Dao
public interface ReadingDao {

    @Insert
    void add(Readings readings);

    @Delete
    void delete(Readings readings);

    @Query("Select * from readings where name like :query")
    List<Readings> filterReadings(String query);

}
