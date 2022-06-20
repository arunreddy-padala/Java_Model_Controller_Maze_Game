package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

/**
 * Matrix class represented in the form of a maze, player, arrows, monsters and treasure.
 */

public class Matrix implements Dungeon {

  private final int rows;
  private final int columns;
  private final int interconnectivity;
  private final Character player;
  private Environment start;
  private Environment end;
  private final WrappingCondition condition;
  private final double treasurePercentage;
  private final Environment[][] maze;
  private final List<Pair> adjacencyList;
  private final RandomGenerator randomValue;
  private int totalOtyughs;


  /**
   * Matrix class constructor represented in the form of a maze, player, arrows,
   * monster and treasure.
   *
   * @param rows               numbers of rows within the maze
   * @param columns            numbers of columns within the maze
   * @param interconnectivity  interconnectivity value of the maze
   * @param condition          The wrapping condition for the maze.
   * @param treasurePercentage percentage of treasure to be added to the cave.
   * @param player             Obj of Character interface.
   * @param totalOtyughs       total number of monsters.
   * @throws IllegalArgumentException if rows, columns, interconnectivity or treasure
   *                                  percentage is less than zero or if player or
   *                                  wrapping condition is null and if monsters
   *                                  is less than 1.
   */
  public Matrix(int rows, int columns, int interconnectivity, WrappingCondition condition,
                Double treasurePercentage, int totalOtyughs, RandomGenerator randomValue,
                Character player)
          throws IllegalArgumentException {
    if ((rows < 0) || (columns < 0) || (interconnectivity < 0) || (player == null)
            || (condition == null) || (treasurePercentage < 0) || (totalOtyughs < 1)) {
      throw new IllegalArgumentException("Invalid values "
              + "for dungeon creation enter new values");
    }

    this.rows = rows;
    this.columns = columns;
    this.interconnectivity = interconnectivity;
    this.condition = condition;
    this.player = player;
    this.treasurePercentage = treasurePercentage;
    this.start = null;
    this.end = null;
    this.randomValue = randomValue;
    maze = new Cave[this.rows][this.columns];
    adjacencyList = new ArrayList<>();
    this.totalOtyughs = totalOtyughs;
    createDungeon(player);

  }

