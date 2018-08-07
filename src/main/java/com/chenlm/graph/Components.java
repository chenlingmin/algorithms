package com.chenlm.graph;

// 求无权图的连通分量
public class Components {

    Graph g;   // 图
    private boolean[] visited; // 记录 dfs 的过程中节点是否被访问
    private int count;  // 记录连通分量个数
    private int[] id; // 每个节点所对应的联通分量标记


    public Components(Graph graph) {
        g = graph;
        visited = new boolean[g.V()];
        id = new int[g.V()];
        count = 0;
        for (int i = 0; i < g.V(); i++) {
            visited[i] = false;
            id[i] = -1;
        }
        for (int i = 0; i < g.V(); i++) {
            if (!visited[i]) {
                dfs(i);
                count++;
            }
        }
    }

    int count() {
        return count;
    }

    boolean isConnected(int v, int w) {
        assert v >= 0 && v < g.V();
        assert w >= 0 && w < g.V();
        return id[v] == id[w];
    }

    // 图的深度遍历
    void dfs(int v) {
        visited[v] = true;
        id[v] = count;
        for (int i : g.adj(v)) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }
}
