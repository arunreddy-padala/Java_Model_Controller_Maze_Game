package game;

/**
 * A Random interface that returns a random value every time it's called.
 */

public interface RandomGenerator {

  /**
   * A method which returns a random value.
   *
   * @param values variable argument values.
   * @return A random value.
   */

  int getNextRandom(int... values);

}
