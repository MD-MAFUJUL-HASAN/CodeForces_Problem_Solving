import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	//use a board to follow our path and look for bugs
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		char[] data = line.toCharArray();
		
		boolean[][] board = new boolean[203][203]; //this will be marked true for each location visited, or each location adjacent to a visited location
		int i = 101; //center i
		int j = 101; //center j
		board[i][j]= true; //mark initial spot as true
		
		//if the distance between any non-consecutive points is <= 1 there's a bug
		//rather than only marking the visited spots, and checking the 4 surrounding points for other visited spots
		//we mark all 4 surrounding points as visited so that we only have to check one point with each move
		for(int r = 0; r < data.length; r++)
		{
			
			switch(data[r])
			{
				case 'U':
					if(board[i-1][j]!=false) //if we hit this it means we are <=1 from another point.
					{
						System.out.println("BUG");
						return;
					}
					
					board[i-1][j] = true; //up from current location
					board[i+1][j] = true; //down from current location
					board[i][j-1] = true; //left from current location
					board[i][j+1] = true; //right from current location
					i--; //move up
										
					break;
				case 'D':
					if(board[i+1][j]!=false) //if we hit this it means we are <=1 from another point.
					{
						System.out.println("BUG");
						return;
					}
					
					board[i-1][j] = true; //up from current location
					board[i+1][j] = true; //down from current location
					board[i][j-1] = true; //left from current location
					board[i][j+1] = true; //right from current location
					
					i++; //move down
					break;
				case 'L':
					if(board[i][j-1]!=false) //if we hit this it means we are <=1 from another point.
					{
						System.out.println("BUG");
						return;
					}
					
					board[i-1][j] = true; //up from current location
					board[i+1][j] = true; //down from current location
					board[i][j-1] = true; //left from current location
					board[i][j+1] = true; //right from current location
					
					j--; //move up
					break;
				case 'R':
					if(board[i][j+1]!=false) //if we hit this it means we are <=1 from another point.
					{
						System.out.println("BUG");
						return;
					}
					
					board[i-1][j] = true; //up from current location
					board[i+1][j] = true; //down from current location
					board[i][j-1] = true; //left from current location
					board[i][j+1] = true; //right from current location

					j++; //move up
					break;
			}
		}
		System.out.println("OK"); //if we never hit a bug, we are good to print "OK"
	}

}
