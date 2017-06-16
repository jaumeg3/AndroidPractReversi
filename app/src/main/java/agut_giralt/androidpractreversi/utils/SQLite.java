package agut_giralt.androidpractreversi.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nil Agut and Jaume Giralt on 12/06/17.
 * This file has been created for this deliver. It manages the database of the game.
 */

public class SQLite extends SQLiteOpenHelper {

    private final static String DB_NAME = "DB";
    private final static int DB_VERSION = 1;
    private final static String sql = GameTable.CREATE + GameTable.NAME + GameTable.HEADER +
            GameTable.USER + GameTable.TEXT_TYPE + GameTable.DATE + GameTable.TEXT_TYPE +
            GameTable.SIZE + GameTable.INT_TYPE + GameTable.TIME + GameTable.BOOL_TYPE +
            GameTable.PLAYERS + GameTable.TEXT_TYPE + GameTable.WHITE_PIECES + GameTable.INT_TYPE +
            GameTable.BLACK_PIECES + GameTable.INT_TYPE + GameTable.FINAL_TIME +
            GameTable.TEXT_TYPE + GameTable.POSITION + " TEXT)";
    private static SQLite sqLiteInstance;

    private SQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static SQLite getInstance(Context context) {
        if (sqLiteInstance == null) {
            sqLiteInstance = new SQLite(context);
        }
        return sqLiteInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Variables.DROP + DB_NAME);
        sqLiteDatabase.execSQL(sql);
    }

    public long register(ContentValues register) {
        SQLiteDatabase database = getWritableDatabase();
        return database.insert(GameTable.NAME, null, register);
    }

    public Cursor getDataFromDB() {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(GameTable.SELECT_ALL + GameTable.NAME, null);
    }


    public class GameTable {
        public static final String USER = "username";
        public static final String DATE = "date";
        public static final String SIZE = "size";
        public static final String TIME = "time";
        public static final String PLAYERS = "players";
        public static final String WHITE_PIECES = "player1";
        public static final String BLACK_PIECES = "player2";
        public static final String FINAL_TIME = "final_time";
        public static final String POSITION = "position";
        private static final String NAME = "GameHistorial";
        private static final String HEADER = "(_id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        private static final String TEXT_TYPE = " TEXT, ";
        private static final String INT_TYPE = " INTEGER, ";
        private static final String BOOL_TYPE = " BOOLEAN, ";
        private static final String CREATE = "CREATE TABLE ";
        private static final String SELECT_ALL = "SELECT * FROM ";
    }
}
