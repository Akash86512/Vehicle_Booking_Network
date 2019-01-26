package com.example.akashkumar.mycabs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Akash Kumar on 30/07/2018.
 */

public class MyCoreDatabase extends SQLiteOpenHelper {

    static final private String DB_NAME="education";
    static final private String DB_TABLE="student";
    static final private int DB_VERSION=1;
    String phonenumber1,Name12,gmail12;

    public String getName12() {
        return Name12;
    }

    public void setName12(String name12) {
        Name12 = name12;
    }

    public String getGmail12() {
        return gmail12;
    }

    public void setGmail12(String gmail12) {
        this.gmail12 = gmail12;
    }

    public String getPhonenumber1() {
        return phonenumber1;
    }

    public void setPhonenumber1(String phonenumber1) {
        this.phonenumber1 = phonenumber1;
    }

    Context ctx;
    SQLiteDatabase myDb;




    public MyCoreDatabase(Context ct)
    {
        super(ct,DB_NAME,null,DB_VERSION);
        ctx=ct;


    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



        sqLiteDatabase.execSQL("create table "+DB_TABLE+" (_id integer primary key autoincrement,Number text,Name text,gmail text);");
        Log.i("database","table create ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+DB_TABLE);
        onCreate(sqLiteDatabase);





    }

    public void insertdata(String s1,String s2,String s3)
    {


        myDb=getWritableDatabase();
        myDb.execSQL("insert into "+DB_TABLE+" (Number,Name,gmail) values('"+s1+"','"+s2+"','"+s3+"');");
        Toast.makeText(ctx," data saved successfully",Toast.LENGTH_SHORT).show();
    }


    public void getAll()
    {
        myDb=getWritableDatabase();
        Cursor cr=myDb.rawQuery("select * from "+DB_TABLE,null);
        StringBuilder str=new StringBuilder();
        StringBuilder str1=new StringBuilder();
        StringBuilder str2=new StringBuilder();

        while(cr.moveToNext())
        {
            String s7=cr.getString(1);
            String s8=cr.getString(2);
            String s9=cr.getString(3);


            str.append(s7+"\n");
            str1.append(s8+"\n");
            str2.append(s9+"\n");


        }


        phonenumber1=str.toString();
        Name12=str1.toString();
        gmail12=str2.toString();

        Toast.makeText(ctx,str2.toString()+""+cr,Toast.LENGTH_LONG).show();
    }




}
