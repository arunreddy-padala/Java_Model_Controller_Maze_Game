package game;

import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Driver class for Dungeon game.
 */

public class Driver {

  /**
   * Main class for the dungeon game.
   *
   * @param args rows, columns, interconnectivity, wrapping condition,
   *             treasure percentage and number of monsters
   */

  public static void main(String[] args) {

    Character player = new Player(1, "Test");
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    RandomGenerator random = new TrueRandom();

    int rows = Integer.parseInt(args[0]);
    int columns = Integer.parseInt(args[1]);
    int interconnectivity = Integer.parseInt(args[2]);
    WrappingCondition condition;
    if (args[3].equalsIgnoreCase("wrapped")) {
      condition = WrappingCondition.WRAPPED;
    } else {
      condition = WrappingCondition.UNWRAPPED;
    }
    double treasurePercentage = Double.parseDouble(args[4]);
    int monsters = Integer.parseInt(args[5]);


    Dungeon dungeon = new Matrix(rows, columns, interconnectivity,
            condition, treasurePercentage, monsters, random, player);


    Controller game = new ConsoleController(input, output);
    try {
      game.playGame(dungeon);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}


