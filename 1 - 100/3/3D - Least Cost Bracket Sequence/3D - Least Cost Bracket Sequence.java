import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class Main {
	
	public static class CostPair implements Comparable<CostPair>{
		public int difference;
		public int index;
		public CostPair(int differenceIn, int indexIn)
		{
			difference = differenceIn;
			index = indexIn;
		}
		
		@Override
		public int compareTo(CostPair c)
		{
			return this.difference - c.difference;
		}
		
	}
	
	static int[][] Qs;
	static char[] ps;
	static PriorityQueue<CostPair> cp;
	public static void main(String[] args) throws IOException
	{
				
		readInput(); //read all data
		int pivot = findWorstCase();
		cp = new PriorityQueue<CostPair>();
		
		long cost1 = scanFromLeftBaseCase(pivot);
		long cost2 = scanFromRightBaseCase(pivot);
		
		if(cost1==-1||cost2==-1)
		{
			System.out.println(-1);
			return;
		}
		
		long fullCost = cost1+cost2;
		
		System.out.println(fullCost);
		System.out.println(new String(ps));
		//System.out.println("pivot: " + pivot);
	}
	
	
	public static void readInput() throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ps = in.readLine().toCharArray();

        int qs = 0;
        
        //start reading input
        int[] qIndexes = new int[ps.length];
        for(int i=0; i<ps.length; i++)
        {
            if(ps[i]=='?')
            {
                qIndexes[qs] = i;
                qs++;
            }

        }
        
        
        Qs = new int[qs][4];
       
        String[] digitPair;
        for(int i=0; i<qs; i++)
        {
            digitPair = in.readLine().split(" ");
            Qs[i][2] = Integer.parseInt(digitPair[0]);
            Qs[i][3] = Integer.parseInt(digitPair[1]);
            Qs[i][0] = Math.abs(Qs[i][2]-Qs[i][3]);
            Qs[i][1] = qIndexes[i];
           
        }
        
        //end reading input
	}
	
	private static long scanFromLeftBaseCase(int pivot)
	{
		int qIndex = 0;
		int count =0;
		long totalCost =0;
		for(int i=0; i< pivot; i++)
		{
			if(ps[i]=='(')
			{
				count++;
			}
			else if(ps[i]==')')
			{
				count--;
			}
			else
			{
				if(Qs[qIndex][2]<=Qs[qIndex][3])
				{
					count++;
					ps[i]='(';
					totalCost+=Qs[qIndex][2];
				}
				else
				{
					count--;
					ps[i]=')';
					totalCost+=Qs[qIndex][3];
					cp.add(new CostPair(Qs[qIndex][2]-Qs[qIndex][3],i));
				}
				qIndex++;
				
			}
			if(count<0)
			{
				CostPair c = cp.poll();
				if(c!=null)
				{
					count+=2;
					ps[c.index] = '(';
					totalCost+= c.difference;
				}
				else
				{
					
					return -1;
				}
				
			}
		}
		return totalCost;
	}
	
	private static long scanFromRightBaseCase(int pivot)
	{
		cp.clear();
		int qIndex = Qs.length-1;
		int count =0;
		long totalCost =0;
		for(int i=ps.length-1; i>=pivot; i--)
		{
			if(ps[i]==')')
			{
				count++;
			}
			else if(ps[i]=='(')
			{
				count--;
			}
			else
			{
				if(Qs[qIndex][2]>Qs[qIndex][3])
				{
					count++;
					ps[i]=')';
					totalCost+=Qs[qIndex][3];
					
				}
				else
				{
					count--;
					ps[i]='(';
					totalCost+=Qs[qIndex][2];
					cp.add(new CostPair(Qs[qIndex][3]-Qs[qIndex][2],i));
				}
				qIndex--;
				
			}
			if(count<0)
			{
				CostPair c = cp.poll();
				if(c!=null)
				{
					count+=2;
					ps[c.index] = ')';
					totalCost+= c.difference;
				}
				else
				{
					
					return -1;
				}
			}
		}
		return totalCost;
	}
	
	private static int findWorstCase()
	{
		int count = 0;
		int countMin = 0;
		int minIndex = 0;
		int qIndex = 0;
		for(int i=0; i<ps.length; i++)
		{
			if(ps[i]=='(')
			{
				count++;
			}
			else if(ps[i]==')')
			{
				count--;
				if(count<countMin)
				{
					minIndex = i+1;
					countMin = count;
				}
			}
			else
			{
				if(Qs[qIndex][2]<=Qs[qIndex][3])
				{
					count++;
				}
				else
				{
					count--;
					if(count<countMin)
					{
						minIndex = i+1;
						countMin = count;
					}
				}
				qIndex++;
			}
		}
		return minIndex;
	}
	
	

}
