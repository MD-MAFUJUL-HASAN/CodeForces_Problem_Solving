import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//This solution is for problem 1B on Codeforces
// http://www.codeforces.com/problemset/problem/1/B

public class Main {

	public static void main(String[] args) throws Exception
	{		
		
		
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			int count = 0;
			int firstNumIndex = 0;
			int lastCharIndex = 0;
			String charString = "0ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			char[] charSet = {'0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
			count = Integer.parseInt(in.readLine());
			String line="";
			char[] lineChar;
			for(int i=0; i<count; i++)
			{
				firstNumIndex =0;
				lastCharIndex = 0;
				line = in.readLine();
				lineChar = line.toCharArray();
				for(int j=0;j<lineChar.length;j++)
				{
					if(!Character.isLetter(lineChar[j])&&firstNumIndex == 0)
					{
						firstNumIndex = j;
					}
					else if(Character.isLetter(lineChar[j]))
					{
						lastCharIndex = j;
					}
				}
				
				if(lastCharIndex>firstNumIndex)
				{
					
					//this case is when starting with an R##C## value
					char[] first = Arrays.copyOfRange(lineChar, 1, lastCharIndex);
					char[] second = Arrays.copyOfRange(lineChar, lastCharIndex+1, lineChar.length);
					int firstInt = Integer.parseInt(new String(first));
					int secondInt = Integer.parseInt(new String(second));
					int[] let = {-1,-1,-1,-1,-1};
					
					int divisor=26;
					
					int index = 4;
					int remainder = 0;
					while(secondInt>divisor)
					{
						remainder = secondInt % divisor;
						secondInt /= divisor;
						if(remainder == 0)
						{
							remainder = 26;
							secondInt -=1;
						}
						let[index] = remainder;
						index--;						
					}
					let[index] = secondInt;
					
					
					StringBuffer sb = new StringBuffer();
					for(int l = 0; l<let.length;l++)
					{
						if(let[l]!=-1)
						{
							
								sb.append(charSet[let[l]]);	
							
						}
					}
					sb.append(firstInt);					
					System.out.println(sb.toString());
					
				}
				else
				{
					int value = 0;
					int totalValue = 0;
					//this case is when starting with an AA## value
					for(int k=firstNumIndex-1; k>=0; k--)
					{
						value = charString.indexOf(String.valueOf(lineChar[k]));
						totalValue += value*(raise(26,(firstNumIndex-1)-k));
					}
					StringBuffer sb = new StringBuffer();
					sb.append("R");
					sb.append(Arrays.copyOfRange(lineChar, lastCharIndex+1, lineChar.length));
					sb.append("C");
					sb.append(totalValue);
					System.out.println(sb.toString());
				}
				
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	private static int raise(int number, int power)
	{
		if(power==0)
		{
			return 1;
		}
		else if(power==1)
		{
			return number;
		}
		else
		{
			return number*(raise(number, power-1));
		}
	}
	
}
