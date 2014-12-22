package view.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImgClock extends JPanel implements Runnable {
	Image img[] = new Image[4];
	int i = 2;
	public ImgClock() {
		img[1] = Toolkit.getDefaultToolkit().createImage("img/cl1.png");
		img[2] = Toolkit.getDefaultToolkit().createImage("img/cl2.png");
		img[3] = Toolkit.getDefaultToolkit().createImage("img/cl3.png");
		img[0] = img[1];
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img[0], 0, 0, this);
	}
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10000);
				i = (i==3) ? 1 : ++i;
				img[0] = img[i];	
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
