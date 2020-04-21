package com.example.application1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "WOLOLO";
    private static final int NEW_EVENEMENT_ACTIVITY_REQUEST_CODE = 1 ;
    public EvenementListAdapter adapter;
    private EvenementViewModel unEvenementViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolb = findViewById(R.id.toolb);
        setSupportActionBar(toolb);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NouvelleEvenement.class);
                startActivityForResult( intent, NEW_EVENEMENT_ACTIVITY_REQUEST_CODE);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new EvenementListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        unEvenementViewModel = ViewModelProviders.of(this).get(EvenementViewModel.class);
        unEvenementViewModel.getMesEvenements().observe(this, new Observer<List<Evenement>>(){
            public void onChanged(List<Evenement> evenements){
                adapter.setMesEvenements(evenements);
            }
        });
    }
    ItemTouchHelper monHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP | ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder origine,
                              @NonNull RecyclerView.ViewHolder destination) {
            int position_origin = origine.getAdapterPosition();
            int position_destination = destination.getAdapterPosition();
            adapter.moveItem(position_origin, position_destination);
            //adapter.notifyItemMoved(position_origin,position_destination);
            return true;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            Evenement monEvenement = adapter.getEvenementALaPosition(position);

            if (direction == ItemTouchHelper.LEFT) {
                Toast.makeText(MainActivity.this, "Suppression de " +
                        monEvenement.getNomEvenement(), Toast.LENGTH_LONG).show();

                unEvenementViewModel.delete(monEvenement);
            } else if (direction == ItemTouchHelper.RIGHT) {

                String nomEvenement = monEvenement.getNomEvenement();

                Toast.makeText(MainActivity.this, "Ceci est une phrase " +
                        nomEvenement, Toast.LENGTH_LONG).show();

                Intent intentmessage = new Intent(MainActivity.this,
                        AfficheEvenement.class);
                // intent.putExtra(MainActivity.EXTRA_MESSAGE,nomMatiere.getText().toString()) ;
                intentmessage.putExtra(MainActivity.EXTRA_MESSAGE, nomEvenement);
                adapter.notifyDataSetChanged();
                startActivity(intentmessage);

            }

        }
    });

    @Override
    protected void onStop() {
        super.onStop();
        for (int i = 0; i < adapter.getItemCount(); i++) {
            Evenement eveASauvegarder = adapter.getEvenementALaPosition(i);
            eveASauvegarder.setOrdreAffiche(i);
            unEvenementViewModel.update(eveASauvegarder);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_EVENEMENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Evenement competence = new Evenement(data.getStringExtra(NouvelleEvenement.EXTRA_REPLY),
                    adapter.getItemCount() + 1);
            unEvenementViewModel.insert(competence);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.supprime_tous_les_evenements) {
            Toast.makeText(this, "Tous les évenements sont supprimés",
                    Toast.LENGTH_SHORT).show();
            unEvenementViewModel.supprimeTous();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
