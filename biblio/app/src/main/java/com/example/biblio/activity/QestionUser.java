package com.example.biblio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.biblio.R;
import com.example.biblio.utils.TinyDB;

public class QestionUser extends AppCompatActivity implements View.OnClickListener {
    TextView T1,T2,T3,T4;
    TextView t4,T5;
    Button B1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_user);
        init();
        T1.setText("1°)  "+new TinyDB(this).getString("rep1"));
        T2.setText("2°)  "+new TinyDB(this).getString("rep2"));
        T3.setText("3°)  "+new TinyDB(this).getString("rep3"));
        t4.setText("4°)  "+new TinyDB(this).getString("rep4"));
        T5.setText("5°)  "+new TinyDB(this).getString("rep5"));
        B1.setOnClickListener(this);



    }

    private void init() {
        T1=findViewById(R.id.rep111);
        T2=findViewById(R.id.rep222);
        T3=findViewById(R.id.rep333);
        t4=findViewById(R.id.rep444);
        T5=findViewById(R.id.rep555);
        B1=findViewById(R.id.Terminer22);

    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(QestionUser.this,BookActivity.class));


    }
}
