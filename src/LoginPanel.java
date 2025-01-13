
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class LoginPanel extends JPanel {
    public LoginPanel() {
        this.setLayout(null);

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
                errorLabel.setText("biết nhập tên không");
            } else if (password.equals("Password") || password.isEmpty()) {
                errorLabel.setText("biết nhập mật khẩu không");
            } else if (username.equals("tao thích") && password.equals("cocainit")) {
                JOptionPane.showMessageDialog(this, "giỏi");
            } else {
                errorLabel.setText("biết mật khẩu không mà nhập.");
            }
        });
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
