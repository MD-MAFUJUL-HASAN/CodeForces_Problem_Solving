import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] first = in.readLine().toCharArray();
		char[] second = in.readLine().toCharArray();
		int x = 0;
		int y = 0;
		x = second[0]-first[0];
		y = Integer.parseInt(Character.toString(second[1])) - Integer.parseInt(Character.toString(first[1]));
		
		int minMoves = Math.max(Math.abs(x), Math.abs(y));
		
		StringBuilder sb = new StringBuilder();
		System.out.println(minMoves);
		for(int i=0; i<minMoves; i++)
		{
			if(x<0)
			{
				sb.append("L");
				x++;
			}
			else if(x>0)
			{
				sb.append("R");
				x--;
			}
			
			if(y<0)
			{
				sb.append("D");
				y++;
			}
			else if(y>0)
			{
				sb.append("U");
				y--;
			}
			
			sb.append("\n");
				
			
		}
		
		System.out.println(sb.toString());
	}
	
	

}
