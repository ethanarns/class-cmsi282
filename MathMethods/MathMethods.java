import java.util.ArrayList;
import java.math.BigInteger;

// http://pdorin.cs.lmu.edu/282.stuff/MathMethods/index.html
class MathMethods {
    public ArrayList<ArrayList<Integer>> resultCol;

    // Runner, takes in input from command line
    public static void main(String args[]) {
        System.out.println("Hello World");
        //System.out.println(MathMethods.gcd(8,18));
        //System.out.println(MathMethods.lcm(0,1));
        //double[] polyList = {2.0,3.0,4.0};
        //MathMethods.poly(3.0, polyList); // Should be 47
        //MathMethods.gcd(9,15);
        //System.out.println(MathMethods.fibonacci(6));
        for(int i = 0; i < 8; i++) {
            System.out.println(MathMethods.power(0,-1*i));
        }
    }

    public MathMethods() {
        resultCol = new ArrayList<ArrayList<Integer>>();
    }

    public static BigInteger factorial(int n) {

        return BigInteger.valueOf(-1);
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
        //System.out.println(m);
        return m;
    }

    public static long lcm(long m, long n) {
        if(m < 1 || n < 1) {
            System.out.println("BAD INPUT");
            return -1;
        }
        return (m*n)/MathMethods.gcd(m,n);
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
            return x*x;
        }
        else {
            return power(x, n/2) * power(x, n-(n/2));
        }
    }

    // A function encapsulating the real function to deal
    // with negative exponents and other potential issues
    public static double power(double x, int n) {
        if(x == 0 && n < 0) { // will end up dividng by zero!
            throw new ArithmeticException("/ by 0");
        }
        if(n == 1) {
            return x;
        }
        else if(n == 0) {
            return 1;
        }
        else if(n < 0) {
            n *= -1;
            return 1 / powerPos(x, n);
        }
        else if (n > 0) {
            return powerPos(x, n);
        }
        else {
            return 0;
        }
    }
    
    // n√x using Binary Search
    public static double root(int n, double x, double episolon) {
        double upperBound = x;
        double lowerBound = 0;
        double guess = x / 2;
        // Use exponents here
        return -1;
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
