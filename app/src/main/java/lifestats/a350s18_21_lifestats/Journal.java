package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Journal extends AppCompatActivity {
    HashMap<String, String> journalStore = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        final EditText journalEntry = (EditText) findViewById(R.id.journalEntry);
        Button submitButton = (Button) findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (journalEntry.getText() != null && journalEntry.getText().toString() != ""
                        && journalEntry.getText().toString() != " ") {
                    String input = journalEntry.getText().toString();
                    // date format
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = sdf.format(Calendar.getInstance().getTime());
                    Toast.makeText(getApplicationContext()
                            , "RECORDED at " + date + " \n" +
                                    "ENTRY: " + input
                            , Toast.LENGTH_LONG).show();

                    journalStore.put(date, input);
                    // reset edit text
                    journalEntry.setText(null);
                } else {
                    Toast.makeText(getApplicationContext()
                            , "Please write something! Cannot submit a blank entry."
                            , Toast.LENGTH_SHORT).show();
                    return;
                }

            }

        });

    }
}
