import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		HashSet<String> peopleSet = new HashSet<String>();
		char[] chars;
		int total = 0;
		while(in.ready())
		{	
			line = in.readLine();
			chars = line.toCharArray();
			if(chars[0] == '+')
			{
				peopleSet.add(line.substring(1, line.length()));
			}
			else if(chars[0] == '-')
			{
				//if(peopleSet.contains(line.substring(1, line.length())))
					peopleSet.remove(line.substring(1, line.length()));
			}
			else
			{
				int index = -1;
				for(int i=0; i<chars.length ;i++)
				{
					if(chars[i]==':')
					{
						index = i;
						break;
					}
				}
				if(index!=-1)
				{
					total+= peopleSet.size() * (line.length()-(index+1));
				}
			}
		}
		System.out.println(total);
	}

}
