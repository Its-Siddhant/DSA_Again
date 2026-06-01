class Solution {
    public int minimumCost(int[] cost) {

        // Frequency of each candy price (1 <= cost[i] <= 100)
        int[] hash = new int[101];

        // Count occurrences of every price
        for (int price : cost) {
            hash[price]++;
        }

        int ans = 0;

        // Number of candies already paid for in current group
        int c = 0;

        // Traverse prices from highest to lowest
        for (int price = 100; price >= 1; price--) {

            // Process all candies having this price
            while (hash[price] > 0) {

                // First two candies in a group must be paid for
                if (c < 2) {
                    ans += price;
                    c++;
                }
                // Third candy becomes free
                else {
                    c = 0;
                }

                hash[price]--;
            }
        }

        return ans;
    }
}