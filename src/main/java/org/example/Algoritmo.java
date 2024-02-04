package org.example;

import Estruturas.Lists.ArrayOrderedList;
import Estruturas.Lists.ArrayUnorderedList;
import Estruturas.Queues.LinkedQueue;
import Estruturas.Stack.LinkedStack;
import Exceptions.NonComparableElementException;
import Exceptions.VertexNotFoundException;

import java.util.Iterator;
import java.util.Stack;

public class Algoritmo {

    private Player player;
    private ArrayUnorderedList<Integer> positions;
    private LinkedStack<Integer> positionsToBase;
    private Iterator iter;

    public Algoritmo(Player player) {
        this.player = player;
        this.positions = new ArrayUnorderedList<>();
    }

    public ArrayUnorderedList<Integer> getPositions() {
        return positions;
    }

    public void setPositions(ArrayUnorderedList<Integer> positions) {
        this.positions = positions;
    }

    public void setPositionsToBase(){
        //this.positionsToBase=positions;
    }

    public void shortestPath(Mapa mapa, int startVertex, int lastVertex) {
        try {

            iter = mapa.iteratorShortestPath(startVertex, lastVertex);

            while (iter.hasNext()) {
                positions.addToRear((Integer) iter.next());
            }

            setPositions(positions);

        } catch (VertexNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void BFS(Mapa mapa, Integer startVertex) {

        try {

            iter = mapa.iteratorBFS(startVertex);

            while (iter.hasNext()) {
                positions.addToRear((Integer) iter.next());
            }

            setPositions(positions);


        } catch (VertexNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void minimumTree(Mapa mapa, Integer startVertex) {

        iter = mapa.iteratorMST(mapa, startVertex);
        while (iter.hasNext()) {
            positions.addToRear((Integer) iter.next());
        }

        setPositions(positions);
    }


    protected Integer getNext() {
        return this.positions.first();
    }



}
