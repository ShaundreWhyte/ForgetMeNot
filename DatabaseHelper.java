package com.example.forgetmenot;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    //creation of database
    public static final String DATABASE_NAME = "Health.db";

    //creation of table Doctor
    public static final String TABLE_DOCTOR= "DOCTOR";
    //declaration of attributes
    public static final String Col_DId= "DoctorId";
    public static final String Col_DName= "DoctorName";
    public static final String Col_DOffice= "DoctorOffice";
    public static final String Col_DPhone= "DoctorPhone";

    //creation of table Caretaker
    public static final String TABLE_CARE= "CARETAKER";
    //declaration of attributes
    public static final String Col_CId= "CareId";
    public static final String Col_CName= "CareName";
    public static final String Col_CAge= "CareAge";
    public static final String Col_CGen= "CareGender";
    public static final String Col_CAdd= "CareAddress";
    public static final String Col_CDId= "CDoctorId";

    //creation of table Patient
    public static final String TABLE_PATIENT= "PATIENT";
    //declaration of attributes
    public static final String Col_PId= "PatientId";
    public static final String Col_PName= "PatientName";
    public static final String Col_PGender= "PatientGender";
    public static final String Col_PAddress= "PatientAddress";
    public static final String Col_PPhone= "PatientPhone";
    public static final String Col_PDOB= "PatientDOB";
    public static final String Col_PDId= "PDoctorId";
    public static final String Col_PCId= "PCareId";

    // creation of table Doctor

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_DOCTOR +
                "(" + Col_DId + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Col_DName + "TEXT,"
                + Col_DOffice + "TEXT,"
                + Col_DPhone + "LONG)");

        db.execSQL("create table " + TABLE_CARE +
                "(" + Col_CId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Col_CName + " TEXT,"
                + Col_CAge + "INTEGER,"
                + Col_CGen + " TEXT,"
                + Col_CAdd + "LONG,"
                + Col_CDId +" INTEGER NOT NULL,FOREIGN KEY ("+Col_CDId+") REFERENCES "+TABLE_DOCTOR+" ("+Col_DId+"));");

        db.execSQL("create table " + TABLE_PATIENT +
                "(" + Col_PId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Col_PName + "TEXT,"
                    + Col_PGender + "TEXT,"
                    + Col_PAddress + "TEXT,"
                    + Col_PPhone + "LONG,"
                    + Col_PDOB + "DATE,"
                    + Col_PDId +" INTEGER NOT NULL,FOREIGN KEY ("+Col_PDId+") REFERENCES "+ TABLE_DOCTOR +"("+Col_DId+"),"
                    + Col_PCId +" INTEGER NOT NULL,FOREIGN KEY("+Col_PCId+") REFERENCES "+ TABLE_CARE +"("+Col_CId+"));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CARE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PATIENT);
        onCreate(db);
    }



}
