package game;

import java.io.IOException;
import java.io.InputStreamReader;

public class Driver3 {

  public static void main(String[] args) throws IOException {

    Character player = new Player(1, "Test2");
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


    Matrix dungeon = new Matrix(rows, columns, interconnectivity,
            condition, treasurePercentage, monsters, random, player);

    DungeonGraphicsView view = new DungeonGraphicsView(dungeon);
    DungeonPanel panel = new DungeonPanel(dungeon);
    panel.visualiseDungeon();

    SetupPanel setup= new SetupPanel();

  }
  }
