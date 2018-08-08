package com.chenlm.graph;

import java.util.Vector;

public class LazyPrimMST<Weight extends Number & Comparable> {

    private WeightedGraph<Weight> g;  // 图
    private MinHeap<Edge<Weight>> pq; // 最小堆, 算法辅助
    private boolean[] marked;         // 标记数组，在算法运行过程中标记节点i是否被访问
    private Vector<Edge<Weight>> mst; // 最小生成树所包含的所有边
    private Number mstWeight;           // 最小生成树的权值

    public LazyPrimMST(WeightedGraph<Weight> g) {
        this.g = g;
        pq = new MinHeap<>(g.E());
        marked = new boolean[g.V()];
        mst = new Vector<>();

        // Lazy Prim
        visit(0);
        while (!pq.isEmpty()) {
            // 使用最小堆找出已经访问的边中权值最小的边
            Edge<Weight> e = pq.extractMin();

            // 如果这条边的两端都已经访问过了, 则扔掉这条边
            if (marked[e.v()] == marked[e.w()]) {
                continue;
            }
            // 否则, 这条边则应该存在在最小生成树中
            mst.add(e);

            if (!marked[e.v()]) {
                visit(e.v());
            } else {
                visit(e.w());
            }
        }

        mstWeight = mst.elementAt(0).wt();
        for( int i = 1 ; i < mst.size() ; i ++ )
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }

    private void visit(int v) {
        assert !marked[v];
        marked[v] = true;
        for (Edge<Weight> e : g.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }


    public Vector<Edge<Weight>> mstEdges() {
        return mst;
    }


    public Number result() {
        return mstWeight;
    }
}
