package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static TicTacToe.MyWindow.jpPlace;

public class Map extends JPanel {
    private int SIZE;
    private String SYMB_X = "X";
    private String SYMB_0 = "O";
    private String curPlayer;
    private String curDot = SYMB_0;
    private String SYMB_EMPTY = " ";
    private int DOTS_TO_WIN;
    private JButton[][] map; //масссив кнопок
    private boolean playerVsPlayer; //Чтобы узнать какой режим игры, для реализации поля, пришлось сюда и в конструктор внести это поле
    private Player player1;
    private Player player2;
    private Random random = new Random();


    public Map (int SIZE, int DOTS_TO_WIN, boolean playerVsPlayer, String namePlayer1, String namePlayer2) {
        this.SIZE = SIZE;
        this.DOTS_TO_WIN = DOTS_TO_WIN;
        this.playerVsPlayer = playerVsPlayer;
        player1 = new Player(SYMB_X,namePlayer1);
        player1.event();
        if (playerVsPlayer) {
            player2 = new Player(SYMB_0,namePlayer2);
        }else {
           player2 = null;
        }
        setLayout(new GridLayout(SIZE, SIZE));
        map = new JButton[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = new JButton(String.valueOf(SYMB_EMPTY));
                map[i][j].setFont(new Font("Atial",Font.PLAIN,((int)(400/SIZE*0.9))));
                map[i][j].setMargin(new Insets (1,1,1,1));
                add(map[i][j]);
                int x = i, y = j;
                map[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setXO(x, y);
                        if (isMapFull()) {
                            Final fin = new Final("Ничья");
                            jpPlace.add(fin,"Final");
                            ((CardLayout) jpPlace.getLayout()).show(jpPlace, "Final");
                        }
                    }
                });
            }
        }
            }

    public void setXO(int x, int y) {//Установка символа в кетку
        if (!map[x][y].getText().equals(SYMB_EMPTY)) {
            return;
        }
        if (playerVsPlayer) {
            if (player1.isStatus()) {
                curDot = player1.turn();
                player2.event();
                curPlayer = player1.getName();
            } else {
                curDot = player2.turn();
                player1.event();
                curPlayer = player2.getName();
            }
            map[x][y].setText(curDot);
            if (checkWin(curDot)) {
                Final fin = new Final("Победил игрок: " + curPlayer);
                jpPlace.add(fin, "Final");
                ((CardLayout) jpPlace.getLayout()).show(jpPlace, "Final");
            }
        } else {
            map[x][y].setText(player1.turn());
            aiTurn();
            if (checkWin(SYMB_X)) {
                Final fin = new Final("Победа!");
                jpPlace.add(fin, "Final");
                ((CardLayout) jpPlace.getLayout()).show(jpPlace, "Final");
            }
        }
    }
    public  void aiTurn() {
        int x = -1, y = -1;
        if(isMapFull()) return;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i,j)) {
                    map[i][j].setText(SYMB_0);
                    if (checkWin(SYMB_0)) {
                        x = i;
                        y = j;
                    }
                    map[i][j].setText(SYMB_EMPTY);
                }
            }
        }
        if (x == -1 && y == -1) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (isCellValid(i, j)) {
                        map[i][j].setText(SYMB_X);
                        if (checkWin(SYMB_X)) {
                            x = i;
                            y = j;
                        }
                        map[i][j].setText(SYMB_EMPTY);
                    }
                }
            }
        }
        if (x == -1 && y == -1) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(x, y));
        }
        map[x][y].setText(SYMB_0);
        if (checkWin(SYMB_0)) {
            Final fin = new Final("Поражение!");
            jpPlace.add(fin, "Final");
            ((CardLayout) jpPlace.getLayout()).show(jpPlace, "Final");
        }
    }
    public boolean isCellValid(int i, int j) {//Проверка на наличие пустой клетки
        if (map[i][j].getText().equals(SYMB_EMPTY)) return true;
        return false;
    }
  public boolean isMapFull() {//Проверка на заполненнось карты
      for (int i = 0; i < SIZE; i++) {
          for (int j = 0; j < SIZE; j++) {
                if (map[i][j].getText().equals(SYMB_EMPTY))
                    return false;
            }
        }
        return true;
    }
    public boolean checkWin(String symb) {//Проверка на победу
        int counter = 0;
        for (int i = 0; i < SIZE; i++) {//Горизонталь
            counter = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j].getText().equals(symb)) {
                    counter++;
                    if (counter == DOTS_TO_WIN) return true;
                } else {
                    counter = 0;
                }
            }
        }
        for (int i = 0; i < SIZE; i++) {//Вертикаль
            counter = 0 ;
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i].getText().equals(symb)) {
                    counter++;
                    if (counter == DOTS_TO_WIN) return true;
                } else {
                    counter = 0;
                }
            }
        }
        for (int i = -(SIZE - 1); i < SIZE; i++) {//Диагональ_1
            counter = 0;
            for (int j = 0; j < SIZE; j++) {
                if (Math.abs(i) + j >= SIZE) break;
                if (i > 0) {
                    if (map[j][Math.abs(i) + j].getText().equals(symb)) {
                        counter++;
                        if (counter == DOTS_TO_WIN) return true;
                    } else {
                        counter = 0;
                    }
                } else {
                    if (map[Math.abs(i) + j][j].getText().equals(symb)) {
                        counter++;
                        if (counter == DOTS_TO_WIN) return true;
                    } else {
                        counter = 0;
                    }
                               }

            }
        }
        for (int i = -(SIZE - 1); i < SIZE; i++) {//Диагональ_2
            counter = 0;
            for (int j = 0; j < SIZE; j++) {
                if (Math.abs(i) + j >= SIZE && SIZE - 1 - j >= 0) break;
                if (i <= 0) {
                    if (map[j + Math.abs(i)][SIZE - 1 - j].getText().equals(symb)) {
                        counter++;
                        if (counter == DOTS_TO_WIN) return true;
                    } else {
                        counter = 0;
                    }
                } else {
                    if (map[j][SIZE - 1 - i - j].getText().equals(symb)) {
                        counter++;
                        if (counter == DOTS_TO_WIN) return true;
                    } else {
                        counter = 0;
                    }
                }
            }
        }
        return false;
    }
}
