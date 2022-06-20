# Java_Model_Controller_Maze_Game
Designed and implemented a controller that uses a model which holds a maze based game. Tested the functionality of the code using Junit4 framework. 

Langauges and Frameworks: Java, Junit4

Game consists of a dungeon, a network of tunnels and caves that are interconnected so that player can explore the entire world by traveling from cave to cave through the tunnels that connect them.
The way the game is played is through printable text data, usually through a console. The dungeon are represented on a 2-D grida and there exists a path from every cave in the dungeon to every other cave in the dungeon. 
Each dungeon can be constructed with a degree of interconnectivity. We define an interconnectivity = 0 when there is exactly one path from every cave in the dungeon to every other cave in the dungeon. Increasing the degree of interconnectivity increases the number of paths between caves.Not all dungeons "wrap" from one side to the other (as defined above).
One cave is randomly selected as the start and one cave is randomly selected to be the end. The path between the start and the end locations will be at least of length 5.

The model provides support for the following features: 

  -> Both wrapping and non-wrapping dungeons to be created with different degrees of interconnectivity.
  -> Support for at least three types of treasure: diamonds, rubies, and sapphires. 
  -> A description of the player that, at a minimum, includes a description of what treasure the player has collected.
     provide a description of the player's location that at the minimum includes a description of treasure in the room and the possible moves (north, east, south, west) that the player can make from their current location
  -> A player to move from their current location. 
  -> A player to pick up treasure that is located in their same location. 
  -> Ability to add at least one Otyugh in the dungeon located at the specially designated end cave. The actual number is specified on the command line. 
  -> Otyugh only occupy caves and are never found in tunnels. Their caves can also contain treasure or other items.
  -> Player starts with 3 crooked arrows but can find additional arrows in the dungeon with the same frequency as treasure.
  -> A player that has arrows, can attempt to slay an Otyugh by specifying a direction and distance in which to shoot their crooked arrow. Distance is defined as the number of caves (but not tunnels) that an arrow travels.
  -> It takes 2 hits to kill an Otyugh. Players has a 50% chance of escaping if the Otyugh if they enter a cave of an injured Otyugh that has been hit by a single crooked arrow.
  
  
