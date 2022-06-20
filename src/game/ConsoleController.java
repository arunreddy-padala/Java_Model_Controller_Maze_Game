package game;

import java.io.IOException;
import java.util.Scanner;

/**
 * ConsoleController class represented in terms of readable and appendable.
 */

public class ConsoleController implements Controller {

  private final Appendable output;
  private final Scanner scan;

  /**
   * Constructor for the ConsoleController represented in terms of readable and appendable.
   *
   * @param in     input to be read
   * @param output output to be printed
   */
  public ConsoleController(Readable in, Appendable output) {
    if (in == null || output == null) {
      throw new IllegalArgumentException("Invalid arguments appendable "
              + "and readable cannot be null");
    }
    this.output = output;
    scan = new Scanner(in);
  }


  @Override
  public void playGame(Dungeon d) {
    if (d == null) {
      throw new IllegalArgumentException();
    }
    try {
      output.append("Move, Pickup, or Shoot (M-P-S)? \n");
      while (scan.hasNext()) {
        String input = scan.next();
        switch (input) {
          case "m":
          case "M":
          case "move":
            try {
              output.append("Which direction do you want to go ?");
              String route = scan.next();
              System.out.println(route);
              if (route.equalsIgnoreCase("S")) {
                output.append(d.movePlayer(Route.SOUTH));
              } else if (route.equalsIgnoreCase("N")) {
                output.append(d.movePlayer(Route.NORTH));
              } else if (route.equalsIgnoreCase("W")) {
                output.append(d.movePlayer(Route.WEST));
              } else if (route.equalsIgnoreCase("E")) {
                output.append(d.movePlayer(Route.EAST));
              }
              output.append("Move, Pickup, or Shoot (M-P-S)? \n");
            } catch (IllegalArgumentException e) {
              System.out.println("Invalid input");
              output.append("Move, Pickup, or Shoot (M-P-S)? \n");
            }
            break;

          case "p":
          case "P":
          case "pickup":
            output.append("pick what ?");
            String name = scan.next();
            if (name.equalsIgnoreCase("arrow")) {
              output.append(d.pickUpArrows());
              output.append("Move, Pickup, or Shoot (M-P-S)? \n");
            } else if (name.equalsIgnoreCase("treasure")) {
              output.append(d.pickUpTreasure(Treasure.SAPPHIRE));
              output.append("Move, Pickup, or Shoot (M-P-S)? \n");
            }
            output.append("Move, Pickup, or Shoot (M-P-S)? \n");
            break;

          case "s":
          case "shoot":
            output.append("No. of caves (1-5)?\n");
            int num = scan.nextInt();
            output.append("Which direction do you want to shoot ? \n");
            String direction = scan.next();
            if (direction.equalsIgnoreCase("S")) {
              output.append(d.shoot(Route.SOUTH, num));
            } else if (direction.equalsIgnoreCase("N")) {
              output.append(d.shoot(Route.NORTH, num));
            } else if (direction.equalsIgnoreCase("W")) {
              output.append(d.shoot(Route.WEST, num));
            } else if (direction.equalsIgnoreCase("E")) {
              output.append(d.shoot(Route.EAST, num));
            }
            output.append("Move, Pickup, or Shoot (M-P-S)? \n");
            break;

          case "q":
          case "Q":
          case "quit":
            output.append("Game has been quit!");
            return;

          default:
            return;
        }
      }
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    }
  }
}


