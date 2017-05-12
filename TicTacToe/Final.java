package TicTacToe;

import javax.swing.*;
import java.awt.*;

public class Final extends JPanel {
    public Final(String str) {
        setLayout(null);
        JTextField event = new JTextField();
        event.setText(str);
        event.setEnabled(false);
        event.setFont(new Font("Arial",Font.CENTER_BASELINE,24));
        event.setHorizontalAlignment(0);
        event.setBounds(0, 100, 390, 60);
        add(event);
    }
}
