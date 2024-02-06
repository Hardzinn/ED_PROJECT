package Interfaces;

import api.Mapa;
import api.MapaType;

import java.util.Iterator;

public interface IMapa {

    public void createMap(int numeroLocal, int density, MapaType type);
    public void addLocal(int numeroLocal);
    public void addConnection(int numVertices, int density);
    public double[] getCoords(int vertice);
    public double[][] getAdjMatrix();
    public boolean edgeExists(int source, int target);
    public Iterable<Integer> getVertices();
    public Iterable<Integer> getNeighbors(int vertex);

    public void exportMap(Mapa mapa, String filename);
    public Mapa importMap(String filename);
    public Iterator<Integer> iteratorMST(Mapa mapa, Integer startVertex);

}
