package agut_giralt.androidpractreversi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.Date;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.utils.Variables;

public class ActivityResult extends AppCompatActivity {

    private int size;
    private boolean withTime;
    private int timeLeft;
    private int score1;
    private int score2;
    private String alias;

    private EditText date;
    private EditText resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        date = (EditText) findViewById(R.id.date);
        resume = (EditText) findViewById(R.id.resume);
        getIntentValues(intent);
        setEditTexts();
    }

    private void setEditTexts() {
        Date actualDate = new Date();
        date.setText(actualDate.toString());
        createLog();
    }

    private void createLog() {
        String moreLog = "";
        if (withTime) {
            moreLog += "\n" + Variables.HaveLeft + timeLeft + Variables.NANOSEGONS;
        }
        if (timeLeft == 0) {
            resume.setText(getString(R.string.Alias) + alias + ". " +
                    getString(R.string.SizeOfTheGrid) + String.valueOf(size) + ".\n" +
                    getString(R.string.Time) + getString(R.string.You) + String.valueOf(score1) +
                    " " + getString(R.string.Player2) + String.valueOf(score2) + "." +
                    Math.abs(score1 - score2) + getString(R.string.Difference) + "." +
                    Math.abs((score1 + score2) - size * size) + " " + getString(R.string.Left) + moreLog);
        } else if (score1 > score2) {
            resume.setText(getString(R.string.Alias) + alias + ". " +
                    getString(R.string.SizeOfTheGrid) + String.valueOf(size) + ".\n" +
                    getString(R.string.Win) + getString(R.string.You) + String.valueOf(score1) +
                    " " + getString(R.string.Player2) + String.valueOf(score2) + "." +
                    Math.abs(score1 - score2) + getString(R.string.Difference) + "." +
                    Math.abs((score1 + score2) - size * size) + " " + getString(R.string.Left) + moreLog);
        } else if (score2 > score1) {
            resume.setText(getString(R.string.Alias) + alias + ". " +
                    getString(R.string.SizeOfTheGrid) + String.valueOf(size) + ".\n" +
                    getString(R.string.Lose) + getString(R.string.You) + String.valueOf(score1) +
                    " " + getString(R.string.Player2) + String.valueOf(score2) + "." +
                    Math.abs(score1 - score2) + getString(R.string.Difference) + "." +
                    Math.abs((score1 + score2) - size * size) + " " + getString(R.string.Left) + moreLog);
        } else if (score1 == score2) {
            resume.setText(getString(R.string.Alias) + alias + ". " +
                    getString(R.string.SizeOfTheGrid) + String.valueOf(size) + ".\n" +
                    getString(R.string.Draw) + getString(R.string.You) + String.valueOf(score1) +
                    " " + getString(R.string.Player2) + String.valueOf(score2) + "." +
                    Math.abs(score1 - score2) + getString(R.string.Difference) + "." +
                    Math.abs((score1 + score2) - size * size) + " " + getString(R.string.Left) + moreLog);
        } else if (score1 == score2 && timeLeft > 0) {
            resume.setText(getString(R.string.Alias) + alias + ". " +
                    getString(R.string.SizeOfTheGrid) + String.valueOf(size) + ".\n" +
                    getString(R.string.Time) + getString(R.string.You) + String.valueOf(score1) +
                    " " + getString(R.string.Player2) + String.valueOf(score2) + "." +
                    Math.abs(score1 - score2) + getString(R.string.Difference) + "." +
                    Math.abs((score1 + score2) - size * size) + " " + getString(R.string.Left) + moreLog);
        }
    }

    private void getIntentValues(Intent intent) {
        size = intent.getIntExtra(Variables.SIZE, 0);
        withTime = intent.getBooleanExtra(Variables.TIME, false);
        timeLeft = intent.getIntExtra(Variables.TIME_LEFT, 0);
        score1 = intent.getIntExtra(Variables.PLAYER1_SCORE, 0);
        score2 = intent.getIntExtra(Variables.PLAYER2_SCORE, 0);
        alias = intent.getStringExtra(Variables.USER);
    }
}
