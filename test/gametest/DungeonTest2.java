package gametest;

import org.junit.Test;
import java.io.IOException;
import java.io.StringReader;
import java.util.NoSuchElementException;
import game.Character;
import game.ConsoleController;
import game.Controller;
import game.Dungeon;
import game.Environment;
import game.FailingAppendable;
import game.Matrix;
import game.Player;
import game.PredefinedRandom;
import game.RandomGenerator;
import game.Route;
import game.WrappingCondition;

import static org.junit.Assert.assertEquals;

/**
 * Junit testing for Dungeon.
 */

public class DungeonTest2 {

  @Test
  public void OtyughNotAtStartTest() {
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
    int x = player.getPlayerLocation().getX();
    int y = player.getPlayerLocation().getY();
    Environment[][] maze = dungeon.getMaze();
    assertEquals(0, maze[x][y].getCountOfMonsters());
  }

  @Test
  public void OtyughAtEndTest() {
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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    int x = player.getPlayerLocation().getX();
    int y = player.getPlayerLocation().getY();
    Environment[][] maze = dungeon.getMaze();
    assertEquals(1, maze[x][y].getCountOfMonsters());
  }

  @Test
  public void OtyughNotAtTunnelTest() {

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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    int x = player.getPlayerLocation().getX();
    int y = player.getPlayerLocation().getY();
    Environment[][] maze = dungeon.getMaze();
    assertEquals(0, maze[x][y].getCountOfMonsters());
  }

  @Test
  public void OtyughAtCaveTest() {

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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    int x = player.getPlayerLocation().getX();
    int y = player.getPlayerLocation().getY();
    Environment[][] maze = dungeon.getMaze();
    assertEquals(1, maze[x][y].getCountOfMonsters());
  }


