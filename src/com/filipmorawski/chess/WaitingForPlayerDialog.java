package com.filipmorawski.chess;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WaitingForPlayerDialog extends JFrame {

	public WaitingForPlayerDialog() {
		this.setSize(200, 100);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("Waiting for players");
		setGUI();
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void setGUI() {
		Font font = new Font("Serif", Font.BOLD, 20);
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Waiting for second player");
		panel.add(label);
		this.add(panel, BorderLayout.CENTER);
	}

}
