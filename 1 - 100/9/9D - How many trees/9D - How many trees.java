import java.util.Scanner;



public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		in.close();
		long[][] trees = new long[n+1][k+1];
		trees[0][0] = 1; //set base case
		
		for(int i=1; i<=n ; i++) //start at base of having only a single node element
		{
			for(int j=0; j<=Math.min(i, k); j++)//calculate possible solutions meeting requirements of different tree heights
			{
				for(int m=1; m<=i; m++)//sum all possible child trees
				{
					
					if(j==0) //special case
					{
						trees[i][j] += trees[m-1][ 0 ] * trees[i-m][ 0 ];
					}
					else
					{
						trees[i][j] += trees[m-1][ 0 ] * trees[i-m][j-1]; //add one child tree 
						trees[i][j] += trees[m-1][j-1] * trees[i-m][ 0 ]; //add other child tree
						trees[i][j] -= trees[m-1][j-1] * trees[i-m][j-1]; //remove double counted items
					}
				}
			}
		}
		System.out.println(trees[n][k]);
	}

}
