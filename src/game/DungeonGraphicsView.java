package game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;

public class DungeonGraphicsView extends JFrame {

  private DungeonPanel dungeonPanel;

  public DungeonGraphicsView(Dungeon Matrix) throws IOException {
    super();
    this.setTitle("Dungeon Game");

    this.setLayout(new BorderLayout());
    this.setSize(500, 500);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);

//    SetupPanel panel = new SetupPanel();
//    panel.setPreferredSize(new Dimension(400,400));
//    this.add(panel,BorderLayout.CENTER);
//    panel.setVisible(true);

    dungeonPanel = new DungeonPanel(Matrix);
    dungeonPanel.setPreferredSize(new Dimension(400, 400));
    this.add(dungeonPanel, BorderLayout.CENTER);
    dungeonPanel.setVisible(true);
    this.setMinimumSize(new Dimension(200, 200));

    refresh();
    this.pack();
  }

  public void refresh() {
    this.repaint();
  }

  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  public void addClickListener(ViewController listener) {
    MouseAdapter clickAdapter = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int rows = e.getY() ;
        int cols = e.getX();
        listener.handleCellPress(rows, cols);
      }
    };
    dungeonPanel.addMouseListener(clickAdapter);
  }



}
