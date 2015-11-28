package br.com.interaje.revendavendedor.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by caio on 27/11/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Revenda";
    private static int DB_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE car (" +
                "_id INTEGER PRIMARY KEY," +
                "name TEXT," +
                "price REAL," +
                "year INTEGER);");

        db.execSQL("CREATE TABLE car_expense (" +
                "_id INTEGER PRIMARY KEY," +
                "name TEXT," +
                "value REAL," +
                "car_id INTEGER," +
                "FOREIGN KEY (car_id) REFERENCES car(_id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
