package agut_giralt.androidpractreversi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;


/**
 * Created by jaume on 19/04/17.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private GameBoard gameBoard;
    private GridView board;
    private TextView cells, timing, score1, score2;
    private boolean withTime;
    private int SIZE;
    private String alias;
    private int TIME = 40;
    private int timeLeft;


    public ImageAdapter(Context c, GameBoard gameBoard, GridView board, String alias, int size,
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
        this.gameBoard.initGameBoard();
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
        } else return R.drawable.wsq;

    }

    private void updateTextViews() {
        this.cells.setText(String.valueOf(SIZE * SIZE - gameBoard.getUserCells().size() -
                gameBoard.getComputerCells().size()));
        this.score1.setText(String.valueOf(gameBoard.getUserCells().size()));
        this.score2.setText(String.valueOf(gameBoard.getComputerCells().size()));
    }

    private class MyOnClickListener implements View.OnClickListener {
        private final int position;
        private Context context;

        MyOnClickListener(int position, Context context) {
            this.position = position;
            this.context = context;
        }

        public void onClick(View v) {
            doTheMovement();
        }

        private void doTheMovement() {
            if (withTime) {
            }
        }
    }
}