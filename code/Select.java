import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Select {
    public static void main (String[] args) {
        // if (args.length != 1) {
        //     System.out.println("BAD DATA");
        //     System.exit(0);
        // }
        //
        int k = 1;
        // try {
        //     k = Integer.parseInt(args[0]);
        // } catch (Exception e) {
        //     System.out.println("BAD DATA");
        //     System.exit(0);
        // }
        //
        // if(k <= 0) {
        // 	System.out.println("BAD DATA");
        // 	System.exit(0);
        // }
        //
        // BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        // ArrayList<Integer> list = new ArrayList<Integer>();
        // try {
	    //     for (String s = stdIn.readLine(); s != null; s = stdIn.readLine()) {
	    //         try {
	    //             list.add(Integer.parseInt(s));
	    //         } catch (Exception e) {
	    //             System.out.println("BAD DATA");
	    //             System.exit(0);
	    //         }
	    //     }
        // } catch (Exception e) {
        // 	System.out.println("BAD DATA");
        // 	System.exit(0);
        // }
        //
        // System.out.println(getKthSmallest(list, k));

       	ArrayList<Integer> a = new ArrayList<Integer>();
       	a.add(10);
        a.add(50);
       	a.add(60);
       	a.add(30);
       	a.add(40);
       	a.add(80);
       	a.add(70);
       	a.add(20);
       	a.add(90);

        System.out.println(getKthSmallest(a, 0, a.size() - 1, k - 1));
    }

    public static int getKthSmallest(ArrayList<Integer> list, int start, int end, int k) {
        // System.out.println(list);
        // System.out.println("start: " + start + ", end: " + end);
        int rand = (int) (Math.random() * ((end - start) + 1) + start);
        // System.out.println("RAND: " + rand);
        int pivotIndex = partition(list, start, end, rand);
        // System.out.println("PivotIndex: " + pivotIndex + ", k: " + k);
        // System.out.println(list + "\n");
        if(start > end) {
            // System.out.println("Start is greater than end");
            return -1;
        }
        if (k < pivotIndex) {
            return getKthSmallest(list, start, pivotIndex - 1, k);
        } else if (k > pivotIndex) {
            return getKthSmallest(list, pivotIndex + 1, end, k);
        }
		return list.get(pivotIndex);
    }

    public static void swap(ArrayList<Integer> a, int i, int j) {
    	int	temp = a.get(i);
    	a.set(i, a.get(j));
    	a.set(j, temp);
    }

    public static int partition(ArrayList<Integer> a, int start, int end, int pivot) {
    	int i = start;
    	int j = start;

    	swap(a, end, pivot);

    	pivot = end;
    	while(j < pivot) {
    		if(a.get(j) < a.get(pivot)) {
    			swap(a, i, j);
    			i++;
    		}
    		j++;
    		//System.out.println("i: " + i + ", j: " + j + ", pivot value: " + a.get(pivot));
    	}
    	//swap i and pivot
    	swap(a, i, pivot);

        // returns the location of the pivot element
        return i;
    }
}
