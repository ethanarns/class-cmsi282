import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Select {
    public static void main (String[] args) {
        if (args.length != 1) {
            System.out.println("BAD DATA");
            System.exit(0);
        }

        int k = 3;
        try {
            k = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("BAD DATA");
            System.exit(0);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
	        for (String s = stdIn.readLine(); s != null; s = stdIn.readLine()) {
	            try {
	                list.add(Integer.parseInt(s));
	            } catch (Exception e) {
	                System.out.println("BAD DATA");
	                System.exit(0);
	            }
	        }
        } catch (Exception e) {
        	System.out.println("BAD DATA");
        	System.exit(0);
        }

        if(k <= 0 || k > list.size()) {
            System.out.println("BAD DATA");
            System.exit(0);
        }
        long currentTime;
        for (int i = 0; i < 100; i++) {
            currentTime = System.currentTimeMillis();
            getKthSmallest(list, 0, list.size() - 1, k - 1);
            System.out.println((System.currentTimeMillis() - currentTime) + " ms.");
        }
    }

    public static int getKthSmallest(ArrayList<Integer> list, int start, int end, int k) {
        // System.out.println(list);
        // System.out.println("start: " + start + ", end: " + end);
        int rand = (int) (Math.random() * ((end - start) + 1) + start);
        // System.out.println("RAND: " + rand);
        int[] pivotRange = partition(list, start, end, rand);
        // System.out.println(list + "\n");
        if (k < pivotRange[0]) {
            return getKthSmallest(list, start, pivotRange[0] - 1, k);
        } else if (k > pivotRange[1]) {
            return getKthSmallest(list, pivotRange[1] + 1, end, k);
        }
		return list.get(pivotRange[0]);
    }

    public static void swap(ArrayList<Integer> a, int i, int j) {
    	int	temp = a.get(i);
    	a.set(i, a.get(j));
    	a.set(j, temp);
    }

    public static int[] partition(ArrayList<Integer> a, int start, int end, int pivot) {
    	int i = start;
    	int j = start;
        int[] res = new int[]{start, end};

    	swap(a, end, pivot);

    	pivot = end;
    	while(j < pivot) {
    		if(a.get(j) < a.get(pivot)) {
    			swap(a, i, j);
    			i++;
    		} else if (a.get(j) == a.get(pivot)) {
                swap(a, j--, --pivot);
            }
    		j++;
    		//System.out.println("i: " + i + ", j: " + j + ", pivot value: " + a.get(pivot));
    	}
        res[0] = i;
        res[1] = i + end - pivot;
        while (pivot <= end) {
            swap(a, i++, pivot++);
        }
        // returns the location of the pivot element
        return res;
    }
}
