package com.chenlm.graph;

import org.junit.Test;

import java.util.Vector;

public class LazyPrimMSTTest {

    @Test
    public void test() {
        String filename = "testGW1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<>(g);
        Vector<Edge<Double>> mst = lazyPrimMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ )
            System.out.println(mst.elementAt(i));
        System.out.println("The MST weight is: " + lazyPrimMST.result());

        System.out.println();
    }

}
