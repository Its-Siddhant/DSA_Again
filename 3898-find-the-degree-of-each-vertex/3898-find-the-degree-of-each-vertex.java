class Solution {
    public int[] findDegrees(int[][] matrix) {
        int n = matrix.length;
        int ans[] = new int[n];
        for(int i=0; i<n; i++){
            ans[i] = arrSum(matrix[i]);
        }
        return ans;

    }
    public static int arrSum(int[] arr){
        int sum = 0;
        for(int i=0; i<arr.length; i++){
            sum += arr[i];
        }
        return sum;
    }
}