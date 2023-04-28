import java.util.Scanner;

//This solution is for codeforces problem 1C
//http://www.codeforces.com/problemset/problem/1/C

public class Main {

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		//read in points
		double x1 = in.nextDouble();
		double y1 = in.nextDouble();
		double x2 = in.nextDouble();
		double y2 = in.nextDouble();
		double x3 = in.nextDouble();
		double y3 = in.nextDouble();
		in.close();
		//get side lengths
		double sideA = getLength(x1,y1,x2,y2);
		double sideB = getLength(x2,y2,x3,y3);
		double sideC = getLength(x3,y3,x1,y1);
		
		//get circumradius
		double CR = getCircumRadius(sideA, sideB, sideC);
		
		//get inner angles relating to our side lengths (not necessarily the polygon's side length)
		double angleA = getAngle(CR, sideA);
		double angleB = getAngle(CR, sideB);
		double angleC = getAngle(CR, sideC);
		
		//calculate number of sides
		int sides = getNumberOfSides(angleA, angleB, angleC);
		
		//calculate area
		double area = getArea(sides, CR);
		
		//output area
		System.out.println(area);
	}
	
	private static double getArea(int n, double R)
	{
		return .5*n*R*R*Math.sin((2*Math.PI)/n);
	}
	
	private static int getNumberOfSides(double A1, double A2, double A3)
	{
		double[] angles = {A1,A2,A3};
		Cmp_Swap(angles, 1,2);
		Cmp_Swap(angles, 0,2);
		Cmp_Swap(angles, 0,1);
		double angleSum = A1+A2+A3;
		//if our angles do not equal a full 2*PI then all of our lines are on the same side of the center
		//point and will not correctly reflect the ratio of angles to sides, so we need to get the inverse 
		//of the largest angle
		if(!(angleSum > 2* Math.PI - 1e-4 && angleSum < 2* Math.PI + 1e-4)) 
		{
			angles[2] = 2*Math.PI - angles[2];
		}
		double minAngle = angles[0];
		int n = 0;
		
		for(int i=2; i<35; i++)
		{
			if(modTol(minAngle,angles[1]) && modTol(minAngle,angles[2]))
			{
				n = Math.round(Math.round(angles[0]/minAngle + angles[1]/minAngle + angles[2]/minAngle));
				break;
			}
			else
			{
				minAngle = angles[0]/i;
			}
		}		
		return n;
	}
	
	private static boolean modTol(double a, double b)
	{
		double remainder = b%a;
		if(remainder<1e-4 && remainder > -1e-4)
		{
			return true;
		}
		else
		{
			double close = (remainder/a) - 1.0;
			if(close <1e-4 && close > -1e-4)
			{
				return true;
			}
		}
		
		return false;
		
	}
	
	private static void Cmp_Swap(double[] a, int i, int j)	
	{
		if(a[i]>a[j])
		{
			double swap = a[i];
			a[i] = a[j];
			a[j] = swap;
		}
	}
	
	private static double getAngle(double CircumRadius, double side)
	{
		double ratio = side/CircumRadius;
		if(ratio < 2+1e-5 && ratio > 2-1e-5)
		{
			return Math.PI;
		}
		return 2*Math.asin(side/(2*CircumRadius));
	}
	
	private static double getCircumRadius(double a, double b, double c)
	{
		return ((a*b*c) / Math.sqrt((a+b+c)*(b+c-a)*(c+a-b)*(a+b-c)));
	}
	
	private static double getLength(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt((x2-x1)*(x2-x1) + (y2 - y1)*(y2-y1));
	}
}
