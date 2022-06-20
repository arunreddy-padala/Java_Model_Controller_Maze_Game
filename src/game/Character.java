package game;

import java.util.List;

/**
 * Interface class for player character represented in terms of
 * player ID, name, player location and treasure.
 */

public interface Character {

  /**
   * A method to get the ID of the player.
   *
   * @return ID of the player.
   */

  int getPlayerId();

  /**
   * A method to get the name of the player.
   *
   * @return name of the player.
   */

  String getPlayerName();

  /**
   * A method to get the location of the player.
   *
   * @return location of the player.
   */

  Pair getPlayerLocation();

  /**
   * A method to get the treasure that the player has collected.
   *
   * @return a list of treasure.
   */

  List<Treasure> getPlayerTreasure();

  /**
   * A method to return the description of the player.
   *
   * @return A formatted string.
   */

  String getPlayerDescription();

  /**
   * A method to return the total count of arrows.
   *
   * @return number of arrows
   */

  int getArrowsCount();

}
