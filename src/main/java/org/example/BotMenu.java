package org.example;

import Estruturas.Lists.ArrayOrderedList;
import Exceptions.NonComparableElementException;

import java.util.Scanner;

public class BotMenu {

    private Game game;

    public BotMenu(Game game) {
        this.game = game;
    }

    public void menuGeral(String namePlayer) throws NonComparableElementException {
        int numBots;

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n----- Bot Menu -----");
        System.out.println("| " + namePlayer + " |");
        System.out.println("How many bots are you gonna use? ");

        do {
            //teste number of bots > 0
            numBots = scanner.nextInt();
            if (numBots == 0) {
                System.out.println("Invalid number of bots");
                System.out.println("Please choose select a number bigger than 0");

            }

        } while (numBots == 0);


        //game.getPlayer1().setBots(algorithmChoice(numBots, game.getPlayer1().getFlag().getPositionFlag(), game.getPlayer2().getFlag().getPositionFlag(),game.getPlayer1()));
        //game.getPlayer2().setBots(algorithmChoice(numBots, game.getPlayer2().getFlag().getPositionFlag(), game.getPlayer1().getFlag().getPositionFlag(),game.getPlayer2()));


        if (namePlayer.equals(game.getPlayer1().getName())) {
            game.getPlayer1().setBots(algorithmChoice(numBots, game.getPlayer1().getFlag().getPositionFlag(), game.getPlayer2().getFlag().getPositionFlag(), game.getPlayer1()));
            game.getPlayer1().setNumBots(numBots);

        } else if (namePlayer.equals(game.getPlayer2().getName())) {
            game.getPlayer2().setBots(algorithmChoice(numBots, game.getPlayer2().getFlag().getPositionFlag(), game.getPlayer1().getFlag().getPositionFlag(), game.getPlayer2()));
            game.getPlayer2().setNumBots(numBots);
        }

        System.out.println("Bots created with success");
    }


    public ArrayOrderedList<Bot> algorithmChoice(int numBots, int start, int end, Player player) throws NonComparableElementException {
        int option;
        ArrayOrderedList<Bot> bots = new ArrayOrderedList<Bot>();
        Scanner scanner = new Scanner(System.in);

        Algoritmo algoritmo = new Algoritmo(player);


        for (int i = 0; i < numBots; i++) {
            i++;
            do {
                System.out.println("\nChoose the algorithm to bot " + i + ": ");
                System.out.println("1 - BFS");
                System.out.println("2 - Shortest Path");
                System.out.println("3 - Minimum Cost Spanning Tree");

                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        Bot bot = new Bot(start, end, algoritmo);
                        algoritmo.BFS(game.getMapa(), start);
                        bot.setPositions(algoritmo.getPositions());
                        //player.addBot(bot);
                        bots.add(bot);
                        break;

                    case 2:
                        Bot bot1 = new Bot(start, end, algoritmo);
                        algoritmo.shortestPath(game.getMapa(), start, end);
                        bot1.setPositions(algoritmo.getPositions());
                        //player.addBot(bot);
                        bots.add(bot1);
                        break;

                    case 3:
                        Bot bot2 = new Bot(start, end, algoritmo);
                        algoritmo.minimumTree(game.getMapa(), start);
                        bot2.setPositions(algoritmo.getPositions());
                        //player.addBot(bot);
                        bots.add(bot2);
                        break;

                    default:
                        System.out.println("Invalid option");
                        break;
                }

                i--;
            } while (option < 0 || option > 3);
        }

        System.out.println("\nCreating bots ...");
        return bots;
    }
}
