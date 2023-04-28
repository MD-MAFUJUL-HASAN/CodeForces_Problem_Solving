import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		String line = "";
		String newLine = "";
		for(int n=0; n<N; n++)
		{
			line = in.readLine();
			if(!map.containsKey(line))
			{
				map.put(line, 0);
				out.append("OK\n");
			}
			else
			{
				newLine = line + (map.get(line)+1);
				map.put(newLine, 0); //create new entry
				map.put(line, (map.get(line)+1)); //update counter for suffixes
				out.append(newLine + "\n");
			}
		}
		System.out.println(out);

	}

}
