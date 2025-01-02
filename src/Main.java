package FlipAndFind;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Game extends JFrame {
    JLabel background;
    // set điểm, số lần thử, số lượt chơi, thời gian, chế độ chơi
    JLabel point, atem, pair, time, level;
    // set Menu cài đặt game
    JButton menu;
    Image originalImage;

    public Game() {

        new JFrame("Flip And Find");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        try {

            ImageIcon backgroundIcon = new ImageIcon("src/castle-4354 (2).gif"); // Thay đường dẫn
            originalImage = backgroundIcon.getImage();
            background = new JLabel(backgroundIcon);
        } catch (Exception e) {
            background = new JLabel("Error loading background image");
            e.printStackTrace();
        }
        background.setBounds(0, 0, 10, 10);
        add(background);

        point = ThongSo("Điểm", 0, 0, 200, 150);
        level = ThongSo("Easy", 220, 0, 200, 80);
        atem = ThongSo("Lần thử: ", 450, 0, 200, 60);
        pair = ThongSo("Số lượt: ", 680, 0, 200, 60);
        time = ThongSo("Time", 1130, 0, 160, 150);

        menu = new JButton("Cài đặt");
        menu.setBounds(910, 0, 200, 80);
        menu.setFont(new Font("Pixel", Font.BOLD, 25));
        menu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#dddddd"), 1),
                BorderFactory.createLineBorder(Color.white, 2)));
        menu.setBorder(BorderFactory.createRaisedBevelBorder());
        menu.setHorizontalAlignment(SwingConstants.CENTER);
        menu.setForeground(Color.BLACK);
        menu.setBackground(Color.decode("#F4A460"));

        background.add(point);
        background.add(atem);
        background.add(pair);
        background.add(level);
        background.add(time);
        background.add(menu);

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameSettings();
            }
        });

    }

    private JLabel ThongSo(String string, int i, int j, int k, int l) {
        JLabel setup = new JLabel(string);
        setup.setHorizontalAlignment(SwingConstants.CENTER);
        setup.setBounds(i, j, k, l);
        setup.setFont(new Font("Pixel", Font.BOLD, 25));
        setup.setBorder(BorderFactory.createLineBorder(Color.decode("#dddddd"), 1));
        setup.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        setup.setBorder(BorderFactory.createRaisedBevelBorder());
        setup.setFocusable(false);
        setup.setForeground(Color.BLACK);
        setup.setOpaque(true);
        setup.setBackground(Color.decode("#ADD8E6"));
        return setup;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (background != null) {
            background.setBounds(0, 0, 1290, 750);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }

    private void GameSettings() {
        JDialog setting = new JDialog(this, "Cài đặt", true);
        setting.setSize(400, 300);
        setting.setLayout(null);

        // Tạo các thành phần cài đặt
        JLabel difficultyLabel = new JLabel("Độ khó:");
        difficultyLabel.setBounds(80, 30, 90, 60);
        String[] difficulties = { "Easy", "Medium", "Hard" };
        JComboBox<String> difficultyComboBox = new JComboBox<>(difficulties);
        difficultyComboBox.setBounds(160, 50, 120, 30);

        JLabel Level = new JLabel("Màn:");
        Level.setBounds(80, 120, 90, 60);
        String[] LevelBox = { "1", "2", "3" };
        JComboBox<String> LevelSetBox = new JComboBox<>(LevelBox);
        LevelSetBox.setBounds(160, 141, 120, 30);

        JButton Save = new JButton("Lưu");
        Save.setBounds(160, 210, 80, 40);
        Save.setBackground(Color.BLACK);
        Save.setForeground(Color.white);

        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                String selectedLevel = (String) LevelSetBox.getSelectedItem();
                JOptionPane.showMessageDialog(setting,
                        "Cài đặt đã lưu:\nĐộ khó: " + selectedDifficulty + "\nMàn: " + selectedLevel, "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                setting.dispose();
            }
        });

        setting.add(difficultyLabel);
        setting.add(difficultyComboBox);
        setting.add(Level);
        setting.add(LevelSetBox);
        setting.add(Save);

        setting.setLocationRelativeTo(this);
        setting.setVisible(true);

    }
}