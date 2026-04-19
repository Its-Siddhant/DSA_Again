class Solution {
    public int maxDistance(int[] A, int[] B) {
        int i, j;

        for (i = 0, j = 1; i < A.length && j < B.length; j++)
            if (A[i] > B[j])
                i++;

        return j - i - 1;
    }
}