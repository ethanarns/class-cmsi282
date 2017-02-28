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
        // Hand off cleaned array to solver function
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
        // Begin filling up memory with previous best combinations
        int[][][] memo = new int[foodList.length][money + 1][foodList.length];
        for(int i = 0; i < memo.length; i++) {
            int[][] row = memo[i];
            for(int j = 1; j < row.length; j++) {
                int[] tuple = row[j];
                // First row fixing, dodges exceptions
                if(i == 0) {
                    if(foodList[0].cost <= j) {
                        tuple[0] = 1;
                    }
                }
                else {
                    // Can I add the current?
                    if(foodList[i].cost <= j) {
                        tuple[i] = 1;
                        // What is left?
                        int remainder = j - foodList[i].cost;
                        int[] otherTuple = memo[i - 1][remainder];
                        for(int t = 0; t < tuple.length; t++) {
                            if(tuple[t] != 1) {
                                tuple[t] = otherTuple[t];
                            }
                        }
                        // Is the one above better?
                        if(total(memo[i - 1][j], foodList) > total(tuple, foodList)) {
                            for(int x = 0; x < tuple.length; x++) {
                                tuple[x] = memo[i - 1][j][x];
                            }
                        }
                    }
                    else {
                        for(int t = 0; t < foodList.length; t++) {
                            tuple[t] = memo[i - 1][j][t];
                        }
                    }
                }
            }
        }

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

    // Totals up all items' pounds in tuple
    public int total(int array[], Food foods[]) {
        int total = 0;
        for(int i = 0; i < array.length; i++) {
            if(array[i] == 1)
                total+= foods[i].pounds;
        }
        return total;
    }

    // Subclass for more organized handling of items
    private class Food {
        public int cost;
        public int pounds;

        public Food(int c, int p) {
            cost = c;
            pounds = p;
        }
    }

}
