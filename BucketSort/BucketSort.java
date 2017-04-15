import java.util.ArrayList;
import java.util.Arrays;

public class BucketSort {
  public static void main(String args[]) {
    double[] dubArray = {2.0, 4.5, 1.9, 1.2, 3.3};
    bucketSort(dubArray);
  }

  public static double[] bucketSort(double[] inputArray) {
    ArrayList<Double> toList = new ArrayList<Double>();
    for(double x : inputArray) {
      toList.add(x);
    }
    ArrayList<Double> resultList = bucketSort(toList);
    double[] result = new double[toList.size()];
    for(int i = 0; i < result.length; i++) {
      result[i] = resultList.get(i);
    }
    return result;
  }

  private static ArrayList<Double> bucketSort(ArrayList<Double> inputArray) {
    System.out.println(inputArray);
    double maxSize = 0;
    for(double x : inputArray) {
      if(x > maxSize) {
        maxSize = x;
      }
    }
    ArrayList<ArrayList<Double>> sorterList = new ArrayList<ArrayList<Double>>();
    for(int i = 0; i <inputArray.size(); i++) {
      sorterList.add(new ArrayList<Double>());
    }
    // We now have a working maxSize and inputArray to work with
    // http://pdorin.cs.lmu.edu/282.html#homework
    // https://www.cs.usfca.edu/~galles/visualization/BucketSort.html
    // placement = value * NUMBER_OF_ELEMENTS/(MAX_ARRAY_VALUE + 1)
    for(double d : inputArray) {
      int placementIndex = (int)(d * inputArray.size()/(maxSize + 1));
      System.out.print(placementIndex + "   ");
      sorterList.get(placementIndex).add(d);
    }
    System.out.println("\n" + sorterList);
    int iSort = 0;
    int i = 0;
    int inputSize = inputArray.size();
    inputArray.clear();
    while(i < inputSize) {
      if(sorterList.get(iSort).size() == 0) {
        iSort++; // Place is empty, move up
        // Don't change iReturn
        continue;
      }
      else if(sorterList.get(iSort).size() == 1) {
        inputArray.add(sorterList.get(iSort).get(0));
        i++;
        iSort++; // Finished that place, move up
      }
      else { // the current spot in the sorterList must have multiple values!
        // Okay that works, so lets actually sort it again
        int minPos = -1;
        double di = -1;
        while(sorterList.get(iSort).size() > 0) {
          di = Double.MAX_VALUE;
          for(int j = 0; j < sorterList.get(iSort).size(); j++) {
            if(sorterList.get(iSort).get(j) < di) {
              di = sorterList.get(iSort).get(j);
              minPos = j;
            }
          }
          //minPos found. Add and remove.
          inputArray.add(sorterList.get(iSort).get(minPos));
          i++;
          sorterList.get(iSort).remove(minPos);
        }
        iSort++;
      }
    }
    System.out.println(inputArray);
    // We got the list!
    return inputArray;
  }
}
