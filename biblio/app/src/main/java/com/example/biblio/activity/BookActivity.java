package com.example.biblio.activity;

import android.os.Bundle;
import android.util.Log;
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
import com.example.biblio.utils.Repance;
import com.example.biblio.utils.TinyDB;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {


    List<Book> list;
    List<Question> questionList;
    List<Repance> repanceList;


    FirebaseDatabase database;
    DatabaseReference myRef, myRefQuestion, myRefReponse;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("books");
        myRefQuestion = database.getReference("questions");
        myRefReponse = database.getReference("repance");



        init();



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Book post = postSnapshot.getValue(Book.class);
                    list.add(post);

                }



                RecyclViewAdapter recycler = new RecyclViewAdapter(list, questionList ,repanceList);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(BookActivity.this);
                ((LinearLayoutManager) layoutmanager).setReverseLayout(true);
                ((LinearLayoutManager) layoutmanager).setStackFromEnd(true);
                recyclerView.setLayoutManager(layoutmanager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recycler);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        myRefQuestion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                questionList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Question question = postSnapshot.getValue(Question.class);
                    questionList.add(question);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        myRefReponse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                repanceList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Repance reponse = postSnapshot.getValue(Repance.class);
                    repanceList.add(reponse);

                }

                Log.i("hello ", "size "+repanceList.size());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



    public void init() {
        recyclerView = findViewById(R.id.recycler);
    }
}
