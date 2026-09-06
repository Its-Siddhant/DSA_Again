import java.util.Arrays;

class Solution {
    public int numDistinct(String s, String t) {
        final int sl = s.length();
        final int tl = t.length();
        final int[][] dp = initializeDp(sl, tl, -1);
        return usingSpaceOptimization(s, t, sl, tl);
    }

    // Recursive Approach
    private int recursiveSolution(final String s, final String t, final int sidx, final int tidx) {
        if (tidx < 0) return 1;
        if (sidx < 0) return 0;
        if (s.charAt(sidx) == t.charAt(tidx)) {
            return recursiveSolution(s, t, sidx - 1, tidx - 1) + recursiveSolution(s, t, sidx - 1, tidx);
        }
        return recursiveSolution(s, t, sidx - 1, tidx);
    }

    // Memoization Approach
    private int memorizationSolution(final String s, final String t, final int sidx, final int tidx, final int[][] dp) {
        if (tidx < 0) return 1;
        if (sidx < 0) return 0;
        if (dp[sidx][tidx] != -1) return dp[sidx][tidx];

        if (s.charAt(sidx) == t.charAt(tidx)) {
            dp[sidx][tidx] = memorizationSolution(s, t, sidx - 1, tidx - 1, dp) +
                             memorizationSolution(s, t, sidx - 1, tidx, dp);
        } else {
            dp[sidx][tidx] = memorizationSolution(s, t, sidx - 1, tidx, dp);
        }
        return dp[sidx][tidx];
    }

    // Tabulation Approach
    private int usingTabulation(final String s, final String t, final int sl, final int tl) {
        final int[][] dp = new int[sl + 1][tl + 1];

        // Base case: empty `t` is always a subsequence of any `s`
        for (int i = 0; i <= sl; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= sl; i++) {
            for (int j = 1; j <= tl; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[sl][tl];
    }

    // Space Optimized Approach
    private int usingSpaceOptimization(final String s, final String t, final int sl, final int tl) {
        int[] prev = new int[tl + 1];
        prev[0] = 1;

        for (int i = 1; i <= sl; i++) {
            final int[] curr = new int[tl + 1];
            curr[0] = 1;
            for (int j = 1; j <= tl; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + prev[j];
                } else {
                    curr[j] = prev[j];
                }
            }
            prev = curr;
        }
        return prev[tl];
    }

    // Helper method to initialize DP array
    private int[][] initializeDp(final int m, final int n, final int val) {
        final int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], val);
        }
        return dp;
    }
}