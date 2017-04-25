package agut_giralt.androidpractreversi.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.activities.ActivityResult;


/**
 * Created by Nil Agut and Jaume Giralt.
 */

public class ImageAdapter extends BaseAdapter {

    private Activity mContext;
    private GameBoard gameBoard;
    private TextView cells, timing, score1, score2;
    private boolean withTime;
    private int SIZE;
    private String alias;
    private int TIME = 40;
    private long timeLeft;
    private boolean posible = false;
    private boolean intelligenceActivated = false; // Ho hem preparat per a que puguin jugar 2 jugadors
    private ArtificialIntelligence ia;


    public ImageAdapter(Activity c, GameBoard gameBoard, String alias, int size,
                        boolean withTime, TextView cells, TextView timing, TextView score1,
                        TextView score2) {
        mContext = c;
        this.gameBoard = gameBoard;
        this.alias = alias;
        this.SIZE = size;
        this.withTime = withTime;
        this.cells = cells;
        this.timing = timing;
        this.score1 = score1;
        this.score2 = score2;
        updateTextViews();
        if (this.withTime) {
            this.timeLeft = TIME;
            updateTime();
        } else {
            chrono();
        }
        this.ia = new ArtificialIntelligence(this.SIZE);
    }

    private void chrono() {
        timeLeft = System.currentTimeMillis();
    }

    private void updateTime() {
        if (withTime) {
            timing.setText(String.valueOf(gameBoard.getTime()));
        } else {
            timing.setText(String.valueOf(timeLeft));
        }
    }

    @Override
    public int getCount() {
        return SIZE * SIZE;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button btn;
        if (convertView == null) {
            btn = new Button(mContext);
            if (getCount() == 64) {
                btn.setLayoutParams(new GridView.LayoutParams(45, 45));
                btn.setPadding(5, 5, 5, 5);
            } else if (getCount() == 36) {
                btn.setLayoutParams(new GridView.LayoutParams(60, 60));
                btn.setPadding(5, 5, 5, 5);
            } else {
                btn.setLayoutParams(new GridView.LayoutParams(100, 100));
                btn.setPadding(5, 5, 5, 5);
            }

        } else {
            btn = (Button) convertView;
        }

        btn.setBackgroundResource(setPiece(position));
        btn.setOnClickListener(new MyOnClickListener(position, mContext));
        btn.setId(position);
        return btn;
    }

    private int setPiece(int position) {
        if (gameBoard.getPositionsUser().contains(position)) {
            return R.drawable.bcr;
        } else if (gameBoard.getPositionsComputer().contains(position)) {
            return R.drawable.wcr;
        } else if (gameBoard.getPositionsPossibleCells().contains(position)) {
            return R.drawable.grc;
        } else {
            return R.drawable.wsq;
        }
    }

    private void updateTextViews() {
        this.cells.setText(String.valueOf(SIZE * SIZE - gameBoard.getPositionsUser().size() -
                gameBoard.getPositionsComputer().size()));
        this.score1.setText(String.valueOf(gameBoard.getPositionsUser().size()));
        this.score2.setText(String.valueOf(gameBoard.getPositionsComputer().size()));
    }

    private void createNewActivity() {
        Intent intent = new Intent(mContext, ActivityResult.class);
        intent.putExtra(Variables.USER, alias);
        intent.putExtra(Variables.TIME, withTime);
        intent.putExtra(Variables.TIME_LEFT, timeLeft);
        intent.putExtra(Variables.PLAYER1_SCORE, Integer.parseInt(score1.getText().toString()));
        intent.putExtra(Variables.PLAYER2_SCORE, Integer.parseInt(score2.getText().toString()));
        intent.putExtra(Variables.SIZE, SIZE);
        mContext.startActivity(intent);
        mContext.finish();
    }

    private class MyOnClickListener implements View.OnClickListener {
        private final int position;
        private Context context;

        MyOnClickListener(int position, Context context) {
            this.position = position;
            this.context = context;
        }

        public void onClick(View v) {
            if (gameBoard.getPositionsPossibleCells().contains(position)) {
                doTheMovement(position);
                if (intelligenceActivated && gameBoard.getPositionsPossibleCells().size() > 0) {
                    doTheMovement(ia.getBestMovement(gameBoard.getPositionsPossibleCells()));
                }
            } else {
                Toast.makeText(context, "Invalid Movement. Try again", Toast.LENGTH_SHORT).show();
            }
        }

        private void doTheMovement(int position) {
            fillTheCells(position);
            gameBoard.changeTurn();
            gameBoard.getPositionsPossible();
            update();
            if (isFinal()) {
                Toast.makeText(context, "Final", Toast.LENGTH_SHORT).show();
                createNewActivity();
            }
        }

        private void update() {
            updateTextViews();
            updateTime();
            notifyDataSetChanged();
        }

        private void fillTheCells(int position) {
            for (int cells :
                    gameBoard.getCellsToChange(position)) {
                gameBoard.fillCell(cells);
            }
        }

        private boolean isFinal() {
            if (gameBoard.isEnd()) {
                return true;
            } else if (gameBoard.timeEnd) {
                return true;
            } else if (gameBoard.getPositionsPossibleCells().isEmpty() && posible) {
                return true;
            } else if (gameBoard.getPositionsPossibleCells().isEmpty()) {
                gameBoard.changeTurn();
                gameBoard.getPositionsPossible();
                notifyDataSetChanged();
                posible = true;
                return false;
            } else {
                posible = false;
                return false;
            }
        }
    }
}