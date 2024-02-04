package org.example;

import java.util.Random;

import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;

import java.util.Iterator;
import java.util.Random;

public class Game {

    private Mapa mapa;
    private Player player1;
    private Player player2;

    private Flag flag;
    private boolean gameOver;

    public Game(Mapa mapa) {
        this.mapa = mapa;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public boolean getGameStatus() {
        return gameOver;
    }

    public void setGameStatus(boolean gameOver) {
        this.gameOver = gameOver;
    }

    //diferente
    public Player determineTurnOrder() throws EmptyCollectionException {
        Random random = new Random();
        int turnOrder = random.nextInt(2) + 1;

        switch (turnOrder) {
            case 1:
                return player1;
            case 2:
                return player2;
            default:
                throw new IllegalStateException("Invalid turn order");
        }
    }

    public void randPlayerChoice() {
        Random rand = new Random();
        int choice = rand.nextInt(2);
        if (choice == 0) {
            System.out.println("Player 1 starts!");
        } else {
            System.out.println("Player 2 starts!");
        }
    }

    public int playRound(Player player) throws EmptyCollectionException, NonComparableElementException {
        int p1, p2;

        if (player.getName().equals(player1.getName())) {
            p1 = player1.moveBot(player1);
            if (p1 == 1) {
                return 1;
            }

            p2 = player2.moveBot(player2);
            if (p2 == 1)
                return 2;
        } else {
            p2 = player2.moveBot(player2);
            if (p2 == 1)
                return 2;
            p1 = player1.moveBot(player1);
            if (p1 == 1)
                return 1;
        }

        return 0;
    }

    public void playRoundToBase(int ordem) throws EmptyCollectionException, NonComparableElementException {
        int p1, p2;

        switch (ordem) {
            case 1:
                p2 = player2.moveBotToBase(player2);
                if (p2 == 1)
                    setGameStatus(true);
                p1 = player1.moveBotToBase(player1);
                if (p1 == 1)
                    setGameStatus(true);
            case 2:
                p1 = player1.moveBotToBase(player1);
                if (p1 == 1) {
                    setGameStatus(true);
                }
                p2 = player2.moveBotToBase(player2);
                if (p2 == 1)
                    setGameStatus(true);
        }
    }
}
