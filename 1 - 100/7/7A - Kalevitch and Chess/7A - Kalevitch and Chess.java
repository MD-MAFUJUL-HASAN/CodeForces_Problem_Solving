import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int count=0;
		int firstFalse = -1;
		String[] lines = new String[8];
		for(int i=0; i<8; i++)
		{
			lines[i] = in.readLine();
			if(lines[i].equals("BBBBBBBB"))
			{
				count++;
			}
			else
			{
				if(firstFalse==-1)
					firstFalse = i;
			}
		}
		
		if(firstFalse!=-1)
		{
			for(int i=0; i<8; i++)
			{
				if(lines[firstFalse].charAt(i)=='B')
				{
					count++;
				}
			}
		}
		System.out.println(count);

	}
	
	

}
