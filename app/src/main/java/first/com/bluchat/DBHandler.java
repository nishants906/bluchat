package first.com.bluchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Test on 8/20/2016.
 */
public  class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "vnb";
    private static final String TABLE_CHAT = "chat";

    private static String KEY_MESSAGE = "message";
    private static String KEY_TIME = "time";
    private static String KEY_STATUS = "status";

    public DBHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_CHAT + "("
                + KEY_MESSAGE + " TEXT,"
                + KEY_TIME + " TEXT UNIQUE,"
                + KEY_STATUS + " TEXT " + ")";

        db.execSQL(CREATE_LOGIN_TABLE);
        Log.d("createtable",CREATE_LOGIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CHAT);
        onCreate(db);

    }

    public void addmessage(String message, String time, String status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGE, message);
        values.put(KEY_TIME, time);
        values.put(KEY_STATUS, status);

        db.insert(TABLE_CHAT, null, values);
        Log.d("query",TABLE_CHAT);

    }

    public List<List<String>> access_data() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<List<String>> list1=new ArrayList<List<String>>();
        String query = "SELECT * FROM " + TABLE_CHAT;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {

            do {

                List<String> row=new ArrayList<>();
                row.add(cursor.getString(0));
                row.add(cursor.getString(1));
                row.add(cursor.getString(2));
                Log.d("rowadd1", String.valueOf(row));
                list1.add(row);
                Log.d("rowadd2", String.valueOf(row));

            } while(cursor.moveToNext());

        }
        cursor.close();
        return list1;

    }



    public void resetTable_Records() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CHAT, null, null);
     }

}
