package com.example.algo;

import java.util.*;

public class Graph {
    public int vertices; // number of vertices
    private LinkedList<Integer>[] adj; // adjacency list

    // constructor
    public Graph(int v) {
        vertices = v;
        adj = new LinkedList[v+1];
        for (int i = 1; i <= v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    // add an edge from u to v
    public void addEdge(int u, int v) {
        adj[u].add(v);
//        System.out.println(adj[u].add(v));
    }

    // remove an edge from u to v
    public void removeEdge(int u, int v) {
        adj[u].remove(Integer.valueOf(v));
    }

    // find a sink in the graph
    public int findSink() {
        boolean[] isSink = new boolean[vertices+1];
        Arrays.fill(isSink, 1, vertices+1, true);
        for (int i = 1; i <= vertices; i++) {
            if(adj[i] != null){
            for (int v  : adj[i]) {
                isSink[i] = false;
            }
        }

        }
        for (int i = 1; i <= vertices; i++) {
            if(adj[i] != null){
            if (isSink[i]) {
                return i;
            }
            }
        }
        return -1;
    }

    // remove a vertex from the graph
    public void removeVertex(int u) {

        for(int i=1; i <= vertices; i++){
            if(adj[i] != null) {
                List<Integer> neighbors = new ArrayList<>(adj[i]);
                for (int v : neighbors) {
                    if (v == u) {
                        removeEdge(i, v);
                    }
                }
            }

        }

        adj[u].clear();
        adj[u] = null;

    }


    public boolean isAcyclic() {
        // create a copy of the graph
        Graph copy = new Graph(vertices);
        for (int u = 1; u <= vertices; u++) {
            for (int v : adj[u]) {
                copy.addEdge(u, v);
            }
        }


        while (vertices > 0) {
            int sink = copy.findSink();
            if (sink == -1) {
                for(int i = 1; i <= vertices; i++){
                        if (hasCycle(copy.adj, i, new HashSet<Integer>())) {
                            return false;
                    }
                }
                return true;
            } else{
                System.out.println("\n" + "Eliminating sink: " + sink );
                copy.removeVertex(sink);
                for (int u = 1; u <= vertices; u++) {
                    if(copy.adj[u] != null){
                    for (int v : adj[u]) {
                        if (v == sink) {
                            System.out.println("Eliminating edges sink: " + u +"-" + v);
                        }
                    }
                    }
                  }
                }
            }

        // graph is acyclic
        return true;
    }

    public static boolean hasCycle(List<Integer>[] adjacencyList, int vertex, Set<Integer> visited) {
        // Mark the vertex as visited.
        visited.add(vertex);
        if (adjacencyList[vertex] != null) {
            // Recursively visit all neighbors of the vertex.
            for (int neighbor : adjacencyList[vertex]) {
                // If the neighbor has already been visited, we have found a cycle.
                if (visited.contains(neighbor)) {
                    System.out.println("Cycle found: " + vertex + " -> " + neighbor);
                    return true;
                }

                // If the neighbor has not been visited yet, search for cycles starting from the neighbor.
                if (hasCycle(adjacencyList, neighbor, visited)) {
                    return true;
                }
            }
        }

        // No cycles were found.
        return false;
    }

}

