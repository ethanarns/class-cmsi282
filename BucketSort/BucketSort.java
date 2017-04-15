import java.util.ArrayList;
import java.util.Arrays;

// http://pdorin.cs.lmu.edu/282.html#homework
// FIX NEGATIVES
public class BucketSort {

  public static void main(String args[]) {
    double[] dubArray = {-0, -1};
    // Feed array into ArrayList then insert
    ArrayList<Double> dubList = new ArrayList<Double>();
    for(int i = 0; i < dubArray.length; i++) {
      dubList.add(dubArray[i]);
    }
    bucketSort(dubList);
  }

  public static ArrayList<Double> bucketSort(ArrayList<Double> inputArray) {
    System.out.println(inputArray);
    ArrayList<Double> pos = new ArrayList<Double>();
    ArrayList<Double> neg = new ArrayList<Double>();
    for(double d : inputArray) {
      if(d >= 0) {
        pos.add(d);
      }
      else {
        d *= -1;
        neg.add(d);
      }
    }
    pos = bucketSortPos(pos);
    neg = bucketSortPos(neg);
    inputArray.clear();
    for(int i = neg.size() - 1; i >= 0; i--) {
      inputArray.add(neg.get(i) * -1);
    }
    for(double x : pos) {
      inputArray.add(x);
    }
    System.out.println(inputArray);
    return inputArray;
  }

  private static ArrayList<Double> bucketSortPos(ArrayList<Double> inputArray) {
    //System.out.println(inputArray);
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
    // https://www.cs.usfca.edu/~galles/visualization/BucketSort.html
    // placement = value * NUMBER_OF_ELEMENTS/(MAX_ARRAY_VALUE + 1)
    for(double d : inputArray) {
      int placementIndex = (int)(d * inputArray.size()/(maxSize + 1));
      sorterList.get(placementIndex).add(d);
    }
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
    //System.out.println(inputArray);
    return inputArray;
  }
}
