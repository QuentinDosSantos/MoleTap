package moletap.dossantos.diiage.org.moletapdossantos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaCas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String NAME = "Name";
    public static final String DATE = "Date";
    SharedPreferences sharedpreferences;
    ArrayList<Score> lstScore = new ArrayList<Score>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sharedpreferences.edit().putLong(DATE, System.currentTimeMillis()).apply();

        Button btnNewGame = findViewById(R.id.btnNewGame);
        Button btnScores = findViewById(R.id.btnScores);
        final EditText txtName = findViewById(R.id.txtName);

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedpreferences.edit().putString(NAME, txtName.getText().toString()).apply();

                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK)
        {
            lstScore.add((Score)data.getSerializableExtra("score"));
        }
    }
}
