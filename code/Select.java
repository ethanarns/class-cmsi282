import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Select {
    public static void main (String[] args) {
        if (args.length != 1 || args[0] <= 0) {
            System.out.println("BAD DATA");
            System.exit(0);
        }

        try {
            int k = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("BAD DATA");
            System.exit(0);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (String s = stdIn.readLine(); s != null; s = stdIn.readLine()) {
            try {
                list.add(Integer.parseInt(s));
            } catch (Exception e) {
                System.out.println("BAD DATA");
                System.exit(0);
            }
        }

        System.out.println(getKthSmallest(list, k));
    }

    public static int getKthSmallest(ArrayList<Integer> list, int k) {
        
    }
}