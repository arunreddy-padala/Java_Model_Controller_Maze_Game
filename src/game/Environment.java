package game;

import java.util.List;
import java.util.Map;

/**
 * Environment interface to represent caves, tunnel and treasure.
 */

public interface Environment {

  /**
   * A method to get the ID associated with a node.
   *
   * @return an ID.
   */

  int getNodeId();

  /**
   * A method to get the list of treasure.
   *
   * @return a list of treasure.
   */

  List<Treasure> getTreasure();

  /**
   * A method to get the current position in cave.
   *
   * @return current position.
   */

  Pair getCurrentPosition();

  /**
   * A method to add the adjacent nodes based on route and pair.
   *
   * @param d1 Direction of the adjacent nodes.
   * @param p1 Position represented as pair of the adjacent nodes.
   */

  void addAdjacentNodes(Route d1, Pair p1);

  /**
   * A method to get the adjacent nodes of a given node.
   *
   * @return adjacent nodes.
   */

  Map<Route, Pair> getAdjacentNodes();

  /**
   * A method to represent whether a cave is a tunnel.
   *
   * @return whether it's a tunnel
   */

  boolean isTunnel();

  /**
   * A method to get the total number of monsters.
   *
   * @return total count of monsters.
   */

  int getCountOfMonsters();

  /**
   * A method to get the total number of arrows.
   *
   * @return total count of arrows.
   */

  int getCountOfArrows();


}
