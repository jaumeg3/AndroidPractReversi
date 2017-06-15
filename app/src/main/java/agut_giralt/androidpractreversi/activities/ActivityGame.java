package agut_giralt.androidpractreversi.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.fragments.FragmentGame;
import agut_giralt.androidpractreversi.fragments.FragmentGameLog;
import agut_giralt.androidpractreversi.utils.LogGame;

/**
 * Created by Nil Agut and Jaume Giralt.
 */

public class ActivityGame extends FragmentActivity implements FragmentGame.GameLogListener {

    public void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_game);
        FragmentGame fragGame = (FragmentGame) getSupportFragmentManager().findFragmentById
                (R.id.GameFragment);
        fragGame.setGameLogListener(this);
    }

    @Override
    public void onGameButtonItemSelected(Integer position) {
        FragmentGameLog fragmentGameLog = (FragmentGameLog) getSupportFragmentManager().
                findFragmentById(R.id.LogFragment);

        if (fragmentGameLog != null && fragmentGameLog.isInLayout()) {
            FragmentGameLog frgdetail = (FragmentGameLog) getSupportFragmentManager().
                    findFragmentById(R.id.LogFragment);
            LogGame logGame = LogGame.getInstance(this);
            frgdetail.showLog(logGame.getLog(position));
        }
    }
}