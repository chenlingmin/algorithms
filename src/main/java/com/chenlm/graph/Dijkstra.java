package com.chenlm.graph;


import java.util.Stack;
import java.util.Vector;

// Dijkstra算法求最短路径
public class Dijkstra<Weight extends Number & Comparable> {
    private WeightedGraph g;           // 图的引用
    private int s;                     // 起始点
    private Number[] distTo;           // distTo[i]存储从起始点s到i的最短路径长度
    private boolean[] marked;          // 标记数组, 在算法运行过程中标记节点i是否被访问
    private Edge<Weight>[] from;       // from[i]记录最短路径中, 到达i点的边是哪一条
// 可以用来恢复整个最短路径

    public Dijkstra(WeightedGraph<Weight> g, int s) {
        this.g = g;
        this.s = s;

        assert s >= 0 && s < g.V();

        distTo = new Number[g.V()];
        marked = new boolean[g.V()];
        from = new Edge[g.V()];

        for (int i = 0; i < g.V(); i++) {
            distTo[i] = 0.0;
            marked[i] = false;
            from[i] = null;
        }

        // 使用索引堆记录当前找到的到达每个顶点的最短距离
        IndexMinHeap<Weight> ipq = new IndexMinHeap<>(g.V());

        // 对于起始点初始化
        distTo[s] = 0.0;
        from[s] = new Edge<>(s, s, (Weight) (Number) 0.0);
        ipq.insert(s, (Weight) distTo[s]);
        marked[s] = true;

        while (!ipq.isEmpty()) {
            int v = ipq.extractMinIndex();

            marked[v] = true;

            // 松弛操作
            for (Edge<Weight> e : g.adj(v)) {
                int w = e.other(v);
                if (!marked[w]) {
                    // 如果w点以前没有访问过,
                    // 或者访问过, 但是通过当前的v点到w点距离更短, 则进行更新
                    if (from[w] == null || distTo[v].doubleValue() + e.wt().doubleValue() < distTo[w].doubleValue()) {
                        distTo[w] = distTo[v].doubleValue() + e.wt().doubleValue();
                        from[w] = e;
                        if (ipq.contain(w)) {
                            ipq.change(w, (Weight) distTo[w]);
                        } else {
                            ipq.insert(w, (Weight) distTo[w]);
                        }
                    }
                }

            }
        }


    }


    // 返回从s点到w点的最短路径长度
    Number shortestPathTo(int w) {
        assert w >= 0 && w < g.V();
        assert hasPathTo(w);
        return distTo[w];
    }

    // 判断从s点到w点是否联通
    boolean hasPathTo(int w) {
        assert w >= 0 && w < g.V();
        return marked[w];
    }

    // 寻找从s到w的最短路径, 将整个路径经过的边存放在vec中
    Vector<Edge<Weight>> shortestPath(int w) {
        // 通过from数组逆向查找到从s到w的路径, 存放到栈中
        Stack<Edge<Weight>> s = new Stack<>();
        Edge<Weight> e = from[w];
        while (e.v() != this.s) {
            s.push(e);
            e = from[e.v()];
        }
        s.push(e);

        // 从栈中依次取出元素, 获得顺序的从s到w的路径
        Vector<Edge<Weight>> res = new Vector<>();
        while (!s.empty()) {
            e = s.pop();
            res.add(e);
        }

        return res;
    }


    // 打印出从s点到w点的路径
    void showPath(int w) {

        assert w >= 0 && w < g.V();
        assert hasPathTo(w);

        Vector<Edge<Weight>> path = shortestPath(w);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.elementAt(i).v() + " -> ");
            if (i == path.size() - 1)
                System.out.println(path.elementAt(i).w());
        }
    }

}

