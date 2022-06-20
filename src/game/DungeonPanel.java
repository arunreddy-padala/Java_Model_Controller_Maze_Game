package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

class DungeonPanel extends JPanel {

  private final Dungeon Matrix;

  //TODO: How to get the row and col values from the setup panel?

  private int rows = 4;
  private int columns = 4;
  JLabel picLabel;

  public DungeonPanel(Dungeon matrix) throws IOException {
    this.Matrix = matrix;
    GridBagLayout gb1 = new GridBagLayout();
    this.setLayout(gb1);
    visualiseDungeon();
    this.setBackground(Color.cyan);
  }

  public void visualiseDungeon() throws IOException {

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {

        JPanel panel = new JPanel();
        BufferedImage dungeonPictures;

        GridBagConstraints grid = new GridBagConstraints();
        grid.gridx = j;
        grid.gridy = i;
        grid.insets.set(-2, -2, -2, -2);
        picLabel = null;

        Environment[][] maze = Matrix.getMaze();
        Map<Route, Pair> adjacentNodes = maze[i][j].getAdjacentNodes();
        StringBuilder sb = new StringBuilder();

        ArrayList<String> dir = new ArrayList<>();
        for (Map.Entry<Route, Pair> mapSet : adjacentNodes.entrySet()) {
          String x = mapSet.getKey().toString();
          dir.add(x);
        }
        Collections.sort(dir);

        for (int i1 = 0; i1 < dir.size(); i1++) {
          sb.append(dir.get(i1).charAt(0));
        }

        sb.append(".png");

        BufferedImage result;
          String name = sb.toString();
          String location = "./res/dungeon-images/" + name;
          dungeonPictures = ImageIO.read(new File(location));
            int width = dungeonPictures.getWidth();
            int height = dungeonPictures.getHeight();
            result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = result.getGraphics();
            g.drawImage(dungeonPictures, 0, 0, null);
        JLabel picLabel = new JLabel();
        picLabel.setIcon(new ImageIcon(result));
        panel.add(picLabel);
        this.add(panel, grid);

           if (maze[i][j].getCountOfMonsters() == 1) {
             try {
               BufferedImage overlay = ImageIO.read(new File("./res/dungeon-images/otyugh.png"));
               int w = Math.max(result.getWidth(), overlay.getWidth());
               int h = Math.max(result.getHeight(), overlay.getHeight());
               BufferedImage result1 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
               Graphics g1 = result1.getGraphics();
               g1.drawImage(dungeonPictures, 0, 0, null);
               g1.drawImage(overlay, 0, 0, null);
               picLabel.setIcon(new ImageIcon(result1));
               panel.add(picLabel);
             } catch (IllegalArgumentException e) {

             }
           }

             if (Matrix.getPlayerLocationKey() == i && Matrix.getPlayerLocationValue() == j)

             {
               try {
                 BufferedImage overlay = ImageIO.read(new File("./res/dungeon-images/player.png"));
                 int w = Math.max(result.getWidth(), overlay.getWidth());
                 int h = Math.max(result.getHeight(), overlay.getHeight());
                 BufferedImage result1 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                 Graphics g1 = result1.getGraphics();
                 g1.drawImage(dungeonPictures, 0, 0, null);
                 g1.drawImage(overlay, 0, 0, null);
                 picLabel.setIcon(new ImageIcon(result1));
                 panel.add(picLabel);
               } catch (IllegalArgumentException e) {

               }

             }

             if (maze[i][j].getCountOfArrows() >= 1) {
            try {
              BufferedImage overlay = ImageIO.read(new File("./res/dungeon-images/arrow-white.png"));
              int w = Math.max(result.getWidth(), overlay.getWidth());
              int h = Math.max(result.getHeight(), overlay.getHeight());
              BufferedImage result1 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
              Graphics g1 = result1.getGraphics();
              g1.drawImage(dungeonPictures, 0, 0, null);
              g1.drawImage(overlay, 0, 0, null);
              picLabel.setIcon(new ImageIcon(result1));
              panel.add(picLabel);
            } catch (IllegalArgumentException e) {

            }

          }

        }
      }
    }
  }







