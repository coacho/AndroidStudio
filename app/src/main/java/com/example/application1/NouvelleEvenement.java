package com.example.application1;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

class NouvelleEvenement extends AppCompatActivity {


    public static final String EXTRA_REPLY = "com.example.android.competencelistsql.REPLY";


    private EditText editionEvenement;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_evenement);
        editionEvenement = findViewById(R.id.edit_evenement);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editionEvenement.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {
                    String evenement = editionEvenement.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, evenement);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
