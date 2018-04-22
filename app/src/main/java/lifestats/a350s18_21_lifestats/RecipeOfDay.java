package lifestats.a350s18_21_lifestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class RecipeOfDay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_of_day);

        String [] recipedataBase = {"Artichoke Hummus" , "Baked White Bean Dip with Rosemary and Parmesan", "Black Bean Dip",
                "Blood Orange and Green Olive Salad", "Bruschetta Three Ways", "Chili Garlic Corn Chips", "Chilled Ginger Cantaloupe Soup",
                "Chipotle Bacon Deviled Eggs", "Coconut Shrimp Poppers with Chili Mango Cream",
                "Creamy Blue Cheese Dip with Lemon and Chives", "Deviled Eggs","Herbed Cheddar Parmesan Crisps",
                "Kettle Corn", "Nando’s Honey Garlic Wings", "Pickled Serrano Chiles", "Pickled Serrano Chiles",
                "Smoked Tuna Dip", "Spicy Pickled Garlic", "Sweet and Spicy Chinese Five Spice Roasted Almonds", "Whole Wheat Pita Chips",
                "Crustless Quiche with Ham, Asparagus, and Gruyere",
                "Curried Chicken Salad",
                "Curried Turkey Salad with Apples, Cranberries, and Walnuts",
                "Easy Egg Salad",
                "Egg in a Nest",
                "Farfalle with Pistachio Cream Sauce",
                "Grilled Chicken and Pineapple Pizza",
                "Grilled Rib Eye with Havarti Horseradish Fondue",
                "Hearty Spinach and Sausage Soup",
                "Herbed Tuna Salad with Feta and Pine Nuts",
                "Homemade Sloppy Joes",
                "Horseradish Meatloaf",
                "Macaroni and Cheese with Bacon, Leeks, and Thyme",
                "Monster Meatball Sandwiches",
                "Pasta with Tomato-Cream Sauce and Fresh Basil",
                "Roasted Chicken Thighs and Cauliflower",
                "Simple Carne Asada",
                "Smoky Spiced Black-Eyed Peas with Bacon",
                "Southwestern Macaroni Casserole",
                "Spice Rubbed Flank Steak",
                "Spiced Turkey Burgers with Green Olives and Feta",
                "Spiked Egg Nog French Toast",
                "The Ultimate Manwich",
                "Three Bean Vegetarian Chili",
                "Tomato Paella with Chorizo",
                "Tofu in Coconut Sauce with Ginger and Lemongrass",
                "Tuna Noodle Casserole",
                "Tuscan Chicken Under a Brick",
                "Veggie Chili Beans and Rice",
                "Whole Wheat Pasta with Browned Butter and Mizithra Cheese",
                "Asparagus Gratin",
                "Black Beans and Corn with Green Chiles",
                "Broccoli Salad with Bacon and Raisins",
                "Brown Rice Salad with Cumin and Lime Vinaigrette",
                "Butternut Squash and Sweet Potato Gratin",
                "Corn Bread with Bacon",
                "Cranberry Applesauce with Fresh Ginger",
                "Creamed Corn with Bacon and Rosemary",
                "Creamy Peas with Ham and Mint",
                "Cumin-Scented Sweet Potato Hash",
                "Curry Artichoke Rice Salad",
                "Extra Creamy Mashed Potatoes",
                "Green Beans with Bacon and Tart Cherry Balsamic Glaze",
                "Green Beans with Balsamic Browned Butter",
                "Grilled Corn, Mango and Jicama Salad with Honey Vinaigrette",
                "Grilled Yellow Squash with Fresh Dill Vinaigrette and Feta",
                "Grilled Peaches with Blue Cheese and Honey",
                "Grilled Zucchini with Lemon and Olive Oil",
                "Orange Cranberry Salad with Walnuts and Blue Cheese",
                "Paella with Tomatoes",
                "Perfect Brown Rice",
                "Quick Pickled Beets",
                "Rice Pilaf with Raisins and Pine Nuts",
                "Roasted Whole Artichokes with Garlic, Lemon, and Olive Oil",
                "Roasted Broccoli with Lemon, Chili-garlic oil, and Parmesan",
                "Rosemary Sweet Potato Fries",
                "Rutabaga Puff",
                "Steamed Cauliflower with Curry Butter and Toasted Almonds",
                "Slow-Roasted Cherry Tomatoes",
                "Semi-Homemade Refried Beans",
                "Shredded Brussels Sprouts with Bacon and Walnuts",
                "Sourdough Stuffing with Apples and Bacon",
                "Sourdough Garlic Croutons",
                "Spaghetti Squash Gratin",
                "Sweet and Sour Grilled Pumpkin",
                "Sweet and Spicy Cucumber Dill Salad",
                "Tabbouleh with Persimmons and Almonds",
                "Tangerine and Jicama Salad with Garlic and Cilantro",
                "Tostones/Fried Plantain Slices",
                "Warm Red Cabbage Slaw with Apple and Caraway Seeds",
                "Whole Wheat Greek Orzo Salad",
                "Zucchini and Tomato Gratin",
                "Cherry Banana Muffins with White Chocolate Chips",
                "Cherry Pecan Bran Muffins",
                "Chocolate Chip Zucchini Bread",
                "Cinnamon Swirl Loaf",
                "Corn Bread with Bacon",
                "Double Dark Chocolate Beet Muffins",
                "Fluffy Buttermilk Biscuits",
                "Heart-Shaped Cinnamon Rolls",
                "Herbed Cheese Biscuits",
                "Honey Lemon Olive Oil Muffins with Lemon Glaze",
                "Lemon Poppyseed Zucchini Bread",
                "Nectarine Muffins",
                "Oatmeal Raisin Cookie Muffins",
                "Orange Yogurt Bread",
                "Pizza Dough"

        };
  

        TextView thisRecipe = findViewById(R.id.recipeMessage);

        //database of recipes


        PastRecipesWrapper dateRecipe = PastRecipesWrapper.getInstance();;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String currDate = dateFormat.format(date);
        Random random = new Random();
        int randomIndex = random.nextInt(recipedataBase.length);
        String randomQuote =  recipedataBase[randomIndex];
        //if it's a new day
        if (!dateRecipe.containsKey(currDate)) {
            dateRecipe.put(currDate, randomQuote);
            thisRecipe.setText(randomQuote);

            //notification
            Toast.makeText(getApplicationContext(), "New recipe of the day!",
                    Toast.LENGTH_LONG).show();
        }
        //if it's not a new day
        else {
            thisRecipe.setText(dateRecipe.get(currDate));
        }


    }
}
