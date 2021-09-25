package com.example.biblio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.biblio.R;
import com.example.biblio.adapter.RecyclViewAdapter;
import com.example.biblio.utils.Book;
import com.example.biblio.utils.Question;
import com.example.biblio.utils.TinyDB;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class QuestActivity extends AppCompatActivity {

    Button terminer;
    EditText qestion1;
    EditText qestion2;
    EditText qestion3;
    EditText qestion4;
    EditText qestion5;
    Question question;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);


        FirebaseApp.initializeApp(this);

        FirebaseApp.initializeApp(this);
        mDatabase = FirebaseDatabase.getInstance();



        init();


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                    new TinyDB(getApplicationContext()).putString("quest1",qestion1.getText().toString());
                    new TinyDB(getApplicationContext()).putString("quest2",qestion2.getText().toString());
                    new TinyDB(getApplicationContext()).putString("quest3",qestion3.getText().toString());
                    new TinyDB(getApplicationContext()).putString("quest4",qestion4.getText().toString());
                    new TinyDB(getApplicationContext()).putString("quest5",qestion5.getText().toString());

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







        terminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createQuest(qestion1.getText().toString(),qestion2.getText().toString(),qestion3.getText().toString(),qestion4.getText().toString(),qestion5.getText().toString());

                startActivity(new Intent(QuestActivity.this,RepanceActivity.class));

            }
        });


    }

    private void init() {
        qestion1=findViewById(R.id.text_input_quest1);
        qestion2=findViewById(R.id.text_input_quest2);
        qestion3=findViewById(R.id.text_input_quest3);
        qestion4=findViewById(R.id.text_input_quest4);
        qestion5=findViewById(R.id.text_input_quest5);
        terminer=findViewById(R.id.btnSuiv);

    }


    private void createQuest(String quest1,String quest2, String quest3,String quest4,String quest5) {

        question = new Question(quest1,quest2,quest3,quest4,quest5);
        String key=new TinyDB(getApplicationContext()).getString("key_set");
        mDatabaseReference = mDatabase.getReference().child("questions").child(key);
        mDatabaseReference.setValue(question);


    }


}