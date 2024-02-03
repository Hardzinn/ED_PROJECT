package org.example;

import Estruturas.Lists.ArrayOrderedList;
import Exceptions.NonComparableElementException;
import Exceptions.EmptyCollectionException;

public class Player {
    private String name;
    private int numPlayer;
    private int numBots;
    private Flag flag;

    private int positionFlag;
    private ArrayOrderedList<Bot> bots;

    public Player(String name) {
        this.name = name;
        //this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPlayer() {
        return numPlayer;
    }

    public void setNumPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public ArrayOrderedList<Bot> getBots() {
        return bots;
    }

    public void setBots(ArrayOrderedList<Bot> bots) {
        this.bots = bots;
    }

    public int getNumBots() {
        return numBots;
    }

    public void setNumBots(int numBots) {
        this.numBots = numBots;
    }

    public int getPositionFlag() {
        return flag.getPositionFlag();
    }


    public void addBot(Bot bot) {
        try {
            bots.add(bot);
            numBots++;
        } catch (NonComparableElementException e) {
            throw new RuntimeException(e);
        }

    }

    public void movePositionToFlag() {
        try {

            Bot atual = this.bots.removeFirst();
            atual.movePositionToFlag();
            this.bots.add(atual);

        } catch (EmptyCollectionException e) {
            throw new RuntimeException(e);
        } catch (NonComparableElementException e) {
            throw new RuntimeException(e);
        }
    }


    public void movePositionToBase() {
        try {

            Bot atual = this.bots.removeLast();
            atual.movePositionToBase();
            this.bots.add(atual);

        } catch (EmptyCollectionException e) {
            throw new RuntimeException(e);
        } catch (NonComparableElementException e) {
            throw new RuntimeException(e);
        }
    }







}
