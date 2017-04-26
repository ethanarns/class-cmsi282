public class Pentagon {
  public static void main(String[] args) {
    int[] wat = {0,1,2,5,3,3,0};
    //int[] wat = {3, 2, 1, 0}; // broken one
    for(int x : wat) {
      System.out.print(x + " ");
    }
    System.out.println();
    for(int i = 0; i < 1; i++) {
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
    // Keep subtracting from end until the next is less than the current pivot
    while(pivotIndex > 0 && array[pivotIndex - 1] >= array[pivotIndex]) {
      pivotIndex--;
    }
    if(pivotIndex < 1) {
      // We hit the end; return the array unchanged
      return array;
    }
    // System.out.println("Pivot is index " + pivotIndex + ": " + array[pivotIndex]);

    int right = array.length - 1;
    while (array[right] <= array[pivotIndex - 1]) {
        right--;
    }

    // System.out.println("Second pivot is index " + right + ": " + array[right]);

    // for(int x : array) {
    //   System.out.print(x + " ");
    // }
    // System.out.println();

    // System.out.println("Swap:");
    swap(array, right, pivotIndex - 1);

    // for(int x : array) {
    //   System.out.print(x + " ");
    // }
    // System.out.println();

    // now just reverse the substring pivotIndex + 1 to end
    int swapArrayLength = (array.length - pivotIndex) / 2;
    // System.out.println("Swap length: " + swapArrayLength);
    for(int i = pivotIndex; i < array.length - swapArrayLength; i++) {
      int realRightIndex = array.length - i + pivotIndex - 1;
      swap(array, i, realRightIndex);
    }

    // Print le array
    for(int x : array) {
      System.out.print(x + " ");
    }
    System.out.println();
    return array; // temp
  }
}
