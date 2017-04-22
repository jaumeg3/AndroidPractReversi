package agut_giralt.androidpractreversi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.variables;

public class ActivityOptions extends AppCompatActivity implements View.OnClickListener {

    private EditText player;
    private RadioButton size;
    private CheckBox time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Button startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);
        player = (EditText) findViewById(R.id.aliasEditText1);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.size);
        size = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        time = (CheckBox) findViewById(R.id.time);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startBtn:
                if (!player.getText().toString().isEmpty()) {
                    Intent intent = new Intent(this, ActivityGame.class);
                    intent.putExtra(variables.USER, player.getText().toString());
                    intent.putExtra(variables.SIZE, Integer.parseInt(size.getText().toString()));
                    intent.putExtra(variables.TIME, time.isChecked());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Please, fill all the fields", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
