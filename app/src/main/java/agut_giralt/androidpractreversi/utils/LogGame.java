package agut_giralt.androidpractreversi.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;

import agut_giralt.androidpractreversi.R;

public class LogGame implements Parcelable {
    private String logDetails = "";

    private static LogGame logGame = null;

    private LogGame(Activity ac) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ac);
        logDetails += "...LOG...\n" +
                " Username: " + prefs.getString(ac.getString(R.string.USER),
                ac.getString(R.string.DEFAULT)) + ";" +
                " Size Grid=" + prefs.getString(ac.getString(R.string.GRID_SIZE),
                ac.getString(R.string.DEFAULT))+"\n";
        if(prefs.getBoolean(ac.getString(R.string.ACTIVE_TIME),false)) {
            logDetails += " Time control disabled\n";
        }else{
            logDetails += " Time control enable\n";
        }
    }

    public static LogGame getInstance(Activity ac){
        if (logGame == null){
            logGame = new LogGame(ac);
        }
        return logGame;
    }

    private LogGame(Parcel in) {
        logDetails = in.readString();
    }

    public static final Creator<LogGame> CREATOR = new Creator<LogGame>() {
        @Override
        public LogGame createFromParcel(Parcel in) {
            return new LogGame(in);
        }

        @Override
        public LogGame[] newArray(int size) {
            return new LogGame[size];
        }
    };

    public String getLog(Integer position) {
        //TODO: LOG String
        return logDetails += "      " + position.toString() + '\n';
        //TODO: Layouts
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(logDetails);
    }
}