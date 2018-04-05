package lifestats.a350s18_21_lifestats;

/**
 * Created by Kevin on 4/5/18.
 */

public class Budget {

    private static double budget;

    public static void setBudget(double amount) {
        budget = amount;
    }

    public static double getBudget() {
        return budget;
    }

    public static void updateBudget(double amount) {
        budget -= amount;
    }
}
