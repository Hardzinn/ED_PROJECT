package Menus;

import org.example.Game;
import org.example.Mapa;
import org.example.MapaUniDirectional;

import java.util.Scanner;

public class GameMenu {


    Scanner scanner = new Scanner(System.in);
    int choice;


    public void gameMenu(Mapa mapa, Game game){

        do {
            System.out.println("\nGame Menu:");
            System.out.println("1. Start New Game");
            System.out.println("2. Load Map");
            System.out.println("3. Save Map");
            System.out.println("4. Start Game");
            System.out.println("5. Exit");
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
                    int mapType = scanner.nextInt();
                    System.out.println("New game started with " + numLoc + " locations and density " + density + "%.");
                    switch (mapType) {
                        case 1:
                            MapaUniDirectional mapaUniDirectional = new MapaUniDirectional();
                            mapaUniDirectional.createMap(numLoc, density);
                            System.out.println(mapaUniDirectional.toString());
                            mapa = mapaUniDirectional;

                            break;
                        case 2:
                            mapa = new Mapa();
                            mapa.createMap(numLoc, density);
                            System.out.println(mapa.toString());
                            break;
                        default:
                            System.out.println("Invalid choice. Defaulting to Bidirectional map.");
                            break;
                    }

                    break;
                case 2:
                    System.out.println("Loading map...");
                    Mapa TempMapa = new Mapa();
                    Mapa imported = TempMapa.importMap("mapa.txt");
                    if (imported != null) {
                        System.out.println("Map loaded successfully.");
                        System.out.println(imported.toString());
                        game.setMapa(imported);
                    } else {
                        System.out.println("Failed to load map.");
                    }
                    break;
                case 3:
                    // Save map
                    if (mapa != null) {
                        System.out.println("Saving map...");
                        mapa.exportMap(mapa,"map.csv");
                    } else {
                        System.out.println("No map to save.");
                    }
                    break;
                case 4:
                    System.out.println("Starting the game.Be Prepared...");

                    break;
                case 5:
                    System.out.println("Exiting game...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }

            game = new Game(mapa);
            game.setMapa(mapa);

        } while (choice != 5);

    }


}
