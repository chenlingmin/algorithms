package com.chenlm.graph;

import java.util.Vector;

// 使用优化的Prim算法求图的最小生成树
public class PrimMST<Weight extends Number & Comparable> {

    private WeightedGraph g;              // 图的引用
    private IndexMinHeap<Weight> ipq;     // 最小索引堆, 算法辅助数据结构
    private Edge<Weight>[] edgeTo;        // 访问的点所对应的边, 算法辅助数据结构
    private boolean[] marked;             // 标记数组, 在算法运行过程中标记节点i是否被访问
    private Vector<Edge<Weight>> mst;     // 最小生成树所包含的所有边
    private Number mstWeight;             // 最小生成树的权值


    public PrimMST(WeightedGraph<Weight> g) {
        this.g = g;

        assert (g.E() >= 1);
        ipq = new IndexMinHeap<>(g.V());

        marked = new boolean[g.V()];
        edgeTo = new Edge[g.V()];
        for (int i = 0; i < g.V(); i++) {
            marked[i] = false;
            edgeTo[i] = null;
        }

        mst = new Vector<>();

        // Prim
        visit(0);
        while (!ipq.isEmpty()) {
            int v = ipq.extractMinIndex();
            assert edgeTo[v] != null;
            mst.add(edgeTo[v]);
            visit(v);
        }
        // 计算最小生成树的权值
        mstWeight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size(); i++)
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }

    // 访问节点v
    private void visit(int v) {
        assert !marked[v];
        marked[v] = true;

        for (Object item : g.adj(v)) {
            Edge<Weight> e = (Edge<Weight>) item;
            int w = e.other(v);
            if (!marked[w]) {
                if (edgeTo[w] == null) {
                    edgeTo[w] = e;
                    ipq.insert(w, e.wt());
                } else if (e.wt().compareTo(edgeTo[w].wt()) < 0) {
                    edgeTo[w] = e;
                    ipq.change(w, e.wt());
                }
            }
        }
    }

    // 返回最小生成树的所有边
    Vector<Edge<Weight>> mstEdges() {
        return mst;
    }

    // 返回最小生成树的权值
    Number result() {
        return mstWeight;
    }

}
