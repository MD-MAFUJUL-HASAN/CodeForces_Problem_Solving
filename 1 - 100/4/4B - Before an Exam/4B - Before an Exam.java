import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Jonathan
// cpt_fwiffo
// 2/22/15

public class Main {

	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		int P = Integer.parseInt(line[0]);
		int T = Integer.parseInt(line[1]);
		
		int[][] pairs = new int[P][3];
		
		for(int p=0; p < P; p++)
		{
			line = in.readLine().split(" ");
			pairs[p][0] = Integer.parseInt(line[0]); 
			pairs[p][1] = Integer.parseInt(line[1]);
			pairs[p][2] = p;
		}
		
		//If Target is greater than max possible amount or less than min possible amount the Target can't be reached
		int max = 0;
		int min = 0;
		for(int i=0; i < P; i++)
		{
			min+=pairs[i][0];
			max+=pairs[i][1];
		}
		
		if(T < min || T > max)
		{
			System.out.println("NO");
			
		}
		else
		{
			
			int[] picks = new int[P];
			int sum = 0;
			for(int i=0; i< P; i++)
			{
				picks[i] = pairs[i][0]; //start with mins on all
				sum+=picks[i];
			}
			
			for(int i=0; i< P; i++) //Increase each value either up to their max or until we reach the target
			{
				while(picks[i]<pairs[i][1] && sum < T)
				{
					picks[i]++;
					sum++;
				}
				if(sum==T)
				{
					System.out.println("YES");
					break;
				}
			}
			
			for(int i=0; i<P ; i++)
			{
				System.out.print(picks[i] + " ");
			}
			
		}
		
	}

}
