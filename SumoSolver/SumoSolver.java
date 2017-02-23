// http://pdorin.cs.lmu.edu/282.stuff/SumoSolver.html
class SumoSolver {
    // Main method
    // Takes in an array of numbers as Strings to build into list of food and money
    public static void main(String args[]) {
        System.out.println("Hello world!");
        if( ((args.length - 1) % 2 != 0) || args.length < 3 ) {
            System.out.println("ERROR: Invalid number of arguments.");
            return;
        }
        int[] foodArray = new int[args.length];
        int testNum = -1;
        for(int i = 0; i < foodArray.length; i++) {
            try {
                testNum = Integer.parseInt(args[i].trim());
            }
            catch (NumberFormatException e) {
                System.out.println("ERROR: Argument not a number!");
                return;
            }
            foodArray[i] = testNum;
        }
        // Now just hand off the nice array to the solver
        SumoSolver solver = new SumoSolver();
        solver.solve(foodArray, foodArray[foodArray.length - 1]);
    }

    // The solver function itself
    public void solve(int array[], int money) {
        // Turn int array into Food
        Food[] foodList = new Food[(array.length - 1) / 2];
        for(int i = 0; i < array.length - 1; i+= 2) {
            foodList[i/2] = new Food(array[i],array[i + 1]);
        }
        for(Food f : foodList) {
            System.out.println(f);
        }
        System.out.println("Money: " + money);
    }

    // Subclass for more organized handling of items
    private class Food {
        public int cost;
        public int pounds;

        public Food(int c, int p) {
            cost = c;
            pounds = p;
        }

        public String toString() {
            return "Food(cost: " + cost + ", pounds: " + pounds + ")";
        }
    }


}
