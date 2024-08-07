import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

class SnowFlakePanel extends JPanel
{
	public SnowFlakePanel()
	{
		super.setPreferredSize(new Dimension(400, 400));
		super.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g)
	{
		int width  = getWidth();
		int height = getHeight();

		super.paintComponent(g);

		/*
		 * DRAWING CODE BELOW
		 */
		g.setColor(Color.BLUE);
		//g.drawLine(0, 0, width - 1, height - 1);
		//drawStar(g, width/2, height/2, width/3);
		//snowflake(g, width/2, height/2, width/3, 1);
		blizzard(g);
	}
	
	public void drawStar(Graphics g, int x, int y, int size) {
		for(int e = 1; e <= 6; e++) {
			g.drawLine(x, y, x + ((int)(size * Math.cos(e*(2*Math.PI/6)))), y - ((int)(size * Math.sin(e*(2*Math.PI/6)))));
		}
	}
	
	public void snowflake(Graphics g, int x, int y, int size, int scalar) {
		int width  = getWidth();
		if(size <= (width/48)/scalar) {
			return;
		}
		for(int e = 1; e <= 6; e++) {
			int newX =  x + ((int)((size) * Math.cos(e*(2*Math.PI/6))));
			int newY = y - ((int)((size) * Math.sin(e*(2*Math.PI/6))));
			g.drawLine(x, y, newX, newY);
			snowflake(g, newX, newY, size/4, scalar);
		}
		return;
	}
	
	public void blizzard(Graphics g) {
		Random rand = new Random();
		int width  = getWidth();
		int scalar = rand.nextInt(20)+1;
		//snowflake(g, rand.nextInt(400), rand.nextInt(400), width/3/scalar, scalar);
		for(int e = 0; e < 10; e++) {
			float r = rand.nextFloat();
			float gr = rand.nextFloat();
			float b = rand.nextFloat();
			Color randomColor = new Color(r, gr, b);
			g.setColor(randomColor);
			snowflake(g, rand.nextInt(400), rand.nextInt(400), width/3/scalar, scalar);
		}
	}
}

public class Snowflake
{
	public static void main ( String[] args )
	{
		/*
		 * A frame is a container for a panel
		 * The panel is where the drawing will take place
		 */
		JFrame frame = new JFrame("Snowflake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SnowFlakePanel());
		frame.pack();
		frame.setVisible(true);
	}
}
