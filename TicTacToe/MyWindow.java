package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {
static JPanel jpPlace;
static JTextArea numberOfTurnsToWin;
static JTextField event;

    public MyWindow() {
        Font btnFont = new Font ("Arial", Font.PLAIN,16);
        Font textFont = new Font ("Arial", Font.PLAIN,16);
        setTitle("Крестики Нолики");
        setBounds(600, 200, 400, 480);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jpPlace = new JPanel(new CardLayout());
        add(jpPlace,BorderLayout.CENTER);

        BackgroundImage fon = new BackgroundImage();//Создаем фон
        jpPlace.add(fon,"BackgroundImage");//Добавляем фон в окно

        SelectPlayers jpSelectPlayers = new SelectPlayers();//Создаем поле выбора игроков и поля
        jpPlace.add(jpSelectPlayers,"SelectPlayers");//Добавляем поле в окно

        JPanel boxText = new JPanel();
        boxText.setLayout(null);
        event = new JTextField();
        boxText.setPreferredSize(new Dimension(400,30));
        boxText.add(event);
        event.setEnabled(false);
        event.setFont(textFont);
        event.setBounds(0, 0, 300, 30);
        numberOfTurnsToWin = new JTextArea();
        boxText.add (numberOfTurnsToWin);
        numberOfTurnsToWin.setEnabled(false);
        numberOfTurnsToWin.setFont(new Font ("Arial", Font.PLAIN,12));
        numberOfTurnsToWin.setBounds(300, 0, 100, 60);
        add(boxText, BorderLayout.NORTH);


        JPanel jpStartExit = new JPanel(new GridLayout());
        jpStartExit.setPreferredSize(new Dimension(1,50));
        add(jpStartExit,BorderLayout.SOUTH);

        JButton jbStart = new JButton("Начать новую игру");
        jbStart.setFont(textFont);
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)jpPlace.getLayout()).show(jpPlace,"SelectPlayers");
            }
        });
        jpStartExit.add(jbStart);

        JButton jbExit = new JButton("Выйти из игры");
        jbExit.setFont(textFont);
        jbExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jpStartExit.add(jbExit);
        setVisible(true);
    }


}

