package game;

import java.util.Random;

/**
 * A Random class that returns a random value within a range every time it's called.
 */

public class TrueRandom implements RandomGenerator {

  private Random randomValue;

  /**
   * Constructor for random value class.
   */

  public TrueRandom() {
    randomValue = new Random();
  }

  @Override
  public int getNextRandom(int... values) {
    int lowerBound = values[0];
    int upperBound = values[1];
    return randomValue.nextInt(upperBound + 1 - lowerBound) + lowerBound;
  }

}