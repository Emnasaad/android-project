package com.example.biblio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.biblio.R;
import com.example.biblio.utils.Question;
import com.example.biblio.utils.Repance;
import com.example.biblio.utils.TinyDB;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RepanceActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    TextView txt5;

    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    EditText edit5;

    Button Terminer;
    Repance repance;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rep);

        init();

        txt1.setText(new TinyDB(this).getString("quest1")+"?");
        txt2.setText(new TinyDB(this).getString("quest2")+"?");
        txt3.setText(new TinyDB(this).getString("quest3")+"?");
        txt4.setText(new TinyDB(this).getString("quest4")+"?");
        txt5.setText(new TinyDB(this).getString("quest5")+"?");

        Terminer.setOnClickListener(this);


    }

    private void init() {
        txt1=findViewById(R.id.tv_quest1);
        txt2=findViewById(R.id.tv_quest2);
        txt3=findViewById(R.id.tv_quest3);
        txt4=findViewById(R.id.tv_quest4);
        txt5=findViewById(R.id.tv_quest5);
        Terminer=findViewById(R.id.buttonUpload);
        edit1=findViewById(R.id.text_input_rep1);
        edit2=findViewById(R.id.text_input_rep2);
        edit3=findViewById(R.id.text_input_rep3);
        edit4=findViewById(R.id.text_input_rep4);
        edit5=findViewById(R.id.text_input_rep5);
        Terminer=findViewById(R.id.btnTerminer);

    }

    private void createQuest(String rep1,String rep2, String rep3,String rep4,String rep5) {

        repance = new Repance(rep1,rep2,rep3,rep4,rep5);
        String key=new TinyDB(getApplicationContext()).getString("key_set");
        mDatabaseReference = mDatabase.getReference().child("repance").child(key);
        mDatabaseReference.setValue(repance);

    }

    @Override
    public void onClick(View view) {
        createQuest(edit1.getText().toString(),edit2.getText().toString(),edit3.getText().toString(),edit4.getText().toString(),edit5.getText().toString());
        Toast.makeText(this, "Livre Ajoutée", Toast.LENGTH_SHORT).show();
       new TinyDB(getApplicationContext()).putString("rep1",txt1.getText().toString());
        new TinyDB(getApplicationContext()).putString("rep2",txt2.getText().toString());
        new TinyDB(getApplicationContext()).putString("rep3",txt3.getText().toString());
        new TinyDB(getApplicationContext()).putString("rep4",txt4.getText().toString());
        new TinyDB(getApplicationContext()).putString("rep5",txt5.getText().toString());
        startActivity(new Intent(RepanceActivity.this,Seconnecte.class));
    }


}
