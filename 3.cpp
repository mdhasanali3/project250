#include <bits/stdc++.h>
using namespace std;
#define pb push_back
#define mod 1000000007
#define ll long long
#define F first
#define S second
#define pii pair<int,int>
#define pi atan(1)*4
#define BS(x,xx) binary_search(x.begin(),x.end(),xx)
#define ALL(x) x.begin(),x.end()

int a[200005];

int main()
{
	int n,k;cin>>n>>k;
	map<int,int>mp;
	for(int i=0;i<n;i++)
	{
		int q;cin>>q;
		mp[q]++;
	}
	for(auto i1=mp.begin(),i2=mp.end();*i1<=*i2;i1++,i2--)
	{
		cout<<i2->S<<endl;
	}
}