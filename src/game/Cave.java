package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cave class to represent caves, tunnels, arrows and treasure.
 */

public class Cave implements Environment {

  private final int id;
  private final Pair currentPosition;
  private int arrowsCount;
  private List<Treasure> treasures;
  private final Map<Route, Pair> neighbours;
  private List<Monster> otyughs;

  /**
   * Constructor for cave class to represent caves, tunnel, arrows and treasure.
   *
   * @param id ID of the cave
   * @param x  X coordinate
   * @param y  Y coordinated
   * @throws IllegalArgumentException if id, x, or y is less than zero
   */

  public Cave(int id, int x, int y) throws IllegalArgumentException {
    if ((id < 0) || (x < 0) || (y < 0)) {
      throw new IllegalArgumentException("Invalid values for Cave, "
              + "please enter valid values");
    }
    this.id = id;
    treasures = new ArrayList<>();
    neighbours = new HashMap<>();
    currentPosition = new Pair(x, y);
    arrowsCount = 0;
    otyughs = new ArrayList<>();
  }

  @Override
  public int getNodeId() {
    return this.id;
  }

  @Override
  public List<Treasure> getTreasure() {
    return this.treasures;
  }

  @Override
  public Pair getCurrentPosition() {
    return this.currentPosition;
  }

  @Override
  public void addAdjacentNodes(Route d1, Pair p1) throws IllegalArgumentException {
    if ((d1 == null) || (p1 == null)) {
      throw new IllegalArgumentException("Invalid values");
    }
    neighbours.put(d1, p1);
  }

  @Override
  public Map<Route, Pair> getAdjacentNodes() {
    return this.neighbours;
  }

  @Override
  public boolean isTunnel() {
    return this.getAdjacentNodes().size() == 2;
  }

  /**
   * Protected method to add treasure.
   *
   * @param value treasure.
   */

  protected void addTreasure(Treasure value) throws IllegalArgumentException {
    if (value == null) {
      throw new IllegalArgumentException("Value cannot be null");
    }
    this.treasures.add(value);
  }

  /**
   * Protected method to add monster.
   *
   * @param value monster.
   */

  protected void addOtyugh(Monster value) throws IllegalArgumentException {
    if (value == null) {
      throw new IllegalArgumentException("Value cannot be null");
    }
    this.otyughs.add(value);
  }

  /**
   * Protected method to add arrows.
   *
   * @param val arrows.
   */

  protected void addArrows(int val) throws IllegalArgumentException {
    if (val < 0) {
      throw new IllegalArgumentException("Value cannot be less than zero");
    }
    this.arrowsCount = arrowsCount + val;
  }


  /**
   * A protected method to get the list of monsters.
   *
   * @return the list of monsters.
   */
  protected List<Monster> getOtyughs() {
    return this.otyughs;
  }

  /**
   * A protected method to get a monster.
   *
   * @return monster.
   */

  protected Monster getOtyugh() {
    return this.otyughs.get(0);
  }

  @Override
  public int getCountOfMonsters() {
    return this.otyughs.size();
  }

  @Override
  public int getCountOfArrows() {
    return this.arrowsCount;
  }

  /**
   * ToString method to print the variables in location class.
   *
   * @return A formatted string.
   */
  @Override
  public String toString() {

    return "Cave id=" + id
            + " currentPosition=" + currentPosition
            + " treasure=" + getTreasure()
            + " neighbours=" + neighbours
            + " isTunnel=" + isTunnel()
            + " otyugh=" + getOtyughs()
            + " arrows=" + getCountOfArrows()
            + " \n"
            + " \n";
  }



}
