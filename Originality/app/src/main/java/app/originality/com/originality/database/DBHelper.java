package app.originality.com.originality.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.originality.com.originality.util.LogOut;

public class DBHelper extends SQLiteOpenHelper {

    private static final int mVersion = 1;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBString.TABLE_NAME_USERINFOR, null, mVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DBString.CREATE_TABLE_USERINFOR);
        } catch (Exception e) {
            LogOut.printStackTrace(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}