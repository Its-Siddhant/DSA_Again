class Solution{
int[] p={2,3,5,7};
int mx=p[p.length-1];

public String smallestNumber(String s,long t){
int[] c=new int[mx+1];
int n=s.length(),m,z=0;

for(int x:p){
while(t%x==0){
t/=x;
c[x]++;
}
}
if(t!=1)return "-1";

m=minLen(c);
if(n<m)return build(c,m,new char[m]);

char[] r=new char[n+1];
for(int i=0;z<n&&(r[++i]=s.charAt(z))!='0';z++)use(c,r[i],-1);

if(minLen(c)==0){
if(z==n)return s;
java.util.Arrays.fill(r,++z,r.length,'1');
return new String(r,1,n);
}

for(int last=n-1,end=Math.min(z,last);end>=0;end--){
for(use(c,r[end+1],1);++r[end+1]<='9';use(c,r[end+1],1)){
use(c,r[end+1],-1);
if(minLen(c)<=last-end)return build(c,last-end,r);
}
}
return build(c,r.length,r);
}

void use(int[] c,int x,int v){
if(x<'2')return;
if(x=='9')c[3]+=v<<1;
else if(x=='4')c[2]+=v<<1;
else if(x=='8')c[2]+=v*3;
else if(x=='6'){c[2]+=v;c[3]+=v;}
else c[x-'0']+=v;
}

String build(int[] c,int len,char[] r){
int i=r.length;
while(c[3]>1){c[3]-=2;r[--i]='9';}
while(c[2]>2){c[2]-=3;r[--i]='8';}
while(c[7]-->0)r[--i]='7';
if(c[2]>0&&c[3]>0){r[--i]='6';c[2]--;c[3]--;}
while(c[5]-->0)r[--i]='5';
while(c[2]>1){c[2]-=2;r[--i]='4';}
while(c[3]-->0)r[--i]='3';
while(c[2]-->0)r[--i]='2';
while(i+len!=r.length)r[--i]='1';
return len==r.length?new String(r):new String(r,1,r.length-1);
}

int minLen(int[] c){
int a=Math.max(0,c[2]),b=Math.max(0,c[3]);
int d=(b&1)+(a%3);
return (b>>1)+(a/3)+Math.max(0,c[7])+Math.max(0,c[5])+(d==3?2:d>0?1:0);
}
}