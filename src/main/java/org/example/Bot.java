package org.example;

import Estruturas.Lists.ArrayOrderedList;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;

import java.util.UUID;

public class Bot {
    private UUID id;
    //private ArrayOrderedList<Integer> position;
    private int position;
    private Algoritmo algoritmo;


    public Bot(int position, Algoritmo algoritmo) {
        this.id = UUID.randomUUID();
        this.position = position;
        this.algoritmo = algoritmo;

    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void movePositionToFlag() {
        try {
            this.position = algoritmo.getPositions().removeFirst();
        } catch (EmptyCollectionException e) {
            throw new RuntimeException(e);
        }


    }

    public void movePositionToBase() {
        try {
            this.position = algoritmo.getPositions().removeLast();
        } catch (EmptyCollectionException e) {
            throw new RuntimeException(e);
        }


    }

}
