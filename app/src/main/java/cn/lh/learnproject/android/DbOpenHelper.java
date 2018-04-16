package cn.lh.learnproject.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "person_list.db";
    public final static String TABLE_NAME = "person";
    private final static int DB_VERSION = 1;
    private final String SQL_CREATE_TABLE = "create table if not exists " + TABLE_NAME + "(_id integer primary key, name TEXT, description TEXT)";

    public DbOpenHelper(final Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {

    }
}
