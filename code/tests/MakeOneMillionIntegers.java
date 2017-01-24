import java.util.Random;

public class MakeOneMillionIntegers {
    public static void main (String[] args) {
        Random random = new Random();
        long factor = 10;
        for (long i = 0; i < 1000000 * factor; i++) {
            System.out.println(random.nextInt(1000000) * 2 - 1000000);
        }
    }
}