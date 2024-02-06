package api;

import Estruturas.Graphs.Network;
import Estruturas.Lists.ArrayOrderedList;
import Estruturas.Lists.ArrayUnorderedList;
import Estruturas.Queues.LinkedQueue;
import Exceptions.EmptyCollectionException;
import Interfaces.IMapa;

import java.io.*;
import java.util.Iterator;
import java.util.Random;

public class Mapa extends Network<Integer> implements IMapa {

    protected static final int MAX_DISTANCE = 15;
    private ArrayOrderedList<Boolean> locationsOccupied;
    private MapaType type;

    public Mapa() {
        super();
    }
    public ArrayOrderedList<Boolean> getLocationsOccupied() {
        return locationsOccupied;
    }

    public void setLocationsOccupied(ArrayOrderedList<Boolean> locationsOccupied) {
        this.locationsOccupied = locationsOccupied;
    }

    public MapaType getType() {
        return type;
    }

    public void setType(MapaType type) {
        this.type = type;
    }

    public void createMap(int numeroLocal, int density, MapaType type) {
        this.addLocal(numeroLocal);
        this.addConnection(numeroLocal, density);
        this.type = type;
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


    public int getNumVertices(){
        return numVertices;
    }

    /*
    public void exportMap(Mapa mapa,String filename) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(mapa);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Mapa importMap(String filename) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            Mapa mapa = (Mapa) in.readObject();
            in.close();
            return mapa;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    */


    public void exportMap(Mapa mapa, String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(mapa.getNumVertices());
            printWriter.println(mapa.getType());
            double[][] adjMatrix = mapa.getAdjMatrix();
            for (int i = 0; i < mapa.getNumVertices(); i++) {
                for (int j = 0; j < mapa.getNumVertices(); j++) {
                    printWriter.print(adjMatrix[i][j] + " ");
                }
                printWriter.println();
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Mapa importMap(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            int numVertices = Integer.parseInt(br.readLine());
            MapaType type = MapaType.valueOf(br.readLine());
            Mapa mapa = new Mapa();
            for (int i = 0; i < numVertices; i++) {
                mapa.addVertex(i);
            }
            for (int i = 0; i < numVertices; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < numVertices; j++) {
                    double weight = Double.parseDouble(line[j]);
                    if (weight != Double.POSITIVE_INFINITY) {
                        mapa.addEdge(i, j, weight);
                    }
                }
            }
            return mapa;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Iterator<Integer> iteratorMST(Mapa mapa, Integer startVertex) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();
        if (mapa.getNumVertices() == 0) {
            return resultList.iterator();
        }
        boolean[] visited = new boolean[mapa.getNumVertices()];

        for(int i = 0; i < mapa.getNumVertices(); i++) {
            visited[i] = false;
        }

        double[][] adjMatrix = mapa.getAdjMatrix();

        queue.enqueue(startVertex);
        visited[startVertex] = true;

        while (!queue.isEmpty()) {
            int vertex = -1;
            try {
                vertex = queue.dequeue();
            } catch (EmptyCollectionException e) {
                System.out.println(e.getMessage());
            }

            resultList.addToRear(vertex);

            for (int i = 0; i < mapa.getNumVertices(); i++) {
                if (adjMatrix[vertex][i] != Double.POSITIVE_INFINITY && !visited[i]) {
                    queue.enqueue(i);
                    visited[i] = true;
                }
            }
        }

        return resultList.iterator();
    }

    @Override
    public String toString() {
        String result = "Mapa Adjacency Matrix:\n     ";

        // Column headers for the matrix
        for (int i = 0; i < numVertices; i++) {
            result += String.format("%5d", i);
        }
        result += "\n";

        // Matrix rows
        for (int i = 0; i < numVertices; i++) {
            result += String.format("%4d ", i);
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] == Double.POSITIVE_INFINITY) {
                    result += "  N/A";
                } else {
                    result += String.format("%5.1f", adjMatrix[i][j]);
                }
            }
            result += "\n";
        }

        result += "\nConnections:\n";

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
