package com.example.biblio.activity;

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
import com.example.biblio.utils.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Inscrir extends AppCompatActivity implements View.OnClickListener {

    EditText Nom;
    EditText prenom;
    EditText phone;
    EditText email;
    EditText password;
    EditText Confirmepassword ;
    Button inscri;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();
    User users;



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_insc);
       // FirebaseApp.initializeApp(this);
        mAuth =FirebaseAuth.getInstance();

        init();

        inscri.setOnClickListener(this);

    }


    private void registerUser() {

        if (email.getText().toString().matches("") || Nom.getText().toString().matches("") || password.getText().toString().matches("") || Confirmepassword.getText().toString().matches("") || phone.getText().toString().matches("") || prenom.getText().toString().matches("") ) {
            Toast.makeText(this, "Veuillez vérifier votre formulaire", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Veuillez saisir une adresse mail valide");
            email.requestFocus();
            return;
        }

        else if (password.getText().toString().length() < 6) {
            password.setError("taille minimum 6");
            password.requestFocus();
            return;
        }
        else if (!password.getText().toString().equals(Confirmepassword.getText().toString())) {
            password.setError("Veuillez saisir un mot de passe valide");
            password.requestFocus();
            return;
        }
        else {


            mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                    .addOnCompleteListener(Inscrir.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

//                        progressBar.setVisibility(View.GONE);

                            if (task.isSuccessful()) {

                                Toast.makeText(Inscrir.this, "User Registred", Toast.LENGTH_SHORT).show();


                                // Sign in success, update UI with the signed-in user's information
                                //      Log.d("test_reg", "createUserWithEmail:success");
                                //FirebaseUser user = mAuth.getCurrentUser();
                                //  String uid = user.getUid().toString();
                                // / Toast.makeText(getApplicationContext(), uid, Toast.LENGTH_LONG).show();
                                //createUser(uid, Nom.getText().toString(), prenom.getText().toString(), "mag@gmail.com", phone.getText().toString());
                                Intent intent = new Intent(Inscrir.this, Seconnecte.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                //    Log.w("testreg1", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Inscrir.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                            }

                        }

                    });
        }
    }


    private void init() {
        Nom=findViewById(R.id.edtNom);
        prenom=findViewById(R.id.edtPren);
        email=findViewById(R.id.edtEmail);
        phone=findViewById(R.id.edtPhone);
        password=findViewById(R.id.edtPsw);
        Confirmepassword=findViewById(R.id.edtCpsw);
        inscri=findViewById(R.id.btn_inscri);
    }


    @Override
    public void onClick(View view) {

            registerUser();
    }



    private void createUser(String id_user,String nom,String prenom, String email, String tel) {

        users = new User(nom,prenom,email,tel);
        mDatabaseReference = mDatabase.getReference().child("users1").child(id_user);
        mDatabaseReference.setValue(users);

    }
}

