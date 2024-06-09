# Maze_Game_Java

This project involves the design and implementation of a game controller that manages a maze-based dungeon game, utilizing a model to maintain the game's state. The functionality of the code has been rigorously tested using the JUnit4 framework.

Programming Languages and Tools: Java, JUnit4

Game Overview:
The game is set in a dungeon consisting of an intricate network of interconnected tunnels and caves, allowing players to explore a detailed world by moving from one cave to another. Gameplay is text-based, typically facilitated through a console interface. The dungeon layout is represented on a 2D grid, ensuring there is a navigable path between all caves. The complexity of pathways can be adjusted by varying the dungeon's interconnectivity level, with a minimum interconnectivity level (zero) offering exactly one unique path between any two caves.

Features:
- Dungeon Configuration: Support for both wrapping and non-wrapping dungeons, with adjustable degrees of interconnectivity.
- Treasures: Players can find three types of treasures: diamonds, rubies, and sapphires.
- Player Interaction:
   - Descriptions include player’s collected treasures and current location, detailing available treasures and potential moves (north, east, south, west).
   - Players can move within the dungeon and pick up treasures in their current location.
- Monsters: Each dungeon can host one or more Otyughs, menacing creatures located specifically in the end cave.
- Combat and Survival:
  - Players start with three crooked arrows but can discover more throughout the game.
  - Players can attempt to slay an Otyugh by shooting an arrow in a specified direction and distance. It takes two successful hits to kill an Otyugh.
 - There's a 50% survival chance when encountering an injured Otyugh.
- The dungeon's end is marked by a randomly selected cave, with a guaranteed minimum path length of five from the starting point to ensure a challenging and engaging gameplay experience.  
  
