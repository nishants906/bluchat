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
    private static final String DATABASE_NAME = " vnb";
    private static final String TABLE_RECIEVER = " recieve";
    private static final String TABLE_SENDER = " send";

    private static final String KEY_MESSAGE_RECIEVE = " send_message";
    private static final String KEY_DATE_RECIEVE = " date";
    private static final String KEY_TIME_RECIEVE = " time";

    private static final String KEY_MESSAGE_SEND = " send_message";
    private static final String KEY_DATE_SEND = " date";
    private static final String KEY_TIME_SEND = " time";


    public DBHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECIEVE_TABLE = "CREATE TABLE " + TABLE_RECIEVER + "("
                + KEY_MESSAGE_RECIEVE + " TEXT, "
                + KEY_TIME_RECIEVE + " TEXT " + ")";
        db.execSQL(CREATE_RECIEVE_TABLE);

        String CREATE_SEND_TABLE = " CREATE TABLE " + TABLE_SENDER + "("
                + KEY_MESSAGE_SEND + " TEXT,"
                + KEY_TIME_SEND + " TEXT " + " )";
        db.execSQL(CREATE_SEND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIEVER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SENDER);
        onCreate(db);
    }
    public void addrecievemessage( String message,String time) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGE_RECIEVE, message);

        values.put(KEY_TIME_RECIEVE, time);

        db.insert(TABLE_RECIEVER, null, values);
        Log.d("send",message);

        db.close();
    }

    public void addsendmessage( String message,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGE_SEND, message);
        values.put(KEY_TIME_SEND, time);
        db.insert(TABLE_SENDER, null, values);
        Log.d("send",message);

        db.close();
    }

    public List<String> recieve_messages() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_RECIEVER;
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

    public List<String> send_messages() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_SENDER;
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
    public void resetTable_reciever() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_RECIEVER, null, null);
        db.close();
    }
    public void resetTable_sender() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SENDER, null, null);
        db.close();
    }


}
