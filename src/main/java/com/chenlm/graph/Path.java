package com.chenlm.graph;

import java.util.Stack;
import java.util.Vector;

public class Path {

    private Graph g;   // 图的引用
    private int s;     // 起始点
    private boolean[] visited;  // 记录dfs的过程中节点是否被访问
    private int[] from;         // 记录路径, from[i]表示查找的路径上i的上一个节点

    // 构造函数, 寻路算法, 寻找图graph从s点到其他点的路径
    public Path(Graph g, int s) {
        this.g = g;
        assert s >= 0 && s < g.V();

        visited = new boolean[g.V()];
        from = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }

        this.s = s;
        // 深度寻路算法
        dfs(s);
    }

    private void dfs(int v) {
        visited[v] = true;
        for (Integer i : g.adj(v)) {
            if (!visited[i]) {
                from[i] = v;
                dfs(i);
            }
        }
    }

    boolean hasPath(int w) {
        assert w >= 0 && w < g.V();
        return visited[w];
    }

    Vector<Integer> path(int w) {
        assert hasPath(w);

        Stack<Integer> s = new Stack<>();

        int p = w;
        while (p != -1) {
            s.push(p);
            p = from[p];
        }

        Vector<Integer> res = new Vector<>();
        while (!s.empty()) {
            res.add(s.pop());
        }
        return res;
    }

    // 打印出从s点到w点的路径
    void showPath(int w) {

        assert hasPath(w);

        Vector<Integer> vec = path(w);
        for (int i = 0; i < vec.size(); i++) {
            System.out.print(vec.elementAt(i));
            if (i == vec.size() - 1)
                System.out.println();
            else
                System.out.print(" -> ");
        }
    }


}
