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

int main()
{
#ifdef LOCAL
    std::ifstream in("in.txt");
    std::streambuf *cinbuf = std::cin.rdbuf(); //save old buf
    std::cin.rdbuf(in.rdbuf()); //redirect std::cin to in.txt!
#endif

    int t, m;
    cin >> t >> m;
    int index = 1;
    int v[101] = {0};
    vector<int> used;
    for (int j = 0; j < t; ++j)
    {
        string op;
        cin >> op;
        if (op == "alloc")
        {
            int len;
            cin >> len;
            int k = 0;            
            bool found = false;
            while (k < m)
            {
                int l = 0;
                while (k < m && v[k] == 0 && l < len)
                {
                    ++k;
                    ++l;
                }
                if (l == len)
                {
                    while (l-- > 0)
                        v[--k] = index;
                    ++index;
                    found = true;
                    break;
                }
                ++k;
            }
            if (found)
                cout << index - 1 << endl;
            else
                cout << "NULL" << endl;
        } 
        else if (op == "erase")
        {
            LL n;
            cin >> n;
            bool found = false;
            if (n > 0 && n < 101)
            {
                for (int k = 0; k < m; ++k)
                {
                    if (v[k] == n)
                    {
                        found = true;
                        v[k] = 0;
                    }
                }
            }           
            if (!found)
                cout << "ILLEGAL_ERASE_ARGUMENT" << endl;
        }
        else if (op == "defragment")
        {
            int p1 = 0;
            for (int p2 = 0; p2 < m; ++p2)
            {
                if (v[p2])
                {
                    v[p1++] = v[p2];
                }
            }
            for (; p1 < m; ++p1)
                v[p1] = 0;
        }
    }

    return 0;
}
