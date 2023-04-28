import java.util.Scanner;


public class Main {
	
	//static int[][] boatArray;
	static int[][] ones;
	static int[][] twos;
	

	
	public static void main(String[] args)
	{
		
		Scanner in = new Scanner(System.in);
		int boatCount = in.nextInt();
		int volume = in.nextInt();
		
		
		
		//boatArray = new int[3][boatCount];
		ones = new int[2][boatCount];
		twos = new int[2][boatCount];
		
		int type= 0;
		int cap = 0;
		int oneCount = 0;
		int twoCount = 0;
		
		for(int i=0; i<boatCount; i++)
		{
			type = in.nextInt();
			cap = in.nextInt();
			
			if(type==1)
			{
				ones[0][oneCount] = i;
				ones[1][oneCount] = cap;
				oneCount++;
			}
			else
			{
				twos[0][twoCount] = i;
				twos[1][twoCount] = cap;
				twoCount++;
			}
		}
		sort(0, oneCount-1, 1);
		sort(0, twoCount-1, 2);
		
		in.close();
		int onesUsed = 0;
		int twosUsed = 0;
		int capacity = 0;
		for(int i=0; i<oneCount && onesUsed<volume; i++)
		{
			capacity += ones[1][i];			
			onesUsed++;
		}
		
		int bestOnes = onesUsed;
		int bestTwos = 0;
		int bestTotal = capacity;
		
		for(int j=0; j<twoCount && onesUsed>0; j++)
		{
			if(onesUsed + twosUsed*2== volume)
			{
				if(onesUsed>1)
				{
					
					capacity-= ones[1][onesUsed-1];
					capacity-= ones[1][onesUsed-2];
					onesUsed-=2;
					capacity+= twos[1][j];
					twosUsed++;
					if(capacity>bestTotal)
					{
						bestOnes = onesUsed;
						bestTwos = twosUsed;
						bestTotal = capacity;
					}
				}
			}
			else if(onesUsed + twosUsed*2== volume-1)
			{
				
				capacity-= ones[1][onesUsed-1];
				onesUsed--;
				capacity+= twos[1][j];
				twosUsed++;
				if(capacity>bestTotal)
				{
					bestOnes = onesUsed;
					bestTwos = twosUsed;
					bestTotal = capacity;
				}
			}
			else if(onesUsed + twosUsed*2<= volume-2)
			{
				
				capacity+= twos[1][j];
				twosUsed++;
				if(capacity>bestTotal)
				{
					bestOnes = onesUsed;
					bestTwos = twosUsed;
					bestTotal = capacity;
				}
			}
		}
		
		
		StringBuffer sb = new StringBuffer();
		
		
		System.out.println(bestTotal);
	
		boolean first = true;
		for(int i=0;i<bestOnes;i++)
		{
			sb.append(ones[0][i]+1);
			first = false;
			if(i!=bestOnes-1)
			{
				sb.append(" ");
			}
		}
		for(int i=0;i<bestTwos;i++)
		{
			if(!first)
			{
				sb.append(" ");
			}
			first = false;
			sb.append(twos[0][i]+1);
			
		}
		System.out.println(sb.toString());
		
	}
	
	private static void sort(int low, int hi, int array)
	{
		if(hi<=low)
			return;
		int j = hi+1;
		int i = low;
		if(array==1)
		{
			int v = ones[1][low];
			
			while(true)
			{
				while(ones[1][++i]>v)
						if(i==hi)
							break;
				while(ones[1][--j]<v)
						if(j==low)
							break;
				if(i>=j)
					break;
				
				swap(i, j, 1);
			}
			swap(low, j, 1);
			sort(low, j-1, 1);
			sort(j+1, hi, 1);
		}
		else
		{
			int v = twos[1][low];
			
			while(true)
			{
				while(twos[1][++i]>v)
						if(i==hi)
							break;
				while(twos[1][--j]<v)
						if(j==low)
							break;
				if(i>=j)
					break;
				
				swap(i, j, 2);
			}
			swap(low, j, 2);
			sort(low, j-1, 2);
			sort(j+1, hi, 2);
		}
		
	}
	
	private static void swap(int i, int j, int array)
	{
		if(array==1)
		{
			int hold = ones[0][i];
			ones[0][i] = ones[0][j];
			ones[0][j] = hold;
			
			hold = ones[1][i];
			ones[1][i] = ones[1][j];
			ones[1][j] = hold;
		}
		else
		{
			int hold = twos[0][i];
			twos[0][i] = twos[0][j];
			twos[0][j] = hold;
			
			hold = twos[1][i];
			twos[1][i] = twos[1][j];
			twos[1][j] = hold;
		}
		 
	}
	
}
