package Menus;

import Estruturas.Lists.ArrayOrderedList;
import Exceptions.NonComparableElementException;
import api.*;

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



        if(game.getMapa().getType() == MapaType.MAPA_UNIDIRECIONAL) {
            if (namePlayer.equals(game.getPlayer1().getName())) {
                game.getPlayer1().setBots(algorithmChoiceUni(numBots, game.getPlayer1().getFlag().getPositionFlag(), game.getPlayer2().getFlag().getPositionFlag(), game.getPlayer1()));
                game.getPlayer1().setNumBots(numBots);

            } else if (namePlayer.equals(game.getPlayer2().getName())) {
                game.getPlayer2().setBots(algorithmChoiceUni(numBots, game.getPlayer2().getFlag().getPositionFlag(), game.getPlayer1().getFlag().getPositionFlag(), game.getPlayer2()));
                game.getPlayer2().setNumBots(numBots);
            }
        }else {
            if (namePlayer.equals(game.getPlayer1().getName())) {
                game.getPlayer1().setBots(algorithmChoiceBid(numBots, game.getPlayer1().getFlag().getPositionFlag(), game.getPlayer2().getFlag().getPositionFlag(), game.getPlayer1()));
                game.getPlayer1().setNumBots(numBots);

            } else if (namePlayer.equals(game.getPlayer2().getName())) {
                game.getPlayer2().setBots(algorithmChoiceBid(numBots, game.getPlayer2().getFlag().getPositionFlag(), game.getPlayer1().getFlag().getPositionFlag(), game.getPlayer2()));
                game.getPlayer2().setNumBots(numBots);
            }
        }

        System.out.println("Bots created with success");
    }


    public ArrayOrderedList<Bot> algorithmChoiceUni(int numBots, int start, int end, Player player) throws NonComparableElementException {
        int option;
        ArrayOrderedList<Bot> bots = new ArrayOrderedList<Bot>();
        Scanner scanner = new Scanner(System.in);
        Bot bot;
        Algoritmo algoritmo = new Algoritmo();


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
                        bot = new Bot(start, end, algoritmo, false);
                        algoritmo.BFS(game.getMapa(), start);
                        bot.setPositions(algoritmo.getPositions());
                        algoritmo.BFS(game.getMapa(), end);
                        bot.setPositionsBase(algoritmo.getPositions());
                        bots.add(bot);
                        break;

                    case 2:
                        bot = new Bot(start, end, algoritmo,false);
                        algoritmo.shortestPath(game.getMapa(), start, end);
                        bot.setPositions(algoritmo.getPositions());
                        algoritmo.shortestPath(game.getMapa(), end, start);
                        bot.setPositionsBase(algoritmo.getPositions());
                        bots.add(bot);
                        break;

                    case 3:
                        bot = new Bot(start, end, algoritmo,false);
                        algoritmo.minimumTree(game.getMapa(), start);
                        bot.setPositions(algoritmo.getPositions());
                        algoritmo.minimumTree(game.getMapa(), end);
                        bot.setPositionsBase(algoritmo.getPositions());
                        bots.add(bot);
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

    public ArrayOrderedList<Bot> algorithmChoiceBid(int numBots, Integer start, Integer end, Player player) throws NonComparableElementException {
        int option;
        ArrayOrderedList<Bot> bots = new ArrayOrderedList<Bot>();
        Scanner scanner = new Scanner(System.in);
        Bot bot;
        Algoritmo algoritmo = new Algoritmo();


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
                        bot = new Bot(start, end, algoritmo,false);
                        algoritmo.BFS(game.getMapa(), start);
                        bot.setPositions(algoritmo.getPositions());
                        bots.add(bot);
                        break;

                    case 2:
                        bot = new Bot(start, end, algoritmo,false);
                        algoritmo.shortestPath(game.getMapa(), start, end);
                        bot.setPositions(algoritmo.getPositions());
                        bots.add(bot);
                        break;

                    case 3:
                        bot = new Bot(start, end, algoritmo,false);
                        algoritmo.minimumTree(game.getMapa(), start);
                        bot.setPositions(algoritmo.getPositions());
                        bots.add(bot);
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
