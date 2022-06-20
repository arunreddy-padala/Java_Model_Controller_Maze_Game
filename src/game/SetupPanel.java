package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class SetupPanel extends JPanel implements ActionListener {

  private JLabel rowLabel;
  private JLabel columnLabel;
  private JLabel interconnectivityLabel;
  private JLabel wrappingLabel;
  private JLabel percentageLabel;
  private JLabel monsterCountLabel;


  private JTextField rowText;
  private JTextField columnText;
  private JTextField interconnectivityText;
  private JTextField percentageText;
  private JTextField monsterCountText;

  private JRadioButton nonWrappingButton;
  private JRadioButton wrappingButton;
  private ButtonGroup conditiongp;

  private JButton submit;

  private Dungeon Matrix;
  private DungeonGraphicsView View;


  private int rows = 0;
  private int cols = 0;
  private int interconnectivity = 0;
  private int percentage = 0;
  private int monsterCount = 0;
  private int condition = 0;
  private WrappingCondition wrappingCondition;
  private double percentage2;
  private RandomGenerator random;
  private Character player;
  private ArrayList<Integer> setupList;
  private ViewController controller;


  public SetupPanel() {

    this.setLayout(new FlowLayout());

    rowLabel = new JLabel("Rows");
    rowLabel.setLocation(500, 50);
    this.add(rowLabel);


    columnLabel = new JLabel("Columns");
    columnLabel.setLocation(500, 80);
    this.add(columnLabel);

    interconnectivityLabel = new JLabel("Interconnectivity");
    interconnectivityLabel.setLocation(500, 110);
    this.add(interconnectivityLabel);

    wrappingLabel = new JLabel("WrappingCondition");
    wrappingLabel.setLocation(500, 140);
    this.add(wrappingLabel);

    percentageLabel = new JLabel("Treasure/Arrow%");
    percentageLabel.setLocation(500, 170);
    this.add(percentageLabel);

    monsterCountLabel = new JLabel("TotalMonsterCount");
    monsterCountLabel.setLocation(500, 200);
    this.add(monsterCountLabel);

    rowText = new JTextField(10);
    this.add(rowText);

    columnText = new JTextField(10);
    this.add(columnText);

    interconnectivityText = new JTextField(10);
    this.add(interconnectivityText);

    percentageText = new JTextField(10);
    this.add(percentageText);

    monsterCountText = new JTextField(10);
    this.add(monsterCountText);


    nonWrappingButton = new JRadioButton("UNWRAPPED");
    this.add(nonWrappingButton);


    wrappingButton = new JRadioButton("WRAPPED");
    this.add(wrappingButton);

    conditiongp = new ButtonGroup();
    conditiongp.add(nonWrappingButton);
    conditiongp.add(wrappingButton);

    submit = new JButton("Submit");
    submit.addActionListener(this);
    this.add(submit);

    this.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == submit) {

      setupList = new ArrayList<Integer>();

      rows = Integer.parseInt(rowText.getText());
      setupList.add(rows);

      cols = Integer.parseInt(columnText.getText());
      setupList.add(cols);

      interconnectivity = Integer.parseInt(interconnectivityText.getText());
      setupList.add(interconnectivity);

      if (wrappingButton.getText().equals("UNWRAPPED")) {
        condition = 0;
      } else {
        condition = 1;
      }
      setupList.add(condition);

      percentage = Integer.parseInt(percentageText.getText());
      setupList.add(percentage);

      monsterCount = Integer.parseInt(monsterCountText.getText());
      setupList.add(monsterCount);

      try {
        createModel();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }

  }

  public ArrayList<Integer> getSetupValues() {
    return setupList;
  }

  public void createModel() throws IOException {

    System.out.println("Create model controller in setup called");
    controller = new ViewController(Matrix, View);
    controller.createModel(setupList);
  }


}
