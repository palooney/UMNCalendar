package umn.umncalendar;

/**
 * Created by Changye on 4/1/17.
 * the database used by this app
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int database_version = 1;

    private static final String databaseName = "userDatabase";
    private static final String userTable = "users";
    private static final String key_uid = "uid";
    private static final String key_username = "username";
    private static final String key_password = "password";

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, database_version);
    }

    /**
     * create the database for this app
     *
     * @param db: database
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE" + userTable + "(" + key_uid + "INTEGER PRIMARY KEY " +
                "AUTOINCREMENT," + key_username + "TEXT," + key_password + "TEXT)");
    }


    /**
     * create a new entry into the database
     *
     * @param user: user info
     */
    public void createUser(UserDatabase user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(key_username, user.getUsername());
        values.put(key_password, user.getPassword());
        db.insert(userTable, null, values);
        db.close();
    }

    /**
     * delete an entry from the database
     *
     * @param user: user info
     */
    public void deleteUser(UserDatabase user) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(userTable, key_uid + " =? ", new String[]{String.valueOf(user.getUid())});
        db.close();
    }

    /**
     * get user info based on user id in the database
     *
     * @param id: uid in the database
     * @return user info
     */
    public UserDatabase getUser(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(userTable, new String[]{key_uid, key_username, key_password},
                key_uid + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        UserDatabase user = new UserDatabase(Integer.parseInt(cursor.getString(0)), cursor
                .getString(1), cursor.getString(2));
        db.close();
        cursor.close();

        return user;

    }
    
}// class end