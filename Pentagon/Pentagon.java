public class Pentagon {
  public static void main(String[] args) {
    int[] nums = {0,1,2,3,4,5,6,7,8,9};

    /*
    s1 = 0,1,2
    s2 = 2,3,4
    s3 = 4,5,6
    s4 = 6,7,8
    s5 = 8,9,0
    */
    int numSolutions = 0;
    System.out.println("Possible solutions to Pentagon Problem:");
    while(canPermute(nums)) {
      permute(nums);
      if((nums[0] + nums[1] + nums[2] == nums[2] + nums[3] + nums[4]) &&
         (nums[2] + nums[3] + nums[4] == nums[4] + nums[5] + nums[6]) &&
         (nums[4] + nums[5] + nums[6] == nums[6] + nums[7] + nums[8]) &&
         (nums[6] + nums[7] + nums[8] == nums[8] + nums[9] + nums[0]) &&
         (nums[8] + nums[9] + nums[0] == nums[0] + nums[1] + nums[2]))
      {
        numSolutions++;
        for(int x : nums) {
          System.out.print(x + " ");
        }
        System.out.print("(each side is worth " + (nums[0] + nums[1] + nums[2]) + ")");
        System.out.println();
      }

    }
    System.out.println("Total number of solutions: " + numSolutions);
  }

  public static void swap(int[] list, int pos1, int pos2) {
    int temp = list[pos1];
    list[pos1] = list[pos2];
    list[pos2] = temp;
  }

  public static boolean canPermute(int[] array) {
    int pivotIndex = array.length - 1; // set initial pivot to end
    // Keep subtracting from end until the next is less than the current pivot
    while(pivotIndex > 0 && array[pivotIndex - 1] >= array[pivotIndex]) {
      pivotIndex--;
    }
    if(pivotIndex < 1) {
      // We hit the end; return the array unchanged
      return false;
    }
    return true;
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

    int right = array.length - 1;
    while (array[right] <= array[pivotIndex - 1]) {
        right--;
    }

    swap(array, right, pivotIndex - 1);

    // now just reverse the substring pivotIndex to end
    for(int i = pivotIndex; i < array.length - ((array.length - pivotIndex) / 2); i++) {
      swap(array, i, array.length - i + pivotIndex - 1);
    }

    return array; // temp
  }
}
