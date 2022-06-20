package game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * A Random class that returns a predictable value every time it's called.
 */

public class PredefinedRandom implements RandomGenerator {
  private List<Integer> listOfValues;

  public PredefinedRandom(Integer... values) {
    this.listOfValues = new LinkedList<>(Arrays.asList(values));
  }

  @Override
  public int getNextRandom(int... values) {
    int value = listOfValues.get(0);
    listOfValues.remove(0);
    return value;
  }
}
