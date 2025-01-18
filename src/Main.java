package src;

import java.awt.CardLayout;
import javax.swing.*;

public class Main extends JFrame {
	private JPanel cardPanel;
	private CardLayout cardLayout;

	public Main() {
		super("Flip and Find");

		cardPanel = new JPanel();
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);

		cardPanel.add(new LoginPanel(cardLayout, cardPanel), "login");

		this.add(cardPanel);

		this.setSize(1200, 700);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}
}
