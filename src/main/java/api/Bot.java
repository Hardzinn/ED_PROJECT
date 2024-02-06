package api;

import Estruturas.Lists.ArrayUnorderedList;
import Exceptions.EmptyCollectionException;

import java.util.UUID;

public class Bot implements Comparable {
    private UUID id;
    private ArrayUnorderedList<Integer> positions;
    private ArrayUnorderedList<Integer> positionsBase;
    private int position;
    private int destination;
    private Algoritmo algoritmo;
    private boolean blocked = false;
    private boolean flag;


    public Bot(int position, int destination, Algoritmo algoritmo, boolean flag) {
        this.id = UUID.randomUUID();
        this.position = position;
        this.destination = destination;
        this.algoritmo = algoritmo;
        this.flag = flag;
    }


    public UUID getId() {
        return id;
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

    public ArrayUnorderedList<Integer> getPositionsBase() {
        return positionsBase;
    }

    public void setPositionsBase(ArrayUnorderedList<Integer> positionsBase) {
        this.positionsBase = positionsBase;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean hasFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public int move() throws EmptyCollectionException {
        if (!positions.isEmpty()) {
            int nextPosition = positions.removeFirst();
            position = nextPosition;

            positions.addToRear(position);

            System.out.println("ID BOT: " + id);
            System.out.println("POSIÇÕES - " + positions.toString());
            System.out.println("Posição atual " + position);

            if (position == destination) {
                setFlag(true);
                return 1;
            }
        }
        return 0;
    }

    public void moveToBase(Player player) throws EmptyCollectionException {
        if (!positions.isEmpty()) {
            if (positions.last() == destination) {
                positions.addToFront(positions.removeLast());
            }

            int nextPosition = positions.removeLast();
            position = nextPosition;

            positions.addToFront(position);
            positions.last();

            System.out.println("\nID BOT: " + id);
            System.out.println("POSIÇÕES - " + positions.toString());
            System.out.println("Posição atual " + position);

            if (position == player.getPositionFlag()) {
                setBlocked(true);
            }

        }
    }


    public void moveUniToBase(Player player) throws EmptyCollectionException {
        if (!positionsBase.isEmpty()) {
            if (positionsBase.first() == destination) {
                positionsBase.addToRear(positionsBase.removeFirst());
            }

            int nextPosition = positionsBase.removeFirst();
            position = nextPosition;

            positionsBase.addToRear(position);

            System.out.println("ID BOT: " + id);
            System.out.println("POSIÇÕES - " + positionsBase.toString());
            System.out.println("Posição atual " + position);

            if (position == player.getPositionFlag()) {
                setBlocked(true);
            }
        }
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
