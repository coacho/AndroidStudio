package com.example.application1;


import android.app.PendingIntent;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Evenement.class}, version = 1)

public abstract class EvenementDataBase extends RoomDatabase {

    public abstract EvenementDao evenementDao();

    public static volatile EvenementDataBase INSTANCE;


    static EvenementDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EvenementDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EvenementDataBase.class, "evenement_database").build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PeuplementDbAsync(INSTANCE).execute();
                }
            };

    private static class PeuplementDbAsync extends AsyncTask<Void, Void, Void>{

        private final EvenementDao eDao;

        PeuplementDbAsync(EvenementDataBase db) { eDao = db.evenementDao(); }

        @Override
        protected Void doInBackground(Void... voids) {
            eDao.deleteAll();
            Evenement unNouvelEvenement= new Evenement("Nouvel evenement!!test!!",0);
            eDao.insert(unNouvelEvenement);
            unNouvelEvenement = new Evenement("!!!!test2!!!!",1);
            eDao.insert(unNouvelEvenement);
            return null;
        }
    }

}
