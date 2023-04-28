import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		String[] values = line.split(" ");
		int Rows = Integer.parseInt(values[0]);
		int Cols = Integer.parseInt(values[1]);
		char Desk = values[2].toCharArray()[0];
		
		char[][] dMap = new char[Rows][Cols];
		
		for(int r = 0; r<Rows; r++)
		{
			dMap[r] = in.readLine().toCharArray();
		}
		
		Set<Character> colors = new HashSet<Character>();
		
		for(int i=0; i<Rows; i++)
		{
			for(int j=0; j<Cols; j++)
			{
				
				if(dMap[i][j]==Desk)
				{
					if(i>0 && dMap[i-1][j]!='.')
					{
						colors.add(dMap[i-1][j]);
					}
					
					if(j>0 && dMap[i][j-1]!='.')
					{
						colors.add(dMap[i][j-1]);
					}
					
					if(i<Rows-1 && dMap[i+1][j]!='.')
					{
						colors.add(dMap[i+1][j]);
					}
					
					if(j<Cols-1 && dMap[i][j+1]!='.')
					{
						colors.add(dMap[i][j+1]);
					}
				}
			}
		}
		
		if(colors.contains(Desk))
		{
			colors.remove(Desk);
		}
		
		System.out.println(colors.size());
		
	}

}
