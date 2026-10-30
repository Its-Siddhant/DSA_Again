class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges)
            adj[e[0]].add(new int[]{e[1], e[2]});

        // dist[node][consec] = min cost, consec = 1..k consecutive same chars
        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        // Start at node 0, consec = 1 (label[0] itself)
        dist[0][1] = 0;

        // PQ: {cost, node, consec}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], u = cur[1], consec = cur[2];

            if (cost > dist[u][consec]) continue;

            for (int[] edge : adj[u]) {
                int v = edge[0], w = edge[1];

                int newConsec;
                if (labels.charAt(v) == labels.charAt(u)) {
                    newConsec = consec + 1;
                } else {
                    newConsec = 1;
                }

                if (newConsec > k) continue; // violates constraint

                int newCost = cost + w;
                if (newCost < dist[v][newConsec]) {
                    dist[v][newConsec] = newCost;
                    pq.offer(new int[]{newCost, v, newConsec});
                }
            }
        }

        // Find min cost to reach n-1 with any valid consec count
        int ans = Integer.MAX_VALUE;
        for (int c = 1; c <= k; c++)
            ans = Math.min(ans, dist[n - 1][c]);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}