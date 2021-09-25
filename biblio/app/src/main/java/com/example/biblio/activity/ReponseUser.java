package com.example.biblio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.biblio.R;
import com.example.biblio.utils.TinyDB;

public class ReponseUser extends AppCompatActivity implements View.OnClickListener {

    EditText e1,e2,e3,e4,e5;
    TextView t1,t2,t3,t4,t5;
    Button B1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reponse_user);
        init();
        t1.setText(new TinyDB(this).getString("question1"));
        t2.setText(new TinyDB(this).getString("question2"));
        t3.setText(new TinyDB(this).getString("question3"));
        t4.setText(new TinyDB(this).getString("question4"));
        t5.setText(new TinyDB(this).getString("question5"));

        B1.setOnClickListener(this);

    }

    private void init() {
        e1=findViewById(R.id.input_rep11);
        e2=findViewById(R.id.input_rep22);
        e3=findViewById(R.id.input_rep33);
        e4=findViewById(R.id.input_rep44);
        e5=findViewById(R.id.input_rep55);
        t1=findViewById(R.id.rep11);
        t2=findViewById(R.id.rep22);
        t3=findViewById(R.id.rep33);
        t4=findViewById(R.id.rep44);
        t5=findViewById(R.id.rep55);
        B1=findViewById(R.id.Terminer11);

    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(ReponseUser.this,QestionUser.class));

    }
}
