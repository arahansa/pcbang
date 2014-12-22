package view;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import view.panel.ClockMessage;
import view.panel.ImgClock;
import view.panel.MyStarPanel;
import view.panel.PanImgload;
import asset.Setting;

@SuppressWarnings("serial")
public class ManageView extends JFrame {

	private JLayeredPane layeredPane = new JLayeredPane();
	//JPanels
	private JPanel backGround = new PanImgload("img/mainHud_back.png");
	private ImgClock imgClock = new ImgClock();
	private ClockMessage clockMessage = new ClockMessage();
	private MyStarPanel myStarPanel = new MyStarPanel();
	
	public ManageView() {
		//Configure this Frame
		setLayout(null);
		setVisible(true);
		setTitle("ManageView");
		setSize(Setting.bDimen);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(Setting.locationX, Setting.locationY);
		
		//LayerdPanel and Component Setting , Finally add To this Frame
		setPanel(layeredPane).setBounds(Setting.bRectangle);
		setPanel(backGround).setBounds(Setting.bpanRectangle);
		setPanel(imgClock).setBounds(Setting.imgClockRectangle);
		setPanel(clockMessage).setBounds(Setting.clockRectangle);
		setPanel(myStarPanel).setBounds(Setting.bpanRectangle);
		add(setJLayered(backGround, myStarPanel, imgClock, clockMessage));
		
		//Action Events
		threadStart(imgClock, clockMessage, myStarPanel); 
	}

	public static void main(String[] args) {
		new ManageView();
	}

	// Setting inner Methods
	private JComponent setPanel(JComponent panel) {
		panel.setLayout(null);
		panel.setOpaque(false);
		return panel;
	}
	private JLayeredPane setJLayered(Component...components) {
		int i = 0;
		for (Component component : components)
			layeredPane.add(component, new Integer(i++));
		return layeredPane;
	}
	private void threadStart(Runnable...target) {
		for (Runnable runnable : target)
			new Thread(runnable).start();
	}

	// TODO 계속 들어감

}
