package com.example.biblio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.biblio.R;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button Seconnecter,Sinscrire;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        init();

        Sinscrire.setOnClickListener(this);
        Seconnecter.setOnClickListener(this);

    }

    void init(){

        Seconnecter = findViewById(R.id.btn_signin);
        Sinscrire = findViewById(R.id.btn_inscri_welcome);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_signin:
                startActivity(new Intent(WelcomeActivity.this,Seconnecte.class));
                break;
            case R.id.btn_inscri_welcome:
                startActivity(new Intent(WelcomeActivity.this,Inscrir.class));
                    break;
        }

    }
}
