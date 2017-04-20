public class CrownAndAnchor {

  public static final int GAMES = 10000000;

  public enum Die {
    CROWN,
    ANCHOR,
    HEART,
    SPADE,
    CLUB,
    DIAMOND
  }

  public static void main(String args[]) {
    int total = 0;
    for(int i = 0; i < GAMES; i++) {
      total += simulate(Die.HEART, 1);
    }
    double result = (((double)(total))/GAMES)*100;
    System.out.println(result);
  }

  public static int simulate(Die bet, int amount) {
    int matches = 0;
    for(int i = 0; i < 3; i++) {
      if(bet == getRoll()) {
        matches++;
      }
    }
    if(matches == 0) {
      // Lose everything, get nothing back
      return 0;
    }
    return amount + (matches * amount);
  }

  public static Die getRoll() {
    Die roll = Die.HEART;
    switch((int) (Math.random() * 6 + 1)) {
      case 1:
        roll = Die.CROWN;
        break;
      case 2:
        roll = Die.ANCHOR;
        break;
      case 3:
        roll = Die.HEART;
        break;
      case 4:
        roll = Die.SPADE;
        break;
      case 5:
        roll = Die.CLUB;
        break;
      case 6:
        roll = Die.DIAMOND;
        break;
      default:
        break;
    }
    return roll;
  }
}
