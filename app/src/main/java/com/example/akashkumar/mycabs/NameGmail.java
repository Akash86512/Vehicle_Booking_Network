package com.example.akashkumar.mycabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameGmail extends AppCompatActivity {

    EditText name1,gmail;
    Button next;
    String Phonenumber,name2,gamil1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_gmail);

        name1=findViewById(R.id.name);
        gmail=findViewById(R.id.email);
        next=findViewById(R.id.next);

        Intent intent = getIntent();
        Phonenumber = intent.getStringExtra("number");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name2=name1.getText().toString();
                gamil1=gmail.getText().toString();

                MyCoreDatabase DB=new MyCoreDatabase(NameGmail.this);
                DB.insertdata(Phonenumber,name2,gamil1);

                Intent intent1=new Intent(NameGmail.this,ProfileActivity.class);
                startActivity(intent1);

            }
        });
    }
}
