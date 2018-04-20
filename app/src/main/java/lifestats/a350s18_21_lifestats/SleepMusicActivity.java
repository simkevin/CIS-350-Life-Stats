package lifestats.a350s18_21_lifestats;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SleepMusicActivity extends AppCompatActivity {

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_music);

        player = MediaPlayer.create(this, R.raw.someonelikeyou);
        player.start();
    }
}
