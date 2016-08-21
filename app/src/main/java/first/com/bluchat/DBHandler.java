package first.com.bluchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Test on 8/20/2016.
 */
public  class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "bluchat";
    private static final String TABLE_CHAT = "chat";



    private static final String KEY_MESSAGE = "message";
    private static final String KEY_TIME = "time";
    private static final String KEY_FLAG = "flag";


    public DBHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CHAT_TABLE = "CREATE TABLE " + TABLE_CHAT + "("
                + KEY_MESSAGE + " TEXT,"
                + KEY_TIME + " TEXT UNIQUE,"
                + KEY_FLAG + " TEXT " + ")";
        db.execSQL(CREATE_CHAT_TABLE);

        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAT);
        onCreate(db);
    }

    public void addmessage(String message, String time, String flag) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGE, message);
        values.put(KEY_TIME, time);
        values.put(KEY_FLAG, flag);

        db.insert(TABLE_CHAT, null, values);
        db.close();
    }


    public List<String> access_messages() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CHAT;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        List<String> subjects = new ArrayList<String>();
        if(cursor.getCount() > 0) {
            do {
                subjects.add(cursor.getString(0));
            } while(cursor.moveToNext());
        }
        return subjects;
    }

    public List<String> access_flag() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CHAT;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        List<String> subjects = new ArrayList<String>();
        if(cursor.getCount() > 0) {
            do {
                subjects.add(cursor.getString(2));
            } while(cursor.moveToNext());
        }
        return subjects;
    }
}
