package com.filipmorawski.chess;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SendButton extends JFrame {
	
	private boolean buttonPressed = false;
	
	public SendButton() {
		this.setTitle("Send");
		this.setSize(200,75);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		makeButton();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(true);
	}

	private void makeButton() {
		JPanel panel = new JPanel();
		JButton send = new JButton("Send");
		
		send.addActionListener(new SendListener());
		panel.add(send, BorderLayout.CENTER);
		this.add(panel);
	}

	public boolean isButtonPressed() {
		return buttonPressed;
	}

	public void setButtonPressed(boolean buttonPressed) {
		this.buttonPressed = buttonPressed;
	}

	class SendListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			buttonPressed = true;
			dispose();
		}
		
	}
	
}
