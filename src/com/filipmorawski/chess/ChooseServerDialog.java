package com.filipmorawski.chess;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ChooseServerDialog extends JFrame {
	
	private JTextArea adressArea, portArea;
	private String url; 
	private int portNumber;
	
	public ChooseServerDialog() {
		createGUI();
	}

	private void createGUI() {
		
		JPanel centralPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new OkButtonListener());
		
		JLabel adressLabel = new JLabel("Adress");
		JLabel portLabel = new JLabel("Port number");
		
		adressArea = new JTextArea("Insert Adress", 1, 15);
		adressArea.setCaretPosition(13);
		portArea = new JTextArea("Insert port number",  1, 15 );
		portArea.setCaretPosition(18);
		
		bottomPanel.add(okButton);
		centralPanel.add(adressLabel);
		centralPanel.add(adressArea);
		centralPanel.add(portLabel);
		centralPanel.add(portArea);
		
		
		this.setTitle("Choose server");
		this.setSize(220,170);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(centralPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);	
		
	}
	
	
	public String getUrl() {
		return url;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}


	public class OkButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			url = adressArea.getText();
			String portString = portArea.getText();
			portNumber = Integer.parseInt(portString);
		
			dispose();
		}
		
	}

}
