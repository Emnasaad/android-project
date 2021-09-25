package com.example.biblio.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.biblio.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

public class Seconnecte extends AppCompatActivity implements View.OnClickListener {
    EditText email;
    EditText Pass;
    Button log;
    private DatabaseReference mDatabaseUser_categori;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    String uid;
    private DatabaseReference mDatabase_email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        
        init();

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();



        FirebaseApp.initializeApp(this);

        log.setOnClickListener(this);
    }
    public void logIn()
    {

        final String mail=email.getText().toString();
        String Password=Pass.getText().toString();
        if(mail.matches("")||Password.matches("")){
            Toast.makeText(this,"vérifier votre formulaire",Toast.LENGTH_SHORT).show();
            return;

        }else if (!Patterns.EMAIL_ADDRESS.matcher( mail ).matches()){
            email.setError("entre une email valide");
            email.requestFocus();
            return;

        }else if(Password.length()<6){
            Pass.setError("vérifier votre password");
            Pass.requestFocus();
            return;

        }else {
            firebaseAuth.signInWithEmailAndPassword(mail,Password)

                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                               // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "signInWithEmail:success");
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                uid = user.getUid();

                                if(mail.equals("emna@gmail.com")){
                                    final ProgressDialog mDialog = new ProgressDialog(Seconnecte.this);
                                    mDialog.setMessage("please waiting");
                                    mDialog.show();
                                    Intent intent = new Intent(Seconnecte.this, MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    final ProgressDialog mDialog = new ProgressDialog(Seconnecte.this);
                                    mDialog.setMessage("please waiting");
                                    mDialog.show();
                                    Intent intent = new Intent(Seconnecte.this, BookActivity.class);
                                    startActivity(intent);
                                    //Toast.makeText(Seconnecte.this, "not admin", Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(Seconnecte.this, "Authentication success",
                                        Toast.LENGTH_SHORT).show();


                            }else{
                                Log.i("TAG", "signInWithEmail:failure", task.getException());
                                Toast.makeText(Seconnecte.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_LONG).show();

                            }


                        }
                    });

        }

    }

    private void init() {
        email =findViewById(R.id.edt_email);
        Pass =findViewById(R.id.edt_pwd);
        log =findViewById(R.id.buttonLogin);



    }

    @Override
    public void onClick(View view) {
        logIn();
        
    }
}
