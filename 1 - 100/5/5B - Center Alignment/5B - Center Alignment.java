import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		List<String> lines = new ArrayList<String>();
		
		String line = in.readLine();
		int max = 0;
		while(line!=null)
		{
			lines.add(line);
			if(line.length()>max)
			{
				max = line.length();
			}
			if(in.ready())
			{
				line = in.readLine();
			}
			else
			{
				line=null;
			}
			
		}
		
		for(int i=0; i<max+2 ; i++)
		{
			output.append('*');
		}
		output.append('\n');
		
		int space = 0;
		boolean odd = false;
		boolean leftFirst = true;
		int right = 0;
		int left = 0;
		for(int i = 0; i<lines.size(); i++)
		{
			
			line = lines.get(i);
			space = max-line.length();
			odd = (space%2==0)?false:true;
			left = space >>1;
			right = space>>1;
			
			output.append('*');
			
			if(odd)
			{
				if(leftFirst)
				{
					right++;
				}
				else
				{
					left++;
				}
				leftFirst = !leftFirst;
			}
			
			for(int l = 0; l<left; l++)
			{
				output.append(' ');
			}
			output.append(line);
			
			for(int r = 0; r<right; r++)
			{
				output.append(' ');
			}
			
			output.append("*\n");
		}
		
		for(int i=0; i<max+2 ; i++)
		{
			output.append('*');
		}
		output.append('\n');
		
		System.out.println(output.toString());
	}

}
