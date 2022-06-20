package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Player class represented in terms of ID, name,
 * health, arrows, and player location.
 */

public class Player implements Character {

  private final String name;
  private final int id;
  private Pair playerLocation;
  private List<Treasure> treasure;
  private int arrowsCount;
  private int health;

  /**
   * Player class constructor represented in terms of name, ID, health and arrows.
   *
   * @param id   player ID
   * @param name player name
   * @throws IllegalArgumentException if player name is null or if ID is less
   *                                  than zero.
   */

  public Player(int id, String name) throws IllegalArgumentException {
    if ((id < 0) || (name == null)) {
      throw new IllegalArgumentException("Invalid values, please enter again");
    }
    this.id = id;
    this.name = name;
    this.playerLocation = null;
    treasure = new ArrayList<>();
    this.arrowsCount = 3;
    this.health = 100;
  }

  @Override
  public int getPlayerId() {
    return this.id;
  }

  @Override
  public String getPlayerName() {
    return this.name;
  }

  /**
   * Protected method to update the players location.
   *
   * @param p Pair object.
   */

  protected void updatePlayerLocation(Pair p) throws IllegalArgumentException {
    if (p == null) {
      throw new IllegalArgumentException("pair cannot be null");
    }
    this.playerLocation = p;
  }

  /**
   * Protected method to add the treasure value.
   *
   * @param value of type treasure.
   */

  protected void addTreasure(Treasure value) throws IllegalArgumentException {
    if (value == null) {
      throw new IllegalArgumentException("value cannot be null");
    }
    treasure.add(value);
  }

  @Override
  public Pair getPlayerLocation() {
    Pair pair = new Pair(playerLocation.getX(), playerLocation.getY());
    return pair;
  }

  @Override
  public List<Treasure> getPlayerTreasure() {
    return treasure;
  }

  @Override
  public String getPlayerDescription() {
    StringBuilder string = new StringBuilder();
    string.append("Player information:\n");
    string.append(this.getPlayerName());
    string.append("\n");
    string.append("Below are the treasure contents that the player holds currently.\n");
    string.append(this.getPlayerTreasure());
    string.append("\n");
    string.append("Below are the arrows contents that the player holds currently.\n");
    string.append(this.getArrowsCount());
    return string.toString();
  }

  /**
   * Protected method to update the arrows that player hold.
   *
   * @param val      number of arrows.
   * @param operator operation to be performed (add/subtract arrows).
   */

  protected void updatePlayerArrowsCount(int val, String operator) throws IllegalArgumentException {
    if (val < 0 || operator == null) {
      throw new IllegalArgumentException("Invalid values for value and operator");
    }
    if (operator.equals("add")) {
      this.arrowsCount = arrowsCount + val;
    } else if (operator.equals("subtract")) {
      this.arrowsCount = arrowsCount - val;
    }
  }

  /**
   * Protected method to update the player's health.
   *
   * @param val value to be updated.
   */
  protected void updatePlayerHealthStatus(int val) {
    if (val < 0) {
      throw new IllegalArgumentException("Player health cannot be less than zero");
    }
    this.health = health - val;
  }

  @Override
  public int getArrowsCount() {
    return this.arrowsCount;
  }

  /**
   * ToString method to print the variables in player class.
   *
   * @return A formatted string.
   */

  @Override
  public String toString() {
    return "Player{"
            + "name='" + name
            + ", id=" + id
            + ", playerLocation=" + playerLocation
            + ", treasure=" + getPlayerTreasure()
            + ", arrow=" + getArrowsCount()
            + '}';
  }
}
