package game;

/**
 * Pair class represented in form of X and Y coordinates.
 */

public class Pair {

  private final int x;
  private final int y;

  /**
   * Constructor for Pair class represented in form of X and Y coordinates.
   *
   * @param x X coordinate
   * @param y Y coordinate
   * @throws IllegalArgumentException if X or Y is less than zero.
   */

  public Pair(int x, int y) throws IllegalArgumentException {
    if ((x < 0) || (y < 0)) {
      throw new
              IllegalArgumentException("Values of X and Y cannot be less than zero");
    }
    this.x = x;
    this.y = y;
  }


  /**
   * A method to get the value of X coordinated.
   *
   * @return the value of x coordinate
   */
  public int getX() {
    return this.x;
  }

  /**
   * A method to get the value of Y coordinated.
   *
   * @return the value of y coordinate
   */

  public int getY() {
    return this.y;
  }

  /**
   * ToString method to print the variables in player class.
   *
   * @return A formatted string.
   */
  @Override
  public String toString() {
    return String.format(" Position in the maze is %d, %d", this.x, this.y);
  }
}

