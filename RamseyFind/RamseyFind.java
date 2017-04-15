import java.util.ArrayList;

public class RamseyFind {
  public static void main(String[] args) {
    if(args.length < 5){
      System.out.println("ERROR: Input too short.");
    }
    int[] inputArray = new int[args.length - 3];
    try {
      for(int i = 3; i < args.length; i++) {
        inputArray[i-3] = Integer.parseInt(args[i]);
      }
      System.out.println(isRamsey(Integer.parseInt(args[0]),
        Integer.parseInt(args[1]), Integer.parseInt(args[2]), inputArray));
    }
    catch(Exception e) {
      System.out.println("ERROR: Bad input.");
    }
  }

  public static boolean isRamsey(int total, int p1, int p2, int[] inputArray) {
    if(p1 + p2 != total) {
      System.out.println("First arguments do not match.");
      return false;
    }
    int countTest = 0;
    for(int x : inputArray) {
      if(x >= total) {
        System.out.println("Partition element too big");
        return false;
      }
      countTest += x;
    }
    if(countTest != total) {
      System.out.println("Invalid sum in partition.");
      return false;
    }

    ArrayList<ArrayList<Integer>> allSets = getAllSetsIn(inputArray);
    // Now we have all the subsets we need for the top level
    for(int i = 0; i < allSets.size(); i++){
      ArrayList<Integer> totalSet = new ArrayList<Integer>(allSets.get(allSets.size() - 1));
      ArrayList<Integer> subSet = new ArrayList<Integer>(allSets.get(i));
      boolean p1satisfied = false;
      // First rule: is there a sublist of subSet that sums up to p1?
      ArrayList<ArrayList<Integer>> subSet_1 = getAllSetsIn(subSet);
      for(ArrayList<Integer> a : subSet_1) {
        if(total(a) == p1) {
          p1satisfied = true;
          break;
        }
      }
      if(p1satisfied)
        return true;
      // Second rule: is there a sublist of the compliment of subset that sums up to p2?
      ArrayList<ArrayList<Integer>> subSet_2 = getAllSetsIn(getCompliment(totalSet, subSet));
      for(ArrayList<Integer> a : subSet_2) {
        if(total(a) == p2)
          return true;
      }
    }

    return false;
  }

  public static ArrayList<Integer> getCompliment(ArrayList<Integer> bigList, ArrayList<Integer> subList) {
    for(int x : subList) {
      // Check to see if any int in the bigList matches the subList
      for(int i = 0; i < bigList.size(); i++) {
        if(bigList.get(i) == x) {
          bigList.remove(i);
          break;
        }
      }
    }
    return bigList;
  }

  public static int total(ArrayList<Integer> list) {
    int result = 0;
    for(int x : list) {
      result += x;
    }
    return result;
  }

  // Transfer to function due to sublevel searching
  public static ArrayList<ArrayList<Integer>> getAllSetsIn(int[] inputArray) {
    // Get all possible sets
    ArrayList<ArrayList<Integer>> allSets = new ArrayList<ArrayList<Integer>>();
    allSets.add(new ArrayList<Integer>());
    // For each item in the origin list
    for(int x : inputArray) {
      ArrayList<ArrayList<Integer>> newAllSets = new ArrayList<ArrayList<Integer>>();
      // Pull out subsets from the current master list
      for(ArrayList<Integer> subset : allSets) {
        // Start rebuilding the replacer
        newAllSets.add(subset);
        ArrayList<Integer> newSubset = new ArrayList<Integer>(subset);
        newSubset.add(x);
        newAllSets.add(newSubset);
      }
      // Replace the master list with the filled one
      allSets = newAllSets;
    }
    return allSets;
  }

  public static ArrayList<ArrayList<Integer>> getAllSetsIn(ArrayList<Integer> inputArray) {
    int[] result = new int[inputArray.size()];
    for(int i = 0; i < result.length; i++) {
      result[i] = inputArray.get(i);
    }
    return getAllSetsIn(result);
  }
}
