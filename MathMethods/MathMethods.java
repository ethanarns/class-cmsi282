import java.math.BigInteger;

// http://pdorin.cs.lmu.edu/282.stuff/MathMethods/index.html
class MathMethods {

    // Runner, takes in input from command line
    public static void main(String args[]) {
        if(args.length < 2) {
            System.out.println("INVALID INPUT");
            return;
        }
        args[0] = args[0].trim().toLowerCase();
        switch(args[0]) {
            // Factorial function
            case("factorial"):
                if(args.length > 2) {
                    System.out.println("INVALID INPUT");
                    return;
                }
                System.out.println(factorial(Integer.parseInt(args[1].trim())));
                break;

            // Fibonacci function
            case("fibonacci"):
                if(args.length > 2) {
                    System.out.println("INVALID INPUT");
                    return;
                }
                System.out.println(fibonacci(Integer.parseInt(args[1].trim())));
                break;

            // Greatest Common Denominator
            case("gcd"):
                if(args.length != 3) {
                    System.out.println("INVALID INPUT");
                    return;
                }
                System.out.println( gcd(Long.parseLong(args[1]), Long.parseLong(args[2]) ) );
                break;

            // Least Common Multiple
            case("lcm"):
                if(args.length != 3) {
                    System.out.println("INVALID INPUT");
                    return;
                }
                System.out.println( lcm(Long.parseLong(args[1]), Long.parseLong(args[2]) ) );
                break;

            // Exponent (2 args)
            case("power"):
                if(args.length != 3) {
                    System.out.println("INVALID INPUT");
                    return;
                }
                System.out.println( power(Double.parseDouble(args[1]), Integer.parseInt(args[2]) ) );
                break;

            // N-th root (3 args)
            case("root"):
                if(args.length != 4) {
                    System.out.println("INVALID INPUT");
                    return;
                }
                System.out.println( root(Integer.parseInt(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]) ) );
                break;

            // Square root (2 args)
            case("sqrt"):
                if(args.length != 3) {
                    System.out.println("INVALID INPUT");
                    return;
                }
                System.out.println( sqrt(Double.parseDouble(args[1]), Double.parseDouble(args[2]) ) );
                break;

            // Polynomial (n args)
            case("poly"):
                if(args.length < 3) {
                    System.out.println("INVALID INPUT");
                    return;
                }
                double[] inputArray = new double[args.length - 2];
                for(int i = 2; i < args.length; i++) {
                    inputArray[i - 2] = Double.parseDouble(args[i]);
                }
                System.out.println( poly(Double.parseDouble(args[1]), inputArray ) );
                break;

            // Error
            default:
                System.out.println("INVALID INPUT");
                return;
        }
    }

    public MathMethods() {

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

    // n√x using Binary Search
    public static double root(int n, double x, double episolon) {
        if(episolon < 0) {
            System.out.println("BAD INPUT");
            return -1.0;
        }
        double upperBound = x;
        double lowerBound = 0;
        double guess = x/2;
        double rooted = power(guess,n);
        int stopper = 0;
        //while( rooted != x ) {
        while(rooted < x - episolon || rooted > x + episolon) {
            stopper++;
            if(stopper > 999999999) {
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
        }
        return guess;
    }

    // √x (Literally just the above, but with n being 2)
    public static double sqrt(double x, double episolon) {
        return MathMethods.root(2, x, episolon);
    }
}
