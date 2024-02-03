package org.example;

import Exceptions.EmptyCollectionException;

import java.util.Scanner;

public class PlayerMenu {

    Game game;
    Scanner scanner = new Scanner(System.in);

    public void menu() {

        Algoritmo algoritmo;
        Player player;
        Mapa mapa = new Mapa();
        int numBots;
        int option;

        System.out.println("\n------ Player Menu ------");
        System.out.println("| PLAYER 1 |\n");

        System.out.print("How many bots are you gonna use? ");
        numBots = scanner.nextInt();
        for (int i = 0; i < numBots; i++) {
            System.out.println("choose the algorithm:");
            System.out.println("1 - BFS");
            System.out.println("2 - DFS");
            System.out.println("3 - Shortest Path");
            System.out.println("4 - Minimum Cost Spanning Tree");
            option = scanner.nextInt();
            player = new Player("Player 1");
            game.setPlayer1(player);

            algoritmo = new Algoritmo(player);


        }

        System.out.println("| PLAYER 2 |\n");

        System.out.print("How many bots are you gonna use? ");
        numBots = scanner.nextInt();
        for (int i = 0; i < numBots; i++) {
            System.out.println("choose the algorithm:");
            System.out.println("1 - BFS");
            System.out.println("2 - DFS");
            System.out.println("3 - Shortest Path");
            System.out.println("4 - Minimum Cost Spanning Tree");
            option = scanner.nextInt();
            player = new Player("Player 2");
            algoritmo = new Algoritmo(player);


        }
    }

    public void flagMenu(){
        Mapa mapa = game.getMapa();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        System.out.println("----- Flag Menu -----");
        do {
            createFlag(player1, mapa);
            createFlag(player2, mapa);

            if (player1.getFlag().getPositionFlag() == player2.getFlag().getPositionFlag()) {
                System.out.println("Players can´t have the same position, please try again \n");
            }
        }while (player1.getFlag().getPositionFlag()==player2.getFlag().getPositionFlag());

        game.setPlayer1(player1);
        game.setPlayer2(player2);
    }


    public void createFlag(Player player, Mapa mapa) {
        int option;
        Flag flag = null;
        

        System.out.print("Position of the flag: ");
        option = scanner.nextInt();
        do {
            option = scanner.nextInt();
            if (option <= 0 || option > mapa.getNumVertices()) {
                System.out.println("Invalid position");
            }
        } while (option <= 0 || option > mapa.getNumVertices());
        player.getFlag().setPositionFlag(option);
    
    }

    public void algoritmoChoice(int option, Algoritmo algoritmo, Flag flag) {

        switch (option) {
            case 1:
                algoritmo.BFS(game.getMapa(), flag.getPositionFlag());
                //Bot bot= new Bot(game.getFlag().getPositionFlag( ), algoritmo);
                break;


            case 2:
                algoritmo.shortestPath(game.getMapa(),game.getPlayer1().getPositionFlag(), game.getPlayer2().getPositionFlag());
                break;
            case 3:
                // arvore geradora
                break;
            default:
                break;
        }

    }
}
