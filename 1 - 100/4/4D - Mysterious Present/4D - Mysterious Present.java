import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// Jonathan
// cpt_fwiffo
// 2/16/2015

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int L = Integer.parseInt(line[1]);
		int R = Integer.parseInt(line[2]);
		
		int[][] envelopesTemp = new int[N][3];
		int index =0;
		int left = 0;
		int right = 0;
		for(int n = 0; n < N; n++)
		{
			line = in.readLine().split(" ");
			left = Integer.parseInt(line[0]);
			right = Integer.parseInt(line[1]);
			if(left > L && right > R)
			{
				envelopesTemp[index][0] = Integer.parseInt(line[0]);
				envelopesTemp[index][1] = Integer.parseInt(line[1]);
				envelopesTemp[index][2] = n+1;
				index++;
			}
		}
		
		int[] value = new int[index];
		int[] s = new int[index];
		
		for(int i=0; i < index ; i++)
		{
			value[i]=1;
			s[i] = -1;
		}
		
		int[][] envelopes = new int[index][3];
		for(int i = 0; i< index; i++)
		{
			envelopes[i][0] = envelopesTemp[i][0];
			envelopes[i][1] = envelopesTemp[i][1];
			envelopes[i][2] = envelopesTemp[i][2];
		}
		
		Arrays.sort(envelopes, compareProduct);
		int maxIndex = findMax(envelopes, value, s, index);
		
		
		if(maxIndex>-1)
		{
			System.out.println(value[maxIndex]);
			//System.out.println(selection[maxIndex]);
			int outIndex = maxIndex;
			String output = "";
			while(outIndex>=0)
			{
				output = envelopes[outIndex][2] + " " + output;
				outIndex = s[outIndex];
			}
			System.out.println(output);
		}
		else
		{
			System.out.println("0");
		}
		
	}
	
	static Comparator<int[]> compareProduct = new Comparator<int[]>()
			{

				@Override
				public int compare(int[] a, int[] b) {

					return (((long)a[0]*a[1]) > ((long)b[0]*b[1]))?1:-1;
				}
			
			};
	
	public static int findMax(int[][] envelopes, int[] value, int[] s, int indexCount)
	{
		int max = 1;
		int maxI = 0;
		if(indexCount <=0)
		{
			return -1;
		}
		
		for(int i=1; i < indexCount ; i++)
		{
			for(int j=i-1; j>= 0 ; j--)
			{
				if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1])
				{
					if(value[i]> value[j] +1)
					{
						value[i] = value[i];
					}
					else
					{
						value[i] = value[j] + 1;
						
						s[i] = j;
					}
					
				}
				
			}
			if(value[i] > max)
			{
				max = value[i];
				maxI = i;
			}
		}
		
		return maxI;
	}
}
