import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokenizer;
		int N = Integer.parseInt(in.readLine());
		tokenizer = new StringTokenizer(in.readLine());
		int[] initialHills = new int[N+1];
		int[] hills = new int[N+1];
		int max = 0;
		int maxi= 0;
		
		for(int n=0; n<N; n+=1)
		{
			initialHills[n] = Integer.parseInt(tokenizer.nextToken());
			if(initialHills[n]>max)
			{
				max = initialHills[n];
				maxi=n;
			}
		}
		
		//rotate list to make maxi =0, and duplicate it to hills[N];
		for(int i=0; i<=N; i++)
		{
			hills[i] = initialHills[maxi];
			maxi = (maxi+1) % N;
		}
		
		hills[N] = hills[0];
		
		int[] right = new int[N+1];
		int[] left  = new int[N+1];
		int[] even  = new int[N+1];
		
		
		long sum = 0;
		for(int i = N-1; i>=0; --i)
		{
			right[i] = i+1;
			while( hills[i] > hills[right[i]] && right[i] < N)
				right[i] = right[right[i]];
			
			if(right[i] < N && hills[i]==hills[right[i]])
			{
				even[i] = even[right[i]] +1;
				right[i] = right[right[i]];
			}
		}
		
		for(int i=1; i<N; i++)
		{
			left[i] = i-1;
			while(hills[i] >= hills[left[i]] && left[i]>0)
				left[i] = left[left[i]];
		}
		
		
		sum+=even[0];
		
		for(int i=1; i<N; i++)
		{
			sum+=even[i];
			if(hills[i]<max)
			{
				if(left[i]==0 && right[i]==N)
					sum++;
				else
					sum+=2;
			}		
		}
		
		System.out.println(sum);
	}

}
