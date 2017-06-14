package agut_giralt.androidpractreversi.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.utils.GameBoard;
import agut_giralt.androidpractreversi.utils.ImageAdapter;
import agut_giralt.androidpractreversi.utils.Variables;


public class GameFragment extends Fragment {


    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    private int SIZE;
    private boolean time;
    private String player1;
    private int countDown = 40;
    private TextView cells, timing, score1, score2;
    private GameBoard gameBoard;
    private int numberOfPlayers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getConfiguration();
        if (savedInstanceState != null) getBackState(savedInstanceState);
        else initGame();
        initGridView();
    }


    private void initGridView() {
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(), gameBoard, player1, SIZE, time,
                cells, timing, score1, score2, numberOfPlayers());
        GridView board = (GridView) getView().findViewById(R.id.board);
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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        player1 = prefs.getString(getResources().getString(R.string.USER),
                getResources().getString(R.string.DEFAULT));
        SIZE = Integer.valueOf(prefs.getString(getResources().getString(R.string.GRID_SIZE),
                getResources().getString(R.string.DEFAULT_GRID_SIZE)));
        time = prefs.getBoolean(getResources().getString(R.string.ACTIVE_TIME),true);
        countDown = Integer.valueOf(prefs.getString(getResources().getString(R.string.TIME_SECONDS),
                "40"));
        numberOfPlayers = Integer.valueOf(prefs.getString(getResources().getString(R.string.PLAYERS)
                ,"1"));
        cells = (TextView) getView().findViewById(R.id.cells);
        timing = (TextView) getView().findViewById(R.id.timing);
        score1 = (TextView) getView().findViewById(R.id.score1);
        score2 = (TextView) getView().findViewById(R.id.score2);
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
