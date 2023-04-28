#include <iostream>
#include <cstdio>
#include <cctype>
#define maxn 1000010
#define ll long long
#define gc getchar
using namespace std;

int read() {
    int x = 0; char c = gc();
    while (!isdigit(c)) c = gc();
    while (isdigit(c)) x = x * 10 + c - '0', c = gc();
    return x;
}

int n, a[maxn], b[maxn];

struct node {
    int v, s;

    node() {}
    node(int _v, int _s) { v = _v; s = _s; } 
} st[maxn]; int top; 


bool vis[maxn];

ll ans;
int main() {
    n = read();
    for (int i = 1; i <= n; ++i) a[i] = b[i] = read(); 
    int mx = 0, p; 
    for (int i = 1; i <= n; ++i) if (a[i] > mx) mx = a[i], p = i;
    for (int i = 1; i <= n - p + 1; ++i) a[i] = b[p + i - 1];
    for (int i = n - p + 2; i <= n; ++i) a[i] = b[i - n + p - 1];
    for (int i = 1; i <= n; ++i) {
        while (top && a[i] > st[top].v) ans += st[top--].s;
        if (a[i] != st[top].v) ans += top > 0, st[++top] = node(a[i], 1);
        else ans += (top > 1) + st[top].s++;
    }
    mx = 0;
    for (int i = 2; i <= n; ++i)
        if (a[i] >= mx) mx = a[i], vis[i] = 1; 
    mx = 0; 
    for (int i = n; i > 1; --i) 
        if (a[i] >= mx) mx = a[i], ans += !vis[i]; 
    cout << ans << endl; 
    return 0;
}
