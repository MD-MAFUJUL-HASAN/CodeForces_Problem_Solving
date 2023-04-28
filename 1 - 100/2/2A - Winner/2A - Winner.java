import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		TreeMap<String, Player> players = new TreeMap<String, Player>();
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int roundCount = Integer.parseInt(in.readLine());
		String name = "";
		int score;
		for(int i=0; i<roundCount; i++)
		{
			String[] line = in.readLine().split(" ");
			name = line[0];
			score = Integer.parseInt(line[1]);
			if(players.containsKey(name))
			{
				players.get(name).AddScore(score, i);				
			}
			else
			{
				players.put(name, new Player(score,i,name));
			}
		}
		
		
		in.close();
		
		
		int highestScore = Integer.MIN_VALUE;
		Player bestPlayer = new Player(Integer.MIN_VALUE, -1, "");
		Player currentPlayer;
		
		for(Map.Entry<String, Player> entry : players.entrySet())
		{
			currentPlayer = entry.getValue();
			
			if(currentPlayer.GetScore()>highestScore)
			{
				bestPlayer = entry.getValue();
				highestScore = currentPlayer.GetScore();
			}
			else if(currentPlayer.GetScore()==highestScore)
			{
				if(currentPlayer.GetEarliestHigh(highestScore) < bestPlayer.GetEarliestHigh(highestScore))
				{
					bestPlayer = entry.getValue();
					highestScore = currentPlayer.GetScore();
				}
			}
		}
		System.out.println(bestPlayer.GetName());
	}
}

class Player
{
	int lastRoundScored;
	int score;
	String name;
	TreeMap<Integer, Integer> runningTotal = new TreeMap<Integer,Integer>();
	
	public Player(int scoreIn, int roundIn, String nameIn)
	{
		name = nameIn;
		score = scoreIn;
		lastRoundScored = roundIn;
		runningTotal.put(roundIn, score);
	}
	
	public void AddScore(int roundScore,int roundNumber)
	{
		lastRoundScored = roundNumber;
		score += roundScore;
		runningTotal.put(roundNumber, score);
	}
	
	public int GetEarliestHigh(int scoreToBeat)
	{
		int earliestHigherScore = Integer.MAX_VALUE;
		
		for(Map.Entry<Integer, Integer> entry : runningTotal.entrySet())
		{
			if(entry.getValue() >= scoreToBeat && entry.getKey() < earliestHigherScore)
			{
				earliestHigherScore = entry.getKey();
			}
		}
		
		return earliestHigherScore;
	}
	
	public int GetScore()
	{
		return score;
	}
	
	public int GetRound()
	{
		return lastRoundScored;
	}
	
	public String GetName()
	{
		return name;
	}
}