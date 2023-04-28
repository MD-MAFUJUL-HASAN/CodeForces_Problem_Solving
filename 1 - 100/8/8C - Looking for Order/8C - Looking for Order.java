import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Jonathan
// cpt_fwiffo

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		int Xs = Integer.parseInt(line[0]);
		int Ys = Integer.parseInt(line[1]);
		int n = Integer.parseInt(in.readLine());
		
		int[][] points = new int[n+1][2];
		points[n][0] = Xs;//set starting point as last point for finding distances
		points[n][1] = Ys;
		
		for(int i=0; i< n ; i++)
		{
			line = in.readLine().split(" ");
			points[i][0] = Integer.parseInt(line[0]);
			points[i][1] = Integer.parseInt(line[1]);
		}
		
		
		int[][] distances = new int[n+1][n+1];
		ComputeDistances(points, distances, n);
		
		int[] dp = new int[1<<n];
		int[] path = new int[1<<n];
		ComputeLowestPath(dp, path, distances, n);
		OutputLowestAndPath(dp, path, n);
		
	}
	
	

	private static void ComputeLowestPath(int[] dp, int[] path, int[][] distances, int n)
	{
		for(int i = 1; i < 1<<n; i++)
		{
			int j = 0;
			while(true) //find first 1 (LSB)
			{
				if((i&(1<<j))!=0) //check for a bit equal to 1 in location j
				{
					break;
				}
				j++;
			}
			
			int pastEntry = i & ~(1<<j); //remove first 1 from i
			path[i] = pastEntry; //set the path we came from to trace later
			int distance = distances[j][n] * 2; //distance from origin to the point being removed, and back
			dp[i] = dp[pastEntry] + distance; //set minimum distance if just this point is added to the collection
			
			for(int m = j +1; m < n; m++)
			{
				if((i & (1<<m))!=0)
				{
					int entry = i & ~((1<<j)|(1<<m)); //remove both 1 at position j and 1 at position m from i
					distance = distances[j][n] + distances[j][m] + distances[m][n]; //calculate round-trip distance to grab 2 points
					
					if(dp[i] > dp[entry] + distance) // check if this new option is a better choice.  If it is, update our value and our path.
					{
						dp[i] = dp[entry] + distance;
						path[i] = entry;
					}
				}
			}
			
		}
	}
	
	private static void OutputLowestAndPath(int[] dp, int[] path, int n)
	{
		
		StringBuilder out = new StringBuilder();
		out.append(dp[(1<<n)-1]);//output min value of traversal
		out.append("\n");
		out.append("0 ");
		int index = (1<<n)-1; //start at our min value
		while(index != 0)
		{
			int j = path[index];
			int k = index ^ j; //find the one or two values that were added at this step
			for(int m = 0; m < n; m++)
			{
				if((k & (1 << m)) != 0)
				{
					out.append(m+1);
					out.append(" ");
				}
			}
			out.append("0 ");
			index = j;
		}
		System.out.println(out.toString());
	}
	
	private static void ComputeDistances(int[][] points, int[][] distances, int n)
	{
		for(int i = 0; i <= n; i++) //calculate distances between all points so that we don't have to compute later
		{
			for(int j=i+1; j<=n; j++)
			{
				int x= points[i][0] - points[j][0];
				int y= points[i][1] - points[j][1];
				distances[i][j] = x*x + y*y;
			}
		}
	}
	
}
