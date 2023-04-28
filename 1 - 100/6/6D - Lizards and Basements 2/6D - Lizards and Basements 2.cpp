#include <algorithm>
#include <iostream>
#include <unordered_map>
#include <numeric>
#include <set>
#include <sstream>
#include <string>
#include <vector>
#include <math.h>
#include <fstream>
using namespace std;
#define LL long long

//#define  LOCAL

void solve(int h[], int n, const int dep, int a, int b, vector<int> &r, vector<int> &hist)
{
    if (dep == n - 1)
    {
        if (r.empty() || r.size() > hist.size())
           r = hist;
        return;
    }
    if (h[dep] < 0)
    {
        solve(h, n, dep + 1, a, b, r, hist);
    }
    else
    {
        //ct is the counter of fireballs on h[dep]
        //ct1 is the counter of fireballs on h[dep + 1]
        //try every combination of ct/ct1 such that h[dep] is killed
        //then go down recursively
        int ct = 0;
        //have + a here because we need to do one more iteration 
        //when h[dep] - ct * a < 0
        while (h[dep] - ct * a + a >= 0)
        {
            for (int i = 0; i < ct; ++i)
            {
                h[dep - 1] -= b;
                h[dep] -= a;
                h[dep + 1] -= b;
                hist.push_back(dep);
            }

            int ct1 = 0;
            while (h[dep] >= 0)
            {
                h[dep] -= b;
                h[dep + 1] -= a;
                h[dep + 2] -= b;
                hist.push_back(dep + 1);
                ++ct1;
            }
            solve(h, n, dep + 1, a, b, r, hist);
            while (ct1 > 0)
            {
                h[dep] += b;
                h[dep + 1] += a;
                h[dep + 2] += b;
                hist.pop_back();
                --ct1;
            }

            for (int i = 0; i < ct; ++i)
            {
                h[dep - 1] += b;
                h[dep] += a;
                h[dep + 1] += b;
                hist.pop_back();
            }
            ++ct;
        }       
        
    }
}

int main()
{
#ifdef LOCAL
    std::ifstream in("in.txt");
    std::streambuf *cinbuf = std::cin.rdbuf(); //save old buf
    std::cin.rdbuf(in.rdbuf()); //redirect std::cin to in.txt!
#endif

    int n, a, b;
    cin >> n >> a >> b;
    int h[16];
    for (int i = 1; i <= n; ++i)
        cin >> h[i];
    vector<int> hist;
    while (h[1] >= 0)
    {
        h[1] -= b;
        h[2] -= a;
        h[3] -= b;
        hist.push_back(2);
    }
    while (h[n] >= 0)
    {
        h[n] -= b;
        h[n - 1] -= a;
        h[n - 2] -= b;
        hist.push_back(n - 1);
    }
    vector<int> r;
    solve(h, n, 2, a, b, r, hist);
    cout << r.size() << endl;
    for (int i = 0; i < r.size(); ++i)
    {
        cout << r[i] << ' ';
    }
   
    return 0;
}
