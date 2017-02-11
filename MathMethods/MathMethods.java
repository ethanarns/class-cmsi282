import java.util.ArrayList;

class MathMethods {
    public ArrayList<ArrayList<Integer>> resultCol;

    public static void main(String args[]) {
        System.out.println("Hello World");
        //double[] polyList = {2.0,3.0,4.0};
        //MathMethods.poly(3.0, polyList); // Should be 47
        MathMethods.gcd(9,15);
    }

    public MathMethods() {
        resultCol = new ArrayList<ArrayList<Integer>>();
    }

    public static long factorial(int n) {
        return -1;
    }

    public static long fibonacci(int n) {
        if(n < 0) {
            System.out.println("BAD INPUT");
            return -1;
        }
        else if (n == 0) {
            return 0;
        }
        else if (n == 1 || n == 2) {
            return 1;
        }
        long firstNum = 1;
        long secondNum = 1;
        long swap = -1;
        for(int i = 2; i < n; i++) {
            swap = secondNum;
            secondNum = firstNum + secondNum;
            firstNum = swap;
        }
        return secondNum;
    }

    public static long gcd(long m, long n) {
        while(n > 0) {
            if(m > n) {
                m = m - n;
            }
            else {
                n = n - m;
            }
        }
        System.out.println(m);
        return m;
    }

    public static long lcm(long m, long n) {
        return -1;
    }

    // coeff[0] + coeff[1] * x + coeff[2] * x^2 + ....
    public static double poly(double x, double[] coeff) {
        double result = 0;
        for(int i = 0; i < coeff.length; i++) {
            result += coeff[i] * Math.pow(x, i);
        }
        return result;
    }

    public static double power(double x, int n) {
        return -1;
    }

    public static double root(int n, double x, double episolon) {
        return -1;
    }

    public static double sqrt(double x, double episolon) {
        return -1;
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
