public class FireModel
{
    public static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;

    public FireModel(FireView view)
    {
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int r=0; r<SIZE; r++)
        {
            for (int c=0; c<SIZE; c++)
            {
                myGrid[r][c] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
    }

    /*
        recursiveFire method here
     */

    public void solve()
    {
    	solveHelper(0, 0, true, "");
    	
    	boolean OnettSafe = true;
        for(int e = 0; e < myGrid[0].length; e++) {
        	if(myGrid[0][e].getStatus() == 2) {
        		OnettSafe = false;
        		break;
        	}
        }
        if(OnettSafe) {
        	System.out.println("Onett is safe");
        }
        else {
        	System.out.println("Onett is in trouble");
        }
        myView.updateView(myGrid);
    }
    
    private void solveHelper(int r, int c, boolean firstCall, String directionOfPrevious) {
    	if(firstCall) {
    		for(int e = 0; e < myGrid[myGrid.length-1].length; e++) {
    			if(myGrid[myGrid.length-1][e].getStatus() == 1) {
    				myGrid[myGrid.length-1][e].setStatus(2);
    				solveHelper(myGrid.length-2, e, false, "down");
    			}
    		}
    	}
    	else {
    		
    		if(myGrid[r][c].getStatus() == 0 || myGrid[r][c].getStatus() == 2) {
    			return;
    		}
    		
    		if(myGrid[r][c].getStatus() == 1) {
    			myGrid[r][c].setStatus(2);
    		}
    		
    		if(r-1 >= 0) {
    			if(!directionOfPrevious.equals("up")) {
    				solveHelper(r-1, c, false, "down");
    			}
        	}
    		
    		if(r+1 < myGrid.length) {
    			if(!directionOfPrevious.equals("down")) {
    				solveHelper(r+1, c, false, "up");
    			}
    		}
    		
    		if(c-1 >= 0) {
    			if(!directionOfPrevious.equals("left")) {
    				solveHelper(r, c-1, false, "right");
    			}
    			
    		}
    		
    		if(c+1 < myGrid[0].length) {
    			if(!directionOfPrevious.equals("right")) {
    				solveHelper(r, c+1, false, "left");
    			}
    		}
    	}
    	return;
    }

}
