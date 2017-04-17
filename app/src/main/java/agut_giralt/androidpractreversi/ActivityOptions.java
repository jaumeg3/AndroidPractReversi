package agut_giralt.androidpractreversi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityOptions extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Button startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startBtn:
                Intent intent = new Intent(this, ActivityGame.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
