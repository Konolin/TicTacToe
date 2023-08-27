import java.awt.*;
import java.awt.event.*;
import java.security.cert.CertPath;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame("TicTacToe");
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1Turn;

    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(60, 60, 60));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(45, 45, 45));
        textField.setForeground(new Color(210, 210, 210));
        textField.setFont(new Font("Futura", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Futura", Font.BOLD, 100));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setBackground(new Color(70, 70, 70));
            buttons[i].setForeground(new Color(0, 0, 0));
            buttons[i].setEnabled(false);
        }

        titlePanel.add(textField);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (buttons[i].getText().isEmpty()) {
                    if (player1Turn) {
                        buttons[i].setText("X");
                        textField.setText("O turn");
                    } else {
                        buttons[i].setText("O");
                        textField.setText("X turn");
                    }
                    player1Turn = !player1Turn;
                    check();
                }
            }
        }
    }

    public void firstTurn() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(true);
        }

        if (random.nextInt(2) == 0) {
            player1Turn = true;
            textField.setText("X turn");
        } else {
            player1Turn = false;
            textField.setText("O turn");
        }
    }

    public void check() {
        // x wins
        if (Objects.equals(buttons[0].getText(), "X") && Objects.equals(buttons[1].getText(), "X") &&
                Objects.equals(buttons[2].getText(), "X")) {
            xWins(0, 1, 2);
        }

        if (Objects.equals(buttons[3].getText(), "X") && Objects.equals(buttons[4].getText(), "X") &&
                Objects.equals(buttons[5].getText(), "X")) {
            xWins(3, 4, 5);
        }

        if (Objects.equals(buttons[6].getText(), "X") && Objects.equals(buttons[7].getText(), "X") &&
                Objects.equals(buttons[8].getText(), "X")) {
            xWins(6, 7, 8);
        }

        if (Objects.equals(buttons[0].getText(), "X") && Objects.equals(buttons[3].getText(), "X") &&
                Objects.equals(buttons[6].getText(), "X")) {
            xWins(0, 3, 6);
        }

        if (Objects.equals(buttons[1].getText(), "X") && Objects.equals(buttons[4].getText(), "X") &&
                Objects.equals(buttons[7].getText(), "X")) {
            xWins(1, 4, 7);
        }

        if (Objects.equals(buttons[2].getText(), "X") && Objects.equals(buttons[5].getText(), "X") &&
                Objects.equals(buttons[8].getText(), "X")) {
            xWins(2, 5, 8);
        }

        if (Objects.equals(buttons[0].getText(), "X") && Objects.equals(buttons[4].getText(), "X") &&
                Objects.equals(buttons[8].getText(), "X")) {
            xWins(0, 4, 8);
        }

        if (Objects.equals(buttons[2].getText(), "X") && Objects.equals(buttons[4].getText(), "X") &&
                Objects.equals(buttons[6].getText(), "X")) {
            xWins(2, 4, 6);
        }

        // o wins
        if (Objects.equals(buttons[0].getText(), "O") && Objects.equals(buttons[1].getText(), "O") &&
                Objects.equals(buttons[2].getText(), "O")) {
            oWins(0, 1, 2);
        }

        if (Objects.equals(buttons[3].getText(), "O") && Objects.equals(buttons[4].getText(), "O") &&
                Objects.equals(buttons[5].getText(), "O")) {
            oWins(3, 4, 5);
        }

        if (Objects.equals(buttons[6].getText(), "O") && Objects.equals(buttons[7].getText(), "O") &&
                Objects.equals(buttons[8].getText(), "O")) {
            oWins(6, 7, 8);
        }

        if (Objects.equals(buttons[0].getText(), "O") && Objects.equals(buttons[3].getText(), "O") &&
                Objects.equals(buttons[6].getText(), "O")) {
            oWins(0, 3, 6);
        }

        if (Objects.equals(buttons[1].getText(), "O") && Objects.equals(buttons[4].getText(), "O") &&
                Objects.equals(buttons[7].getText(), "O")) {
            oWins(1, 4, 7);
        }

        if (Objects.equals(buttons[2].getText(), "O") && Objects.equals(buttons[5].getText(), "O") &&
                Objects.equals(buttons[8].getText(), "O")) {
            oWins(2, 5, 8);
        }

        if (Objects.equals(buttons[0].getText(), "O") && Objects.equals(buttons[4].getText(), "O") &&
                Objects.equals(buttons[8].getText(), "O")) {
            oWins(0, 4, 8);
        }

        if (Objects.equals(buttons[2].getText(), "O") && Objects.equals(buttons[4].getText(), "O") &&
                Objects.equals(buttons[6].getText(), "O")) {
            oWins(2, 4, 6);
        }
    }

    public void xWins(int a, int b, int c) {
        wins(a, b, c);
        textField.setText("X wins");
    }

    public void oWins(int a, int b, int c) {
        wins(a, b, c);
        textField.setText("O wins");
    }

    public void wins (int a, int b, int c) {
        buttons[a].setBackground(new Color(190, 190, 190));
        buttons[b].setBackground(new Color(190, 190, 190));
        buttons[c].setBackground(new Color(190, 190, 190));

        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
    }
}
