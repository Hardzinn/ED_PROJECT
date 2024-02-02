package org.example;

import Estruturas.Queues.LinkedQueue;

public class Player {

    private String name;
    private int numPlayer;
    private Flag flag;
    private Player player;
    private LinkedQueue<Bot> bots;

    public Player(String name, Flag flag, LinkedQueue<Bot> bot) {
        this.name = name;
        this.flag = flag;
        this.bots = new LinkedQueue<>();
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

    public LinkedQueue<Bot> getBots() {
        return bots;
    }

    public void setBots(LinkedQueue<Bot> bots) {
        this.bots = bots;
    }

    public void addBot(Bot bot) {
        LinkedQueue<Bot> newBots = new LinkedQueue<>();
        bots.enqueue(bot);
    }



}
