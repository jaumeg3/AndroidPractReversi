package agut_giralt.androidpractreversi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnHelp = (Button) findViewById(R.id.btnHelp);
        Button btnInit = (Button) findViewById(R.id.btnInit);
        Button btnExit = (Button) findViewById(R.id.btnExit);
        btnHelp.setOnClickListener(this);
        btnInit.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHelp:
                Intent intent = new Intent(this, help.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnInit:
                Intent intent1 = new Intent(this, ActivityOptions.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.btnExit:
                finish();
                break;
        }
    }
}
