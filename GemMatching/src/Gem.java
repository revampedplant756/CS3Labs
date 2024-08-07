import java.awt.Color;
import java.awt.Font;
import java.util.Random;

enum GemType {
    GREEN, BLUE, ORANGE; //define the different types of Gems, comma delimited
}

public class Gem 
{	
	/** Tester main method */
	public static void main(String [] args)
	{
		final int maxGems = 16;
		
		//Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);
		
		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
	
	GemType type;
	int points;
	
	public Gem() {
		GemType[] colors = new GemType[] {GemType.GREEN, GemType.BLUE, GemType.ORANGE};
		Random rand = new Random();
		type = colors[rand.nextInt(3)];
		points = rand.nextInt(11) * 5;
	}
	
	public Gem(GemType type, int points) {
		this.type = type;
		this.points = points;
	}
	
	public String toString() {
		return "" + type;
	}
	
	public GemType getType() {
		return type;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void draw(double x, double y) {
		StdDraw.picture(x, y, "gem_"+toString()+".png");
		StdDraw.setFont(new Font("SansSerif", Font.BOLD, 14));
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(x, y, Integer.toString(getPoints()));
	}
}
