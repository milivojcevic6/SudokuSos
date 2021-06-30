package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class sudoku {

	static JFrame frame;
	JPanel panel;
	JButton start, help, demo, cleanTable;
	static JButton[][] button;
	JLabel label, copyrights;
	static int[][] a;

	public sudoku() {

		frame = new JFrame();
		panel = new JPanel();
		panel.setBackground(new Color(64, 164, 213));
		panel.setLayout(null);

		label = new JLabel("SUDOKU");
		label.setLocation(85 , 70 );
		label.setSize(200, 50);
		label.setFont(new Font("Serif", Font.BOLD, 40));
		panel.add(label);

		JTextField entry = new JTextField("Type a number here");
		entry.setLocation(70, 170);
		entry.setSize(200, 40);
		panel.add(entry);
		entry.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!(Character.isDigit(c))) {
					if (!(c == KeyEvent.VK_DELETE || c == KeyEvent.VK_BACK_SPACE))
						Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		start = new JButton("RUN");
		cleanTable = new JButton("Clean the Table");
		start.setLocation(70, 260);
		cleanTable.setLocation(70, 330);
		start.setSize(200, 40);
		cleanTable.setSize(200, 40);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				solve(a);
				if (emptySpace(a) != 0)
					JOptionPane.showMessageDialog(frame, "This Sudoku doesn't have solution!\nTry different numbers",
							"UNFORTUNATELY", JOptionPane.OK_CANCEL_OPTION);
			}
		});
		cleanTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < 9; i++)
					for (int j = 0; j < 9; j++) {
						a[i][j] = 0;
						button[i][j].setText("");
					}
			}
		});

		panel.add(start);
		panel.add(cleanTable);

		help = new JButton("?");
		demo = new JButton("Demo");
		help.setLocation(70, 400);
		demo.setLocation(190, 400);
		help.setSize(80, 30);
		demo.setSize(80, 30);
		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(frame, "Firstly input numbers and then press RUN for the slovution",
						"HOW TO PLAY", JOptionPane.QUESTION_MESSAGE);
			}
		});
		demo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int[][] b = { { 6, 0, 8, 1, 2, 3, 0, 7, 9 }, { 0, 0, 3, 0, 8, 0, 0, 6, 2 },
						{ 2, 9, 1, 0, 4, 0, 5, 3, 0 }, { 0, 0, 0, 0, 3, 0, 2, 0, 0 }, { 0, 0, 4, 9, 5, 1, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 9, 1, 3 }, { 9, 0, 0, 8, 0, 7, 0, 0, 5 }, { 8, 0, 0, 0, 9, 0, 0, 2, 6 },
						{ 4, 0, 0, 0, 6, 5, 8, 0, 0 } };
				a = b;
				for (int i = 0; i < 9; i++)
					for (int j = 0; j < 9; j++)
						if (a[i][j] != 0)
							button[i][j].setText(a[i][j] + "");
			}
		});
		panel.add(help);
		panel.add(demo);

		button = new JButton[9][9];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				button[i][j] = new JButton(" ");
				if ((i / 3 + j / 3) % 2 == 0)
					button[i][j].setBackground(Color.WHITE);
				else
					button[i][j].setBackground(Color.gray);
				button[i][j].setForeground(Color.BLACK);
				button[i][j].setLocation(j * 41 + 300, i * 41 + 70);
				button[i][j].setSize(40, 40);
				final int p = i, q = j;
				button[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						int input;
						try {
							input = Integer.parseInt(entry.getText());
							if (1 > input || input > 9) {
								JOptionPane.showMessageDialog(frame, "Number must be between 1-9", "TRY AGAIN",
										JOptionPane.WHEN_FOCUSED);
							} else {
								button[p][q].setText("" + input);
								a[p][q] = input;
								entry.setText("");
							}

						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(frame, "Number must be writen first", "TRY AGAIN",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				});

				panel.add(button[i][j]);
			}

		copyrights = new JLabel("© milivojcevic6");
		copyrights.setLocation(600, 410);
		copyrights.setSize(110, 80);
		copyrights.setFont(new Font("Pumkins", Font.ITALIC, 10));
		panel.add(copyrights);

		ImageIcon img = new ImageIcon("sudoku.png");

		frame.add(panel);

		frame.setSize(700, 500);
		frame.setLocation(500, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("SUDOKU");
		frame.setIconImage(img.getImage());
		frame.setVisible(true);

	}

	public static void main(String[] args) {

		new sudoku();
		a = createArray();

		/*
		 * print(a); if (!checkTable(a)) System.out.println("Nepravilan unos!"); else {
		 * System.out.println("There is " + emptySpace(a) + " empty spaces");
		 * System.out.println("..........................\nSolution is:"); solve(a); if
		 * (emptySpace(a) != 0) // JOptionPane.showMessageDialog(frame, "This Sudoku
		 * doesn't have solution!\nTry // different numbers"); print(a);
		 * System.out.println("There is " + emptySpace(a) + " empty spaces"); }
		 */
	}

	private static int[][] createArray() {
		int[][] a = new int[9][9];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				a[i][j] = 0;
		return a;
	}

	private static void solve(int[][] a) {
		int m = emptySpace(a), times = 0;
		while (emptySpace(a) != 0 && times < 2) {
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[0].length; j++) {
					if (a[i][j] == 0 && check(a, i, j) == 1) {
						a[i][j] = fillNum(a, i, j);
						button[i][j].setText(a[i][j] + "");
					}
				}
			}
			if (m != emptySpace(a)) {
				m--;
				times = 0;
			}

			else
				times++;
		}
	}

	private static int check(int[][] a, int y, int x) {
		int counter = 0;
		for (int num = 1; num < 10; num++) {
			if (checkNum(a, y, x, num))
				counter++;
		}
		return counter;
	}

	private static boolean checkNum(int[][] a, int row, int col, int num) {
		return !inRow(a, row, num) && !inCol(a, col, num) && !inBox(a, row, col, num);
	}

	private static boolean inRow(int[][] a, int row, int num) {
		for (int i = 0; i < a[0].length; i++) {
			if (a[row][i] == num)
				return true;
		}
		return false;
	}

	private static boolean inCol(int[][] a, int col, int num) {
		for (int i = 0; i < a.length; i++) {
			if (a[i][col] == num)
				return true;
		}
		return false;
	}

	private static boolean inBox(int[][] a, int row, int col, int num) {
		int p = row - row % 3;
		int q = col - col % 3;
		for (int i = p; i < p + 3; i++) {
			for (int j = q; j < q + 3; j++) {
				if (a[i][j] == num)
					return true;
			}
		}
		return false;
	}

	private static int fillNum(int[][] a, int y, int x) {
		for (int num = 1; num < 10; num++) {
			if (checkNum(a, y, x, num))
				return num;
		}
		return 0;
	}

	private static boolean checkTable(int[][] a) {
		int rows = a.length;
		for (int i = 0; i < a.length; i++) {
			if (a[i].length != rows)
				return false;
		}
		return true;
	}

	private static void print(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print("[");
			for (int j = 0; j < a[0].length; j++) {
				if (a[i][j] == 0)
					System.out.print(" ");
				else
					System.out.print(a[i][j]);
				if (j % 3 == 2 && j != a[0].length - 1)
					System.out.print("|");
				else if (j != a[0].length - 1)
					System.out.print(",");
			}
			System.out.println("]");
		}
	}

	private static int emptySpace(int[][] a) {
		int counter = a.length * a[0].length;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				if (a[i][j] != 0)
					counter--;
			}
		}
		return counter;
	}
}
