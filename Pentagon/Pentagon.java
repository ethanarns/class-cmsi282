public class Pentagon {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    int[] wat = {0,1,2,5,3,3,0};
    //int[] wat = {3, 2, 1, 0}; // broken one
    permute(wat);
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
        System.out.println("Already complete?");
        return array;
      }
      if(array[pivotIndex - 1] < array[pivotIndex]) {
        pivotIndex--; // pivot is left of found spot
        break;
      }
    }
    System.out.println("Pivot is index " + pivotIndex + ": " + array[pivotIndex]);
    int lowestIndex = -1;
    int lowestNumber = Integer.MAX_VALUE;
    for(int i = pivotIndex + 1; i < array.length; i++) {
      // Must be greater than the pivot
      if(array[i] > array[pivotIndex]) {
        if(array[i] < lowestNumber) {
          lowestNumber = array[i];
          lowestIndex = i;
        }
        // What if its the same number, but further right?
        else if(array[i] == lowestNumber) {
          lowestIndex = i;
        }
      }
    }
    System.out.println("Second pivot is index " + lowestIndex + ": " + lowestNumber);
    return array; // temp
  }
}
