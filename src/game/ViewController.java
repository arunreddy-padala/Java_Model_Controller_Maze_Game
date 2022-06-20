package game;

import java.io.IOException;
import java.util.ArrayList;

public class ViewController {

  private Dungeon Matrix;
  private DungeonGraphicsView view;
  private SetupPanel setup;

  private int rows;
  private int cols;
  private int interconnectivity;
  private WrappingCondition condition;
  private int percentage;
  private double percentage2;
  private int monsterCount;

  private RandomGenerator random;
  private Character player;


  public ViewController(Dungeon Matrix, DungeonGraphicsView view) {
    this.Matrix = Matrix;
    this.view = view;
    view.addClickListener(this);
  }


  public void createModel(ArrayList arrayList) {

    rows = setup.getSetupValues().get(0);
    cols = setup.getSetupValues().get(1);
    interconnectivity = setup.getSetupValues().get(2);
    if (setup.getSetupValues().get(3) == 0) {

      condition = WrappingCondition.UNWRAPPED;

    }
    percentage = setup.getSetupValues().get(4);
    percentage2 = percentage;
    monsterCount = setup.getSetupValues().get(5);

    System.out.println("Rows:" + rows);

    random = new TrueRandom();
    player = new Player(1, "Test");

    Matrix = new Matrix(rows, cols, interconnectivity, condition, percentage2, monsterCount, random, player);

  }

  public void playGame() throws IOException {
    if (Matrix == null) {
      throw new IllegalArgumentException("Invalid! Model cannot be null");
    }

    rows = setup.getSetupValues().get(0);
    cols = setup.getSetupValues().get(1);
    interconnectivity = setup.getSetupValues().get(2);
    if (setup.getSetupValues().get(3) == 0) {

      condition = WrappingCondition.UNWRAPPED;

    }
    percentage = setup.getSetupValues().get(4);
    percentage2 = percentage;
    monsterCount = setup.getSetupValues().get(5);

    System.out.println("Rows:" + rows);

    random = new TrueRandom();
    player = new Player(1, "Test");

    Matrix = new Matrix(rows, cols, interconnectivity, condition, percentage2, monsterCount, random, player);

    DungeonGraphicsView view = new DungeonGraphicsView(Matrix);
  }


  public void handleCellPress(int x, int y) throws IllegalArgumentException {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Invalid values for x and y");
    }

    try {
      this.Matrix.movePlayer(Route.SOUTH);
      view.refresh();
    } catch (Exception e) {
      view.showErrorMessage(e.getMessage());

      //TODO: To implement the move function based on X & Y values.

    }


  }
}
