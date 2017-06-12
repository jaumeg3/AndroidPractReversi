package agut_giralt.androidpractreversi.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nil Agut and Jaume Giralt on 12/06/17.
 *
 */

public class SQLite extends SQLiteOpenHelper{

    private static SQLite sqLiteInstance;
    private final static String DB_NAME = "DB";
    private final static int DB_VERSION = 1;
    private Context context;
    private final static String sql = "CREATE TABLE ";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Variables.DROP + DB_NAME);
        sqLiteDatabase.execSQL(sql);
    }

    private SQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public static SQLite getInstance(Context context) {
        if (sqLiteInstance == null){
            sqLiteInstance = new SQLite(context);
        }
        return sqLiteInstance;
    }
}