  @Test
  public void OtyughMoreThanOneTest() {

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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    int x1 = player.getPlayerLocation().getX();
    int y1 = player.getPlayerLocation().getY();
    Environment[][] maze1 = dungeon.getMaze();
    assertEquals(1, maze1[x1][y1].getCountOfMonsters());
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.SOUTH);
    int x2 = player.getPlayerLocation().getX();
    int y2 = player.getPlayerLocation().getY();
    Environment[][] maze = dungeon.getMaze();
    assertEquals(1, maze[x2][y2].getCountOfMonsters());


  }

  @Test
  public void OtyughSmellTest() {

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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    assertEquals("you smell something less pungent here",
            dungeon.movePlayer(Route.SOUTH));
  }

  @Test
  public void OtyughSmellTest2() {

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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.SOUTH);
    assertEquals("Cautious, You smell something terrible here",
            dungeon.movePlayer(Route.NORTH));
  }


  @Test
  public void OtyughKillsPlayerTest() {

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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    assertEquals("Oh noo, you were eaten by an Otyugh :(",
            dungeon.movePlayer(Route.WEST));
  }

  @Test
  public void PlayerInitialArrowsTest() {
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
    int x = player.getPlayerLocation().getX();
    int y = player.getPlayerLocation().getY();
    Environment[][] maze = dungeon.getMaze();
    assertEquals(3, maze[x][y].getCountOfArrows());
  }

  @Test
  public void PlayerArrowShootTest() {

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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    assertEquals("You shot an arrow into the darkness",
            dungeon.shoot(Route.EAST, 1));
  }


  @Test
  public void PlayerArrowShootTest2() {

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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    assertEquals("Awesome, arrow has hit the monster! Try again to kill it",
            dungeon.shoot(Route.WEST, 1));
  }

  @Test
  public void PlayerArrowShootTest3() {

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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.shoot(Route.WEST, 1);
    assertEquals("You hear a great howl in the distance, monster has died!",
            dungeon.shoot(Route.WEST, 1));
  }

  @Test
  public void PlayerArrowShootTunnelTest() {

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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    assertEquals("You shot an arrow into the darkness",
            dungeon.shoot(Route.EAST, 1));
  }

  @Test
  public void PlayerArrowShootCaveTest() {

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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.shoot(Route.WEST, 1);
    assertEquals("You hear a great howl in the distance, monster has died!",
            dungeon.shoot(Route.WEST, 1));
  }

  @Test
  public void PlayerOutOfArrowsTest() {

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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.shoot(Route.WEST, 1);
    dungeon.shoot(Route.NORTH, 1);
    dungeon.shoot(Route.SOUTH, 1);
    assertEquals("Player has no arrows with him",
            dungeon.shoot(Route.SOUTH, 1));
  }

  @Test
  public void PlayerArrowDistanceTest() {

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
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.shoot(Route.WEST, 1);
    assertEquals("You hear a great howl in the distance, monster has died!",
            dungeon.shoot(Route.WEST, 1));
  }


  @Test
  public void PlayerWinningGameTest() {

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
            WrappingCondition.UNWRAPPED, 50.00, 8, random, player);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.shoot(Route.WEST, 1);
    dungeon.shoot(Route.WEST, 1);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.EAST);
    assertEquals("Player won the Game!!", dungeon.movePlayer(Route.EAST));
  }


  @Test
  public void PlayerOtyughEscapeTest() {

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
            WrappingCondition.UNWRAPPED, 50.00, 8, random, player);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.WEST);
    dungeon.movePlayer(Route.EAST);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.movePlayer(Route.SOUTH);
    dungeon.movePlayer(Route.NORTH);
    dungeon.shoot(Route.WEST, 1);
    assertEquals("Phew! You escaped from an Otyugh!",
            dungeon.movePlayer(Route.WEST));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidModelTest() throws IOException {

    Dungeon m = new MockModel();
    Character player = new Player(1, "ABC");
    RandomGenerator random = new PredefinedRandom(15, 1, 35, 27, 8, 24, 12,
            9, 11, 27, 0, 20, 20, 23, 3, 7, 10,
            8, 16, 14, 15, 15, 9, 13, 15, 0, 7,
            0, 2, 8, 7, 6, 2, 2, 4, 0, 2, 0,
            0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 1,
            1, 2, 0, 1, 1, 2, 2, 2, 1, 1, 2,
            2, 3, 3, 13, 15, 8, 0, 9, 11, 14,
            11, 5, 8, 9, 8, 6, 18, 21);
    Dungeon dungeon = new Matrix(-5, -5, -1,
            WrappingCondition.UNWRAPPED, 50.00, 0,
            random, player);
    StringReader input = new StringReader(dungeon.toString());
    Appendable gameLog = new FailingAppendable();
    Controller game = new ConsoleController(input, gameLog);
    game.playGame(m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullReadableTest() {
    StringBuilder gameLog = new StringBuilder();
    Controller game = new ConsoleController(null, gameLog);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullAppendableTest() {
    StringReader input = new StringReader("m N S E");
    Controller game = new ConsoleController(input, null);
  }


  @Test
  public void quitGameTest() throws IOException {
    Dungeon m = new MockModel();
    StringReader input = new StringReader("m N q");
    StringBuilder gameLog = new StringBuilder();
    Controller controller = new ConsoleController(input, gameLog);
    controller.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game quit! Ending game state:", 3, lines.length);
  }

  @Test
  public void ControllerPlayerValidMoveTest() throws IOException {
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
            WrappingCondition.UNWRAPPED, 50.00, 8, random, player);
    StringReader input = new StringReader("m W");
    StringBuilder gameLog = new StringBuilder();
    Controller controller = new ConsoleController(input, gameLog);
    controller.playGame(dungeon);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(12, lines.length);
    assertEquals("The current location is a cave", lines[lines.length - 10]);

  }

  @Test
  public void ControllerPlayerInvalidMoveTest() throws IOException {
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
            WrappingCondition.UNWRAPPED, 50.00, 8, random, player);
    StringReader input = new StringReader("m W m R m S");
    StringBuilder gameLog = new StringBuilder();
    Controller controller = new ConsoleController(input, gameLog);
    controller.playGame(dungeon);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(20, lines.length);
    assertEquals("Move, Pickup, or Shoot (M-P-S)? ", lines[lines.length - 1]);

  }

  @Test(expected = NoSuchElementException.class)
  public void ControllerPlayerInvalidOptionTest() throws IOException {
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
            WrappingCondition.UNWRAPPED, 50.00, 8, random, player);
    StringReader input = new StringReader("p");
    StringBuilder gameLog = new StringBuilder();
    Controller controller = new ConsoleController(input, gameLog);
    controller.playGame(dungeon);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(20, lines.length);
  }


  @Test
  public void ControllerPlayerPickTreasureTest() throws IOException {
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
            WrappingCondition.UNWRAPPED, 50.00, 8, random, player);
    StringReader input = new StringReader("m W p treasure");
    StringBuilder gameLog = new StringBuilder();
    Controller controller = new ConsoleController(input, gameLog);
    controller.playGame(dungeon);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(14, lines.length);
    assertEquals("[RUBY, DIAMOND]", lines[lines.length - 6]);

  }


  @Test
  public void ControllerPlayerPickArrowTest() throws IOException {

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
            WrappingCondition.UNWRAPPED, 50.00, 8, random, player);
    StringReader input = new StringReader("m W p arrow");
    StringBuilder gameLog = new StringBuilder();
    Controller controller = new ConsoleController(input, gameLog);
    controller.playGame(dungeon);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(14, lines.length);
    assertEquals("pick what ?Player has 16 number of arrows", lines[lines.length - 2]);

  }

  @Test
  public void ControllerOtyughSmellTest() throws IOException {

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
            WrappingCondition.UNWRAPPED, 50.00, 8, random, player);
    StringReader input = new StringReader("m W p arrow");
    StringBuilder gameLog = new StringBuilder();
    Controller controller = new ConsoleController(input, gameLog);
    controller.playGame(dungeon);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(14, lines.length);
    assertEquals("you smell something less pungent here", lines[lines.length - 4]);

  }


  @Test
  public void ControllerPlayerShootArrowTest() throws IOException {

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
            WrappingCondition.UNWRAPPED, 50.00, 8, random, player);
    StringReader input = new StringReader("m W s 1 N");
    StringBuilder gameLog = new StringBuilder();
    Controller controller = new ConsoleController(input, gameLog);
    controller.playGame(dungeon);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(16, lines.length);
    assertEquals("You shot an arrow into the darkness", lines[lines.length - 2]);

  }

  @Test
  public void ControllerPlayerKilledByOtyughTest() throws IOException {

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
            WrappingCondition.UNWRAPPED, 50.00, 8, random, player);
    StringReader input = new StringReader("m W m S m S");
    StringBuilder gameLog = new StringBuilder();
    Controller controller = new ConsoleController(input, gameLog);
    controller.playGame(dungeon);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(24, lines.length);
    assertEquals("Oh noo, you were eaten by an Otyugh ", lines[lines.length - 3]);

  }


  @Test
  public void ControllerPlayerEscapesOtyughTest() throws IOException {

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
            WrappingCondition.UNWRAPPED, 50.00, 8, random, player);
    StringReader input = new StringReader("m W m S s 1 S m S");
    StringBuilder gameLog = new StringBuilder();
    Controller controller = new ConsoleController(input, gameLog);
    controller.playGame(dungeon);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(33, lines.length);
    assertEquals("Phew! You escaped from an Otyugh!",
            lines[lines.length - 8]);

  }

  @Test
  public void ControllerPlayerKillsOtyughTest() throws IOException {

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
            WrappingCondition.UNWRAPPED, 50.00, 8, random, player);
    StringReader input = new StringReader("m W m S s 1 S s 1 S");
    StringBuilder gameLog = new StringBuilder();
    Controller controller = new ConsoleController(input, gameLog);
    controller.playGame(dungeon);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(27, lines.length);
    assertEquals("You hear a great howl in the distance, monster has died! Game over!!",
            lines[lines.length - 2]);
  }

  @Test
  public void ControllerPlayerInjuresOtyughTest() throws IOException {

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
            WrappingCondition.UNWRAPPED, 50.00, 8, random, player);
    StringReader input = new StringReader("m W m S s 1 S");
    StringBuilder gameLog = new StringBuilder();
    Controller controller = new ConsoleController(input, gameLog);
    controller.playGame(dungeon);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(23, lines.length);
    assertEquals("Awesome, arrow has hit the monster! Try again to kill it",
            lines[lines.length - 2]);
  }

  @Test
  public void ControllerPlayerWinsTest() throws IOException {

    Character player = new Player(1, "ABC");
    RandomGenerator random = new PredefinedRandom(11, 1, 8, 6, 4, 3, 5,
            4, 2, 1, 0, 0, 0, 0, 3, 2, 1,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 7,
            0, 2, 8, 7, 6, 2, 2, 4, 0, 2, 0,
            0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 1,
            1, 2, 0, 1, 1, 2, 2, 2, 1, 1, 2,
            2, 3, 3, 13, 15, 8, 0, 9, 11, 14,
            11, 5, 8, 9, 8, 6, 18, 21);
    Dungeon dungeon = new Matrix(3, 3, 0,
            WrappingCondition.UNWRAPPED, 50.00, 2, random, player);
    StringReader input = new StringReader("m S m E m E m S m W s 1 W s 1 W m W");
    StringBuilder gameLog = new StringBuilder();
    Controller controller = new ConsoleController(input, gameLog);
    controller.playGame(dungeon);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(51, lines.length);
    assertEquals("Player won the Game!!Move, Pickup, or Shoot (M-P-S)? ",
            lines[lines.length - 1]);

  }
}





