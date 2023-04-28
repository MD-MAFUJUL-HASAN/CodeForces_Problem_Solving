import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	static char[][] board = new char[3][3];
	static int xCount;
	static int oCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		xCount = 0;
		oCount = 0;
		for(int i=0;i<3;i++)
		{
			char[] row = in.readLine().toCharArray();
			for(int j=0; j<3; j++)
			{
				if(row[j]=='X')
				{
					xCount++;
				}
				if(row[j]=='0')
				{
					oCount++;
				}
				board[i][j] = row[j];
			}
		}
		
		char wins = checkForWins();
		
		if(xCount>oCount+1 || oCount>xCount)
		{
			System.out.println("illegal");
		}
		else if(wins=='i')
		{
			System.out.println("illegal");
		}
		else if(wins=='X')
		{
			if(xCount==oCount+1)
			{
				System.out.println("the first player won");
			}
			else
			{
				System.out.println("illegal");
			}
		}
		else if(wins=='0')
		{
			if(oCount == xCount)
			{
				System.out.println("the second player won");
			}
			else
			{
				System.out.println("illegal");
			}
		}
		else if(xCount+oCount == 9)
		{
			System.out.println("draw");
		}
		else if(xCount==oCount)
		{
			System.out.println("first");
		}
		else
		{
			System.out.println("second");
		}
		
	}
	
	private static char checkForWins()
	{
		char winner = 'n';
		//check diagonals
		if(board[0][0]==board[1][1] && board[0][0] == board[2][2]&&(board[0][0]=='X'||board[0][0]=='0'))
		{
			winner = board[0][0];
		}
		
		if(board[0][2]==board[1][1] && board[0][2] == board[2][0]&&(board[0][2]=='X'||board[0][2]=='0'))
		{
			if(winner=='n')
			{
				winner = board[0][2];
			}
			else if(winner!=board[0][2])
			{
				return 'i';
			}
		}
		//check horizontals
		for(int i=0; i<3; i++)
		{
			if(board[i][0]==board[i][1] && board[i][0]==board[i][2]&&(board[i][0]=='X'||board[i][0]=='0'))
			{
				if(winner=='n')
				{
					winner = board[i][0];
				}
				else if(winner!=board[i][0])
				{
					return 'i';
				}
			}
		}
		
		
		//check verticals
		for(int i=0; i<3; i++)
		{
			if(board[0][i]==board[1][i] && board[0][i]==board[2][i]&&(board[0][i]=='X'||board[0][i]=='0'))
			{
				if(winner=='n')
				{
					winner = board[0][i];
				}
				else if(winner!=board[0][i])
				{
					return 'i';
				}
			}
		}
		
		return winner;
	}

}
