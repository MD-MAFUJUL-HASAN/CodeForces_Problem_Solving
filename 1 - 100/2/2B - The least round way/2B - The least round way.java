import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	
	static int[][] board;
	static int[][] board5s;
	static int[][] board2s;
	static char[][] step5s;
	static char[][] step2s;
	
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			int N = Integer.parseInt(in.readLine());
			board = new int[N][N];
			String[] line = null;
			boolean hasZero = false;
			
			int zeroj = 0;
			for(int n=0; n < N; n++)
			{
				line = in.readLine().split(" ");
				for(int m=0; m < N; m++)
				{
					board[n][m] = Integer.parseInt(line[m]);
					if(board[n][m]==0)
					{
						
						hasZero=true;
						zeroj = m;
					       
					}
				}
			}
			
			
			board5s = new int[N][N];
			board2s = new int[N][N];
			step5s = new char[N][N];
			step2s = new char[N][N];
			
			
			for(int j = N-1; j>=0; j--)
			{
				for(int i=N-1; i>=0; i--)
				{
					mins(i,j,N);					
				}
			}
			
			
			if(board2s[0][0]<=board5s[0][0])
			{
				if(1<board2s[0][0] && hasZero)
				{
					printZeroCase(zeroj, N);
				}
				else
				{
					System.out.println(board2s[0][0]);
					System.out.println(getPath(step2s, N));
				}
							
			}
			else
			{
				if(1<board5s[0][0] && hasZero)
				{
					printZeroCase(zeroj, N);
				}
				else
				{
					System.out.println(board5s[0][0]);
					System.out.println(getPath(step5s, N));
				}
				
			}
			
		
			
			
	}
	
	private static void printZeroCase(int zeroj, int N)
	{
		System.out.println("1");
		StringBuilder zsb = new StringBuilder();
		for(int j = 0; j < zeroj; j++)
		{
			zsb.append("R");
		}
		for(int i = 0; i < N-1; i++)
		{
			zsb.append("D");
		}
		for(int j = zeroj; j < N-1; j++)
		{
			zsb.append("R");
		}
		System.out.println(zsb.toString());
	}
	
	private static String getPath(char[][] board, int N)
	{
		char current = 'S';
		int i=0;
		int j=0;
		StringBuilder sb = new StringBuilder();
		while(current!='E')
		{
			current = board[i][j];
			if(current!='E')
			{
				sb.append(current);
				if(current=='D')
				{
					i++;
				}
				else if(current=='R')
				{
					j++;
				}
					
			}
		}
		return sb.toString();
		
	}
	
	private static void mins(int i, int j, int N)
	{
		
		int right = Integer.MAX_VALUE;
		int down = Integer.MAX_VALUE;
		int right5 = Integer.MAX_VALUE;
		int down5 = Integer.MAX_VALUE;
		int right2 = Integer.MAX_VALUE;
		int down2 = Integer.MAX_VALUE;
		if (i<N-1)
		{
			down = 1;
			down5 = board5s[i+1][j];
			down2 = board2s[i+1][j];
		}
		
		if(j<N-1)
		{
			right = 1;
			right5 = board5s[i][j+1];
			right2 = board2s[i][j+1];
		}
		
		if(down == Integer.MAX_VALUE && right == Integer.MAX_VALUE) //case for bottom right square
		{
			board5s[i][j] = count5Factors(board[i][j]);
			step5s[i][j] = 'E';
			board2s[i][j] = count2Factors(board[i][j]);
			step2s[i][j] = 'E';
		} 
		else 
		{
			if (down5 <= right5) //choose the lesser direction
			{
				board5s[i][j] = down5 + count5Factors(board[i][j]);
				step5s[i][j] = 'D';	
			}
			else 
			{
				board5s[i][j] = right5 + count5Factors(board[i][j]);
				step5s[i][j] = 'R';
			}
			
			if (down2 <= right2) //choose the lesser direction
			{
				board2s[i][j] = down2 + count2Factors(board[i][j]);
				step2s[i][j] = 'D';	
			}
			else 
			{
				board2s[i][j] = right2 + count2Factors(board[i][j]);
				step2s[i][j] = 'R';
			}
		}
	}
	
	
	private static int count2Factors(int input)
	{
		if(input==0)
		{
			return 1;
		}
		
		int count = 0;
		int remainder = 0;
	
		int one = 1;
		while(remainder==0)
		{
			if((input & one) ==0)
			{
				count++;
				input = input>>1;
			}
			else
			{
				remainder = 1;
			}	
		}
		
		return count;		
	}
	
	private static int count5Factors(int input)
	{
		if(input==0)
		{
			return 1;
		}
		int count = 0;
		int remainder = 0;
		while(remainder==0)
		{
			remainder = input % 5;
			if(remainder==0)
			{
				count++;
				input /= 5;
			}
			
		}
		
		return count;	
	}

}
