import java.math.BigInteger;

// http://pdorin.cs.lmu.edu/282.stuff/MathMethods/index.html
class MathMethods {

    // Runner, takes in input from command line
    public static void main(String args[]) {
        if(args.length < 1) {
            System.out.println("Welcome to MathMethods!");
            System.out.println("---");
            System.out.println("GCD of 8 and 28: " + MathMethods.gcd(8,28));
            System.out.println("---");
            System.out.println("LCM of 8 and 5: " + MathMethods.lcm(8,5));
            System.out.println("---");
            double[] polyList = {2.0,3.0,4.0};
            System.out.println("Polynomial: " + MathMethods.poly(3.0, polyList) + " should be 47"); // Should be 47
            System.out.println("---");
            System.out.println("Fibonacci 0 to 8:");
            for(int i = 0; i <= 8; i++) {
                System.out.print("" + MathMethods.fibonacci(i) + " ");
            }
            System.out.println("\n---");
            System.out.println("Powers of 2:");
            for(int i = 0; i < 8; i++) {
                System.out.println("2^" + i + " = " + MathMethods.power(2,i));
            }
            System.out.println("---");
            System.out.println("nth-roots:");
            double powQuestion;
            int root = 2;
            double orig = 8;
            double ans;
            System.out.println("" + root + "√" + orig + " = " + (powQuestion = MathMethods.root(root,orig,0.00001)));
            ans = MathMethods.power(powQuestion,root);
            System.out.println("ans: " + ans + ", orig: " + orig);
            System.out.println("---");
            System.out.println("Square root of 64: " + MathMethods.sqrt(64,0.1));
            System.out.println("Square root of 60: " + MathMethods.sqrt(60,0.00001));
            System.out.println("---");
            System.out.println("Factorial 4: " + MathMethods.factorial(4));
            System.out.println("Factorial 7: " + MathMethods.factorial(7));
        }
    }

    public MathMethods() {
        resultCol = new ArrayList<ArrayList<Integer>>();
    }

    public static BigInteger factorial(int n) {
        if(n == 0)
            return BigInteger.valueOf(1);
        else if (n < 0) {
            System.out.println("BAD INPUT");
            return BigInteger.valueOf(-1);
        }
        BigInteger result = BigInteger.valueOf(1);
        for(int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static BigInteger fibonacci(int n) {
        if(n < 0) {
            System.out.println("BAD INPUT");
            return BigInteger.valueOf(-1);
        }
        else if (n == 0) {
            return BigInteger.valueOf(0);
        }
        else if (n == 1 || n == 2) {
            return BigInteger.valueOf(1);
        }
        BigInteger firstNum = BigInteger.valueOf(1);
        BigInteger secondNum = BigInteger.valueOf(1);
        BigInteger swap = BigInteger.valueOf(-1);
        for(long i = 2; i < n; i++) {
            swap = secondNum;
            secondNum = firstNum.add(secondNum);
            firstNum = swap;
        }
        return secondNum;
    }

    public static long gcd(long m, long n) {
        if(m < 1 || n < 1) {
            System.out.println("BAD INPUT");
            return -1;
        }
        while(n > 0) {
            if(m > n) {
                m = m - n;
            }
            else {
                n = n - m;
            }
        }
        return m;
    }

    public static long lcm(long m, long n) {
        if(m < 1 || n < 1) {
            System.out.println("BAD INPUT");
            return -1;
        }
        // Neat shortcut
        return (m * n) / MathMethods.gcd(m,n);
    }

    // coeff[0] + coeff[1] * x + coeff[2] * x^2 + ....
    public static double poly(double x, double[] coeff) {
        double result = 0;
        for(int i = 0; i < coeff.length; i++) {
            result += coeff[i] * Math.pow(x, i);
        }
        return result;
    }

    // The real function, only works with positive numbers
    private static double powerPos(double x, int n) {
        if(n == 0) {
            return 1;
        }
        if(n == 1) {
            return x;
        }
        else if(n == 2) {
            return x * x;
        }
        else {
            return power(x, n/2) * power(x, n-(n/2));
        }
    }

    // A function encapsulating the real function to deal
    // with negative exponents and other potential issues
    public static double power(double x, int n) {
        if(x == 0 && n < 0) // will end up dividng by zero!
            throw new ArithmeticException("/ by 0");
        if(n == 1)
            return x;
        else if(n == 0)
            return 1;
        else if(n < 0) {
            n *= -1; // Make n positive
            return 1 / powerPos(x, n);
        }
        else if (n > 0)
            return powerPos(x, n);
        else
            return 0;
    }

    private static boolean isInRange(double num, double idealNum, double range) {
        if(num + range > idealNum) {
            if(num - range < idealNum) {
                return true;
            }
        }
        return false;
    }

    // n√x using Binary Search
    // what = n√x?
    // In other words, what^n = x?
    public static double root(int n, double x, double episolon) {
        double upperBound = x;
        double lowerBound = 0;
        double guess = x/2;
        double rooted = power(guess,n);
        int stopper = 0;
        //System.out.println("LowerBound: " + lowerBound + ", UpperBound: " + upperBound + ", Guess: " + guess + ", Rooted: " + rooted + ", x: " + x);
        //while( rooted != x ) {
        while(rooted < x - episolon || rooted > x + episolon) {
            stopper++;
            if(stopper > 999999) {
                // Just in case
                System.out.println("MAX ITERATIONS REACHED.");
                return guess;
            }
            if(rooted > x) {
                upperBound = guess;
            }
            else if(rooted < x) {
                lowerBound = guess;
            }
            guess = (upperBound + lowerBound) / 2;
            rooted = power(guess, n);
            //System.out.println("LowerBound: " + lowerBound + ", UpperBound: " + upperBound + ", Guess: " + guess + ", Rooted: " + rooted + ", x: " + x);
        }
        return guess;
    }

    // √x (Literally just the above, but with n being 2)
    public static double sqrt(double x, double episolon) {
        return MathMethods.root(2, x, episolon);
    }

    public static void printTest() {
        int[] results1 = {1, 2, 3, 4};
        int[] results2 = {5, 6, 7, 8};
        int[][] results = {results1, results2};
        for(int i = 0; i < results1.length; i++) {
            for(int[] r : results) {
                System.out.print(r[i] + " ");
            }
            System.out.println();
        }
    }
}
