package com.example.biblio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.biblio.R;

public class SplashScreen extends AppCompatActivity {

    ImageView image;
    Animation anim;
    Thread Th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        image=findViewById(R.id.imageview);
        anim=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        image.startAnimation(anim);

       Th =new Thread() {
           @Override
           public void run() {
               try {
                   sleep(5000);
                   Log.i("message","message");
                   startActivity(new Intent(SplashScreen.this, WelcomeActivity.class));
                   super.run();
               }catch(InterruptedException e){
                   e.printStackTrace();
               }
           }
       };
       Th.start();











    }


}
