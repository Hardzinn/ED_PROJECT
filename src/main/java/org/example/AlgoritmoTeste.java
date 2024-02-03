package org.example;

import Exceptions.EmptyCollectionException;

public class AlgoritmoTeste {

    public static void main(String[] args) throws EmptyCollectionException {
        Mapa mapa = new Mapa();
        mapa.createMap(5, 50);
        System.out.println(mapa.toString());

        System.out.println(mapa.mstNetworkToString());

        Algoritmo algoritmo = new Algoritmo(new Player("Player 1"));

        algoritmo.shortestPath(mapa, 0, 4);
        System.out.println("Shortest Path : " + algoritmo.getPositions());

        algoritmo.BFS(mapa,1);
        System.out.println("DFS Path: " + algoritmo.getPositions());

        System.out.println(algoritmo.getPositions().toString());

        algoritmo.minimumTree(mapa, 0);
        System.out.println(algoritmo.getPositions().toString());
    }
}
