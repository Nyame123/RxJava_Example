package com.bisapp.rxjavaexamples.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bisapp.rxjavaexamples.database.dao.ReadingDao;

@Database(entities = {Readings.class},version = AppDatabase.VERSION)
public abstract class AppDatabase extends RoomDatabase {
  public static final int VERSION = 1;
  public static final String NAME = "readings";

  public abstract ReadingDao readingDao();

}
