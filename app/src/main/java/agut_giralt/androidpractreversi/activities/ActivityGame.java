package agut_giralt.androidpractreversi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;

import agut_giralt.androidpractreversi.GameBoard;
import agut_giralt.androidpractreversi.ImageAdapter;
import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.variables;

public class ActivityGame extends AppCompatActivity {

    private int SIZE;
    private boolean time;
    private String player1;

    private TextView cells, timing, score1, score2;

    private GameBoard gameBoard;
    private GridView board;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getConfiguration();
        if (savedInstanceState == null) initGame();
        else {
            getBackState(savedInstanceState);
        }
        initGridView();
    }

    private void initGridView() {
        this.imageAdapter = new ImageAdapter(this, gameBoard, board, player1, SIZE, time,
                cells, timing, score1, score2);
        this.board = (GridView) findViewById(R.id.board);
        this.board.setAdapter(imageAdapter);
        this.board.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        this.board.setNumColumns(SIZE);
    }

    private void getBackState(Bundle savedInstanceState) {
        gameBoard = savedInstanceState.getParcelable(variables.GAMEBOARD);
        this.player1 = savedInstanceState.getString(variables.USER);
        this.SIZE = savedInstanceState.getInt(variables.SIZE);
        this.time = savedInstanceState.getBoolean(variables.TIME);
    }

    private void initGame() {
        gameBoard = new GameBoard(SIZE);
        gameBoard.initGameBoard();
    }

    private void getConfiguration() {
        player1 = getIntent().getStringExtra(variables.USER);
        SIZE = getIntent().getIntExtra(variables.SIZE, 4);
        time = getIntent().getBooleanExtra(variables.TIME, false);
        cells = (TextView) findViewById(R.id.cells);
        timing = (TextView) findViewById(R.id.timing);
        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(variables.GAMEBOARD, gameBoard);
        outState.putString(variables.USER, player1);
        outState.putInt(variables.SIZE, SIZE);
        outState.putBoolean(variables.TIME, time);
    }


}