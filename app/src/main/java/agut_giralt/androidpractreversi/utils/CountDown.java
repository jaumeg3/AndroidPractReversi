package agut_giralt.androidpractreversi.utils;

import android.os.CountDownTimer;

/**
 * Created by Jaume on 24/04/17.
 */

class CountDown extends CountDownTimer {
    private final GameBoard gameBoard;
    private long timeToFinish;

    CountDown(long millisInFuture, long countDownInterval, GameBoard gameBoard) {
        super(millisInFuture, countDownInterval);
        this.gameBoard = gameBoard;
    }

    @Override
    public void onFinish() {
        gameBoard.timeEnd = true;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        this.timeToFinish = millisUntilFinished;
    }

    public long getTime() {
        return timeToFinish;
    }
}