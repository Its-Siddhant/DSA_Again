class Solution {
    public int maxSubarraySumCircular(int[] nums) {

        // Max Subarray (Kadane)
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;

        for(int num : nums){
            currSum = Math.max(num, currSum + num);
            maxSum = Math.max(maxSum, currSum);
        }

        // Min Subarray (Kadane)
        int minSum = Integer.MAX_VALUE;
        currSum = 0;

        for(int num : nums){
            currSum = Math.min(num, currSum + num);
            minSum = Math.min(minSum, currSum);
        }

        // Total Sum
        int totalSum = 0;
        for(int num : nums){
            totalSum += num;
        }

        int maxCircular = totalSum - minSum;

        if(maxSum > 0){
            return Math.max(maxSum, maxCircular);
        }

        return maxSum;
    }
}