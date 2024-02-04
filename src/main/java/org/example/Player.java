package org.example;

import Estruturas.Lists.ArrayOrderedList;
import Estruturas.Lists.ArrayUnorderedList;
import Exceptions.NonComparableElementException;
import Exceptions.EmptyCollectionException;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player {
    private String name;
    private int numPlayer;
    private int numBots;
    private int botIndex;
    private Flag flag;


    private ArrayOrderedList<Bot> bots;

    public Player(String name) {
        this.name = name;
        this.bots = new ArrayOrderedList<Bot>();
        this.botIndex = 0;
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


    //diferent


    public int moveBot(Player player) throws EmptyCollectionException, NonComparableElementException {
        Bot currentBot = bots.get(botIndex);

        System.out.println(player.getName());
        for (int i = 0; i < numBots; i++) {
            currentBot.move();
        }
        botIndex = (botIndex + 1) % bots.size(); // Alterna entre os bots

        if (currentBot.hasFlag()) {
            flag.capture(player);

            return 1;
        }
        return 0;
    }

    public int moveBotToBase(Player player) throws EmptyCollectionException, NonComparableElementException {
        Bot currentBot = bots.get(botIndex);

        System.out.println(player.getName());
        for (int i = 0; i < numBots; i++) {
            currentBot.moveToBase();
        }
        botIndex = (botIndex + 1) % bots.size(); // Alterna entre os bots

        if (currentBot.hasFlag()) {
            flag.capture(player);

            return 1;
        }
        return 0;
    }
}
