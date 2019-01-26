package com.example.akashkumar.mycabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileDetails1 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details1);

        Thread thread=new Thread(){
            @Override
            public void run() {
               try{
                   sleep(3000);
                   Intent intent=new Intent(ProfileDetails1.this,MainActivity.class);
                   startActivity(intent);
                   finish();
               }catch (InterruptedException e)
               {
                   e.printStackTrace();
               }
            }
        };
        thread.start();



    }
}
