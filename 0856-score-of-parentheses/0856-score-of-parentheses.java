class Solution{
    public int scoreOfParentheses(String s){
        Stack<Integer> stack=new Stack<>();
        stack.push(0);
        for(char ch:s.toCharArray()){
            if(ch=='('){
                stack.push(0);
            }else{
                int x=stack.pop();
                if(x==0){
                    stack.push(stack.pop()+1);
                }else{
                    stack.push(stack.pop()+2*x);
                }
            }
        }
        return stack.pop();
    }
}