package TicTacToe;


import static TicTacToe.MyWindow.event;

public class Player {
    private String SYMB;
    private String name;
    private boolean status;

    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Player(String SYMB, String name) {
        this.SYMB = SYMB;
        this.name = name;
        if (SYMB.equals("X")) {
            status = true;
        } else {
            status = false;
        }
    }

    public String turn() {
        event.setText("Ходит игрок " + name);
        status = false;
        return SYMB;
    }

    public void event() {
        event.setText("Ходит игрок " + name);
        status = true;
    }
}
