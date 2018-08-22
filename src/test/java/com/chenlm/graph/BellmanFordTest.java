package com.chenlm.graph;

import org.junit.Test;

public class BellmanFordTest {
    @Test
    public void test() {

//        String filename = "testGW12.txt";
        String filename = "testGW12_negative_circle.txt";
        int V = 5;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, true);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        System.out.println("Test Bellman-Ford:\n");

        int s = 0;
        BellmanFord<Double> bellmanFord = new BellmanFord<Double>(g, s);
        if( bellmanFord.negativeCycle() )
            System.out.println("The graph contain negative cycle!");
        else
            for( int i = 0 ; i < V ; i ++ ){
                if(i == s)
                    continue;

                if(bellmanFord.hasPathTo(i)) {
                    System.out.println("Shortest Path to " + i + " : " + bellmanFord.shortestPathTo(i));
                    bellmanFord.showPath(i);
                }
                else
                    System.out.println("No Path to " + i );

                System.out.println("----------");
            }

    }
}
