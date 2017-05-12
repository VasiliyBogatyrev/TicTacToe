package TicTacToe;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static ru.vasileck.MyWindow.numberOfTurnsToWin;

public class SelectPlayers extends JPanel {
    private JRadioButton playerVsPlayer = new JRadioButton("Игрок против игрока");
    private JRadioButton playerVsAi = new JRadioButton("Игрок против компьютера");
    private JButton start = new JButton("Start");
    private Font textFont = new Font("Arial", Font.PLAIN, 16);

    public SelectPlayers() {
        setLayout(null);
        ButtonGroup bg = new ButtonGroup();
        Box boxSelect = Box.createVerticalBox();
        bg.add(playerVsPlayer);
        bg.add(playerVsAi); // сделали кнопки взаимоисключающими
        boxSelect.add(playerVsPlayer);
        boxSelect.add(playerVsAi); // добавили кнопки на панель
        playerVsAi.setSelected(true);
        boxSelect.setBorder(new TitledBorder("Выберите игроков"));
        add(boxSelect);
        boxSelect.setBounds(0, 0, 200, 100);

        Box boxName = Box.createVerticalBox();
        boxName.setBorder(new TitledBorder("Введите имена игроков"));
        JTextField[] player = new JTextField[2];
        for (int i = 0; i < 2; i++) {
            player[i] = new JTextField();
            player[i].setFont(textFont);
            player[i].setSize(1, 30);
            boxName.add(player[i]);
        }
        add(boxName);
        player[1].setEnabled(false);
        player[0].setText("Игрок1");
        player[1].setText("Компьютер");
        boxName.setBounds(200, 0, 200, 100);
        player[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (playerVsAi.isSelected()) {
                    player[1].setEnabled(false);
                } else {
                    player[1].setEnabled(true);
                }
            }
        });
        Box boxSize = Box.createVerticalBox();
        boxSize.setBorder(new TitledBorder("Выберите размер поля и победную серию"));
        Box boxSizeMap = Box.createVerticalBox();
        boxSizeMap.setBorder(new TitledBorder("Укажите размер  карты N х N"));
        JSlider sizeMap = new JSlider(JSlider.HORIZONTAL, 3, 15, 5);
        sizeMap.setMajorTickSpacing(5);
        sizeMap.setMinorTickSpacing(1);
        sizeMap.setPaintTicks(true);
        sizeMap.setPaintLabels(true);
        sizeMap.setSnapToTicks(true);
        Box boxSizeWin = Box.createVerticalBox();
        boxSizeWin.setBorder(new TitledBorder("Количество победной серии"));

        JSlider sizeWin = new JSlider(JSlider.HORIZONTAL, 3, 15, 3);
        sizeWin.setMajorTickSpacing(5);
        sizeWin.setMinorTickSpacing(1);
        sizeWin.setPaintTicks(true);
        sizeWin.setPaintLabels(true);
        sizeWin.setSnapToTicks(true);
        boxSizeMap.add(sizeMap);
        boxSizeWin.add(sizeWin);
        boxSize.add(boxSizeMap);
        boxSize.add(boxSizeWin);
        add(boxSize);
        boxSize.setBounds(0, 100, 400, 300);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel map = new Map(sizeMap.getValue(), sizeWin.getValue(),playerVsPlayer.isSelected(),player[0].getText(),player[1].getText());//Создаем поле
                MyWindow.jpPlace.add(map, "Map");//Добавляем поле в окно
                if (sizeWin.getValue() > sizeMap.getValue()) {
                      sizeWin.setValue(sizeMap.getValue());
                    }
                ((CardLayout) MyWindow.jpPlace.getLayout()).show(MyWindow.jpPlace, "Map");
                numberOfTurnsToWin.setAlignmentX(0.0f);
                numberOfTurnsToWin.setText("Победная серия"+ "\n" + String.valueOf(sizeWin.getValue())) ;
            }

        });
        add(start);
        start.setBounds(160, 330, 80, 40);
    }
}