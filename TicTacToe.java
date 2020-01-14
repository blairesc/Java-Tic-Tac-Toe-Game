/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fau.COT4930;

/**
 *
 * @author Blaire Swaby-Clacken
 */
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class TicTacToe extends JFrame {
    //TicTacToe Window
    JPanel mainPanel = new JPanel();
    JPanel topPanel = new JPanel(); //contains the label which player goes next
    JLabel playersTurnLabel = new JLabel();
    JPanel centerPanel = new JPanel(); //tictactoe panel
    JPanel bottomPanel = new JPanel(); //score keep panel
    JLabel xScore = new JLabel();
    JLabel oScore = new JLabel();
    
    //Menu Bar
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    //Menu list
    JMenuItem help = new JMenuItem ("Help");
    JMenuItem newGame = new JMenuItem("New Game");
    JMenuItem endGame = new JMenuItem("End Game");
    
    //buttons
    //the 3 by 3 grid buttons
    JButton row1Col1Button = new JButton();
    JButton row1Col2Button = new JButton();
    JButton row1Col3Button = new JButton();
    JButton row2Col1Button = new JButton();
    JButton row2Col2Button = new JButton();
    JButton row2Col3Button = new JButton();
    JButton row3Col1Button = new JButton();
    JButton row3Col2Button = new JButton();
    JButton row3Col3Button = new JButton();
    
    //Welcome Window     
    JPanel welcomeTopPanel = new JPanel();
    JLabel welcomeLabel = new JLabel("TicTacToe Game! ");
    
    JPanel welcomeCenterPanel = new JPanel();
    JLabel player1Label = new JLabel("Player 1 is X.");
    JLabel player2Label = new JLabel("Player 2 is O.");
    
    JPanel welcomeBottomPanel = new JPanel();
    JButton continueButton = new JButton("Continue");
    JButton cancelButton = new JButton("Cancel");
    
    JPanel welcomeMainPanel = new JPanel();
    JFrame welcomeFrame = new JFrame();

    //others
    boolean xTurn;
    int[] buttonStates = new int[9];
    int buttonsClicked;
    String xInput, oInput;
    int countX = 0, countO = 0;
    Player playerX = new Player();
    Player playerO = new Player();
    
    public static void main(String[] args) {
        new TicTacToe();
    }
    
    public TicTacToe()  {
        welcome();
    }
    
    /**
        Method to build the welcome window and continue to
        tic tac toe window.
    */
    //build welcome window
    public void welcome() {
        welcomeFrame.setTitle("Welcome!");
        welcomeFrame.setSize(400, 300);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setLocationRelativeTo(null); //center the frame on screen
        welcomeFrame.setResizable(true);
        
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        player1Label.setFont(new Font("Arial", Font.PLAIN, 25));
        player2Label.setFont(new Font("Arial", Font.PLAIN, 25));
        
        welcomeLabel.setForeground(Color.red);
        player1Label.setForeground(Color.magenta);
        player2Label.setForeground(Color.blue);
        
        welcomeTopPanel.setBackground(Color.white);
        welcomeTopPanel.add(welcomeLabel);
        
        
        welcomeCenterPanel.setLayout(new GridLayout(2,1));
        welcomeCenterPanel.setBackground(Color.white);
        welcomeCenterPanel.add(player1Label);
        welcomeCenterPanel.add(player2Label);
        
        welcomeBottomPanel.setLayout(new GridLayout(2,1));
        welcomeBottomPanel.setBackground(Color.white);
        welcomeBottomPanel.add(continueButton);
        welcomeBottomPanel.add(cancelButton);
        
        welcomeMainPanel.setLayout(new BorderLayout());
        welcomeMainPanel.setBackground(Color.white);
        welcomeMainPanel.add(welcomeTopPanel, BorderLayout.NORTH);
        welcomeMainPanel.add(welcomeCenterPanel, BorderLayout.CENTER);
        welcomeMainPanel.add(welcomeBottomPanel, BorderLayout.SOUTH);
        
        welcomeFrame.add(welcomeMainPanel);
        welcomeFrame.setVisible(true);
        
        continueButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                playerX.setName("X");
                playerO.setName("O");
                resetGame();
                startGame();
                window();
                welcomeFrame.setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    public void startGame() { 
        playersTurnLabel.setText("X goes first");
    }
    
    /**
       Method to reset the grid button
       to clean states and zero the number of buttons clicked.   
    */
    public void resetGame() { //restarts the game 
        xTurn = true;
        buttonsClicked = 0;
        
        row1Col1Button.setText("");
        row1Col2Button.setText("");
        row1Col3Button.setText("");
        row2Col1Button.setText("");
        row2Col2Button.setText("");
        row2Col3Button.setText("");
        row3Col1Button.setText("");
        row3Col2Button.setText("");
        row3Col3Button.setText("");
        
        row1Col1Button.setBackground(null);
        row1Col2Button.setBackground(null);
        row1Col3Button.setBackground(null);
        row2Col1Button.setBackground(null);
        row2Col2Button.setBackground(null);
        row2Col3Button.setBackground(null);
        row3Col1Button.setBackground(null);
        row3Col2Button.setBackground(null);
        row3Col3Button.setBackground(null);
        
        for(int i = 0; i < buttonStates.length; i++) {
            buttonStates[i] = 0;
        }
    }
    
    //build tictactoe window
    public void window() {
        this.setTitle("Blaire's TicTacToe");
        this.setSize(500, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); //center the frame on screen
        this.setResizable(true);
        this.setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(help);
        menu.add(newGame);
        menu.add(endGame);
        
        help.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                try {
                    String url = "https://en.wikipedia.org/wiki/Tic-tac-toe";
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
                }
                catch(IOException ex) {
                    JOptionPane.showMessageDialog(centerPanel, ex);
                }
            }
        });
        
        newGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                resetGame();
                countX = 0;
                countO = 0;
                playerX.resetScore();
                playerO.resetScore();
                xScore.setText("Player " + playerX.getName() + " : " + playerX.getScore());
                oScore.setText("Player " + playerO.getName() + " : " + playerO.getScore());
                //players();
            }
        });
        
        endGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                int choi = JOptionPane.showOptionDialog(null, "Are you sure you want to quit the game?", "Exit?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if (choi == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        topPanel.add(playersTurnLabel);
        
        //font of grid buttons
        row1Col1Button.setFont(new Font("Arial", Font.PLAIN, 20));
        row1Col2Button.setFont(new Font("Arial", Font.PLAIN, 20));
        row1Col3Button.setFont(new Font("Arial", Font.PLAIN, 20));
        row2Col1Button.setFont(new Font("Arial", Font.PLAIN, 20));
        row2Col2Button.setFont(new Font("Arial", Font.PLAIN, 20));
        row2Col3Button.setFont(new Font("Arial", Font.PLAIN, 20));
        row3Col1Button.setFont(new Font("Arial", Font.PLAIN, 20));
        row3Col2Button.setFont(new Font("Arial", Font.PLAIN, 20));
        row3Col3Button.setFont(new Font("Arial", Font.PLAIN, 20));
        
        //action of grid buttons
        row1Col1Button.addActionListener(new XOActionListener());
        row1Col2Button.addActionListener(new XOActionListener());
        row1Col3Button.addActionListener(new XOActionListener());
        row2Col1Button.addActionListener(new XOActionListener());
        row2Col2Button.addActionListener(new XOActionListener());
        row2Col3Button.addActionListener(new XOActionListener());
        row3Col1Button.addActionListener(new XOActionListener());
        row3Col2Button.addActionListener(new XOActionListener());
        row3Col3Button.addActionListener(new XOActionListener());
        
        //add grid buttons to center panel 
        centerPanel.setLayout(new GridLayout(3, 3, 10, 10));
        centerPanel.add(row1Col1Button);
        centerPanel.add(row1Col2Button);
        centerPanel.add(row1Col3Button);
        centerPanel.add(row2Col1Button);
        centerPanel.add(row2Col2Button);
        centerPanel.add(row2Col3Button);
        centerPanel.add(row3Col1Button);
        centerPanel.add(row3Col2Button);
        centerPanel.add(row3Col3Button);

        xScore.setText("Player " + playerX.getName() + " : " +playerX.getScore());
        oScore.setText("Player " + playerO.getName() + " : " + playerO.getScore());
        JButton space = new JButton();
        space.setBackground(Color.red);
        bottomPanel.add(xScore);
        bottomPanel.add(space);
        bottomPanel.add(oScore);
        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.setVisible(true);
    }
    
    /**
        Method to determine if there is a wining condition
        horizontally, vertically or diagonally.
    */
    public boolean isWin() { //check if there is a winning conditon
        /* 0 1 2 
           3 4 5
           6 7 8
        */
        boolean win = false;
        
        //check win horizontally
        if (buttonStates[0] == buttonStates[1] && buttonStates[1] == buttonStates[2] && buttonStates[2] == buttonStates[0] && buttonStates[0] > 0 && buttonStates[1] > 0 && buttonStates[2] > 0) {
            row1Col1Button.setBackground(Color.white);
            row1Col2Button.setBackground(Color.white);
            row1Col3Button.setBackground(Color.white);
            return true;
        }
        if (buttonStates[3] == buttonStates[4] && buttonStates[4] == buttonStates[5] && buttonStates[5] == buttonStates[3] && buttonStates[3] > 0 && buttonStates[4] > 0 && buttonStates[5] > 0) {
            row2Col1Button.setBackground(Color.white);
            row2Col2Button.setBackground(Color.white);
            row2Col3Button.setBackground(Color.white);
            return true;
        }
        if (buttonStates[6] == buttonStates[7] && buttonStates[7] == buttonStates[8] && buttonStates[8] == buttonStates[6] && buttonStates[6] > 0 && buttonStates[7] > 0 && buttonStates[8] > 0) {
            row3Col1Button.setBackground(Color.white);
            row3Col2Button.setBackground(Color.white);
            row3Col3Button.setBackground(Color.white);
            return true;
        }
        
        //check win vertically
        if (buttonStates[0] == buttonStates[3] && buttonStates[3] == buttonStates[6] && buttonStates[6] == buttonStates[0] && buttonStates[0] > 0 && buttonStates[3] > 0 && buttonStates[6] > 0) {
            row1Col1Button.setBackground(Color.white);
            row2Col1Button.setBackground(Color.white);
            row3Col1Button.setBackground(Color.white);   
            return true;
        }
        if (buttonStates[1] == buttonStates[4] && buttonStates[4] == buttonStates[7] && buttonStates[7] == buttonStates[1] && buttonStates[1] > 0 && buttonStates[4] > 0 && buttonStates[7] > 0) {
            row1Col2Button.setBackground(Color.white);
            row2Col2Button.setBackground(Color.white);
            row3Col2Button.setBackground(Color.white);
            return true;
        }
        if (buttonStates[2] == buttonStates[5] && buttonStates[5] == buttonStates[8] && buttonStates[8] == buttonStates[2]&& buttonStates[2] > 0 && buttonStates[5] > 0 && buttonStates[8] > 0) {
            row1Col3Button.setBackground(Color.white);
            row2Col3Button.setBackground(Color.white);
            row3Col3Button.setBackground(Color.white);
            win = true;
        }
        
        //check win diagonally
        if (buttonStates[0] == buttonStates[4] && buttonStates[4] == buttonStates[8] && buttonStates[8] == buttonStates[0] && buttonStates[0] > 0 && buttonStates[4] > 0 && buttonStates[8] > 0) {
            row1Col1Button.setBackground(Color.white);
            row2Col2Button.setBackground(Color.white);
            row3Col3Button.setBackground(Color.white);
            return true;
        }
        if (buttonStates[2] == buttonStates[4] && buttonStates[4] == buttonStates[6] && buttonStates[6] == buttonStates[2] && buttonStates[2] > 0 && buttonStates[4] > 0 && buttonStates[6] > 0) {
            row1Col3Button.setBackground(Color.white);
            row2Col2Button.setBackground(Color.white);
            row3Col1Button.setBackground(Color.white);
            return true;
        }
        
        return win;
    }
    
    /**
            Method to allow to play again, the score is
            kept and the game continues.
    */
    public void repeat() {
        //repeatable
        int choice = JOptionPane.showOptionDialog(null, "Would you like to play again?", "Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);      
        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
            startGame();
        }
        else { //any option other than yes then you want to quit the game
            System.exit(0);
        }
    }
    
    /**
        Method for the action of the clicked button, determined the scores.
    */
    private class XOActionListener implements ActionListener  { 
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonClicked = (JButton)e.getSource();
            
            if (!buttonClicked.getText().equals("")) {
                return;
            }
            if (xTurn) {
                buttonClicked.setText("X");
                buttonClicked.setForeground(Color.magenta);
            }
            else {
               buttonClicked.setText("O");
               buttonClicked.setForeground(Color.blue);
            }
       
            int index = 0;
            if (buttonClicked.equals(row1Col1Button)) {
                index = 0;
                buttonsClicked += 1;
            }
            else if (buttonClicked.equals(row1Col2Button)) {
                index = 1;
                buttonsClicked += 1;
            }
            else if (buttonClicked.equals(row1Col3Button)) {
                index = 2;
                buttonsClicked += 1;
            }
            else if (buttonClicked.equals(row2Col1Button)) {
                index = 3;
                buttonsClicked += 1;
            }
            else if (buttonClicked.equals(row2Col2Button)) {
                index = 4;
                buttonsClicked += 1;
            }
            else if (buttonClicked.equals(row2Col3Button)) {
                index = 5;
                buttonsClicked += 1;
            }
            else if (buttonClicked.equals(row3Col1Button)) {
                index = 6;
                buttonsClicked += 1;
            }
            else if (buttonClicked.equals(row3Col2Button)) {
                index = 7;
                buttonsClicked += 1;
            }
            else if (buttonClicked.equals(row3Col3Button)) {
                index = 8;
                buttonsClicked += 1;
            }
         
            if (xTurn) {
               buttonStates[index] = 1;
            }
            else {
               buttonStates[index] = 2;
            }
            
            boolean win = isWin(); 
            if (win) {
                String winString = "Congrats, Winner! ";
                if (xTurn) {
                    winString += "X";
                    countX += 1;
                    playerX.setScore(countX);
                    xScore.setText("Player " + playerX.getName() + " : " + playerX.getScore());
                }
                else {
                    winString += "O";
                    countO += 1;
                    playerO.setScore(countO);
                    oScore.setText("Player " + playerO.getName() + " : " + playerO.getScore());
                }
                JOptionPane.showMessageDialog(null, winString, "You Won!", JOptionPane.INFORMATION_MESSAGE);
            }
            xTurn = !xTurn; //turn triggered
            
            if (!win){
                if (buttonsClicked == 9) {
                    String tieString = "X and O tied!";
                    JOptionPane.showMessageDialog(null, tieString, "Tied!", JOptionPane.INFORMATION_MESSAGE);
                    repeat();
                }
                else { 
                    if (xTurn) {
                        playersTurnLabel.setText("X goes now.");
                    }
                    else {
                        playersTurnLabel.setText("O goes now.");
                    }
                }
               
            }
            else {
                repeat();
            }
        }
    }
}
    
    
