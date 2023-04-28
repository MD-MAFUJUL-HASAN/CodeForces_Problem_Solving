#include <algorithm>
#include <iostream>
#include <unordered_map>
#include <unordered_set>
#include <numeric>
#include <set>
#include <sstream>
#include <string>
#include <vector>
#include <math.h>
#include <fstream>
#include <stack>
#include <list>
using namespace std;
#define LL long long

//#define LOCAL

unordered_map<int, int> costTab;
unordered_map<int, vector<int> > pathTab;

//minCost is the minimum cost returned from lower levels
void helper(vector<vector<int> > &book, int &used, int &nUsed, int &minCost, vector<int> &minCostPath)
{
    int n = book.size();
    if (nUsed == n)
    {
        minCost = 0;
        minCostPath = vector<int>();
        return;
    }
    if (!costTab.count(used))
    {
        int resCost = INT_MAX;
        vector<int> resPath;
        vector<int> path;
        int i;
        for (i = 0; i < n; ++i)
            if (!(used & 1 << i))
                break; 
        int iDist = book[i][0] * book[i][0] + book[i][1] * book[i][1];
        used = used | 1 << i;
        ++nUsed;    
        path.push_back(i + 1);
        path.push_back(0);
        //take book i and go down recursion
        helper(book, used, nUsed, minCost, minCostPath);
        //if the cost is smaller, save it in resCost
        if (minCost + 2 * iDist < resCost)
        {
            resCost = minCost + 2 * iDist;
            resPath = path;
            resPath.insert(resPath.end(), minCostPath.begin(), minCostPath.end());
        }
        path.pop_back();
        for (int j = i + 1; j < n; ++j)
        {
            if (used & 1 << j)
                continue;
            int jDist = book[j][0] * book[j][0] + book[j][1] * book[j][1];
            int ijD = (book[i][0] - book[j][0]) * (book[i][0] - book[j][0]) + 
                (book[i][1] - book[j][1]) * (book[i][1] - book[j][1]);
            if (iDist + jDist < ijD)
                continue;
            used = used | 1 << j;
            ++nUsed; 
            path.push_back(j + 1);
            path.push_back(0);
            //take book i,j and go down recursion
            helper(book, used, nUsed, minCost, minCostPath);
            //if the cost is smaller, save it in resCost
            if (minCost + iDist + jDist + ijD < resCost)
            {
                resCost = minCost + iDist + jDist + ijD;
                resPath = path;
                resPath.insert(resPath.end(), minCostPath.begin(), minCostPath.end());
            }
            path.pop_back();
            path.pop_back();
            --nUsed;
            used = used & ~(1 << j);
        }
        path.pop_back();
        --nUsed;
        used = used & ~(1 << i);
        //save to hash
        costTab[used] = resCost;
        pathTab[used] = resPath;
    }
    minCost = costTab[used];
    minCostPath = pathTab[used];
}

int main()
{
#ifdef LOCAL
    std::ifstream in("in.txt");
    std::streambuf *cinbuf = std::cin.rdbuf(); //save old buf
    std::cin.rdbuf(in.rdbuf()); //redirect std::cin to in.txt!
#endif

    int x, y, n;
    cin >> x >> y >> n;
    vector<vector<int> > book(n, vector<int>(2));
    for (int i = 0; i < n; ++i)
    {
        cin >> book[i][0] >> book[i][1];
        book[i][0] -= x;
        book[i][1] -= y;
    }
    int used = 0;
    int nUsed = 0;
    int minCost = INT_MAX;
    vector<int> minCostPath;
    int cost = 0;
    vector<int> path;
    path.push_back(0);
    helper(book, used, nUsed, minCost, minCostPath);
    cout << minCost << endl;
    cout << 0 << ' ';
    for (int i = 0; i < minCostPath.size(); ++i)
        cout << minCostPath[i] << ' ';

    return 0;
}
