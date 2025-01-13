import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        super("Flip and Find");

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
