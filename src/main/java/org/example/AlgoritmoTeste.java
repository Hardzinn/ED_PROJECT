package org.example;

import Exceptions.EmptyCollectionException;

public class AlgoritmoTeste {

    public static void main(String[] args) throws EmptyCollectionException {
        Mapa mapa = new Mapa();
        mapa.createMap(5, 50);
        System.out.println(mapa.toString());

        System.out.println(mapa.mstNetworkToString());

        Algoritmo algoritmo = new Algoritmo(new Player("Player 1"));


        algoritmo.minimumTree(mapa, 1);
        System.out.println("Minimum Tree: " + algoritmo.getPositions());


    }
}
