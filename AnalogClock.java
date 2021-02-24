import java.awt.*;
import java.util.Calendar;
import javax.swing.*;

public class AnalogClock extends JPanel implements Runnable {
	Calendar now;
	Thread th;
	Font F = new Font("TimesRoman", Font.PLAIN, 25);
	Color fgcol = new Color(170,10,0);
	Color fgcol2 = Color.RED;
	Color fgcol3 = Color.BLACK;

  public AnalogClock() {
	 setOpaque(false);
 	}

	public static void main(String args[]) {
		JFrame app = new JFrame();
		AnalogClock clock = new AnalogClock();
		app.add(clock);
		app.setSize(500, 500);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
		clock.start();
	}

	public void start() {
		if (th == null) {
			th = new Thread(this);
			th.start();
		}
	}

	public void stop() {
		if (th != null) {
			th = null;
		}
	}

	public void run() {
		while (th != null) {
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	public void paintComponent(Graphics g) {
		int xh, yh, xm, ym, xs, ys, s, m, h, xcenter, ycenter;

		now = Calendar.getInstance();
		s = now.get(Calendar.SECOND);
		m = now.get(Calendar.MINUTE);
		h = now.get(Calendar.HOUR_OF_DAY);
		xcenter = 250;
		ycenter = 200;

		xs = (int) (Math.sin(s * 3.14f / 30) * 140 + xcenter);
		ys = (int) (-Math.cos(s * 3.14f / 30) * 140 + ycenter);
		xm = (int) (Math.sin(m * 3.14f / 30) * 120 + xcenter);
		ym = (int) (-Math.cos(m * 3.14f / 30) * 120 + ycenter);
		xh = (int) (Math.sin((h * 30 + m / 2) * 3.14f / 180) * 90 + xcenter);
		yh = (int) (-Math.cos((h * 30 + m / 2) * 3.14f / 180) * 90 + ycenter);

    g.setFont(F);
		g.setColor(fgcol3);
		g.drawString("CITIZEN", xcenter - 50, ycenter + 60);

		g.setFont(F);
		g.setColor(fgcol);
		g.fillOval(xcenter - 185, ycenter - 185, 370, 370);
		g.setColor(fgcol3);
		g.drawLine(xcenter, ycenter - 1, xm, ym);
		g.drawLine(xcenter - 1, ycenter, xm, ym);
		g.drawLine(xcenter, ycenter - 1, xh, yh);
		g.drawLine(xcenter - 1, ycenter, xh, yh);

		g.drawString("5", xcenter + 85, ycenter + 155);
		g.drawString("4", xcenter + 140, ycenter + 90);
		g.drawString("2", xcenter + 140, ycenter - 80);
		g.drawString("1", xcenter + 70, ycenter - 140);
		g.drawString("11", xcenter - 90, ycenter - 135);
		g.drawString("10", xcenter - 155, ycenter - 80);
		g.drawString("8", xcenter - 150, ycenter + 90);
		g.drawString("7", xcenter - 85, ycenter + 155);
		g.drawString("9", xcenter - 170, ycenter + 3);
		g.drawString("3", xcenter + 160, ycenter + 3);
		g.drawString("12", xcenter - 5, ycenter - 160);
		g.drawString("6", xcenter - 3, ycenter + 180);
		g.drawLine(xcenter, ycenter, xs, ys);
	}
}
