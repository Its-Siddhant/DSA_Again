class Solution{
static long LIM=1000001;

public String smallestPalindrome(String s,int k){
int[] f=new int[26];
for(int i=0;i<s.length();i++)f[s.charAt(i)-'a']++;

char m=0;
for(int i=0;i<26;i++){
if((f[i]&1)==1){
m=(char)('a'+i);
f[i]--;
break;
}
}

int[] h=new int[26];
int len=0;
for(int i=0;i<26;i++){
h[i]=f[i]/2;
len+=h[i];
}

if(k>cnt(h))return "";

StringBuilder a=new StringBuilder();
for(int i=0;i<len;i++){
for(int c=0;c<26;c++){
if(h[c]>0){
h[c]--;
long x=cnt(h);
if(x>=k){
a.append((char)('a'+c));
break;
}else{
k-=x;
h[c]++;
}
}
}
}

String b=new StringBuilder(a).reverse().toString();

StringBuilder r=new StringBuilder(a);
if(m!=0)r.append(m);
r.append(b);

return r.toString();
}

long cnt(int[] a){
int n=0;
for(int x:a)n+=x;
long r=1;
for(int i=0;i<26;i++){
r*=comb(n,a[i]);
if(r>=LIM)return LIM;
n-=a[i];
}
return r;
}

long comb(int n,int k){
if(k>n)return 0;
if(k>n-k)k=n-k;
long r=1;
for(int i=1;i<=k;i++){
r=r*(n-i+1)/i;
if(r>=LIM)return LIM;
}
return r;
}
}