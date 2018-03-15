package moletap.dossantos.diiage.org.moletapdossantos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    Timer timer = new Timer();
    //temps restant en ms
    int remainingTime = 10000;

    int nbTaupeTouche = 0;
    int nbTaupeManque = 0;
    ArrayList<ImageButton> buttons;
    Random random = new Random();
    static int i = 0;

    ArrayList<Integer> tempsReaction = new ArrayList<Integer>();
    //Permet de mesurer le temps que met l'utilisateur pour taper sur la taupe
    long startTaupeShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttons = new ArrayList<ImageButton>();
        buttons.add((ImageButton)findViewById(R.id.mole1));
        buttons.add((ImageButton)findViewById(R.id.mole2));
        buttons.add((ImageButton)findViewById(R.id.mole3));
        buttons.add((ImageButton)findViewById(R.id.mole4));
        buttons.add((ImageButton)findViewById(R.id.mole5));
        buttons.add((ImageButton)findViewById(R.id.mole6));
        buttons.add((ImageButton)findViewById(R.id.mole7));
        buttons.add((ImageButton)findViewById(R.id.mole8));
        buttons.add((ImageButton)findViewById(R.id.mole9));

        for (ImageButton btn : buttons)
        {
            btn.setTag(false);
            btn.setImageDrawable(null);
            btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    ImageButton btn = (ImageButton) view;
                    if ((boolean)btn.getTag())
                    {
                        Integer temps = (int) (System.currentTimeMillis() - startTaupeShow);
                        btn.setImageDrawable(null);
                        nbTaupeTouche++;
                        btn.setTag(false);
                        tempsReaction.add(temps);
                    }
                }
            });
        }
        startTimer();
    }

    protected void startTimer() {
        timer.schedule(new TimerTask() {
            public void run() {
                mHandlerTaupe.obtainMessage(1).sendToTarget();
                remainingTime -= 1000;
                if (remainingTime <= 0)
                {
                    timer.cancel();
                    mHandlerFin.obtainMessage(1).sendToTarget();
                }
            }
        }, 0, 1000);

    }

    //Handler qui affiche les taupes.
    @SuppressLint("HandlerLeak")
    public Handler mHandlerTaupe = new Handler() {
        public void handleMessage(Message msg) {
            if ((boolean)buttons.get(i).getTag())
            {
                buttons.get(i).setImageDrawable(null);
                buttons.get(i).setTag(false);
                nbTaupeManque++;
            }

            i = random.nextInt(8 - 1);
            buttons.get(i).setImageResource(R.drawable.lilmole);
            buttons.get(i).setTag(true);
            startTaupeShow = System.currentTimeMillis();

        }
    };

    //Handler qui retourne sur MainActivity quand le temps est écoulé.
    @SuppressLint("HandlerLeak")
    public Handler mHandlerFin = new Handler() {
        public void handleMessage(Message msg) {
            Intent returnIntent = new Intent();
            Score score = new Score();
            score.setNbPoint(nbTaupeTouche);
            score.setNbManque(nbTaupeManque);
            if (tempsReaction.isEmpty())
            {
                score.setTpsReactionMin(Collections.min(tempsReaction));
                score.setTpsReactionMax(Collections.max(tempsReaction));
                score.setTpsReactionAvg(calculateAverage(tempsReaction));
            }
            else
            {
                score.setTpsReactionMin(0);
                score.setTpsReactionMax(0);
                score.setTpsReactionAvg(0);
            }
            returnIntent.putExtra("score", score);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
    };

    private Integer calculateAverage(List <Integer> marks) {
        Integer sum = 0;
        if(!marks.isEmpty()) {
            for (Integer mark : marks) {
                sum += mark;
            }
            return sum / marks.size();
        }
        return sum;
    }
}
