package com.bisapp.rxjavaexamples;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.bisapp.rxjavaexamples.database.AppDatabase;

import static com.bisapp.rxjavaexamples.database.AppDatabase.NAME;

public class App extends Application {
 private static AppDatabase appDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        initRoomDatabase(getApplicationContext());

    }

    private static void initRoomDatabase(Context context){

        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class,NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build(); //adding callback from Room
        }
    }

    public static AppDatabase getAppDatabase(Context context){
        if (appDatabase == null){
            initRoomDatabase(context);
        }
        return appDatabase;
    }
}