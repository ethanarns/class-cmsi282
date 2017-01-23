import java.util.Random;

public class MakeOneMillionIntegers {
    public static void main (String[] args) {
        Random random = new Random();
        for (long i = 0; i < 100000; i++) {
            System.out.println(random.nextInt(1000000) * 2 - 1000000);
        }
    }
}