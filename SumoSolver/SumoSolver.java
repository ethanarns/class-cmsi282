// http://pdorin.cs.lmu.edu/282.stuff/SumoSolver.html
class SumoSolver {
    public Food[] foodList;

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
                // System.out.print("[");
                // for(int x = 0; x < tuple.length; x++) {
                //     System.out.print(x + ", ");
                // }
                // System.out.println("]");
                // System.out.println(total(tuple, foodList));
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
                        // Okay, but is the one above better?

                    }
                    else {
                        for(int t = 0; t < foodList.length; t++) {
                            tuple[t] = memo[i - 1][j][t];
                        }
                    }
                }
            }
        }
        printList(memo);
        for(int i = 0; i < memo[foodList.length - 1][money].length; i++) {
            if(memo[foodList.length - 1][money][i] == 1) {
                System.out.println("$" + foodList[i].cost + " / " + foodList[i].pounds + " pounds");
            }
        }
        int final_weight = 0;
        for(int i = 0; i < foodList.length; i++) {
            if(memo[foodList.length - 1][money][i] == 1) {
                final_weight += foodList[i].pounds;
            }
        }
        int final_count = 0;
        for(int i = 0; i < foodList.length; i++) {
            if(memo[foodList.length - 1][money][i] == 1) {
                final_count++;
            }
        }
        int final_cost = 0;
        for(int i = 0; i < foodList.length; i++) {
            if(memo[foodList.length - 1][money][i] == 1) {
                final_cost += foodList[i].cost;
            }
        }

        System.out.println("" + final_count + " items / $" + final_cost + " / " + total(memo[foodList.length - 1][money], foodList) + " pounds");
    }


    // Total functions
    // Totals up all items' pounds in tuple
    public int total(int array[], Food foods[]) {
        int total = 0;
        for(int i = 0; i < array.length; i++) {
            if(array[i] == 1)
                total+= foods[i].pounds;
        }
        return total;
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
