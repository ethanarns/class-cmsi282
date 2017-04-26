public class Pentagon {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    int[] wat = {0,1,2,5,3,3,0};
  }

  public static void swap(int[] list, int pos1, int pos2) {
    int temp = list[pos1];
    list[pos1] = list[pos2];
    list[pos2] = temp;
  }
}
