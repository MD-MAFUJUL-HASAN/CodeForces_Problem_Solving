import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] line = in.readLine().toCharArray();
		int maxLength = 0;
		int matchingLengthCount = 0;
		int openCount = 0;
		int closeCount = 0;
		int startIndex = 0;
		
		for(int i=0; i<line.length; i++)
		{
			
			if(line[i]=='(')
			{
				openCount++;
			}
			else if(line[i]==')')
			{
				closeCount++;
			}
			
			if(closeCount==openCount)
			{
				int length = i-(startIndex-1);
				if(length>maxLength)
				{
					maxLength = length;
					matchingLengthCount = 1;
				}
				else if(length==maxLength)
				{
					matchingLengthCount++;
				}
				openCount=0;
				closeCount=0;
				
			}
			else if(closeCount>openCount)
			{
				startIndex=i+1;
				openCount=0;
				closeCount=0;
			}
			
			
		}
		openCount = 0;
		closeCount = 0;
		startIndex = line.length-1;
		int matchingLengthCount2 =0;
		
		for(int i=line.length-1; i>=0; i--)
		{
			
			if(line[i]=='(')
			{
				openCount++;
			}
			else if(line[i]==')')
			{
				closeCount++;
			}
			
			if(closeCount==openCount)
			{
				int length = startIndex-(i-1);
				if(length>maxLength)
				{
					maxLength = length;
					matchingLengthCount2 = 1;
				}
				else if(length==maxLength)
				{
					matchingLengthCount2++;
				}
				openCount=0;
				closeCount=0;
				
			}
			else if(openCount>closeCount)
			{
				startIndex=i-1;
				openCount=0;
				closeCount=0;
			}
			
			
		}
		
		matchingLengthCount = Math.max(matchingLengthCount, matchingLengthCount2);
		
		
		if(maxLength==0)
		{
			matchingLengthCount = 1;
		}
		System.out.println(maxLength + " " + matchingLengthCount);

	}

}
