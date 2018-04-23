package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MusicCenter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_center);
    }

    public void openProductiveMusic(View view) {
        Intent intent = new Intent(this, ProductiveMusicActivity.class);
        startActivity(intent);
    }

    public void openDestressMusic(View view) {
        Intent intent = new Intent(this, DestressMusicActivity.class);
        startActivity(intent);
    }

    public void openHappyMusic(View view) {
        Intent intent = new Intent(this, HappyMusicActivity.class);
        startActivity(intent);
    }

    public void openSleepMusic(View view) {
        Intent intent = new Intent(this, SleepMusicActivity.class);
        startActivity(intent);
    }
}
