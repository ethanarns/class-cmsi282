import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Select {
    public static void main (String[] args) {
        if (args.length != 1) {
            System.out.println("BAD DATA");
            System.exit(0);
        }
        
        int k = -1;
        try {
            k = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("BAD DATA");
            System.exit(0);
        }
        
        if(k <= 0) {
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

        System.out.println(getKthSmallest(list, k));
    	
//    	ArrayList<Integer> a = new ArrayList<Integer>();
//    	a.add(1);
//    	a.add(5);
//    	a.add(6);
//    	a.add(3);
//    	a.add(4);
//    	a.add(8);
//    	a.add(7);
//    	a.add(2);
//    	a.add(9);
//    	
//    	for(int i = 0; i < 9; i++) {
//    		partition((ArrayList<Integer>)a.clone(), i);
//    	} 	
//    	partition(a, 3);
    }
    
    /**
     * Returns the kth smallest integer in the dataset
     * @param list   array to search
     * @param k      index to find
     * @return       the kth smallest number in the array
     */
    public static int getKthSmallest(ArrayList<Integer> list, int k) {
    	
		return k;
    }
    
    /**
     * Swaps i and j in the array a
     * @param a   array to swap i and j in
     * @param i   first index to swap
     * @param j   second index to swap
     */
    public static void swap(ArrayList<Integer> a, int i, int j) {
    	int	temp = a.get(i);
    	a.set(i, a.get(j));
    	a.set(j, temp);
    }
    
    /**
     * Performs one partition of an array
     * @param a       array to change
     * @param pivot   a random Integer taken from function called
     */
    public static void partition(ArrayList<Integer> a, int pivot) {
    	int i = 0;
    	int j = 0;
    	
    	swap(a, a.size() - 1, pivot);
    	
    	pivot = a.size() - 1;
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
    	System.out.println(a);
    }
}