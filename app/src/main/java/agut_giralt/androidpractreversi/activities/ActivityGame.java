package agut_giralt.androidpractreversi.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.utils.GameBoard;
import agut_giralt.androidpractreversi.utils.ImageAdapter;
import agut_giralt.androidpractreversi.utils.Variables;

/**
 * Created by Nil Agut and Jaume Giralt.
 */

public class ActivityGame extends AppCompatActivity {

    private int SIZE;
    private boolean time;
    private String player1;
    private int countDown = 40;
    private TextView cells, timing, score1, score2;
    private GameBoard gameBoard;
    private int numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getConfiguration();
        if (savedInstanceState == null) initGame();
        else getBackState(savedInstanceState);
        initGridView();
    }

    private void initGridView() {
        ImageAdapter imageAdapter = new ImageAdapter(this, gameBoard, player1, SIZE, time,
                cells, timing, score1, score2, numberOfPlayers());
        GridView board = (GridView) findViewById(R.id.board);
        board.setAdapter(imageAdapter);
        board.setBackgroundColor(getResources().getColor(R.color.board));
        board.setNumColumns(SIZE);
    }

    private boolean numberOfPlayers() {
        return numberOfPlayers == 1;
    }

    private void getBackState(Bundle savedInstanceState) {
        gameBoard = savedInstanceState.getParcelable(Variables.GAMEBOARD);
        this.player1 = savedInstanceState.getString(getResources().getString(R.string.USER));
        this.SIZE = savedInstanceState.getInt(getResources().getString(R.string.GRID_SIZE));
        this.time = savedInstanceState.getBoolean(getResources().getString(R.string.ACTIVE_TIME));
        this.countDown = savedInstanceState.getInt(getResources().getString(R.string.TIME_SECONDS));
    }

    private void initGame() {
        gameBoard = new GameBoard(SIZE);
        gameBoard.initGameBoard(time, countDown);
    }

    private void getConfiguration() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        player1 = prefs.getString(getResources().getString(R.string.USER),
                getResources().getString(R.string.DEFAULT));
        SIZE = Integer.valueOf(prefs.getString(getResources().getString(R.string.GRID_SIZE),
                getResources().getString(R.string.DEFAULT_GRID_SIZE)));
        time = prefs.getBoolean(getResources().getString(R.string.ACTIVE_TIME),true);
        countDown = Integer.valueOf(prefs.getString(getResources().getString(R.string.TIME_SECONDS),
                "40"));
        numberOfPlayers = Integer.valueOf(prefs.getString(getResources().getString(R.string.PLAYERS)
                ,"1"));
        cells = (TextView) findViewById(R.id.cells);
        timing = (TextView) findViewById(R.id.timing);
        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Variables.GAMEBOARD, gameBoard);
        outState.putString(getResources().getString(R.string.USER), player1);
        outState.putInt(getResources().getString(R.string.GRID_SIZE), SIZE);
        outState.putBoolean(getResources().getString(R.string.ACTIVE_TIME), time);
        outState.putInt(getResources().getString(R.string.TIME_SECONDS), countDown);
    }
}