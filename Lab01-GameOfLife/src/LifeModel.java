import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

	/*
	 *  This is the Model component.
	 */

	private static int SIZE = 60;
	private LifeCell[][] grid;
	
	LifeView myView;
	Timer timer;

	/** Construct a new model using a particular file */
	public LifeModel(LifeView view, String fileName) throws IOException
	{       
		int r, c;
		grid = new LifeCell[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new LifeCell();

		if ( fileName == null ) //use random population
		{                                           
			for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
				}
			}
		}
		else
		{                 
			Scanner input = new Scanner(new File(fileName));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				r = input.nextInt();
				c = input.nextInt();
				grid[r][c].setAliveNow(true);
			}
			input.close();
		}

		myView = view;
		myView.updateView(grid);

	}

	/** Constructor a randomized model */
	public LifeModel(LifeView view) throws IOException
	{
		this(view, null);
	}

	/** pause the simulation (the pause button in the GUI */
	public void pause()
	{
		timer.stop();
	}
	
	/** resume the simulation (the pause button in the GUI */
	public void resume()
	{
		timer.restart();
	}
	
	/** run the simulation (the pause button in the GUI */
	public void run()
	{
		timer = new Timer(50, this);
		timer.setCoalesce(true);
		timer.start();
	}

	/** called each time timer fires */
	public void actionPerformed(ActionEvent e)
	{
		oneGeneration();
		myView.updateView(grid);
	}

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration()
	{
		int countAround = 0;
		
		for(int e = 0; e < grid.length; e++) {
			for(int x = 0; x < grid[e].length; x++) {
				
				  if(e == 0 && x == 0) { 
					  if(grid[e][x+1].isAliveNow()) { 
						  countAround++;
						  }
					  if(grid[e+1][x].isAliveNow()) { 
						  countAround++;
						  }
					  if(grid[e+1][x+1].isAliveNow()) {
						  countAround++;
					  	  }
				  } 
				  else if(e == 0 && x == grid[e].length-1) { 
					  if(grid[e][x-1].isAliveNow()) {
						  countAround++; 
						  } 
					  if(grid[e+1][x].isAliveNow()) {
						  countAround++; 
						  }
					  if(grid[e+1][x-1].isAliveNow()) { 
						  countAround++; 
						  }
				  } else if(e == grid.length-1 && x == 0) { 
					  if(grid[e][x+1].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e-1][x].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e-1][x+1].isAliveNow()) { 
						  countAround++; 
						  }
				  } else if(e == grid.length-1 && x == grid[e].length-1) { 
					  if(grid[e][x-1].isAliveNow()) {
						  countAround++; 
						  }
					  if(grid[e-1][x].isAliveNow()) {
						  countAround++; 
						  }
					  if(grid[e-1][x-1].isAliveNow()) { 
						  countAround++; 
						  }
				  } else if(e == 0) {
					  if(grid[e][x+1].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e][x-1].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e+1][x].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e+1][x+1].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e+1][x-1].isAliveNow()) { 
						  countAround++; 
						  }
				  } else if(x == 0) {
					  if(grid[e][x+1].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e+1][x].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e-1][x].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e+1][x+1].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e-1][x+1].isAliveNow()) { 
						  countAround++; 
						  }
					  
				  } else if(x==grid[e].length-1) { 
					  if(grid[e][x-1].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e+1][x].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e-1][x].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e+1][x-1].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e-1][x-1].isAliveNow()) { 
						  countAround++; 
						  }
				  } else if(e == grid.length-1) { 
					  if(grid[e][x+1].isAliveNow()) { 
						  countAround++; }
					  if(grid[e][x-1].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e-1][x].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e-1][x+1].isAliveNow()) { 
						  countAround++; 
						  }
					  if(grid[e-1][x-1].isAliveNow()) { 
						  countAround++; 
						  }
				  } else {
					  if(grid[e][x+1].isAliveNow()) { 
						  countAround++;
						  }
					  if(grid[e][x-1].isAliveNow()) { 
						  countAround++;
						  }
					  if(grid[e+1][x].isAliveNow()) { 
						  countAround++;
						  }
					  if(grid[e-1][x].isAliveNow()) { 
						  countAround++;
						  }
					  if(grid[e+1][x-1].isAliveNow()) { 
						  countAround++;
						  }
					  if(grid[e+1][x+1].isAliveNow()) { 
						  countAround++;
						  }
					  if(grid[e-1][x-1].isAliveNow()) { 
						  countAround++;
						  }
					  if(grid[e-1][x+1].isAliveNow()) { 
						  countAround++;
						  }
				   }
				  
//				  if(grid[e][x].isAliveNow()) { 
//					  if(countAround != 2 || countAround != 3) {
//						  grid[e][x].setAliveNext(false); 
//						  }
//				  else { 
//				       grid[e][x].setAliveNext(true); 
//				   }
//				  
				  
				  
				  //System.out.println(grid[e][x].isAliveNext());
//					System.out.println(countAround);
				  System.out.println("CountAround: " + countAround + " at " + e + " " + x);
				  countAround = 0;
			}
		}
		
//		for(int e = 0; e < grid.length; e++) {
//			for(int x = 0; x < grid[e].length; x++) {
//				grid[e][x].setAliveNow(grid[e][x].isAliveNext());
//			}
//		}
	}
}

