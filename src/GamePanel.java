import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class GamePanel extends JPanel {
	JLabel background;
	JLabel point, atem, pair, time, level;
	JButton menu;
	Image originalImage;
	String gameMode = "Easy";
	String allGameMode[] = { "Easy", "Medium", "Hard" };
	ArrayList<JButton> cards;
	Timer timer1, timer3;
	JButton selected;
	int[][] amountImagePerLevel = { { 3, 4 } };
	int[][] result;
	int x, y;
	int attemptsLeft;
	int numPairsLeft;
	int numPoint = 1000;
	int timeLeft = 60;
	int timeToHide = 1000;
	int timePerRefesh = 1000;
	int pointAdd = 50;
	int pointSub = 40;

	public GamePanel() {
		setVisible(true);
		setLayout(null);
		try {

			ImageIcon backgroundIcon = new ImageIcon("src/FlipAndFind/castle-4354 (2).gif"); // Thay đường dẫn
			originalImage = backgroundIcon.getImage();
			background = new JLabel(backgroundIcon);
		} catch (Exception e) {
			background = new JLabel("Error loading background image");
			e.printStackTrace();
		}
		background.setBounds(0, 0, 10, 10);
		add(background);

		int getIOfGameMode = indexOf(gameMode, allGameMode);
		int[] amountOfLevel = amountImagePerLevel[getIOfGameMode];
		this.x = amountOfLevel[0];
		this.y = amountOfLevel[1];
		attemptsLeft = x * y + 10;
		numPairsLeft = (x * y) / 2;
		result = generateRandom2DArray();

		point = ThongSo("Điểm", 0, 0, 174, 171);
		level = ThongSo("Easy", 174, 0, 214, 98);
		atem = ThongSo("Lần thử: ", 388, 0, 214, 71);
		pair = ThongSo("Số lượt: ", 602, 0, 214, 71);
		time = ThongSo("Time", 1030, 0, 170, 171);

		menu = new JButton("Cài đặt");
		menu.setBounds(816, 0, 214, 98);
		menu.setFont(new Font("Pixel", Font.BOLD, 25));
		menu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#dddddd"), 1),
				BorderFactory.createLineBorder(Color.white, 2)));
		menu.setBorder(BorderFactory.createRaisedBevelBorder());
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		menu.setForeground(Color.BLACK);
		menu.setBackground(Color.decode("#F4A460"));

		cards = createCard();
		for (JButton item : cards) {
			background.add(item);
		}

		timer1 = new Timer(timeToHide, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected != null) {
					selected.setIcon(new ImageIcon(getClass().getResource("/Images/question.png")));
					selected = null;
				}
				for (JButton btn : cards) {
					if (btn.getIcon() != null && !btn.getIcon().toString().contains("question")) {
						btn.setIcon(new ImageIcon(getClass().getResource("/Images/question.png")));
					}
				}
				timer1.stop();
			}
		});

		timer3 = new Timer(timePerRefesh, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeLeft--;
				if (timeLeft >= 0) {
					time.setText("Time Left: " + timeLeft);
				} else {
					for (JButton card : cards) {
						card.setEnabled(false);
					}
					timer3.stop();
					time.setText("End of time!");
				}
			}
		});
		timer3.start();

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

	public static <T> int indexOf(T needle, T[] haystack) {
		for (int i = 0; i < haystack.length; i++) {
			if (haystack[i] != null && haystack[i].equals(needle) || needle == null && haystack[i] == null)
				return i;
		}

		return -1;
	}

	public int[][] generateRandom2DArray() {
		// Step 1: Create a list of numbers 1 to 6, each appearing twice
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int i = 1; i <= (this.x * this.y) / 2; i++) {
			numbers.add(i);
			numbers.add(i);
		}
		Collections.shuffle(numbers);
		int[][] result = new int[this.x][this.y];
		int index = 0;
		for (int i = 0; i < this.x; i++) {
			for (int j = 0; j < this.y; j++) {
				result[i][j] = numbers.get(index++);
			}
		}

		return result;
	}

	public ArrayList<JButton> createCard() {
		ArrayList<JButton> cards = new ArrayList<JButton>();
		for (int i = 0; i < this.x * this.y; i++) {
			int x = i / this.y;
			int y = i % this.y;
			String k = String.valueOf(result[x][y]);
			JButton card = new JButton(k);

			// Refactor:
			card.setBounds(51 + y * (99) + 200 * y, 249 + x * 44 + 100 * x, 200, 100);
			card.addActionListener(btnAction());
			card.setIcon(new ImageIcon(getClass().getResource("/Images/question.png")));
			cards.add(card);
		}
		return cards;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (background != null) {
			background.setBounds(0, 0, 1195, 750);
		}
	}

	private void GameSettings() {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		JDialog setting = new JDialog(frame, "Cài đặt", true);
		setting.setSize(400, 300);
		setting.setLayout(null);

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

	public ActionListener btnAction() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timer1.isRunning())
					return;
				JButton current = (JButton) e.getSource();
				if (!current.getIcon().toString().contains("question"))
					return;
				if (attemptsLeft == 0) {
					selected = null;
					for (JButton card : cards) {
						card.setEnabled(false);
					}
					timer3.stop();
					atem.setText("May thua roaiiiiii");
					return;
				}
				if (selected == null) {
					selected = current;
					current.setIcon(
							new ImageIcon(getClass().getResource("/Images/" + current.getText().trim() + ".png")));
				} else {
					attemptsLeft -= 1;
					atem.setText("Attempts: " + attemptsLeft);
					if (selected.getText().trim().equalsIgnoreCase(current.getText().trim())) {
						numPoint += pointAdd;
						numPairsLeft -= 1;
						pair.setText("Pairs left: " + numPairsLeft);
						point.setText("Point: " + numPoint);

						current.setIcon(
								new ImageIcon(getClass().getResource("/Images/" + current.getText().trim() + ".png")));
						current.setEnabled(false);
						selected.setEnabled(false);
						selected = null;
						if (numPairsLeft == 0) {
							timer3.stop();
							pair.setText("May thang roaiiiiii");
						}
					} else {
						numPoint -= pointSub;
						point.setText("Point: " + numPoint);
						current.setIcon(
								new ImageIcon(getClass().getResource("/Images/" + current.getText().trim() + ".png")));
						timer1.start();
					}
				}
			}
		};
	}

}
