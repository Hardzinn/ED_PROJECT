package Interfaces;

import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;
import api.Player;

/**
 * Interface que representa um jogo. Esta interface e utilizada
 * para implementar um jogo, permitindo determinar a ordem dos jogadores entre outras funcionalidades.
 */
public interface IGame {

    /**
     * Metodo que de maneira random, determina a ordem da ronda dos jogadores
     * @return ordem dos players
     */
    Player determineTurnOrder();

    /**
     * Metodo que permite jogar uma ronda. Para isso teremos que identificar primeiro qual o jogador que vai jogar.
     * @param player Jogador que vai comecar a jogar
     * @throws EmptyCollectionException Caso a colecao esteja vazia
     * @throws NonComparableElementException Caso o elemento nao seja comparavel
     */
    void playRound(Player player) throws EmptyCollectionException, NonComparableElementException;

    /**
     * Metodo que define o vencedor do jogo
     * @param player Jogador vencedor
     */
    void setWinner(Player player);



}
