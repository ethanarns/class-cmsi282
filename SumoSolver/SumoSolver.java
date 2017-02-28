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

        solveR(memo, foodList, 2, money, 0);




        // Kill the rest of the function
        if(true)
            return;
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
                        if(remainder > 0) { // So we have a remainder
                            int[] otherTuple = memo[i - 1][remainder];
                            for(int t = 0; t < tuple.length; t++) { // Add 1s to the current tuple based on the remainder position on graph
                                if(tuple[t] != 1) {
                                    tuple[t] = otherTuple[t];
                                }
                            }
                        }
                        // Okay, set, but is the one above better?
                        if(total(memo[i - 1][j], foodList) > total(tuple, foodList)) { // Just overwrite it
                            for(int x = 0; x < tuple.length; x++) { // with the tuple directly above
                                tuple[x] = memo[i - 1][j][x];
                            }
                        }

                    }
                    else { // You can't add the current, therefore just make it the one above
                        for(int t = 0; t < foodList.length; t++) {
                            tuple[t] = memo[i - 1][j][t];
                        }
                    }
                }
            }
        }

        // Finished! Lets print out the results
        //printList(memo);
        for(int i = 0; i < memo[foodList.length - 1][money].length; i++) {
            if(memo[foodList.length - 1][money][i] == 1) {
                System.out.println("$" + foodList[i].cost + " / " + foodList[i].pounds + " pounds");
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

    // curMoney = "j" or "x value", where you search for remainder
    public int[] solveR(int memo[][][], Food foods[], int yPos, int curMoney, int tuplePos) {
        if(yPos == 0) { // On the top row, its only [1 0 0] or [0 0 0]
            int[] resultTuple = new int[foods.length];
            int curFoodCost = foods[0].cost;

        }
        int[] curTuple = memo[yPos][curMoney];
        int remainder = curMoney - foods[tuplePos].cost;
        int[] remainderLoc = memo[yPos - 1][remainder];
        return null;
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
