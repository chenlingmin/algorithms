package com.chenlm.graph;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadGraph {
    private Scanner scanner;

    public ReadGraph(Graph graph, String filename) {
        readFile(filename);
        try {
            int V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            }
            assert V == graph.V();
            int E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            }

            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                assert v >= 0 && v < V;
                assert w >= 0 && w < V;
                graph.addEdge(v, w);
            }
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from input stream, but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from input stream, but there are no more tokens available");
        }
    }

    private void readFile(String filename) {
        assert filename != null;
        try {
            File file = new File(this.getClass().getClassLoader().getResource("").getPath() + "graph/" + filename);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fileInputStream), "UTF-8");
                scanner.useLocale(Locale.CHINESE);
            } else {
                throw new IllegalArgumentException(filename + "doesn't exist.");
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Could not open " + filename, e);
        }

    }
}
