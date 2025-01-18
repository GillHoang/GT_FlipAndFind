package src;


import javax.swing.*;
import java.awt.*;
import java.util.*;

public class LoginPanel extends JPanel {
	private static final int LEAF_COUNT = 1; // Số lượng lá
	private final Leaf[] leaves = new Leaf[LEAF_COUNT];
	private Leaf leaf;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private String _username = "username";
	private String _password = "password";

	public LoginPanel(CardLayout cardLayout, JPanel cardPanel) {
		this.setLayout(null);
		this.cardLayout = cardLayout;
		this.mainPanel = cardPanel;

		// Tiêu đề
		JLabel titleLabel = new JLabel("Sign In", JLabel.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(500, 170, 200, 150);
		this.add(titleLabel);

		// Trường nhập Username
		JTextField usernameField = new JTextField("Username");
		usernameField.setFont(new Font("Arial", Font.PLAIN, 20));
		usernameField.setBounds(420, 300, 350, 50);
		usernameField.setBorder(BorderFactory.createEmptyBorder());
		usernameField.setBackground(new Color(224, 247, 250));
		usernameField.setForeground(Color.GRAY);
		this.add(usernameField);

		// Xử lý focus cho trường Username
		usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (usernameField.getText().equals("Username")) {
					usernameField.setText("");
					usernameField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (usernameField.getText().isEmpty()) {
					usernameField.setText("Username");
					usernameField.setForeground(Color.GRAY);
				}
			}
		});

		// Trường nhập Password
		JPasswordField passwordField = new JPasswordField("Password");
		passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
		passwordField.setBounds(420, 380, 350, 50);
		passwordField.setBorder(BorderFactory.createEmptyBorder());
		passwordField.setBackground(new Color(224, 247, 250));
		passwordField.setForeground(Color.GRAY);
		passwordField.setEchoChar((char) 0);
		this.add(passwordField);

		// Xử lý focus cho trường Password
		passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (String.valueOf(passwordField.getPassword()).equals("Password")) {
					passwordField.setText("");
					passwordField.setForeground(Color.BLACK);
					passwordField.setEchoChar('•');
				}
			}

			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (String.valueOf(passwordField.getPassword()).isEmpty()) {
					passwordField.setText("Password");
					passwordField.setForeground(Color.GRAY);
					passwordField.setEchoChar((char) 0);
				}
			}
		});

		// Nút Login
		JButton loginButton = new JButton("LOGIN");
		loginButton.setFont(new Font("Arial", Font.BOLD, 24));
		loginButton.setBounds(420, 480, 350, 60);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(new Color(30, 120, 180));
		loginButton.setBorder(BorderFactory.createEmptyBorder());
		this.add(loginButton);

		// Thông báo lỗi
		JLabel errorLabel = new JLabel("", JLabel.CENTER);
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(450, 440, 300, 50);
		this.add(errorLabel);

		// Xử lý sự kiện khi nhấn nút Login
		loginButton.addActionListener(e -> {
			String username = usernameField.getText();
			String password = String.valueOf(passwordField.getPassword());

			if (username.equals("Username") || username.isEmpty()) {
				errorLabel.setText("Bạn chưa nhập tên!");
			} else if (password.equals("Password") || password.isEmpty()) {
				errorLabel.setText("Bạn chưa nhập mật khẩu!");
			} else if (username.equals(_username) && password.equals(_password)) {
				cardPanel.add(new GamePanel(), "game");
				cardLayout.show(cardPanel, "game");
			} else {
				errorLabel.setText("Sai tên tài khoản hoặc mật khẩu");
			}
		});

		//khởi tạo lá
		Random random = new Random();
		for (int i = 0; i < LEAF_COUNT; i++) {
			leaves[i] = new Leaf((int) (Math.random() * 800), (int) (Math.random() * 600), 1 + (int) (Math.random() * 3));
		}

		// Cập nhật vị trí của lá trong một luồng riêng
		Thread leafUpdater = new Thread(() -> {
			while (true) {
				for (Leaf leaf : leaves) {
					leaf.y += leaf.speed * 0.1;// slowmotion
					leaf.x += Math.sin(leaf.y / 100.0) * 1; //lắc nhẹ theo trục x
					if (leaf.y > getHeight()) {
						leaf.y = -20; // reset vị trí nếu rơi xuống
						leaf.x = (int) (Math.random() * getWidth());
					}
				}
				// Cập nhật giao diện
				SwingUtilities.invokeLater(this::repaint);

				try {
					Thread.sleep(1500); // Cập nhật mỗi 50ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		leafUpdater.setDaemon(true); // Để chương trình dừng khi thoát
		leafUpdater.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Vẽ nền gradient
		GradientPaint gradient = new GradientPaint(0, 0, new Color(33, 176, 130), getWidth(), getHeight(), new Color(30, 120, 180));
		g2d.setPaint(gradient);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		// Vẽ họa tiết hình tròn mờ
		g2d.setColor(new Color(255, 255, 255, 50));
		for (int i = 0; i < 15; i++) {
			int x = (int) (Math.random() * getWidth());
			int y = (int) (Math.random() * getHeight());
			int radius = 50 + (int) (Math.random() * 100);
			g2d.fillOval(x, y, radius, radius);
		}

		// Vẽ họa tiết hình tròn đậm hơn
		g2d.setColor(new Color(255, 255, 255, 150));
		for (int i = 0; i < 10; i++) {
			int x = (int) (Math.random() * getWidth());
			int y = (int) (Math.random() * getHeight());
			int radius = 30 + (int) (Math.random() * 60);
			g2d.fillOval(x, y, radius, radius);
		}

		// Vẽ họa tiết hình lục giác
		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;
		int radius = 320;

		Polygon hexagon = new Polygon();
		for (int i = 0; i < 6; i++) {
			int x = (int) (centerX + radius * Math.cos(i * Math.PI / 3));
			int y = (int) (centerY + radius * Math.sin(i * Math.PI / 3));
			hexagon.addPoint(x, y);
		}

		// Vẽ đường viền lục giác
		g2d.setColor(new Color(255, 255, 255, 150));
		g2d.setStroke(new BasicStroke(4));
		g2d.drawPolygon(hexagon);

		// Tô màu lục giác
		g2d.setColor(new Color(255, 255, 255, 50));
		g2d.fillPolygon(hexagon);
	}
}


// Lớp đại diện cho một chiếc lá
class Leaf {
	int x, y, speed;

	public Leaf(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
}
