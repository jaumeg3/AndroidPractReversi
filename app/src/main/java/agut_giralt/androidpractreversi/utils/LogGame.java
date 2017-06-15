package agut_giralt.androidpractreversi.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;

import java.io.Serializable;

import agut_giralt.androidpractreversi.R;

public class LogGame implements Serializable {

    private static LogGame INSTANCE = null;
    private String logDetails = "";

    public LogGame(Activity ac) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ac);
        logDetails += "...LOG...\n" +
                " Username: " + prefs.getString(ac.getString(R.string.USER),
                "Error loading the username") + ";" +
                " Size Grid=" + prefs.getString(ac.getString(R.string.GRID_SIZE),
                "Error loading the Grid Size")+"\n";
        if(prefs.getBoolean(ac.getString(R.string.ACTIVE_TIME),false)) {
            logDetails += " Time control disabled\n";
        }else{
            logDetails += " Time control enable\n";
        }
    }

    public static LogGame getINSTANCE(Activity ac){
        if (INSTANCE == null) {
            INSTANCE = new LogGame(ac);
        }
        return INSTANCE;
    }

    public static void deleteLog() {
        INSTANCE = null;
    }

    public String getLog(Integer position) {
        //TODO: LOG String
        return logDetails += "      " + position.toString() + '\n';
        //TODO: Layouts
    }
}