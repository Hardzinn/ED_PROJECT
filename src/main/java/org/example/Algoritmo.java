package org.example;

import Estruturas.Graphs.Network;
import Estruturas.Lists.ArrayOrderedList;
import Estruturas.Queues.LinkedQueue;
import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;

import java.util.Iterator;

public class Algoritmo extends Network<Integer> {

    private Player player;
    private ArrayOrderedList<Integer> positions;
    private Iterator iter;

    public Algoritmo(Player player) {
        this.player = player;
    }

    public ArrayOrderedList<Integer> getPositions() {
        return positions;
    }

    public void setPositions(ArrayOrderedList<Integer> positions) {
        this.positions = positions;
    }

    public void shortestPath(Mapa mapa, int startVertex, int lastVertex) {
        try {
            iter = mapa.iteratorShortestPath(startVertex, lastVertex);

            while (iter.hasNext()) {
                positions.add((Integer) iter.next());
            }

        } catch (EmptyCollectionException e) {
            System.out.println("Mapa vazio");
        } catch (NonComparableElementException e) {
            throw new RuntimeException(e);
        }
    }


    public void DFS(Mapa mapa, Integer startVertex) throws EmptyCollectionException {
        try {

            iter = mapa.iteratorDFS(startVertex);

            if (iter.hasNext()) {
                positions.add((Integer) iter.next());
            }

            /* if (lastVertex.equals(flag.getPositionFlag()))
                flag.capture(player); */

        } catch (EmptyCollectionException e) {
            System.out.println("Mapa vazio");
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

        } catch (EmptyCollectionException e) {
            System.out.println("Mapa vazio");
        } catch (NonComparableElementException e) {
            throw new RuntimeException(e);
        }
    }


}
