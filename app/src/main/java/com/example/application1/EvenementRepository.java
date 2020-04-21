package com.example.application1;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import java.util.List;

public class EvenementRepository {

    private EvenementDao monEvenementDao;
    private LiveData<List<Evenement>> mesEvenements;

    public EvenementRepository(Application application) {
        EvenementDataBase dataBase = EvenementDataBase.getDatabase(application);
        monEvenementDao = dataBase.evenementDao();//Etienne Dao
        mesEvenements = monEvenementDao.getTousEvenements();
    }

    public LiveData<List<Evenement>> getMesEvenements() { return mesEvenements; }

    public void insert(Evenement unEvenement) { new insertAsyncTask(monEvenementDao).execute(unEvenement); }

    public void updateEvenement(Evenement unEvenement) { new updateEvenementAsyncTask(monEvenementDao).execute(unEvenement); }

    public void deleteEvenement(Evenement unEvenement) { new deleteEvenementAsyncTask(monEvenementDao).execute(unEvenement); }

    public void supprimeTous( ) { new supprimeTousEvenementAsyncTask(monEvenementDao).execute(); }




    private static class insertAsyncTask extends AsyncTask<Evenement, Void, Void > {

        private EvenementDao maTacheDao; //monEvenementDao existe déjà

        insertAsyncTask(EvenementDao dao) { maTacheDao = dao; }

        @Override
        protected Void doInBackground(Evenement... evparams) {
            maTacheDao.insert(evparams[0]);
            return null;
        }
    }

    private class updateEvenementAsyncTask extends AsyncTask<Evenement,Void,Void>{

        private EvenementDao monDao;
        public updateEvenementAsyncTask(EvenementDao dao) { monDao = dao; }

        @Override
        protected Void doInBackground(Evenement... evparams) {
            monDao.updateEvenement(evparams[0]);
            return null;
        }
    }

    private static class deleteEvenementAsyncTask extends AsyncTask<Evenement,Void,Void> {

        private EvenementDao maTacheDao;

        deleteEvenementAsyncTask(EvenementDao dao) { maTacheDao = dao; }

        @Override
        protected Void doInBackground(final Evenement... evparams) {
            maTacheDao.deleteEvenement(evparams[0]);
            return null;
        }
    }

    private static class supprimeTousEvenementAsyncTask extends AsyncTask<Void,Void,Void> {

        private EvenementDao maTacheDao;

        public supprimeTousEvenementAsyncTask(EvenementDao uneDao) { maTacheDao= uneDao; }

        @Override
        protected Void doInBackground(Void... voids) {
            maTacheDao.deleteAll();
            return null;
        }
    }
}
