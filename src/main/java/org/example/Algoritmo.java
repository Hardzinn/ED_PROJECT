package org.example;

import Estruturas.Lists.ArrayOrderedList;
import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;
import Exceptions.VertexNotFoundException;

import java.util.Iterator;

public class Algoritmo  {

    private Player player;
    private ArrayOrderedList<Integer> positions;
    private Iterator iter;

    public Algoritmo(Player player) {
        this.player = player;
        this.positions = new ArrayOrderedList<>();
    }

    public ArrayOrderedList<Integer> getPositions() {
        return positions;
    }

    public void setPositions(ArrayOrderedList<Integer> positions) {
        this.positions = positions;
    }

    public void shortestPath(Mapa mapa, int startVertex, int lastVertex) {
        try {

            ArrayOrderedList<Integer> dados= new ArrayOrderedList<>();
            iter = mapa.iteratorShortestPath(startVertex, lastVertex);

            while (iter.hasNext()) {
                dados.add((Integer) iter.next());
                positions=dados;
            }

        } catch (NonComparableElementException | VertexNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void DFS(Mapa mapa, Integer startVertex) throws EmptyCollectionException {
        try {

            ArrayOrderedList<Integer> dados= new ArrayOrderedList<>();
            iter = mapa.iteratorDFS(startVertex);

            while (iter.hasNext()) {
                dados.add((Integer) iter.next());
                positions=dados;
            }


        } catch (NonComparableElementException e) {
            throw new RuntimeException(e);
        }
    }

    public void BFS(Mapa mapa, Integer startVertex) {

        try {

            iter = mapa.iteratorBFS(startVertex);

            if (iter.hasNext()) {
                positions.add((Integer) iter.next());
            }

        } catch (VertexNotFoundException | NonComparableElementException e) {
            throw new RuntimeException(e);
        }
    }

    public void minimumTree(Mapa mapa, Integer startVertex) {
        try {
            iter = mapa.iteratorMST(mapa,startVertex);

            while (iter.hasNext()) {
                positions.add((Integer) iter.next());
            }

        } catch (NonComparableElementException e) {
            throw new RuntimeException(e);
        }
    }


}
