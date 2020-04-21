package com.example.application1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EvenementListAdapter extends RecyclerView.Adapter<EvenementListAdapter.EvenementViewHolder> {

    public class EvenementViewHolder extends RecyclerView.ViewHolder {

        private final TextView evenementItemView;

        public EvenementViewHolder(@NonNull View evenementView) {
            super(evenementView);
            evenementItemView = evenementView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater monInflateur;
    private List<Evenement> mesEvenements; //cache des evenments
    /*
     *       ( °)>
     *    ___//         ( °)< coin coin
     *   ( <  )       (  < )
     *    |_ |_
     *
     * */
    EvenementListAdapter(Context context) {monInflateur = LayoutInflater.from(context);}

    @Override
    public EvenementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = monInflateur.inflate(R.layout.recyclerview_item, parent, false);
        return new EvenementViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EvenementListAdapter.EvenementViewHolder holder, int position) {
        if (mesEvenements != null) {
            Evenement evenementEnCour = mesEvenements.get(position);
            holder.evenementItemView.setText(evenementEnCour.getNomEvenement());
        }
        else { // kein evenement nada none
            holder.evenementItemView.setText("Glandu tu fait rien!!! Du bist Scheiß");
        }

    }

   /* @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }*/

    public void setMesEvenements(List<Evenement> evenements) {
        mesEvenements = evenements;
        notifyDataSetChanged();
    }

    public void  moveItem(int oldPos, int newPos) {
        Evenement unEvenment = mesEvenements.get(oldPos);
        mesEvenements.remove(oldPos);
        mesEvenements.add(newPos,unEvenment);
        notifyDataSetChanged();
    }

    public Evenement getEvenementALaPosition(int position){
        return mesEvenements.get(position);
    }

    public int getItemCount(){
        if(mesEvenements !=null)
            return mesEvenements.size();
        else return 0;
    }

}
