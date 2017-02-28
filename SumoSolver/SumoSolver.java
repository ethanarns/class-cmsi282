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
    public void solve(int array[], final int money) {
        // Turn int array into Food
        Food[] foodList = new Food[(array.length - 1) / 2];
        for(int i = 0; i < array.length - 1; i+= 2) {
            foodList[i/2] = new Food(array[i],array[i + 1]);
        }
        // Print it out
        for(int i = 0; i < foodList.length - 1; i++) {
            System.out.print(foodList[i] + ", ");
        }
        System.out.print(foodList[foodList.length - 1]);
        System.out.println(", Money: " + money);


        int[][][] memo = new int[foodList.length][money + 1][foodList.length];
        // This loop is going through each of the rows 1 at a time
        for(int i = 0; i < memo.length; i++) {
            // Simplify by making "row" variable passed by reference
            int[][] row = memo[i];
            for(int j = 1; j < row.length; j++) {
                int[] tuple = row[j];
                // First row fixing, dodges
                if(i == 0) {
                    if(foodList[0].cost <= j) {
                        tuple[0] = 1;
                    }
                }
                // Lets do the rest of it now
                else {
                    // Can I add the current?
                    if(foodList[i].cost <= j) {
                        tuple[i] = 1;
                        // Great! Now, lets see what we have left...
                        int remainder = j - foodList[i].cost;
                        int[] otherTuple = memo[i - 1][remainder];
                        for(int t = 0; t < tuple.length; t++) {
                            if(tuple[t] != 1) {
                                tuple[t] = otherTuple[t];
                            }
                        }
                    }
                }
            }
        }
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

    public int total(int[] a, int to) {
        int result = 0;
        if(to > a.length - 1 || to < 0) {
            System.out.println("ERROR: 'to' in total() is out of bounds!");
            return -1;
        }
        for(int i = 0; i <= to; i++) {
            result += a[i];
        }
        return result;
    }

    public int total(int[] a) {
        int result = 0;
        for(int i = 0; i < a.length; i++) {
            result += a[i];
        }
        return result;
    }

    public void printList(int list[][][]) {
        final int LENGTH_OF_TUPLE = 1 + (list.length * 2);
        for(int m = 0; m < list[0].length; m++) {
            System.out.print(" " + m);
            for(int n = 0; n < LENGTH_OF_TUPLE - ("" + m).length(); n++) {
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
