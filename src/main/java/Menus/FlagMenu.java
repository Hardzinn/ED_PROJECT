package Menus;

import api.Flag;
import api.Game;
import api.Mapa;
import api.Player;

import java.util.Scanner;

/**
 * Classe que representa o menu das flags. Esta classe e responsavel por criar o menu das flags,
 * permitindo ao jogador escolher a posicao da flag.
 */
public class FlagMenu {

    /**
     * Atributos da classe FlagMenu
     * game - jogo a ser utilizado
     */
    private Game game;

    /**
     * Construtor da classe FlagMenu
     * @param game Jogo a ser utilizado
     */
    public FlagMenu(Game game) {
        this.game = game;
    }

    /**
     * Metodo que permite criar as flags. Para isso teremos que identificar primeiro qual o jogador que vai criar as flags.
     */
    public void flagMenu(){
        Scanner scanner= new Scanner(System.in);

        Mapa mapa = game.getMapa();

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        System.out.println("\n----- Flag Menu -----");
        do {
            createFlag(player1, mapa);
            createFlag(player2, mapa);

            if (player1.getFlag().getPositionFlag().equals(player2.getFlag().getPositionFlag())) {
                System.out.println("Players can´t have the same position, please try again \n");
            }
        }while (player1.getFlag().getPositionFlag().equals(player2.getFlag().getPositionFlag()));

        game.setPlayer1(player1);
        game.setPlayer2(player2);

    }

    /**
     * Metodo que permite criar a flag. Para isso teremos que identificar primeiro qual o jogador que vai criar a flag.
     * @param player Jogador que vai criar a flag
     * @param mapa Mapa a ser utilizado
     */
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
