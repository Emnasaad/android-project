package com.example.biblio.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.biblio.R;
import com.example.biblio.utils.TinyDB;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileActivity extends AppCompatActivity {

    PDFView pdfView;


    private FirebaseDatabase data = FirebaseDatabase.getInstance();
    private DatabaseReference ref = data.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        pdfView = findViewById(R.id.pdfView);
        // new RetrievePDFStream().execute(new TinyDB(this).getString("url"));

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String values = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        new RetrievePDFStream().execute(new TinyDB(this).getString("url_file"));

    }

    class RetrievePDFStream extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;

            try {

                URL urlx = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) urlx.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());

                }
            } catch (IOException e) {
                return null;
            }
            return inputStream;

        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
        }
    }
}
