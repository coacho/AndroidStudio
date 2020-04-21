package com.example.application1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
interface EvenementDao {

    //ts supprimer
    @Query("DELETE FROM evenement_table")
    void deleteAll();

    @Insert
    void insert(Evenement unNouvelEvenement);

    @Query("SELECT * from evenement_table ORDER BY ordreAffiche ASC")
    LiveData<List<Evenement>> getTousEvenements();

    // @Query("SELECT MAX(ordreAffiche) from evenement_table")
    // int getMaxOrdre();

    @Update
    void updateEvenement(Evenement evenement);

    @Delete
    void deleteEvenement(Evenement evparam);
}
