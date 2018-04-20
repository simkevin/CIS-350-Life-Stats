package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class NutritionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        final Button nutrition = findViewById(R.id.nutritionsubmit);
        final EditText nutritionTextField = findViewById(R.id.nutrition_edit);
        final EditText calorieTextField = findViewById(R.id.caloriesValue);
        final Button archiveNutrition = findViewById(R.id.pastFood);
        final NutritionWrapper dateNutrition = NutritionWrapper.getInstance();

        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting the current date and the amount spent
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date date = new Date();
                String currDate = dateFormat.format(date);
                String foodText = nutritionTextField.getText().toString();
                String calorieText = calorieTextField.getText().toString();


                //if more is being added to a current day
                if (dateNutrition.containsKey(currDate)) {
                    String text = dateNutrition.get(currDate);
                    text = text + ", " + foodText + " (calories: "+ calorieText + ")";
                    dateNutrition.put(currDate, text);
                }
                //if its a new day
                else {
                    dateNutrition.put(currDate, foodText +  " (calories: "+ calorieText + ")");
                }
                nutritionTextField.setText("");
                calorieTextField.setText("");
            }
        });

        archiveNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> dateList = new ArrayList<String>();
                ArrayList<String>  foodList = new ArrayList<String>();
                for (String s : dateNutrition.keySet()) {
                    String text = s + ": " + dateNutrition.get(s);
                    dateList.add(text);
                    foodList.add(dateNutrition.get(s));
                }
                Intent archives = new Intent(NutritionActivity.this, pastNutrition.class);
                archives.putExtra("dateList", dateList);
                archives.putExtra("foodList", foodList);
                startActivity(archives);
            }
        });
    }
}
