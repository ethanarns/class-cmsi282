// http://pdorin.cs.lmu.edu/282.stuff/SumoSolver.html
class SumoSolver {
    // Main method
    // Takes in an array of numbers as Strings from execution on command line
    // Checks arguments, converts to ints, then hands off to instantiated Solver
    public static void main(String args[]) {
        if( ((args.length - 1) % 2 != 0) || args.length < 3 ) {
            System.out.println("ERROR: Invalid number of arguments.");
            return;
        }
        // foodArray is args[], converted to ints
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
            if(testNum < 0) {
                System.out.println("ERROR: No negative numbers.");
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
        //First is 3 combos of food, second is 0 - money amount, last is tuple size
        int[][][] memo = new int[foodList.length][money + 1][foodList.length];
        printList(memo);

        // How it works
        /*
        Memoization vastly increases speed by saving previous sub-problems
        and then going back and checking it later to see if its the most
        optimal. So with this, we have a variety of food the sumo wrestler
        can get 1 or 0 of. This is a variation of the change problem, which
        you need a 2D array for. However you can only use 1 at most, and are trying to maximize profit
        */
    }

    public void printList(int list[][][]) {
        String len = "";
        for(int m = 0; m < list[0].length; m++) {
            System.out.print(" " + m);
            for(int n = 0; n < 7 - ("" + m).length(); n++) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for(int i = 0; i < list.length; i++) {
            for(int j = 0; j < list[0].length; j++) {
                System.out.print("[");
                for(int k = 0; k < list[0][0].length; k++) {
                    System.out.print("" + list[i][j][k]);
                    if(k != list[0][0].length - 1) {
                        System.out.print(",");
                    }
                }
                System.out.print("] ");
            }
            System.out.println();
        }
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
            //return "Food(cost: " + cost + ", pounds: " + pounds + ")";
            return("[" + cost + "," + pounds + "]");
        }
    }


}