  /**
   * Private helper method to create the dungeon.
   *
   * @throws IllegalStateException if player is null.
   */
  private void createDungeon(Character Player) {

    int row = this.rows;
    int column = this.columns;
    int[] root = new int[row * column];
    int counter = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        maze[i][j] = new Cave(counter, i, j);
        root[counter++] = maze[i][j].getNodeId();
      }
    }

    wrappingCondition();
    kruskalAlgorithm(root);
    gameNodesSet();
    addTreasureCaves();
    addArrowsCaves();
    addOtyughCaves();
    ((Player) (player)).updatePlayerLocation(this.start.getCurrentPosition());
  }

  /**
   * A private method to determine the wrapping of the dungeon.
   */

  private void wrappingCondition() {

    int row = this.rows;
    int column = this.columns;

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        if (j + 1 < column) {
          adjacencyList.add(new Pair(maze[i][j].getNodeId(),
                  maze[i][j + 1].getNodeId()));
        }
        if (i + 1 < row) {
          adjacencyList.add(new Pair(maze[i][j].getNodeId(),
                  maze[i + 1][j].getNodeId()));
        }
        wrapped();
      }
    }
  }

  /**
   * A private method to determine a wrapped dungeon.
   */

  private void wrapped() {

    int row = this.rows;
    int column = this.columns;

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {

        if (this.condition == WrappingCondition.WRAPPED) {
          if (i == 0) {
            if (j == 0) {
              adjacencyList.add(new Pair(maze[i][j].getNodeId(),
                      maze[this.rows - 1][j].getNodeId()));
              adjacencyList.add(new Pair(maze[i][j].getNodeId(),
                      maze[i][this.columns - 1].getNodeId()));
            } else {
              adjacencyList.add(new Pair(maze[i][j].getNodeId(),
                      maze[this.rows - 1][j].getNodeId()));
            }
          } else {
            if (j == 0) {
              adjacencyList.add(new Pair(maze[i][j].getNodeId(),
                      maze[i][this.columns - 1].getNodeId()));
            }
          }
        }
      }
    }
  }

  /**
   * A private method to determine the start and end nodes.
   */


  private void gameNodesSet() {
    List<Environment> nodes = getGameStartEndNodes();
    this.start = nodes.get(0);
    this.end = nodes.get(1);
  }

  /**
   * A private helper method to get the potential end paths from a node.
   *
   * @param e object of the type environment
   * @return A list of end nodes that are greater than distance 5.
   */

  private List<Environment> getEndPaths(Environment e) {

    List<Environment> endPaths = new ArrayList<>();
    int totalNodes = this.rows * this.columns;
    int[] root = new int[totalNodes];
    int[] length = new int[totalNodes];
    boolean[] isVisited = new boolean[totalNodes];
    Arrays.fill(isVisited, false);
    Arrays.fill(length, -1);
    Arrays.fill(root, -1);
    isVisited[e.getNodeId()] = true;
    root[e.getNodeId()] = e.getNodeId();
    length[e.getNodeId()] = 0;
    Queue<Environment> queue = new LinkedList<>();
    queue.add(e);
    while (!queue.isEmpty()) {
      Environment node = queue.poll();
      Map<Route, Pair> neighbours = node.getAdjacentNodes();
      for (Map.Entry<Route, Pair> mapSet : neighbours.entrySet()) {
        int x = mapSet.getValue().getX();
        int y = mapSet.getValue().getY();
        int id = maze[x][y].getNodeId();
        if (!isVisited[id]) {
          isVisited[id] = true;
          queue.add(getPosition(id));
          root[id] = node.getNodeId();
          length[id] = length[node.getNodeId()] + 1;
        }
      }
    }
    for (int i = 0; i < length.length; i++) {
      if (length[i] >= 5) {
        endPaths.add(getPosition(i));
      }
    }
    return endPaths;
  }

  /**
   * A private helper method to add treasure to the caves.
   */


  private void addTreasureCaves() {

    int cavesCount = 0;
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        boolean value = maze[i][j].isTunnel();
        if (!value) {
          cavesCount++;
        }
      }
    }

    int total = (int) ((double) (cavesCount) * Math.round(this.treasurePercentage) / 100);
    int pos = 2;
    while (total != 0) {
      if (!getPosition(pos).isTunnel()) {
        List<Treasure> treasure = new ArrayList<>();
        treasure.add(Treasure.SAPPHIRE);
        treasure.add(Treasure.RUBY);
        treasure.add(Treasure.DIAMOND);
        int random = randomValue.getNextRandom(1, 3);
        while (random != 0) {
          int value = randomValue.getNextRandom(0, treasure.size() - 1);
          ((Cave) getPosition(pos)).addTreasure(treasure.get(value));
          treasure.remove(value);
          random--;
        }
        total--;
      }
      pos++;
    }
  }


  /**
   * A private method to retrieve the position of a node based on ID.
   *
   * @param id an integer ID.
   * @return position of the node.
   */

  private Environment getPosition(int id) {
    for (int x = 0; x < rows; x++) {
      for (int y = 0; y < columns; y++) {
        if (id == maze[x][y].getNodeId()) {
          return maze[x][y];
        }
      }
    }
    return null;
  }


  /**
   * A private helper method to add neighbours of a node based on direction.
   *
   * @param p1 object of the type Environment
   * @param p2 object of the type Environment
   */

  private void addNeighbour(Environment p1, Environment p2) {
    if (p1.getCurrentPosition().getY() + 1 == p2.getCurrentPosition().getY()) {
      p1.addAdjacentNodes(Route.EAST, p2.getCurrentPosition());
      p2.addAdjacentNodes(Route.WEST, p1.getCurrentPosition());
    } else if (p1.getCurrentPosition().getY() + columns - 1
            == p2.getCurrentPosition().getY()) {
      p1.addAdjacentNodes(Route.WEST, p2.getCurrentPosition());
      p2.addAdjacentNodes(Route.EAST, p1.getCurrentPosition());
    } else if (p1.getCurrentPosition().getX() + rows - 1
            == p2.getCurrentPosition().getX()) {
      p1.addAdjacentNodes(Route.NORTH, p2.getCurrentPosition());
      p1.addAdjacentNodes(Route.SOUTH, p1.getCurrentPosition());
    } else {
      p1.addAdjacentNodes(Route.SOUTH, p2.getCurrentPosition());
      p2.addAdjacentNodes(Route.NORTH, p1.getCurrentPosition());
    }
  }

  /**
   * Find method used in Kruskal's algorithm to find the root of a given key.
   *
   * @param x    an integer value
   * @param root an integer array.
   * @return root of a given key
   */


  private int find(int x, int[] root) {
    if (root[x] != x) {
      root[x] = find(root[x], root);
    }
    return root[x];
  }

  /**
   * Union method that unites the set that includes the parameters.
   *
   * @param x    an integer value
   * @param y    an integer value
   * @param root root element.
   */
  private void union(int x, int y, int[] root) {
    int u1 = find(x, root);
    int u2 = find(y, root);

    if (u1 == u2) {
      return;
    }
    root[u2] = u1;
  }

  /**
   * Kruskal's algorithm to build the maze.
   *
   * @param root an integer array.
   */
  private void kruskalAlgorithm(int[] root) {
    List<Pair> leftOverList = new ArrayList<>();
    while (adjacencyList.size() != 0) {
      int value = this.randomValue.getNextRandom(0, adjacencyList.size() - 1);
      int x = adjacencyList.get(value).getX();
      int y = adjacencyList.get(value).getY();
      if (find(x, root) == find(y, root)) {
        leftOverList.add(new Pair(x, y));
      } else {
        union(x, y, root);
        Environment e1 = getPosition(x);
        Environment e2 = getPosition(y);
        addNeighbour(e1, e2);
      }
      adjacencyList.remove(value);
    }
    if (interconnectivity > 0) {
      int connection = interconnectivity;
      while (connection != 0 && leftOverList.size() != 0) {
        int a = leftOverList.get(0).getX();
        int b = leftOverList.get(0).getY();
        addNeighbour(getPosition(a),
                getPosition(b));
        leftOverList.remove(0);
        connection--;
      }
    }
  }

  /**
   * Private helper method to get the start and end nodes.
   *
   * @return A list of nodes.
   */

  private List<Environment> getGameStartEndNodes() {
    Environment pos1 = null;
    boolean counter = true;
    while (counter) {
      int size = this.rows * this.columns;
      int rand1 = randomValue.getNextRandom(0, size - 1);
      pos1 = getPosition(rand1);
      if (!pos1.isTunnel()) {
        counter = false;
      }
    }
    List<Environment> endNodes = getEndPaths(pos1);
    counter = true;
    Environment pos2 = null;
    while (counter) {
      int rand2 = randomValue.getNextRandom(0, endNodes.size() - 1);
      pos2 = endNodes.get(rand2);
      if (!pos2.isTunnel()) {
        counter = false;
      }
    }
    List<Environment> nodes = new ArrayList<>();
    nodes.add(pos1);
    nodes.add(pos2);
    return nodes;
  }

  @Override
  public String movePlayer(Route direction) throws IllegalArgumentException {
    if (direction == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }
    StringBuilder string = new StringBuilder();
    boolean finish = false;
    int x = player.getPlayerLocation().getX();
    int y = player.getPlayerLocation().getY();
    Map<Route, Pair> route = maze[x][y].getAdjacentNodes();
    string.append(route);
    string.append("\n");
    if (!route.containsKey(direction)) {
      throw new IllegalArgumentException("Invalid direction");
    }
    Pair position = route.get(direction);
    ((Player) (player)).updatePlayerLocation(position);
    x = player.getPlayerLocation().getX();
    y = player.getPlayerLocation().getY();
    if (maze[x][y].isTunnel()) {
      string.append("The current location is a tunnel\n");
    } else {
      string.append("The current location is a cave\n");
    }


    Map<Route, Pair> route2 = maze[x][y].getAdjacentNodes();
    string.append("Doors lead to: ");
    string.append("\n");
    for (Map.Entry<?, ?> dir : route2.entrySet()) {
      string.append(dir.getKey());
      string.append(" ");
      string.append("\n");
    }
    if (maze[x][y].getTreasure().size() > 0) {
      string.append("The following treasures are here: \n");
      string.append(maze[x][y].getTreasure());
      string.append("\n");
    }
    int count = maze[x][y].getCountOfArrows();
    if (count > 0) {
      string.append("The following count of arrows are: ");
      string.append(count);
      string.append("\n");
    }
    int smell = determineSmell(x, y);
    if (smell == 0) {
      string.append("Cautious, You smell something terrible here \n");
    } else if (smell == 1) {
      string.append("you smell something less pungent here\n");
    }
    return string.toString();
  }

  private void test() {
    
  }

  @Override
  public Environment[][] getMaze() {
    return this.maze;
  }

  @Override
  public String playerDescription() {
    StringBuilder string = new StringBuilder();
    string.append("Player next possible moves are as follows:\n");
    int x = this.player.getPlayerLocation().getX();
    int y = this.player.getPlayerLocation().getY();
    Map<Route, Pair> adjacentNodes = this.maze[x][y].getAdjacentNodes();
    for (Map.Entry<Route, Pair> entry : adjacentNodes.entrySet()) {
      string.append(entry.getKey().toString());
      string.append(" ");
    }
    string.append("\nThe current location has the following treasures:\n");
    List<Treasure> test = this.maze[x][y].getTreasure();
    for (Object i : test) {
      string.append(i.toString());
      string.append("\t");
    }
    return string.toString();
  }

  @Override
  public int getPlayerStartLocation() {
    return this.start.getNodeId();
  }

  @Override
  public int getPlayerEndLocation() {
    return this.end.getNodeId();
  }

  /**
   * A private method to add Otyughs to caves.
   */


  private void addOtyughCaves() {
    int cavesCount = 0;
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        boolean value = maze[i][j].isTunnel();
        if (!value) {
          cavesCount++;
        }
      }
    }
    int counter = this.totalOtyughs;
    if (counter > cavesCount - 2) {
      counter = cavesCount - 2;
    } else {
      counter = counter - 1;
    }
    Monster monster = new Monster(1, "Otyugh");
    ((Cave) (this.end)).addOtyugh(monster);
    int index = 2;

    List<Integer> listOfCaves = new ArrayList<>();
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        boolean value = maze[i][j].isTunnel();
        if (!value) {
          listOfCaves.add(maze[i][j].getNodeId());
        }
      }
    }
    int index2 = listOfCaves.indexOf(this.start.getNodeId());
    listOfCaves.remove(index);

    List<Integer> listOfCaveId = listOfCaves;
    int value = listOfCaveId.indexOf(this.end.getNodeId());
    listOfCaveId.remove(value);
    while (counter != 0) {
      int rand = randomValue.getNextRandom(0, listOfCaveId.size() - 1);
      int node = listOfCaveId.get(rand);
      if (Objects.requireNonNull(getPosition(node)).getCountOfMonsters() == 0) {
        String name = String.format("Otyugh%d", index);
        Monster m = new Monster(index, name);
        ((Cave) (Objects.requireNonNull(getPosition(node)))).addOtyugh(m);
        counter--;
        index++;
        listOfCaveId.remove(rand);
      }
    }
  }

  /**
   * A private method to add arrows to caves.
   */

  private void addArrowsCaves() {
    int total = (int) ((double) (this.rows * this.columns)
            * Math.round(this.treasurePercentage) / 100);
    int pos = 0;
    while (total != 0) {
      int value = randomValue.getNextRandom(1, 3);
      assert (getPosition(pos)) != null;
      ((Cave) ((getPosition(pos)))).addArrows(value);
      pos++;
      total--;
    }
  }

  @Override
  public String pickUpArrows() {
    StringBuilder string = new StringBuilder();
    int x = player.getPlayerLocation().getX();
    int y = player.getPlayerLocation().getY();
    int count = maze[x][y].getCountOfArrows();
    ((Player) (player)).updatePlayerArrowsCount(count, "add");
    string.append(String.format("Player has %d number of arrows", this.player.getArrowsCount()));
    string.append("\n");
    return string.toString();
  }

  @Override
  public String pickUpTreasure(Treasure t) throws IllegalArgumentException {
    if (t == null) {
      throw new IllegalArgumentException("Treasure object cannot be null");
    }
    StringBuilder string = new StringBuilder();
    int x = player.getPlayerLocation().getX();
    int y = player.getPlayerLocation().getY();
    string.append(String.format("Player has picked %s treasure", t.toString()));
    string.append("\n");
    List<Treasure> treasures = maze[x][y].getTreasure();
    ((Player) (player)).addTreasure(t);
    treasures.remove(t);
    return string.toString();
  }

  /**
   * A private helper method to calculate the nearby monster smell.
   *
   * @param x position within the matrix.
   * @param y position within the matrix.
   * @return an integer value based on the smell.
   */

  private int determineSmell(int x, int y) throws IllegalArgumentException {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("values of X and Y cannot be less than zero");
    }
    Map<Route, Pair> adjacentNodes = maze[x][y].getAdjacentNodes();
    for (Map.Entry<Route, Pair> entry : adjacentNodes.entrySet()) {
      int pos1 = entry.getValue().getX();
      int pos2 = entry.getValue().getY();
      int count = maze[pos1][pos2].getCountOfMonsters();
      int health = 0;
      if (count > 0) {
        health = ((Cave) maze[pos1][pos2]).getOtyugh().getHealth();
      }
      if (count == 1 && health > 0) {
        return 0;
      } else {
        Map<Route, Pair> adjacentNodes2 = maze[pos1][pos2].getAdjacentNodes();
        for (Map.Entry<Route, Pair> entry1 : adjacentNodes2.entrySet()) {
          int pos3 = entry1.getValue().getX();
          int pos4 = entry1.getValue().getY();
          int count2 = maze[pos3][pos4].getCountOfMonsters();
          int health2 = 0;
          if (count2 > 0) {
            health2 = ((Cave) maze[pos3][pos4]).getOtyugh().getHealth();
          }
          if (count2 == 1 && health2 > 0) {
            return 1;
          }
        }
      }
    }
    return -1;
  }

  //TODO: Remove this method
  private StringBuilder dumpDungeon() {
    StringBuilder string = new StringBuilder();
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        string.append("Current position in dungeon is: ");
        string.append(maze[i][j].getCurrentPosition().getX());
        string.append(" ");
        string.append(maze[i][j].getCurrentPosition().getY());
        string.append("\n");
        string.append("The adjacent nodes are: ");
        string.append(maze[i][j].getAdjacentNodes());
        string.append("\n");
        string.append("Monsters in the position are: ");
        string.append(maze[i][j].getCountOfMonsters());
        string.append("\n");
        string.append("Treasures in the position are: ");
        string.append(maze[i][j].getTreasure());
        string.append("\n");
        string.append("Arrows in the position are: ");
        string.append(maze[i][j].getCountOfArrows());
        string.append("\n");
        string.append("\n");
      }
      string.append("\n");
    }
    return string;
  }

  @Override
  public String shoot(Route direction, int length) {
    StringBuilder string = new StringBuilder();
    if (length < 0) {
      throw new IllegalArgumentException();
    }
    int x = player.getPlayerLocation().getX();
    int y = player.getPlayerLocation().getY();
    Map<Route, Pair> nodes = maze[x][y].getAdjacentNodes();
    if (!nodes.containsKey(direction) || direction == null) {
      throw new IllegalArgumentException("Invalid direction");
    }
    int numberOfArrows = this.player.getArrowsCount();
    if (numberOfArrows == 0) {
      throw new IllegalStateException("Player has no arrows with him");
    }
    ((Player) (player)).updatePlayerArrowsCount(1, "subtract");
    int counter = length;
    while (counter != 0) {
      nodes = maze[x][y].getAdjacentNodes();
      if (nodes.containsKey(direction)) {
        Pair pos = nodes.get(direction);
        x = pos.getX();
        y = pos.getY();
        if (!maze[x][y].isTunnel()) {
          counter--;
        }
      } else if (maze[x][y].isTunnel()) {
        Map<Route, Pair> nodes2 = maze[x][y].getAdjacentNodes();
        if (direction.equals(Route.EAST) || direction.equals(Route.WEST)) {
          if (nodes2.containsKey(Route.SOUTH)) {
            direction = Route.SOUTH;
            x = x + 1;
          } else if (nodes2.containsKey(Route.NORTH)) {
            direction = Route.NORTH;
            x = x - 1;
          }
        } else if (direction.equals(Route.SOUTH) || direction.equals(Route.NORTH)) {
          if (nodes2.containsKey(Route.EAST)) {
            direction = Route.EAST;
            y = y + 1;
          } else if (nodes2.containsKey(Route.WEST)) {
            direction = Route.WEST;
            y = y - 1;
          }
        }
        if (!maze[x][y].isTunnel()) {
          counter--;
        }
      } else {
        break;
      }
      if (maze[x][y].getCountOfMonsters() == 1 && counter == 0) {
        ((Cave) (maze[x][y])).getOtyugh().monsterHealthUpdate(50);
        int health = ((Cave) (maze[x][y])).getOtyugh().getHealth();
        if (health == 0) {
          string.append("You hear a great howl in the distance, monster has died! Game over!!\n");
        } else if (health == 50) {
          string.append("Awesome, arrow has hit the monster! Try again to kill it\n");
        }
      } else if (counter == 0) {
        string.append("You shot an arrow into the darkness\n");
      }
    }
    return string.toString();
  }

  /**
   * ToString method to print the variables in matrix class.
   *
   * @return A formatted string.
   */
  @Override
  public String toString() {
    return "Matrix{"
            + "rows="
            + rows
            + ", columns="
            + columns
            + ", interconnectivity="
            + interconnectivity
            + ", player="
            + player
            + ", start="
            + start
            + ", end="
            + end
            + ", condition="
            + condition
            + ", treasurePercentage="
            + treasurePercentage
            + ", maze="
            + Arrays.toString(maze)
            + ", adjacencyList="
            + adjacencyList
            + ", randomValue="
            + randomValue
            + ", totalOtyughs="
            + totalOtyughs
            +
            '}';
  }

  @Override
  public int getPlayerLocationKey() {
    return  player.getPlayerLocation().getX();
  }
  @Override
  public int getPlayerLocationValue() {
    return  player.getPlayerLocation().getY();

  }
}
