package org.example;

import Estruturas.Graphs.Network;
import Estruturas.Lists.ArrayUnorderedList;

import java.io.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;


public class Mapa extends Network<Integer> {

    protected static final int MAX_DISTANCE = 15;

    public Mapa() {
        super();

    }

    public void createMap(int numeroLocal, int density) {
        this.addLocal(numeroLocal);
        this.addConnection(numeroLocal, density);
    }

    public void addLocal(int numeroLocal) {
        for(int i = 0; i < numeroLocal; i++) {
            this.addVertex(i);
        }
    }

    public void addConnection(int numVertices, int density) {
        Random rand = new Random();
        int maxEdges = (int) ((numVertices * (numVertices - 1) / 2) * (density / 100.0));

        for(int i = 0; i < numVertices; i++) {
            for(int j = 0; j < i; j++) {
                if(rand.nextDouble() < density) {
                    int distancia = rand.nextInt(MAX_DISTANCE) + 1;
                    this.addEdge(i, j, distancia);
                    this.addEdge(j, i, distancia);
                }
                if(i >= maxEdges) {
                    return;
                }
            }
        }
    }

    public double[] getCoords(int vertice){
        return this.getAdjMatrix()[vertice];
    }

    public double[][] getAdjMatrix(){
        return this.adjMatrix;
    }

    public boolean edgeExists(int source, int target) {
        return adjMatrix[source][target] != Double.POSITIVE_INFINITY;
    }


    public Iterable<Integer> getVertices() {
        ArrayUnorderedList<Integer> vertices = new ArrayUnorderedList<>();
        for (int i = 0; i < numVertices; i++) {
            vertices.addToRear(i);
        }
        return vertices;
    }



    public Iterable<Integer> getNeighbors(int vertex) {

        ArrayUnorderedList<Integer> neighbors = new ArrayUnorderedList<>();
        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[vertex][i] != Double.POSITIVE_INFINITY) {
                neighbors.addToRear(i);
            }
        }
        return neighbors;
    }


    //toDo - implementar o método para importar o mapa de um ficheiro CSV
    //toDo - implementar o método para exportar o mapa para um ficheiro CSV


    @Override
    public String toString() {
        String result = "Mapa Adjacency Matrix:\n     "; // Start with the matrix title

        // Column headers for the matrix
        for (int i = 0; i < numVertices; i++) {
            result += String.format("%5d", i);
        }
        result += "\n";

        // Matrix rows
        for (int i = 0; i < numVertices; i++) {
            result += String.format("%4d ", i); // Row header
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] == Double.POSITIVE_INFINITY) {
                    result += "  N/A";
                } else {
                    // Adjusted for consistency and clarity
                    result += String.format("%5.1f", adjMatrix[i][j]);
                }
            }
            result += "\n"; // New line at the end of each row
        }

        // Append a separator between the matrix and the list of connections
        result += "\nConnections:\n";

        // List of connections
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] != Double.POSITIVE_INFINITY) {
                    result += "Vertex " + i + " is connected to Vertex " + j;
                    result += " with weight " + adjMatrix[i][j] + "\n";
                }
            }
        }

        return result;
    }



}
