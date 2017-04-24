package agut_giralt.androidpractreversi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by jaume on 19/04/17.
 *
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private GameBoard gameBoard;
    private TextView cells, timing, score1, score2;
    private boolean withTime;
    private int SIZE;
    private String alias;
    private int TIME = 40;
    private int timeLeft;
    private boolean posible = false;


    public ImageAdapter(Context c, GameBoard gameBoard, String alias, int size,
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
        this.timeLeft = TIME;
        //updateTime();
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
            btn.setLayoutParams(new GridView.LayoutParams(100, 100));
            btn.setPadding(5, 5, 5, 5);
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
        this.timing.setText(String.valueOf(gameBoard.getTurn()));
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
            } else {
                Toast.makeText(context, "Invalid Movement. Try again", Toast.LENGTH_SHORT).show();
            }
        }

        private void doTheMovement(int position) {
            if (withTime) {
                Toast.makeText(context, "T", Toast.LENGTH_SHORT).show();
            }
            fillTheCells(position);
            gameBoard.changeTurn();
            gameBoard.getPositionsPossible();
            updateTextViews();
            notifyDataSetChanged();
            if (isFinal()) {
                Toast.makeText(context, "Final", Toast.LENGTH_SHORT).show();
                createNewActivity();
            }
        }

        private void createNewActivity() {
        }

        private void fillTheCells(int position) {
            for (int cells :
                    gameBoard.getCellsToChange(position)) {
                gameBoard.fillCell(cells);
            }
        }

        private boolean isFinal() {
            if (gameBoard.isEnd()) return true;
            if (gameBoard.getPositionsPossibleCells().isEmpty() && posible) {
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