package com.example.user.datenspeichern;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 29.02.2016.
 */
public class Database extends SQLiteOpenHelper
{
    public static final String Database_name = "Students.db";
    public static final String Table_name ="Schüler";
    public static final String PrimaryKey ="ID";
    public static final String Voname = "Name";
    public static final String Nachname = "Nachname";

  public Database(Context context)
  {
      super(context,Database_name,null,1);
  }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
     db.execSQL("DROP TABLE IF EXISTS "+Table_name+";");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Table_name + " (" + PrimaryKey + " INTEGER PRIMARY KEY AUTOINCREMENT," + Voname + " TEXT," + Nachname + " TEXT );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name+";");
        onCreate(db);
    }

    public boolean Insert(String name,String nachname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Voname,name);
        contentValues.put(Nachname, nachname);

        long result = db.insert(Table_name,null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =db.rawQuery("SELECT * FROM "+Table_name+";",null);
        return res;
    }
}
