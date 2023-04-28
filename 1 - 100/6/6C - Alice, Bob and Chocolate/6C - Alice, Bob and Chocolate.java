import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int[] bars = new int[N];
		String[] line = in.readLine().split(" ");
		int total = 0;
		for(int n = 0; n < N; n++)
		{
			bars[n] = Integer.parseInt(line[n]);
			total+=bars[n];
		}
		
		int leftP = 0;
		int rightP = N-1;
		int leftCount = 0;
		int rightCount = 0;
		int leftTime = 0;
		int rightTime = 0;
		
		for(int i =0; i < total; i++)
		{
			if(leftP==rightP-1)
			{
				break;
			}
			if(leftP==rightP)
			{
				rightP++;
				break;
			}
			if(leftTime==bars[leftP])
			{
				leftP++;
				leftTime=1;
			}
			else
			{
				leftTime++;
			}
			if(rightTime==bars[rightP])
			{
				rightP--;
				rightTime=1;
			}
			else
			{
				rightTime++;
			}
		}
		
		if(leftP==rightP)
		{
			rightP++;
		}
		leftCount = leftP+1;
		rightCount = N-rightP;
		
		//System.out.println("Left: " + leftCount);
		//System.out.println("Right: " + rightCount);
		System.out.println(leftCount + " " + rightCount);
		
	}

}
