package com.example.application1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

class EvenementViewModel  extends AndroidViewModel {

    private EvenementRepository monRepository;
    private LiveData<List<Evenement>> mesEvenements;


    public EvenementViewModel(Application application) {
        super(application);
        monRepository = new EvenementRepository(application);
        mesEvenements = monRepository.getMesEvenements();
    }



    public LiveData<List<Evenement>> getMesEvenements() { return mesEvenements;}

    public void insert(Evenement unEvenement) { monRepository.insert(unEvenement); }

    public void update(Evenement unEvenement) { monRepository.updateEvenement(unEvenement); }

    public void delete(Evenement unEvenement) { monRepository.deleteEvenement(unEvenement); }

    public void  supprimeTous() { monRepository.supprimeTous();}

}