package gametest;

import org.junit.Test;

import game.Character;
import game.Dungeon;
import game.Environment;
import game.Matrix;
import game.Player;
import game.PredefinedRandom;
import game.RandomGenerator;
import game.Route;
import game.TrueRandom;
import game.WrappingCondition;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Junit testing for Dungeon.
 */

public class DungeonTest {


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMatrix() {
    Character player = new Player(1, "ABC");
    RandomGenerator random = new TrueRandom();
    Dungeon dungeon = new Matrix(-1, -3, -4,
            WrappingCondition.UNWRAPPED, -10.00, 1,
            random, null);
  }

  @Test
  public void WrappedEdges() {
    Character player = new Player(1, "ABC");
    RandomGenerator random = new PredefinedRandom(15, 1, 35, 27, 8, 24, 12,
            9, 11, 27, 0, 20, 20, 23, 3, 7, 10,
            8, 16, 14, 15, 15, 9, 13, 15, 0, 7,
            0, 2, 8, 7, 6, 2, 2, 4, 0, 2, 0,
            0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 1,
            1, 2, 0, 1, 1, 2, 2, 2, 1, 1, 2,
            2, 3, 3, 13, 15, 8, 0, 9, 11, 14,
            11, 5, 8, 9, 8, 6, 18, 21);
    Dungeon dungeon = new Matrix(5, 5, 0,
            WrappingCondition.UNWRAPPED, 50.00, 5,
            random, player);
    Environment[][] maze = dungeon.getMaze();
    assertEquals(maze[0][0].getAdjacentNodes().get(Route.NORTH).getX(),
            maze[3][0].getCurrentPosition().getX());
  }

  @Test
  public void EdgesNotWrapped() {
    Character player = new Player(1, "ABC");
    RandomGenerator random = new TrueRandom();
    Dungeon dungeon = new Matrix(5, 5, 0,
            WrappingCondition.UNWRAPPED, 20.00, 1,
            random, player);
    Environment[][] maze = dungeon.getMaze();
    for (int i = 0; i < 4; i++) {
      assertNull(maze[i][0].getAdjacentNodes().get(Route.WEST));
    }
  }

