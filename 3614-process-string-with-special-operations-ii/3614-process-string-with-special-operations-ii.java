class Solution {
    public char processStr(String s, long k) {
        long len=0;
        for (char c:s.toCharArray()) {
            if (c=='*') {
                if(len>0) len--;
            } else if(c=='#') {
                len*=2;
            } else if(c!='%') {
                len++;
            }
        }
        if(k>=len) return '.';

        for (int i =s.length()-1;i>=0;i--) {
            char c =s.charAt(i);
            if(c=='*') 
            {
                len++;
            }
            else if(c=='#') {
                long half =(len+1)/2;
                if(k>=half) k-=len/2;
                len=half;
            } else if(c=='%') {
                k=len-k-1;
            } else{
                if(k==len-1) return c;
                len--;
            }
        }
        return '.';
    }
}