import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;


//Jonathan
//cpt_fwiffo
// 3-3-15
public class Main {
	static class EndPair{
		public int start;
		public int end;
		EndPair(int s, int e)
		{
			start = s;
			end = e;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		String line = in.readLine();
		st = new StringTokenizer(line, " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] books = new int[n+1];
		line = in.readLine();
		st = new StringTokenizer(line, " ");
		for(int i=1; i<=n; i++)
		{
			books[i] = Integer.parseInt(st.nextToken());
		}
		
		List<EndPair> endPairs = new ArrayList<EndPair>();
		
		SortedMap<Integer, Integer> bookSubGroup = new TreeMap<Integer, Integer>();
		
		StringBuilder sb = new StringBuilder();
		int maxSubArrayLength = 0;
		int i=1;
		
		
		for(int j=1; j<=n; j++) //iterate through all books to find various subarrays
		{
			if(bookSubGroup.containsKey(books[j])) //if a book of same height is already in list just increment count, else add to list
			{
				bookSubGroup.put(books[j], bookSubGroup.get(books[j])+1); 
			}
			else
			{
				bookSubGroup.put(books[j], 1);
			}
			
			while(bookSubGroup.lastKey() - bookSubGroup.firstKey() > k) //if the difference in our book heights is greater than the max, move i forward removing books until back within the threshold
			{
				int count = bookSubGroup.get(books[i]); //get count of this same height book
				if(count==1) //only one book this height
				{
					bookSubGroup.remove(books[i]); //remove book
				}
				else //more than one book this height
				{
					bookSubGroup.put(books[i], count-1); //decrement book count
				}
				i++;
			}
			
			//now that interval is found, see if we should save it
			if(j - i +1 > maxSubArrayLength)
			{
				maxSubArrayLength = j-i+1;
				endPairs.clear(); //erase our list because we have a new max
				endPairs.add(new EndPair(i,j)); //save current interval
			}
			else if ( j-i+1 == maxSubArrayLength) //if we have a tie we should save the interval
			{
				endPairs.add(new EndPair(i,j));				
			}
			
		}
		sb.append(maxSubArrayLength + " " + endPairs.size() + "\n");
		for(EndPair p : endPairs)
		{
			sb.append(p.start + " " + p.end + "\n");
		}
		System.out.print(sb.toString());	
	}
	
	
}


