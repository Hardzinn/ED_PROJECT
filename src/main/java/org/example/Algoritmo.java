package org.example;

import Estruturas.Graphs.Network;
import Estruturas.Lists.ArrayOrderedList;
import Estruturas.Queues.LinkedQueue;
import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;
import Exceptions.VertexNotFoundException;

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
            iter = this.iteratorMinimumSpanningTree(mapa,startVertex);

            while (iter.hasNext()) {
                positions.add((Integer) iter.next());
            }

        } catch (NonComparableElementException | EmptyCollectionException e) {
            throw new RuntimeException(e);
        }
    }

    protected Iterator<Integer> iteratorMinimumSpanningTree(Mapa mapa,Integer startVertex) throws NonComparableElementException, EmptyCollectionException {

        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayOrderedList<Integer> resultList = new ArrayOrderedList<>();
        boolean[] visited = new boolean[mapa.size()];

        if (isEmpty()) {
            return resultList.iterator();
        }

        traversalQueue.enqueue(startVertex);

        while (!traversalQueue.isEmpty()) {
            int currentVertex = traversalQueue.dequeue();

            if (!visited[currentVertex]) {
                resultList.add(currentVertex);
                visited[currentVertex] = true;
            }

            for (int i = 0; i < mapa.size(); i++) {
                if (mapa.edgeExists(currentVertex, i) && !visited[i]) {
                    traversalQueue.enqueue(i);
                }
            }
        }

        return resultList.iterator();
    }



}
