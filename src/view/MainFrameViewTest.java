package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.PCServerBackground;

public class MainFrameViewTest extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel[] = new JPanel[2];
	
	public MainFrameViewTest() {
		
		setLayout(new GridLayout(1, 2));
		panel[0] = new JPanel();
		panel[1] = new JPanel();
		panel[0].setBackground(Color.black);
		panel[1].setBackground(Color.red);
		
		
		add(panel[0]);
		add(panel[1]);
				
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(200, 200, 1600, 900);	
	}
	public static void main(String[] args) {
		MainFrameViewTest test =  new MainFrameViewTest();
		PCServerBackground pcServer = new PCServerBackground();
		pcServer.setGui(test);
		pcServer.setting();
	}
	
	public void connectClient(){
		panel[1].setBackground(Color.blue);
	}

}
