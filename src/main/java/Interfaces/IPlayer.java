package Interfaces;

import Exceptions.EmptyCollectionException;
import enums.MapaType;
import api.Player;

/**
 * Interface que representa um jogador. Esta interface e utilizada
 * para implementar um jogador, permitindo mover os bots, verificar se estes tem a flag e devolver a flag a base.
 */
public interface IPlayer {

    /**
     * Metodo que nos permite comparar a posicao dos bots e verificar se estes tem a flag.
     * Apos isso caso tenham a flag e estejam na mesma posicao, a flag e devolvida a base.
     * @param player1 player 1
     * @param player2 player 2
     */
    void checkBotHaveFlagIsInSamePos(Player player1, Player player2) ;

    /**
     * Metodo para verificar se o bot a mover tem flag e entra na mesma posicao que um bot inimigo sem flag.
     * Se isso acontecer a flag e devolvida a base.
     * @param player1 player 1
     * @param player2 player 2
     * @param location posicao do bot a mover
     */
    void checkFlagReturnToBase(Player player1, Player player2, int location);

    /**
     * Metodo que nos permite mover os bots . Quando captura a flag o bot retorna a base.
     * @param player1 player 1
     * @param player2 player 2
     * @param type tipo de mapa
     * @return 1 se o bot chegou a base, 0 se nao chegou
     * @throws EmptyCollectionException Caso a lista de posicoes esteja vazia
     */
    public int moveBot(Player player1, Player player2, MapaType type) throws EmptyCollectionException;

    /**
     * Metodo que nos permite mover os bots para a base. Quando captura a flag o bot retorna a base.
     * Utilizamos os metodos que verificam as posicoes dos bots e se estes tem a flag para devolver a flag a base.
     * Metodo para mapa bidirecional
     * @param player1 player 1
     * @param player2 player 2
     * @return 1 se o bot chegou a base, 0 se nao chegou
     * @throws EmptyCollectionException Caso a lista de posicoes esteja vazia
     */
    public int moveBotToBase(Player player1, Player player2) throws EmptyCollectionException;

    /**
     * Metodo que nos permite mover os bots para a base. Quando captura a flag o bot retorna a base.
     * Utilizamos os metodos que verificam as posicoes dos bots e se estes tem a flag para devolver a flag a base.
     * Metodo para mapa unidirecional
     * @param player1 player 1
     * @param player2 player 2
     * @return 1 se o bot chegou a base, 0 se nao chegou
     * @throws EmptyCollectionException Caso a lista de posicoes esteja vazia
     */
    public int moveBotToBaseUnidirecional(Player player1,  Player player2) throws EmptyCollectionException;


}
