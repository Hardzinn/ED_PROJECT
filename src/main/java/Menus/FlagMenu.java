package Menus;

import Exceptions.NonComparableElementException;
import org.example.Flag;
import org.example.Game;
import org.example.Mapa;
import org.example.Player;

import java.util.Scanner;

public class FlagMenu {
    private Game game;

    public FlagMenu(Game game) {
        this.game = game;
    }

    public void flagMenu(){
        Scanner scanner= new Scanner(System.in);

        Mapa mapa = game.getMapa();

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        System.out.println("\n----- Flag Menu -----");
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
        Scanner scanner= new Scanner(System.in);
        int option;

        System.out.println(player.getName());
        System.out.print("Position of the flag: ");

        do {

            option = scanner.nextInt();
            if (option < 0 || option > (mapa.getNumVertices() - 1)) {
                System.out.println("Invalid position");
            }
        } while (option < 0 || option > (mapa.getNumVertices() - 1));

        player.setFlag(new Flag(option));
        player.getFlag().setPositionBase(option);
    }
}
