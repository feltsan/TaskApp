package test.test.myapplication.supp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import test.test.myapplication.ThirdLessonActivity;

/**
 * Created by john on 10.10.14.
 */
public class TaskDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "task_database.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "task_table";
    public static final String UID = "_id";
    public static final String NAME = "name";
    public static final String TEXT = "text";
    public static final String SMALL_IMAGE = "small_image";
    public static final String BIG_IMAGE = "big_image";


    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" + UID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " VARCHAR(255)," + TEXT + " VARCHAR(255),"
            + SMALL_IMAGE + " VARCHAR(255)," + BIG_IMAGE + " VARCHAR(255));";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public TaskDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


}

