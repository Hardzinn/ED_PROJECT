package Interfaces;

import api.Mapa;

public interface IAlgoritmo {

    public void shortestPath(Mapa mapa, int startVertex, int lastVertex);
    public void BFS(Mapa mapa, Integer startVertex);
    public void minimumTree(Mapa mapa, Integer startVertex);
}
