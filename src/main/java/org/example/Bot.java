package org.example;

import Estruturas.Lists.ArrayOrderedList;
import Estruturas.Lists.ArrayUnorderedList;
import Estruturas.Queues.LinkedQueue;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;

import java.util.UUID;

public class Bot implements Comparable {
    private UUID id;
    private ArrayUnorderedList<Integer> positions;
    private int position;
    private int destination;
    private Algoritmo algoritmo;
    private boolean blocked = false;
    private boolean flag = false;


    public Bot(int position, int destination, Algoritmo algoritmo) {
        this.id = UUID.randomUUID();
        this.position = position;
        this.destination = destination;
        this.algoritmo = algoritmo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public ArrayUnorderedList<Integer> getPositions() {
        return positions;
    }

    public void setPositions(ArrayUnorderedList<Integer> positions) {
        this.positions = positions;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean hasFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public void move() throws EmptyCollectionException {
        if (!positions.isEmpty()) {
            int nextPosition = positions.removeFirst();
            position = nextPosition;

            positions.addToRear(position);
            System.out.println(id);
            System.out.println(positions.toString());
            System.out.println(position);

            if (position == destination) {
                setFlag(true);
            }

        }
    }

    public void moveToBase() throws EmptyCollectionException {
        if (!positions.isEmpty()) {
            int nextPosition = positions.removeLast();
            position = nextPosition;

            positions.addToFront(position);

            System.out.println("ID BOT: " + id);
            System.out.println("POSIÇÕES\n");
            System.out.println(positions.toString());
            System.out.println("Posição atual" + position);

            if (position == destination) {
                setFlag(true);
            }

        }
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
