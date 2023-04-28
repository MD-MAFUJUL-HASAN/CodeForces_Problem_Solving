import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	static int hits(int health, int damage)
	{
		return health / damage + (health % damage ==0 ? 0:1); //find out how many hits are required to kill this archer
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String[] line = in.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int a = Integer.parseInt(line[1]);
		int b = Integer.parseInt(line[2]);
		line = in.readLine().split(" ");
		int[] archers = new int[n+1];
		
		for(int i=1; i<=n; i++)
		{
			archers[i] = Integer.parseInt(line[i-1])+1; //increment by 1 so death is <= 0 instead of < 0
		}
		
		//handle special case of n==3 where there is only one spot you can attack
		if(n == 3)
		{
			int x,y,z, max;
			x = hits(archers[1], b); //find hits to kill archer 1
			y = hits(archers[2], a); //find hits to kill archer 2
			z = hits(archers[3], b); //find hits to kill archer 3
			max = Math.max(Math.max(x,y),z);
			out.append(max);
			out.append("\n");
			for(int i=0; i<max;i++)
			{
				out.append("2 ");
			}
			System.out.println(out.toString());
			return;			
		}
		
		//kill the unreachable outer archers
		int hits1 = hits(archers[1],b); //number of hits to kill the left most unreachable archer
		int hits2 = hits(archers[n],b); //number of hits to kill the right most unreachable archer
		archers[1] = 0;
		archers[2] = Math.max(0, archers[2] - hits1 * a); //direct hit damage
		archers[3] = Math.max(0, archers[3] - hits1 * b); //reduce life due to splash damage
		
		archers[n-2] = Math.max(0, archers[n-2] - hits2 * b); //splash damage
		archers[n-1] = Math.max(0, archers[n-1] - hits2 * a); //direct hit damage
		archers[ n ] = 0;
		
		int[][][] dp = new int[n+1][17][17]; //this will be used to track possible hit combinations
		
		int[][] a2 = new int[17][17];
		int[][] a3 = new int[17][17];
		
		for(int i=0; i<=archers[2]; i++) //precomputer dp[3]
		{
			for(int j=0; j<=archers[3]; j++)
			{
				if(i==0 && j==0)
					continue;
				if(i >= j)
				{
					int h1 = Math.max(0, i-a);
					int h2 = Math.max(0, j-b);
					a2[i][j] = a2[h1][h2]+1;
					a3[i][j] = a3[h1][h2];
				}
				else
				{
					int h1 = Math.max(0, i-b);
					int h2 = Math.max(0, j-a);
					a2[i][j] = a2[h1][h2];
					a3[i][j] = a3[h1][h2]+1;
				}
				dp[3][i][j] = a2[i][j] + a3[i][j];
			}
		}
		
		int[][][] p = new int[n+1][17][17]; //track
		
		for(int ar = 4; ar<n; ar++) //pointer to current archer
		{
			for(int i = 0; i<= archers[ar-1]; i++) //health of archer[ar-1]
			{
				for(int j=0; j<=archers[ar]; j++)  //health of archer[ar]
				{
					int h1, h2;
					if(j==0)
					{
						h1 = archers[ar-2];
						h2 = i;
						dp[ar][i][j] =  dp[ar-1][h1][h2];						
					}
					else
					{
						int hit1 = hits(j, b);
						h1 = Math.max(0, archers[ar-2]-hit1 * b);
						h2 = Math.max(0, i - hit1 * a);
						
						dp[ar][i][j] = dp[ar-1][h1][h2]+hit1;
						p[ar][i][j] = hit1;
						
						h1 = Math.max(0, i-b); 
						h2 = Math.max(0, j-a);
						if(dp[ar][i][j] > dp[ar][h1][h2] +1) //take min of two choices
						{
							dp[ar][i][j] = dp[ar][h1][h2] +1;
							p[ar][i][j] = -1; //mark combination
						}
					}
				}
			}
		}
		
		int count = dp[n-1][archers[n-2]][archers[n-1]] + hits1 + hits2; //total hits required for min;
		
		//create first line output
		out.append(count);
		out.append("\n");
		
		//list hits to kill outlier archers
		for(int i=0; i<hits1; i++)
		{
			out.append("2 ");
		}
		for(int i=0; i<hits2; i++)
		{
			out.append(n-1);
			out.append(" ");
		}
		
		int ar = n-1;
		int i = archers[ar-1];
		int j = archers[ar];
		
		//add hits for main archers
		while(ar>3)
		{
			int c = p[ar][i][j];
			int h1, h2;
			if(c == 0)
			{
				h1 = archers[ar-2];
				h2 = i;
				ar--;
			}
			else if(c>0)
			{
				for(int k = 0; k<c; k++)
				{
					out.append(ar-1).append(" ");					
				}
				h1 = Math.max(0,  archers[ar-2] - b * c);
				h2 = Math.max(0,  i - a * c);
				ar--;
			}
			else 
			{
				out.append(ar).append(" ");
				h1 = Math.max(0, i-b);
				h2 = Math.max(0, j-a);
			}
			
			i = h1;
			j = h2;
			
		}
		//add earlier computed hits
		for(int k = 0; k< a3[i][j]; k++){
			out.append(3).append(" ");
		}
		for(int k = 0; k< a2[i][j]; k++){
			out.append(2).append(" ");
		}
		
		System.out.println(out.toString());
	}

}
