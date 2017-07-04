package com.filipmorawski.chess.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MessageReceivedDialog extends JFrame{

	public JTextArea centralArea;
	
	public MessageReceivedDialog() {
		this.setTitle("Messages from server");
		this.setSize(200,200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		addUI();
		this.setVisible(true);
		this.setResizable(false);
	}

	private void addUI() {
		JPanel panel = new JPanel();
		centralArea = new JTextArea("", 5,5);
		centralArea.setLineWrap(true);
		panel.add(centralArea);
		this.add(panel);
	}
	
	
	
}
