#include<iostream>
#include<cstring>
#include<stack>
#include<cstdlib>
#define maxn 1010 
#define LL long long int
using namespace std;

int n;
int a[maxn][maxn],dp[2][maxn][maxn];
bool way[2][maxn][maxn];

void count(int x,int &a,int &b){
	a=b=0;
	while(x&&!(x%2)){
		x/=2;
		++a;
	}
	while(x&&!(x%5)){
		x/=5;
		++b;
	}
}

void zeroout(int x,int y){
	cout<<1<<endl;
	int i=1,j=1;
	while(i<x){
		cout<<'D';
		++i;
	}
	while(j<y){
		cout<<'R';
		++j;
	}
	while(i<n){
		cout<<'D';
		++i;
	}
	while(j<n){
		cout<<'R';
		++j;
	}
	cout<<endl;
}

int main(){
	ios::sync_with_stdio(false);
	
	int i,j,ans=0,zeroi=0,zeroj=0;
	cin>>n;
	for(i=1;i<=n;++i)
		for(j=1;j<=n;++j)
			cin>>a[i][j];
	
	if(!a[1][1]){
		zeroout(1,1);
		return 0; 
	}
	
	for(i=0;i<=n;++i)
		for(j=0;j<=n;++j)
			for(int k=0;k<2;++k)
				dp[k][i][j]=0x7FFFFFFF;
	
	int x,y;
	count(a[1][1],x,y);
	dp[0][1][1]=x;
	dp[1][1][1]=y;
	for(i=1;i<=n;++i)
		for(j=1;j<=n;++j)
			if(!(i==1&&j==1))
				if(a[i][j]){
					count(a[i][j],x,y);
					if(dp[0][i-1][j]<dp[0][i][j-1]){
						dp[0][i][j]=dp[0][i-1][j]+x;
						way[0][i][j]=0;
					}
					else{
						dp[0][i][j]=dp[0][i][j-1]+x;
						way[0][i][j]=1;
					}
					if(dp[1][i-1][j]<dp[1][i][j-1]){
						dp[1][i][j]=dp[1][i-1][j]+y;
						way[1][i][j]=0;
					}
					else{
						dp[1][i][j]=dp[1][i][j-1]+y;
						way[1][i][j]=1;
					}
				}
				else{
					zeroi=i;
					zeroj=j;
				}
			
	if(dp[0][n][n]<dp[1][n][n]){
		ans=0;
		x=dp[0][n][n];
	} 
	else{
		ans=1;
		x=dp[1][n][n];
	}
	
	if(x>=1&&zeroi&&zeroj)
		zeroout(zeroi,zeroj);
	else{
		cout<<x<<endl;
		i=n;j=n;
		bool b;
		stack<char> out;
		while(!(i==1&&j==1)){
			b=way[ans][i][j];
			if(!b){
				out.push('D');
				--i;
			}
			else{
				out.push('R');
				--j;
			}
		}
	
		while(!out.empty()){
			cout<<out.top();
			out.pop();
		}
		cout<<endl;
	}
	return 0;
} 