  @Test
  public void tunnelFormation() {
    Character player = new Player(1, "ABC");
    RandomGenerator random = new TrueRandom();
    Dungeon dungeon = new Matrix(5, 5, 0,
            WrappingCondition.UNWRAPPED, 20.00, 1, random, player);
    Environment[][] maze = dungeon.getMaze();
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (maze[i][j].getAdjacentNodes().size() == 2) {
          assertTrue(maze[i][j].isTunnel());
        }
      }
    }
  }

  @Test
  public void testPlayerStart() {
    Character player = new Player(1, "ABC");
    RandomGenerator random = new TrueRandom();
    Dungeon dungeon = new Matrix(5, 5, 0,
            WrappingCondition.UNWRAPPED, 20.00, 1,
            random, player);
    Environment[][] maze = dungeon.getMaze();
    int x = player.getPlayerLocation().getX();
    int y = player.getPlayerLocation().getY();
    assertEquals(maze[x][y].getNodeId(), dungeon.getPlayerStartLocation());
  }

  @Test
  public void testPlayerEnd() {
    Character player = new Player(1, "ABC");
    RandomGenerator random = new PredefinedRandom(15, 1, 35, 27, 8, 24, 12,
            9, 11, 27, 0, 20, 20, 23, 3, 7, 10,
            8, 16, 14, 15, 15, 9, 13, 15, 0, 7,
            0, 2, 8, 7, 6, 2, 2, 4, 0, 2, 0,
            0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 1,
            1, 2, 0, 1, 1, 2, 2, 2, 1, 1, 2,
            2, 3, 3, 13, 15, 8, 0, 9, 11, 14,
            11, 5, 8, 9, 8, 6, 18, 21);
    Dungeon dungeon = new Matrix(5, 5, 0,
            WrappingCondition.UNWRAPPED, 50.00, 5,
            random, player);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    Environment[][] maze = dungeon.getMaze();
    int x = player.getPlayerLocation().getX();
    int y = player.getPlayerLocation().getY();
    assertEquals(20, dungeon.getPlayerEndLocation());
  }

  @Test
  public void PlayerMoveTest() {
    Character player = new Player(1, "ABC");
    RandomGenerator random = new PredefinedRandom(15, 1, 35, 27, 8, 24, 12,
            9, 11, 27, 0, 20, 20, 23, 3, 7, 10,
            8, 16, 14, 15, 15, 9, 13, 15, 0, 7,
            0, 2, 8, 7, 6, 2, 2, 4, 0, 2, 0,
            0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 1,
            1, 2, 0, 1, 1, 2, 2, 2, 1, 1, 2,
            2, 3, 3, 13, 15, 8, 0, 9, 11, 14,
            11, 5, 8, 9, 8, 6, 18, 21);
    Dungeon dungeon = new Matrix(5, 5, 0,
            WrappingCondition.UNWRAPPED, 50.00, 5,
            random, player);
    Environment[][] maze = dungeon.getMaze();
    dungeon.movePlayer(Route.EAST);
    int x = player.getPlayerLocation().getX();
    int y = player.getPlayerLocation().getY();
    assertNotEquals(maze[x][y].getNodeId(), dungeon.getPlayerStartLocation());
  }

  private int getEdges(Environment[][] maze, int row, int column) {
    int counter = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        if (maze[i][j].getAdjacentNodes().get(Route.NORTH) != null) {
          counter++;
        }
        if (maze[i][j].getAdjacentNodes().get(Route.SOUTH) != null) {
          counter++;
        }
      }
    }
    return counter;
  }

  @Test
  public void UnWrappedDungeonInterconnectivityTest() {
    Character player = new Player(1, "ABC");
    RandomGenerator random = new TrueRandom();
    Dungeon dungeon = new Matrix(5, 5, 0,
            WrappingCondition.UNWRAPPED, 20.00, 1,
            random, player);
    Environment[][] maze = dungeon.getMaze();
    assertEquals(26, getEdges(maze, 5, 5));
  }

  @Test
  public void WrappedDungeonInterconnectivityTest() {
    Character player = new Player(1, "ABC");
    RandomGenerator random = new TrueRandom();
    Dungeon dungeon = new Matrix(5, 5, 0,
            WrappingCondition.WRAPPED, 20.00, 1,
            random, player);
    Environment[][] maze = dungeon.getMaze();
    assertEquals(17, getEdges(maze, 4, 5));
  }


  @Test
  public void PlayerAllDirectionMovesTest() {
    Character player = new Player(1, "ABC");
    RandomGenerator random = new PredefinedRandom(15, 1, 35, 27, 8, 24, 12,
            9, 11, 27, 0, 20, 20, 23, 3, 7, 10,
            8, 16, 14, 15, 15, 9, 13, 15, 0, 7,
            0, 2, 8, 7, 6, 2, 2, 4, 0, 2, 0,
            0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 1,
            1, 2, 0, 1, 1, 2, 2, 2, 1, 1, 2,
            2, 3, 3, 13, 15, 8, 0, 9, 11, 14,
            11, 5, 8, 9, 8, 6, 18, 21);
    Dungeon dungeon = new Matrix(5, 5, 0,
            WrappingCondition.UNWRAPPED, 50.00, 5,
            random, player);
    Environment[][] maze = dungeon.getMaze();
    int x = player.getPlayerLocation().getX();
    int y = player.getPlayerLocation().getY();
    assertEquals(maze[x][y].getNodeId(), dungeon.getPlayerStartLocation());
    dungeon.movePlayer(Route.SOUTH);
    x = player.getPlayerLocation().getX();
    y = player.getPlayerLocation().getY();

    assertEquals(15, maze[x][y].getNodeId());
    dungeon.movePlayer(Route.WEST);
    x = player.getPlayerLocation().getX();
    y = player.getPlayerLocation().getY();

    assertEquals(18, maze[x][y].getNodeId());
    dungeon.movePlayer(Route.SOUTH);
    x = player.getPlayerLocation().getX();
    y = player.getPlayerLocation().getY();

    assertEquals(0, maze[x][y].getNodeId());
    dungeon.movePlayer(Route.EAST);
    x = player.getPlayerLocation().getX();
    y = player.getPlayerLocation().getY();

    assertEquals(1, maze[x][y].getNodeId());
    dungeon.movePlayer(Route.EAST);
    x = player.getPlayerLocation().getX();
    y = player.getPlayerLocation().getY();

    assertEquals(2, maze[x][y].getNodeId());
    dungeon.movePlayer(Route.NORTH);
    x = player.getPlayerLocation().getX();
    y = player.getPlayerLocation().getY();
    assertEquals(20, maze[x][y].getNodeId());
  }

  @Test
  public void PlayerDescriptionLocationTest() {
    Character player = new Player(1, "ABC");
    RandomGenerator random = new PredefinedRandom(15, 1, 35, 27, 8, 24, 12,
            9, 11, 27, 0, 20, 20, 23, 3, 7, 10,
            8, 16, 14, 15, 15, 9, 13, 15, 0, 7,
            0, 2, 8, 7, 6, 2, 2, 4, 0, 2, 0,
            0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 1,
            1, 2, 0, 1, 1, 2, 2, 2, 1, 1, 2,
            2, 3, 3, 13, 15, 8, 0, 9, 11, 14,
            11, 5, 8, 9, 8, 6, 18, 21);
    Dungeon dungeon = new Matrix(5, 5, 0,
            WrappingCondition.UNWRAPPED, 50.00, 5, random, player);
    dungeon.movePlayer(Route.EAST);
    assertEquals(" Player next possible moves are in these directions: \n"
            + "WEST\t\n"
            + "The current location has the following treasures :\n"
            + "DIAMOND\t", dungeon.playerDescription());
  }


}