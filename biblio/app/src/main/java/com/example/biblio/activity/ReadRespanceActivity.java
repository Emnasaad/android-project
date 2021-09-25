package com.example.biblio.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.biblio.R;
import com.example.biblio.utils.TinyDB;

public class ReadRespanceActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView photo;
    TextView title;
    Button read, respance;

    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rr);

        photo = (ImageView) findViewById(R.id.iv_file);
        title = (TextView) findViewById(R.id.tv_title_file);
        read = (Button) findViewById(R.id.btn_read);
        respance = (Button) findViewById(R.id.btn_rep_question);


        Glide.with(this)
                //.load(url)
                .load(new TinyDB(this).getString("url_image")) // image url
                .placeholder(R.drawable.logo) // any placeholder to load at start
                .error(R.drawable.logo)  // any image in case of error
                .into(photo);
        title.setText(new TinyDB(this).getString("title_file"));

        //   read.setOnClickListener(this);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(new TinyDB(getApplicationContext()).getString("url_pdf")), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Intent newIntent = Intent.createChooser(intent, "ouvrire fichier");

                try {
                    startActivity(newIntent);
                } catch (ActivityNotFoundException e) {
                    // Instruct the user to install a PDF reader here, or something
                }

            }
        });

        respance.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_rep_question:
                startActivity(new Intent(ReadRespanceActivity.this, ReponseUser.class));
                break;
        }
    }
}
