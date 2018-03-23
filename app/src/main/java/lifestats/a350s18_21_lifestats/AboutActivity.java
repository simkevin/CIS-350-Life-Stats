package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*
 * This activity displays information about the app in text. It has a back button to go back to
 * the control panel.
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    // This method takes the user back to the previous activity, the Control Panel
    public void backButtonClick(View view) {
        finish();
    }

}
