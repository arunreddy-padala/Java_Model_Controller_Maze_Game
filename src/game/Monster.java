package game;

/**
 * Monster class represented in terms of name, id and health.
 */

public class Monster {

  private final String name;
  private final int id;
  private int health;


  /**
   * Constructor for Monster class.
   *
   * @param id   ID for the monster.
   * @param name name of the monster.
   * @throws IllegalArgumentException if id is less than zero and name is null.
   */

  public Monster(int id, String name) throws IllegalArgumentException {
    if (id < 0 || name == null) {
      throw new IllegalArgumentException("ID cannot be less than zero and name cannot be null");
    }
    this.id = id;
    this.name = name;
    this.health = 100;
  }

  /**
   * Method to get the id of the monster.
   *
   * @return ID of the monster.
   */

  public int getId() {
    return this.id;
  }

  /**
   * Method to get the name of the monster.
   *
   * @return name of the monster.
   */

  public String getName() {
    return this.name;
  }

  /**
   * Method to get the health of the monster.
   *
   * @return health of the monster.
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Method to update the health of monster.
   *
   * @return updated health of the monster.
   */

  public int monsterHealthUpdate(int val) {
    return this.health = health - val;
  }

  /**
   * A to String method for the monster class.
   *
   * @return A formatted string
   */

  @Override
  public String toString() {
    return "Monster{"
            + "id="
            + id
            + ", name='"
            + name
            + '\''
            + ", health="
            + health
            + '}';
  }
}
