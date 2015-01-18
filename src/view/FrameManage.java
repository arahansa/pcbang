package view;

import java.awt.Component;
import java.awt.Rectangle;
import java.lang.reflect.Field;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import view.panel.ClockMessage;
import view.panel.ImgClock;
import view.panel.MyStarPanel;
import view.panel.PanImgload;
import asset.Setting;

@SuppressWarnings("serial")
public class FrameManage extends JFrame {

	public JLayeredPane layeredPane = new JLayeredPane();
	// JPanels
	private PanImgload backGround = new PanImgload("img/mainHud_back.png");
	private MyStarPanel myStarPanel = new MyStarPanel();
	private ImgClock imgClock = new ImgClock();
	private ClockMessage clockMessage = new ClockMessage();

	
	public FrameManage()  {
		// Configure this Frame
		setLayout(null);
		setVisible(true);
		setTitle("ManageView");
		setSize(Setting.bDimen);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(Setting.locationX, Setting.locationY);
		add(setJLayered(backGround, myStarPanel, imgClock, clockMessage));
		add(layeredPane);
	}

	public static void main(String[] args) throws Exception {
		FrameManage manageView = new FrameManage();
		manageView.setRectangles(FrameManage.class, manageView, Setting.class, Setting.getInstance());
		
	}

	// Setting inner Methods
	private JComponent setJLayered(Component...components) {
		int i = 0;
		for (Component component : components)
			layeredPane.add(component, new Integer(i++));
		return layeredPane;
	}
	// Reflection Practice
	public void setRectangles(Class<?> clazz, Object instance,  Class<?> targetClass, Object target) throws Exception {
		Object tempObject = null;
		for (Field field : clazz.getDeclaredFields()){
			if ((tempObject = field.get(instance)) instanceof JComponent){
				((JComponent) tempObject).setBounds((Rectangle) targetClass.getDeclaredField(field.getName()).get(target));
				((JComponent) tempObject).setOpaque(false);
				((JComponent) tempObject).setLayout(null);
			}
			if(tempObject instanceof Runnable)
				new Thread((Runnable) tempObject).start();
		}		
	}
	// TODO 계속 들어감

}
