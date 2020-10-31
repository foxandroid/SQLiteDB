package com.example.databasetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FOXANDROID.db";
    public static final String TABLE_NAME = "FRUITS";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ITEM";
    public static final String COL_3 = "QTY";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY," + COL_2 + " TEXT," + COL_3 + " INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String item, int qty){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_2,item);
        values.put(COL_3,qty);

        long res = db.insert(TABLE_NAME,null,values);

        if (res == -1)
            return false;
        else
            return true;


    }

    public Cursor readData(String id){

        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME,null,"ID = ?",new String[]{id},null,null,null,null);

    }

    public boolean updateData(String id, String item, Integer qty){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,item);
        contentValues.put(COL_3,qty);

        Integer res;
        res = db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        if (res > 0)
            return true;
        else
            return false;
    }

    public Integer deleteData(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});

    }

}
