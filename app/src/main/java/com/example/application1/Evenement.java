package com.example.application1;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "evenement_table")
public class Evenement {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="NomEvenement")
    private String nomEvenement;

    @NonNull
    @ColumnInfo(name="ordreAffiche")
    private int ordreAffiche;

    public Evenement(String nomEvenement, int ordreAffiche){
        this.nomEvenement = nomEvenement;
        this.ordreAffiche = ordreAffiche;
    }

    public String getNomEvenement() { return nomEvenement; }

    public int getOrdreAffiche() { return ordreAffiche; }

    public void setOrdreAffiche(int ordreAffiche) { this.ordreAffiche = ordreAffiche; }

    public void setNomEvenement(@NonNull String nomEvenement) { this.nomEvenement = nomEvenement; }
}
