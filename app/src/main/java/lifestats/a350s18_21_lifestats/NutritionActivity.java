package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class NutritionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        // Get the buttons
        final Button nutrition = findViewById(R.id.nutritionsubmit);
        final EditText nutritionTextField = findViewById(R.id.nutrition_edit);
        final EditText calorieTextField = findViewById(R.id.caloriesValue);
        final Button archiveNutrition = findViewById(R.id.pastFood);
        final NutritionWrapper dateNutrition = NutritionWrapper.getInstance();
        final TextView calorieBudgetText = findViewById(R.id.calorieBudgetText);

        int budget = getRemainingCalorieBalance();
        calorieBudgetText.setText("Calories Remaining: " + budget);

        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting the current date and the amount spent
                String currDate = getTodaysDate();
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

                // We update the calorie budget
                int budget = getRemainingCalorieBalance();
                calorieBudgetText.setText("Calories Remaining: " + budget);
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
                Intent archives = new Intent(NutritionActivity.this, PastNutrition.class);
                archives.putExtra("dateList", dateList);
                archives.putExtra("foodList", foodList);
                startActivity(archives);
            }
        });
    }

    // Redirects to the calorie budget activity
    public void openCalorieBudget(View view) {
        Intent intent = new Intent(this, CalorieBudgetActivity.class);
        startActivity(intent);
    }

    private int getRemainingCalorieBalance() {
        int budget = CalorieBudgetWrapper.getInstance().getBudget();
        String currDate = getTodaysDate();
        String todaysFoodConsumed = NutritionWrapper.getInstance().get(currDate);

        // If food was eaten today, we sum up the calories and subtract it from the budget
        if (todaysFoodConsumed != null) {
            String[] values = todaysFoodConsumed.split("calories: ");
            int calorieSum = 0;
            for (int i = 1; i < values.length; i++) {
                calorieSum += Integer.parseInt(values[i].split("\\)")[0]);
            }
            budget = budget - calorieSum;
        }
        return budget;
    }

    private String getTodaysDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String currDate = dateFormat.format(date);
        return currDate;
    }

}
