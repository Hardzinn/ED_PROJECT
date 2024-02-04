package org.example;

import java.util.Random;

public class Game {

    private Mapa mapa;
    private Player player1;
    private Player player2;

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


    public void randPlayerChoice(){
        Random rand = new Random();
        int choice = rand.nextInt(2);
        if(choice == 0){
            System.out.println("Player 1 starts!");
        }else{
            System.out.println("Player 2 starts!");
        }
    }

}
