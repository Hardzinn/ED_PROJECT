package Menus;

import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;
import Menus.BotMenu;
import Menus.FlagMenu;
import org.example.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;
        Mapa mapa = null;
        Game game = new Game(mapa);


        new GameMenu().gameMenu(mapa, game);


        new FlagMenu(game).flagMenu();


        try {
            new BotMenu(game).menuGeral(game.getPlayer1().getName());
            new BotMenu(game).menuGeral(game.getPlayer2().getName());
            //Player player = game.determineTurnOrder();
            //game.playGame(player);

            Player player = game.determineTurnOrder();

            int ordem = 0;
            while (player.getFlag().getStatus() != FlagStatus.CAPTURED) {
                ordem = game.playRound(player);
            }

          /* while (!game.getGameStatus()){
               game.playRoundToBase(ordem);
           }*/

            //game.playGame();
        } catch (NonComparableElementException | EmptyCollectionException e) {
            throw new RuntimeException(e);
        }


        scanner.close();
    }

}