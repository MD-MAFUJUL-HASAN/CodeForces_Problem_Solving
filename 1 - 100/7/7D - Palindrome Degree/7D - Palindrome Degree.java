import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		int n = line.length();
		int n2 = n*2;
		int[] Ps = new int[n*2];
		int[] DP = new int[n+1];
		
		String reverse = new StringBuilder(line).reverse().toString();
		String jointLine = line + reverse;
		ZSearch(Ps, jointLine, jointLine.length());
		
		int degree = 0;
		for(int i=1; i <= n ; i++)
		{
			if(Ps[n2-i]==i)
			{
				DP[i] = DP[i>>1] + 1;
				degree+=DP[i];
			}
		}
		System.out.println(degree);
		//System.out.println(Arrays.toString(Ps));
	}
	
	private static void ZSearch(int[] array, String line, int n)
	{
		int L = 0;
		int R = 0;
		for(int i = 0; i < n; i++)
		{
			if ( i > R)
			{
				L = i;
				R = i;
				while(R < n && line.charAt(R-L) == line.charAt(R))
				{
					R++;
				}
				array[i] = R-L; 
				R--;
				
			}
			else
			{
				int k = i-L;
				if(array[k] < R-i+1)
				{
					array[i] = array[k];
				}
				else
				{
					L = i;
					while(R < n && line.charAt(R-L) == line.charAt(R))
					{
						R++;
					}
					array[i] = R-L;
					R--;
				}
			}
		}
	}

}
