package com.example.akashkumar.mycabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class BookingActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    String id;
    Button payment,read;
    String seater,name,mail,phonenumber,sourcearea,destinationarea,distancebw,totalprice,priceperkm,date,time,travelethod,watingtime,pincode,Noofvehicle;
    EditText clinetname,clinetemail,claientnumber;
    TextView Fifteen,source,destination,distancebk,clienttotalprice,clientprriceperm,clientdate,clienttime,clienttravlemethod,Noofvehicle1,clientpincode,clientwaiting,Seater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Noofvehicle1=findViewById(R.id.noofvehicle);
        clinetname=findViewById(R.id.name);
        clinetemail=findViewById(R.id.email);
        claientnumber=findViewById(R.id.mobilenumber);
        source=findViewById(R.id.source);
        destination=findViewById(R.id.destination);
        distancebk=findViewById(R.id.distance);
        clienttotalprice=findViewById(R.id.totalprice);
        clientprriceperm=findViewById(R.id.priceperkm);
        clientdate=findViewById(R.id.date);
        clienttime=findViewById(R.id.time);
        clientwaiting=findViewById(R.id.waitingtime);
        clienttravlemethod=findViewById(R.id.travlemethod);
        clientpincode=findViewById(R.id.pincode);
        clienttime=findViewById(R.id.time);
        Seater=findViewById(R.id.seater);
        Fifteen=findViewById(R.id.fifteen);

        MyCoreDatabase md=new MyCoreDatabase(this);
        md.getAll();
        name=md.getName12();
        mail=md.getGmail12();
        phonenumber=md.getPhonenumber1();


        Intent intent = getIntent();
        sourcearea = intent.getStringExtra("source");
        destinationarea = intent.getStringExtra("destination");
        totalprice = intent.getStringExtra("totalprice");
        priceperkm = intent.getStringExtra("priceperkm");
        date = intent.getStringExtra("date");
        time = intent.getStringExtra("time");
        travelethod = intent.getStringExtra("travelmehod");
        watingtime = intent.getStringExtra("waitingtiming");
        pincode = intent.getStringExtra("pincode");
        distancebw = intent.getStringExtra("distance");
        seater=intent.getStringExtra("seater");
        Noofvehicle=intent.getStringExtra("noofvehicle");




        Noofvehicle1.setText(Noofvehicle);
        Seater.setText(seater);
        clinetname.setText(name);
        clinetemail.setText(mail);
        claientnumber.setText(phonenumber);
        source.setText(sourcearea);
        destination.setText(destinationarea);
        distancebk.setText(distancebw+" KM");
        clienttotalprice.setText(totalprice+"");
        clientprriceperm.setText(priceperkm+"");
        clientdate.setText(date);
        clienttime.setText(time);
        clienttravlemethod.setText(travelethod);


        clientpincode.setText(pincode);
        clientwaiting.setText(watingtime);

       int total=Integer.parseInt(totalprice);
       int p15=(total/100)*20;

        Fifteen.setText(""+p15);

        payment=findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                senddata();
                Toast.makeText(BookingActivity.this, "data save sucessfull", Toast.LENGTH_SHORT).show();
            }
        });
        read=findViewById(R.id.read);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        readdata();


            }
        });


    }

    void senddata()
    {




       database = FirebaseDatabase.getInstance();
        myRef = database.getReference("SunSky"+pincode);
         id=myRef.push().getKey();

        UserData userData=new UserData(id+pincode,seater,name,mail,phonenumber,sourcearea,destinationarea,distancebw,totalprice,priceperkm,date,time,travelethod,watingtime,pincode,Noofvehicle);
        myRef.child(id+pincode).setValue(userData);


    }

    void readdata()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("SunSky"+pincode);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                Map<String,String> map=dataSnapshot.getValue(Map.class);
                String s1=map.get("name");
                String s2=map.get("destinationarea");
                Toast.makeText(BookingActivity.this,"jmjh"+s2+s1,Toast.LENGTH_SHORT).show();


//                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
//
//
//
//
//
//
//                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }


}
