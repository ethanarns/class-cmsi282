public class RamseyFind {
  public static void main(String[] args) {
    if(args.length < 5){
      System.out.println("ERROR: Input too short.");
    }
    int[] arguments = new int[3];
    int[] inputArray = new int[args.length - 3];
    try {
      for(int i = 0; i < arguments.length; i++) {
        arguments[i] = Integer.parseInt(args[i]);
      }
      for(int i = 3; i < args.length; i++) {
        inputArray[i-3] = Integer.parseInt(args[i]);
      }
    }
    catch(Exception e) {
      System.out.println("ERROR: Bad input.");
    }
    //example: int[] inputArray = {5, 3, 2, 1, 1, 1};

  }
}
