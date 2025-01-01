import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class Game extends JFrame {
    // set điểm, số lần thử, số lượt chơi, thời gian, chế độ chơi
    JLabel point, atem, pair, time, level;
    // set Menu cài đặt game
    JButton menu;

    public Game() {

        new JFrame("Flip And Find");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        point = ThongSo("Điểm", 0, 0, 200, 150);
        level = ThongSo("Easy", 220, 0, 200, 80);
        atem = ThongSo("Lần thử: ", 450, 0, 200, 60);
        pair = ThongSo("Số lượt: ", 680, 0, 200, 60);
        time = ThongSo("Time", 1130, 0, 160, 150);

        menu = new JButton("Cài đặt");
        menu.setBounds(910, 0, 200, 80);
        menu.setFont(new Font("Digital-7", Font.BOLD, 25));
        menu.setBorder(new LineBorder(Color.decode("#dddddd"), 1, true));
        menu.setHorizontalAlignment(SwingConstants.CENTER);

        add(point);
        add(atem);
        add(pair);
        add(level);
        add(time);
        add(menu);

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
        setup.setFont(new Font("Digital-7", Font.BOLD, 25));
        setup.setBorder(new LineBorder(Color.decode("#dddddd"), 1, true));
        setup.setFocusable(false);
        return setup;
    }

    public static void main(String[] args) {
        new Game();
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

        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                String selectedLevel = (String) LevelSetBox.getSelectedItem();
                JOptionPane.showMessageDialog(setting,
                        "Cài đặt đã lưu:\nĐộ khó: " + selectedDifficulty + "\nÂm lượng: " + selectedLevel,
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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