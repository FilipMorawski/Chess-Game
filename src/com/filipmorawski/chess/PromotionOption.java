package com.filipmorawski.chess;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.event.*;

public class PromotionOption extends JOptionPane{
	
	private String choose = "Queen";

//	Promotion dialog frame
	
	public PromotionOption() {
		
//		frame = new JFrame();
//		frame.setTitle("Promotion!");
//		frame.setSize(300, 100);
//		frame.setLocation(270, 270);
//		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);
		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel southPanel = new JPanel();
		
		JLabel label = new JLabel("Choose figure to switch up");
		northPanel.add(label);
		
		Box hBox = Box.createHorizontalBox();
		JRadioButton radio1 = new JRadioButton("Tower", false);
		JRadioButton radio2 = new JRadioButton("Jumper", false);
		JRadioButton radio3 = new JRadioButton("Courier", false);
		JRadioButton radio4 = new JRadioButton("Queen", false);
		RadioListener listener = new RadioListener();
		
		radio1.addItemListener(listener); radio2.addItemListener(listener); radio3.addItemListener(listener); radio4.addItemListener(listener);
		
		hBox.add(radio1); hBox.add(radio2); hBox.add(radio3); hBox.add(radio4);
		centerPanel.add(hBox);
		
		panel.add(centerPanel, BorderLayout.CENTER);
		panel.add(northPanel, BorderLayout.NORTH);

		JOptionPane.showMessageDialog(null, panel, "Promotion!", JOptionPane.QUESTION_MESSAGE);
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

	public class RadioListener implements ItemListener  {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == 1) {
				JRadioButton rb = (JRadioButton) e.getSource();
				choose = rb.getText();
				System.out.println("Choose is : " + choose);
			}
		}
	}	
}

