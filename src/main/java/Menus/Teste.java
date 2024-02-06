package Menus;


import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;
import org.example.*;

import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;
        Mapa mapa = null;
        Game game ;
        int mapType = 0;
        int m=0;

        do {
            System.out.println("\nGame Menu:");
            System.out.println("1. Start New Game");
            System.out.println("2. Load Map");
            System.out.println("3. Save Map");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Start new game
                    System.out.println("Starting new game...");
                    System.out.print("Enter the number of locations: ");
                    int numLoc = scanner.nextInt();
                    if (numLoc < 2) {
                        System.out.println("Invalid number of locations. Defaulting to 2.");
                        numLoc = 2;
                    }
                    System.out.print("Enter the density: ");
                    int density = scanner.nextInt();
                    System.out.print("Choose map type (1 for Unidirectional, 2 for Bidirectional): ");
                    mapType = scanner.nextInt();
                    System.out.println("New game started with " + numLoc + " locations and density " + density + "%.");
                    switch (mapType) {
                        case 1:
                            MapaUniDirectional mapaUniDirectional = new MapaUniDirectional();
                            mapaUniDirectional.createMap(numLoc, density);
                            System.out.println(mapaUniDirectional.toString());
                            mapa = mapaUniDirectional;

                           //m=1;
                            break;
                        case 2:
                            mapa = new Mapa();
                            mapa.createMap(numLoc, density);
                            System.out.println(mapa.toString());

                            //m=2;
                            break;
                        default:
                            System.out.println("Invalid choice. Defaulting to Bidirectional map.");
                            break;
                    }

                    game = new Game(mapa);
                    game.setMapaType(mapType);
                    game.setMapa(mapa);
                    break;
                case 2:
                    // Load map
                    System.out.println("Loading map...");
                    //mapa = Mapa.importMapFromCSV("map.csv");
                    System.out.println("Map loaded successfully.");
                    //System.out.println(mapa.toString());
                    break;
                case 3:
                    // Save map
                    if (mapa != null) {
                        System.out.println("Saving map...");
                        //mapa.saveMapToCSV("map.csv");
                    } else {
                        System.out.println("No map to save.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting game...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }

            game = new Game(mapa);
            game.setMapaType(mapType);
            game.setMapa(mapa);

        } while (choice != 4);


        new FlagMenu(game).flagMenu();


        try {
            new BotMenu(game).menuGeral(game.getPlayer1().getName());
            new BotMenu(game).menuGeral(game.getPlayer2().getName());

            Player player = game.determineTurnOrder();

          /*  if (game.getMapaType() == 2) {
                System.out.println("BIDIRECIONAL");
                while (!game.getGameStatus()) {
                    game.playRound(player, game.getMapaType());
                }
            } else {
                System.out.println("UNIDIRECIONAL");
                while (!game.getGameStatus()) {
                    game.playRoundUni(player, game.getMapaType());
                }
            }*/

            while (!game.getGameStatus()) {
                game.playRound(player, game.getMapaType());
            }


        } catch (NonComparableElementException | EmptyCollectionException e) {
            throw new RuntimeException(e);
        }


        scanner.close();

    }
}
