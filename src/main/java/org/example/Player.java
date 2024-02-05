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


    public int moveBot(Player player1, Player player2, int o) throws EmptyCollectionException {
       // int p1 = 0;
        for (int i = 0; i < numBots; i++) {
            Bot currentBot = bots.get(i);

            System.out.println("\n" + player1.getName());
            if (!currentBot.hasFlag()) {
                bots.get(i).move();
            }else
            {
                //currentBot.move();
                // } else {
                flag.capture(player1);
                System.out.println("\nBASE");
                if (o == 1) {
                    return moveBotToBase(player1);
                } else {
                    return moveBotToBaseUnidirecional(player1);
                }

            }

           // botIndex = (botIndex + 1) % bots.size(); // Alterna entre os bots

        }

        return 0;
    }

    public int moveBotToBase(Player player1) throws EmptyCollectionException {
        for (int i = 0; i < numBots; i++) {
            Bot currentBot = bots.get(botIndex);

            System.out.println(player1.getName());

            currentBot.moveToBase(player1);

            botIndex = (botIndex + 1) % bots.size(); // Alterna entre os bots

            if (currentBot.isBlocked()) {
                flag.capturetoBase(player1);
                return 1;
            }
        }
        return 0;
    }


    public int moveBotToBaseUnidirecional(Player player1) throws EmptyCollectionException {
        for (int i = 0; i < numBots; i++) {
            Bot currentBot = bots.get(botIndex);

            System.out.println(player1.getName());

            currentBot.moveUniToBase(player1);

            botIndex = (botIndex + 1) % bots.size(); // Alterna entre os bots

            if (currentBot.isBlocked()) {
                flag.capturetoBase(player1);
                return 1;
            }
        }
        return 0;
    }

}
