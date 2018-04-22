package lifestats.a350s18_21_lifestats;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class SleepMusicActivity extends AppCompatActivity {

    MediaPlayer player1;
    MediaPlayer player2;
    MediaPlayer player3;
    MediaPlayer player4;
    MediaPlayer player5;
    MediaPlayer player6;
    MediaPlayer player7;
    MediaPlayer player8;
    MediaPlayer player9;
    MediaPlayer player10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_music);

        player1 = MediaPlayer.create(this, R.raw.someonelikeyou);
        player2 = MediaPlayer.create(this, R.raw.strawberryswing_coldplay);
        player3 = MediaPlayer.create(this, R.raw.allsaints_pureshores);
        player4 = MediaPlayer.create(this, R.raw.barcelona_pleasedontgo);
        player5 = MediaPlayer.create(this, R.raw.cafedelmar_wecanfly);
        player6 = MediaPlayer.create(this, R.raw.djshah_mellomaniac);
        player7 = MediaPlayer.create(this, R.raw.enya_watermark);
        player8 = MediaPlayer.create(this, R.raw.marconiunion_weightless);
        player9 = MediaPlayer.create(this, R.raw.mozart_canzonetta_sullaria);
        player10 = MediaPlayer.create(this, R.raw.purechillout_airstream_electra);

        player1.start();
        player1.setNextMediaPlayer(player2);
        player2.setNextMediaPlayer(player3);
        player3.setNextMediaPlayer(player4);
        player4.setNextMediaPlayer(player5);
        player5.setNextMediaPlayer(player6);
        player6.setNextMediaPlayer(player7);
        player7.setNextMediaPlayer(player8);
        player8.setNextMediaPlayer(player9);
        player9.setNextMediaPlayer(player10);

    }
}
