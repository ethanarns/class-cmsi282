class MathMethods {
    public static void main(String args[]) {
        System.out.println("Hello World");
        MathMethods.printTest();
    }

    public MathMethods() {

    }

    public static long factorial(int n) {
        return -1;
    }

    public static long fibonacci(int n) {
        int firstNum = 0;
        int secondNum = 1;
        int swap = -1;
        for(int i = 2; i < n; i++) {

        }
        return -1;
    }

    public static long gcd(long m, long n) {
        return -1;
    }

    public static long lcm(long m, long n) {
        return -1;
    }

    public static double poly(double x, double[] coeff) {
        return -1;
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
