package agut_giralt.androidpractreversi.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import agut_giralt.androidpractreversi.R;

/**
 * Created by Nil Agut and Jaume Giralt.
 * This file has been created for this deliver. It creates the log for the actual game.
 */

public class LogGame implements Serializable {

    private static LogGame INSTANCE = null;
    private final boolean timeActive;
    private String logDetails = "";
    private String time;
    private String size;

    private LogGame(Activity ac) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ac);
        timeActive = prefs.getBoolean(ac.getString(R.string.ACTIVE_TIME), false);
        size = prefs.getString(ac.getString(R.string.GRID_SIZE), "Error loading the Grid Size");
        logDetails += "...LOG...\n" +
                " Username: " + prefs.getString(ac.getString(R.string.USER),
                "Error loading the username") + ";" +
                " Size Grid=" + size + "\n";
        if (!timeActive) {
            logDetails += " Time control disabled\n";
        }else{
            logDetails += " Time control enable\n";
        }
        time = MyTime.getTime();
    }

    public static LogGame getINSTANCE(Activity ac){
        if (INSTANCE == null) {
            INSTANCE = new LogGame(ac);
        }
        return INSTANCE;
    }

    static void deleteLog() {
        INSTANCE = null;
    }

    public String getLog(Integer position, GameBoard gameboard) {
        String turn = calculateTurn(gameboard);
        String pos = calculatePosition(position);
        String remaining = calculateRemaining(gameboard);
        String time = calculateTime();
        String TRemaining = "";
        if (timeActive) {
            TRemaining = calculateRemainingTime(gameboard);
        }
        return logDetails += turn + pos + remaining + time + TRemaining + '\n';
    }

    private String calculateTurn(GameBoard gameboard) {
        if (gameboard.getTurn() == 2) return "Turn: Player1 \n";
        else return "Turn: Player2 \n";
    }

    private String calculateRemainingTime(GameBoard gameBoard) {
        return " \t Remaining Time: " + String.valueOf(gameBoard.getTime()/Variables.SEGON) +
                " secs. \n";
    }

    private String calculateRemaining(GameBoard gameBoard) {
        Integer size = Integer.valueOf(this.size);
        return " \t Remaining Buttons: " + String.valueOf(size * size -
                gameBoard.getPositionsComputer().size() - gameBoard.getPositionsUser().size())
                + '\n';
    }

    private String calculateTime() {
        String oldTime = this.time;
        this.time = MyTime.getTime();
        return " \t Time start: " + oldTime + " seconds; Time Finish: " + time + " seconds. \n";
    }

    private String calculatePosition(Integer position) {
        return " \t Selected Button: " + String.valueOf(position / Integer.valueOf(size)) + "," +
                String.valueOf(position % Integer.valueOf(size)) + '\n';
    }

    private static class MyTime {

        static SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss");

        private static String getTime() {
            return s.format(new Date());
        }
    }
}