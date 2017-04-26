public class Pentagon {
  public static void main(String[] args) {
    int[] wat = {0,1,2};
    //int[] wat = {3, 2, 1, 0}; // broken one
    for(int x : wat) {
      System.out.print(x + " ");
    }
    System.out.println();
    for(int i = 0; i < 30; i++) {
      permute(wat);
    }
  }

  public static void swap(int[] list, int pos1, int pos2) {
    int temp = list[pos1];
    list[pos1] = list[pos2];
    list[pos2] = temp;
  }

  public static int[] permute(int[] array) {
    int pivotIndex = array.length - 1; // set initial pivot to end
    while(pivotIndex >= 0) {
      pivotIndex--;
      if(pivotIndex <= 0) {
        // Cannot be permuted further
        for(int x : array) {
          System.out.print(x + " ");
        }
        System.out.println();
        return array;
      }
      if(array[pivotIndex - 1] < array[pivotIndex]) {
        pivotIndex--; // pivot is 1 left of found spot
        break;
      }
    }
    //System.out.println("Pivot is index " + pivotIndex + ": " + array[pivotIndex]);
    int lowestIndex = -1;
    int lowestNumber = Integer.MAX_VALUE;
    for(int i = pivotIndex + 1; i < array.length; i++) {
      // Must be greater than the pivot
      if(array[i] > array[pivotIndex]) {
        if(array[i] <= lowestNumber) {
          lowestNumber = array[i];
          lowestIndex = i;
        }
      }
    }
    //System.out.println("Second pivot is index " + lowestIndex + ": " + lowestNumber);
    swap(array, lowestIndex, pivotIndex);
    // now just reverse the substring pivotIndex + 1 to end
    int swapArrayLength = (array.length - pivotIndex - 1) / 2;
    for(int i = pivotIndex + 1; i < pivotIndex + 1 + swapArrayLength; i++) {
      int realIndex = i - pivotIndex - 1;
      swap(array, i, array.length - realIndex - 1);
    }
    // Print le array
    for(int x : array) {
      System.out.print(x + " ");
    }
    System.out.println();
    return array; // temp
  }





  public static boolean nextPermutation(int[] array) {
    // Find non-increasing suffix
    int i = array.length - 1;
    while (i > 0 && array[i - 1] >= array[i])
        i--;
    if (i <= 0)
        return false;

    // Find successor to pivot
    int j = array.length - 1;
    while (array[j] <= array[i - 1])
        j--;
    int temp = array[i - 1];
    array[i - 1] = array[j];
    array[j] = temp;

    // Reverse suffix
    j = array.length - 1;
    while (i < j) {
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        i++;
        j--;
    }
    for(int x : array) {
      System.out.print(x + " ");
    }
    System.out.println();
    return true;
  }
}
