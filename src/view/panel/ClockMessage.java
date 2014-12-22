package view.panel;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import asset.Setting;

@SuppressWarnings("serial")
public class ClockMessage extends JPanel implements Runnable {
	int i = Calendar.getInstance().get(Calendar.AM_PM);
	String[] ampm = { "AM", "PM" };
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	String time = sdf.format(new Date());
	JLabel timeLabel, ampmLabel;

	public ClockMessage() {
		setLayout(null);

		timeLabel = new JLabel(time);
		setLable(timeLabel).setBounds(Setting.timeLabel);
		ampmLabel = new JLabel(ampm[i]);
		setLable(ampmLabel).setBounds(Setting.ampmLabel);

		add(timeLabel, BorderLayout.NORTH);
		add(ampmLabel, BorderLayout.CENTER);
	}

	@Override
	public void run() {
		do {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timeLabel.setText(sdf.format(new Date()));
		} while (true);
	}
	//Setting Methods
	private JComponent setLable(JComponent component){
		component.setForeground(Setting.bColor);
		component.setFont(Setting.bFont);
		return component;
	}
}
