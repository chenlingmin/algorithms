package com.chenlm.graph;

import org.junit.Test;

public class DijkstraTest {

    @Test
    public void test() {

        String filename = "testGW11.txt";
        int V = 5;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, true);
        // Dijkstra最短路径算法同样适用于有向图
        //SparseGraph<int> g = SparseGraph<int>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        System.out.println("Test Dijkstra:\n");
        Dijkstra<Double> dij = new Dijkstra<Double>(g,0);
        for( int i = 1 ; i < V ; i ++ ){
            if(dij.hasPathTo(i)) {
                System.out.println("Shortest Path to " + i + " : " + dij.shortestPathTo(i));
                dij.showPath(i);
            }
            else
                System.out.println("No Path to " + i );

            System.out.println("----------");
        }

    }
}
