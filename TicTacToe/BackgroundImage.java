package TicTacToe;

import javax.swing.*;

public class BackgroundImage extends JPanel {

    public BackgroundImage() {
        Icon image = new ImageIcon("C:\\Users\\Vasileck\\IdeaProjects\\Lesson_4\\src\\ru\\vasileck\\XO.jpg");
        JLabel label = new  JLabel (image, SwingConstants.CENTER );
        add(label);
    }

}
