package gametest;

import game.Dungeon;
import game.Environment;
import game.Route;
import game.Treasure;

/**
 * MockModel class to mock the Dungeon model.
 */

public class MockModel implements Dungeon {

  @Override
  public String movePlayer(Route direction) {
    StringBuilder string = new StringBuilder();
    string.append("Inside move player method");
    return string.toString();
  }

  @Override
  public Environment[][] getMaze() {
    return new Environment[0][];
  }

  @Override
  public String playerDescription() {
    return null;
  }

  @Override
  public int getPlayerStartLocation() {
    return 0;
  }

  @Override
  public int getPlayerEndLocation() {
    return 0;
  }

  @Override
  public String pickUpArrows() {
    StringBuilder string = new StringBuilder();
    string.append("Inside pick up arrows method");
    return string.toString();
  }

  @Override
  public String pickUpTreasure(Treasure t) {
    StringBuilder string = new StringBuilder();
    string.append("Inside pick up treasure method");
    return string.toString();
  }



  @Override
  public String shoot(Route direction, int length) {
    StringBuilder string = new StringBuilder();
    string.append("Inside shoot method");
    return string.toString();
  }

  @Override
  public int getPlayerLocationValue() {
    return 0;
  }

  @Override
  public int getPlayerLocationKey() {
    return 0;
  }
}
