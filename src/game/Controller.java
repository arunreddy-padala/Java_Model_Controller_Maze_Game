package game;

import java.io.IOException;


/**
 * Represents a Controller for Dungeon model to handle user moves by interacting with the model
 * and outputs the information to the user.
 */

public interface Controller {

  /**
   * Method to play a single game.
   *
   * @param d a dungeon model.
   * @throws IOException for invalid user inputs.
   */

  void playGame(Dungeon d) throws IOException;

}
