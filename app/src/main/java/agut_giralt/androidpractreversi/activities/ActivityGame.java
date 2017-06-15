package agut_giralt.androidpractreversi.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.fragments.FragmentGame;
import agut_giralt.androidpractreversi.fragments.FragmentGameLog;
import agut_giralt.androidpractreversi.utils.GameBoard;
import agut_giralt.androidpractreversi.utils.LogGame;

/**
 * Created by Nil Agut and Jaume Giralt.
 */

public class ActivityGame extends FragmentActivity implements FragmentGame.GameLogListener {

    private LogGame logGame;
    private String logs;

    public void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_game);
        FragmentGame fragGame = (FragmentGame) getSupportFragmentManager().findFragmentById
                (R.id.GameFragment);
        fragGame.setGameLogListener(this);
        logGame = LogGame.getINSTANCE(this);
    }

    @Override
    public void onGameButtonItemSelected(Integer position, GameBoard gameBoard) {
        FragmentGameLog fragmentGameLog = (FragmentGameLog) getSupportFragmentManager().
                findFragmentById(R.id.LogFragment);

        if (fragmentGameLog != null && fragmentGameLog.isInLayout()) {
            FragmentGameLog frgdetail = (FragmentGameLog) getSupportFragmentManager().
                    findFragmentById(R.id.LogFragment);
            logs = logGame.getLog(position, gameBoard);
            frgdetail.showLog(logs);
        }
    }
}