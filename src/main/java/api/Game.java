package api;

import java.util.Random;

import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;


public class Game {

    private Mapa mapa;
    private Player player1;
    private Player player2;
    private int p1;
    private int p2;
    private int modCount1;
    private int modCount2;
    private int mapaType;
    private Flag flag;
    private boolean gameOver;

    public Game(Mapa mapa) {
        this.mapa = mapa;
        this.modCount1 = 0;
        this.modCount2 = 0;
        this.p1 = 0;
        this.p2 = 0;
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

    public void setWinner(Player player) {
        System.out.println("The winner is " + player.getName());
    }

    public void setMapaType(int mapaType) {
        this.mapaType = mapaType;
    }

    public int getMapaType() {
        return this.mapaType;
    }

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
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


    public void playRound(Player player, int o) throws EmptyCollectionException, NonComparableElementException {
        int p3, p4;

        if (player.getName().equals(player1.getName())) {

            p3 = player1.moveBot(player1, player2, o);
            if (p3 == 1) {
                setGameStatus(true);
                setWinner(player1);
                return;
            }

            p4 = player2.moveBot(player2,player1, o);
            if (p4 == 1) {
                setGameStatus(true);
                setWinner(player2);
                return;
            }

        } else {

            p4 = player2.moveBot(player2,player1, o);
            if (p4 == 1) {
                setGameStatus(true);
                setWinner(player2);
                return;
            }

            p3 = player1.moveBot(player1,player2, o);
            if (p3 == 1) {
                setGameStatus(true);
                setWinner(player1);
                return;
            }

        }

    }

}
