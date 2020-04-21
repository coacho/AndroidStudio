package com.example.application1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class AfficheEvenement extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceStates) {
        super.onCreate(savedInstanceStates);
        setContentView(R.layout.content_affiche_evenement_test);

        Intent intent = getIntent();

        String messageRecu = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView MonDiffuseur = findViewById(R.id.diffuseMessage);
        WebView MonDiffuseurWeb = findViewById(R.id.navigateur);
    }

}
