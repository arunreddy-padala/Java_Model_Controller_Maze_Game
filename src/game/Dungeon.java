package game;

/**
 * Dungeon interface represented in the form of a maze, player and treasure.
 */

public interface Dungeon {

  /**
   * A method to move the player within the dungeon.
   *
   * @param direction Direction that the player needs to move to.
   */

  String movePlayer(Route direction);

  /**
   * A method to return the maze.
   *
   * @return a maze.
   */

  Environment[][] getMaze();

  /**
   * A method to print the player description.
   *
   * @return a formatted string
   */

  String playerDescription();

  /**
   * A method to get the player's start location.
   *
   * @return start location of the player.
   */

  int getPlayerStartLocation();

  /**
   * A method to get the player's end location.
   *
   * @return end location of the player.
   */

  int getPlayerEndLocation();

  /**
   * A method for the player to pick up arrows.
   *
   * @return A formatted string.
   */

  String pickUpArrows();

  /**
   * A method for the player to pick up arrows.
   *
   * @param t treasure.
   * @return A formatted string.
   */

  String pickUpTreasure(Treasure t);

  /**
   * A method for the player to shoot arrows.
   *
   * @param direction Direction to shoot.
   * @param length    distance for the arrow to travel.
   * @return A formatted string
   */

  String shoot(Route direction, int length);

  int getPlayerLocationValue();

  int getPlayerLocationKey();

}
