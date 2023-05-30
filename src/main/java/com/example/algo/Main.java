package com.example.algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // create a new graph with 6 vertices
        Graph g = new Graph(6);

        try {
            // read from input file
            File inputFile = new File("input1.txt");
            Scanner scanner = new Scanner(inputFile);

            // read edges from input file and add them to the graph
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] vertices = line.split(" ");
                int u = Integer.parseInt(vertices[0]);
                int v = Integer.parseInt(vertices[1]);
                g.addEdge(u, v);
//                System.out.println(v);
            }

            // close the scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

//         determine if the graph is acyclic and output the result
        boolean isAcyclic = g.isAcyclic();
        if (isAcyclic) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }
}
