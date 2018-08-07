package com.chenlm.graph;

import org.junit.Test;

public class PathTest {
    @Test
    public void pathTest() {
        String filename = "testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();
        System.out.println();

        Path path = new Path(g,0);
        System.out.println("Path from 0 to 6 : ");
        path.showPath(6);
    }
    @Test
    public void shortestPathTest() {
        String filename = "testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();
        System.out.println();

        ShortestPath path = new ShortestPath(g,0);
        System.out.println("Path from 0 to 6 : ");
        path.showPath(6);
    }
}
