package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

public class CalorieBudgetActivity extends AppCompatActivity {
    private Spinner weightLossGoalSpinner;
    private Spinner genderSpinner;
    private EditText weightEntry;
    private EditText heightEntry;
    private EditText ageEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_budget);
        setSpinnerAndTextValues();
    }

    // This method sets the spinner values to the appropriate weight loss goals
    private void setSpinnerAndTextValues() {
        // We set the weight loss spinner
        weightLossGoalSpinner = (Spinner) findViewById(R.id.spinnerWeightLoss);
        ArrayAdapter<CharSequence> weightLossGoals = ArrayAdapter.createFromResource(this,
                R.array.weight_loss_goals, android.R.layout.simple_spinner_item);
        weightLossGoals.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightLossGoalSpinner.setAdapter(weightLossGoals);

        // We set the gender spinner
        genderSpinner = (Spinner) findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> genders = ArrayAdapter.createFromResource(this,
                R.array.genders, android.R.layout.simple_spinner_item);
        genders.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genders);

        // We set the edit text values
        weightEntry = findViewById(R.id.weightEntry);
        heightEntry = findViewById(R.id.heightEntry);
        ageEntry = findViewById(R.id.ageEntry);
    }

    // This calculates the calorie budget for the individual given their inputs
    public void calorieBudgetSubmit(View view) {
        String selection = weightLossGoalSpinner.getSelectedItem().toString();
        // We now match the selection
        double rateOfLoss = 0;
        if (selection.equals("Lose 2 pounds a week")) {
            rateOfLoss = 2;
        } else if (selection.equals("Lose 1.5 pounds a week")) {
            rateOfLoss = 1.5;
        } else if (selection.equals("Lose 1 pound a week")) {
            rateOfLoss = 1;
        } else if (selection.equals("Lose 0.5 pounds a week")) {
            rateOfLoss = 0.5;
        } else if (selection.equals("Maintain current weight")){
            rateOfLoss = 0;
        } else {
            rateOfLoss = 0;
        }

        // Now we get the inputed parameters
        Double weight = Double.parseDouble(weightEntry.getText().toString());
        Double height = Double.parseDouble(heightEntry.getText().toString());
        Double age = Double.parseDouble(ageEntry.getText().toString());
        String gender = genderSpinner.getSelectedItem().toString();

        // Next we error check the parameters
        boolean validWeight = isValidWeight(weight);
        boolean validHeight = isValidHeight(height);
        boolean validAge = isValidAge(age);
        if (validWeight && validHeight && validAge) {
            calculateCalorieBudget(rateOfLoss, weight, height, age, gender);
        }
    }

    /*
     * Takes a weight value, returns whether it is valid or not, and if invalid
     * displays a toast
     */
    private boolean isValidWeight(Double weight) {
        if (weight < 0 || weight > 1000) {
            Context context = getApplicationContext();
            CharSequence text = "Please enter a valid weight";
            int duration = Toast.LENGTH_LONG;
            Toast.makeText(context, text, duration).show();
            return false;
        }
        return true;
    }

    /*
     * Takes a height value, returns whether it is valid or not, and if invalid
     * displays a toast
     */
    private boolean isValidHeight(Double height) {
        // If height is negative or greater than 120in, it is an error
        if (height < 0 || height > 120) {
            Context context = getApplicationContext();
            CharSequence text = "Please enter a valid height";
            int duration = Toast.LENGTH_LONG;
            Toast.makeText(context, text, duration).show();
            return false;
        }
        return true;
    }

    /*
     * Takes an age value, returns whether it is valid or not, and if invalid
     * displays a toast
     */
    private boolean isValidAge(Double age) {
        // If height is negative or greater than 120in, it is an error
        if (age < 0 || age > 130) {
            Context context = getApplicationContext();
            CharSequence text = "Please enter a valid age";
            int duration = Toast.LENGTH_LONG;
            Toast.makeText(context, text, duration).show();
            return false;
        }
        return true;
    }

    /*
     *This calculates the calorie budget and updates the database. Note that the age is in years,
     * the weight in lbs, and the height in inches.
     */
    private void calculateCalorieBudget(double poundsPerWeek, double weight, double height,
                                        double age, String gender) {
        /*
         * The equation for calculating calories burned is the BMR (basal
         * metabolic rate) times 1.53 for everyday movement and activity
         * (like walking around between rooms)
         */

        double bmr;
        if (gender.equals("Male")) {
            bmr = 66 + (6.2 * weight) + (12.7 * height) - (6.76 * age);
        } else if (gender.equals("Female")) {
            bmr =  655.1 + (4.35 * weight) + (4.7 * height) - (4.7 * age);
        } else{
            // There is an error in the spinner selection which is a bug
            return;
        }

        // Once we have the bmr, we get the caloric rate
        double caloriesBurnedPerDay = bmr * 1.53;

        // A deficit of 3500 calories a week will burn off one pound
        int calorieBudget = (int) (caloriesBurnedPerDay - ((3500 * poundsPerWeek) / 7));

        // Displays the newly calculated budget
        displayBudgetToast(calorieBudget);

        // Stores the new calorie budget in the database
        CalorieBudgetWrapper.getInstance().setBudget(calorieBudget);
    }

    // Displays a toast message that notifies the user of the new calorieBudget value
    private void displayBudgetToast(int calorieBudget) {
        Context context = getApplicationContext();
        CharSequence text = "Your daily calorie budget is " + calorieBudget + " calories.";
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(context, text, duration).show();
    }
}
