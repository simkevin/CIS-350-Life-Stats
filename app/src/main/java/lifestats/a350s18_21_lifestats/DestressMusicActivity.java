package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class DestressMusicActivity extends AppCompatActivity {

    MediaPlayer player1;
    MediaPlayer player2;
    MediaPlayer player3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destress_music);

        player1 = MediaPlayer.create(this, R.raw.allsaints_pureshores);
        player2 = MediaPlayer.create(this, R.raw.cafedelmar_wecanfly);
        player3 = MediaPlayer.create(this, R.raw.djshah_mellomaniac);

        player1.start();
        player1.setNextMediaPlayer(player2);
        player2.setNextMediaPlayer(player3);
    }

    public void back(View view) {

        if (player1.isPlaying()) {
            player1.stop();
        } else if (player2.isPlaying()) {
            player2.stop();
        } else if (player3.isPlaying()) {
            player3.stop();
        }

        Intent intent = new Intent(this, MusicCenter.class);
        startActivity(intent);
    }
}
