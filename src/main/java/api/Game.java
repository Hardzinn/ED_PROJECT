package api;

import java.util.Random;

import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;
import Interfaces.IGame;


/**
 * Classe que representa um jogo. Esta classe implementa a interface IGame.
 * Esta classe e responsavel por criar um jogo, definir o mapa, os jogadores e o estado do jogo.
 * Utilizamos player1 e player2 para simplificar a implementacao do jogo.
 * @author : André Faria
 * @author : Daniela Mendes
 */
public class Game implements IGame {

    /**
     * Atributos da classe Game
     * mapa - mapa a ser utilizado
     * player1 - jogador 1
     * player2 - jogador 2
     * gameOver - estado do jogo. Neste caso para terminar jogo
     */
    private Mapa mapa;
    private Player player1;
    private Player player2;
    private boolean gameOver;

    /**
     * Construtor da classe Game
     * @param mapa Mapa a ser utilizado.Pode ser uni direcional e bi direcional
     */
    public Game(Mapa mapa) {
        this.mapa = mapa;
    }

    /**
     * Metodo que retorna o mapa
     * @return mapa
     */
    public Mapa getMapa() {
        return mapa;
    }

    /**
     * Metodo que define o mapa
     * @param mapa Mapa a ser definido
     */
    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    /**
     * Metodo que retorna o jogador 1
     * @return jogador 1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Metodo que define o jogador 1
     * @param player1 Jogador 1 a ser definido
     */
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    /**
     * Metodo que retorna o jogador 2
     * @return jogador 2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Metodo que define o jogador 2
     * @param player2 Jogador 2 a ser definido
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
     * Metodo que retorna o estado do jogo
     * @return estado do jogo
     */
    public boolean getGameStatus() {
        return gameOver;
    }

    /**
     * Metodo que define o estado do jogo
     * @param gameOver Estado do jogo a ser definido
     */
    public void setGameStatus(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Metodo que define o vencedor do jogo
     * @param player Jogador vencedor
     */
    public void setWinner(Player player) {
        System.out.println("The winner is " + player.getName());
    }

    /**
     * Metodo que de maneira random, determina a ordem da ronda dos jogadores
      * @return ordem dos players
     */
    public Player determineTurnOrder() {
        Random random = new Random();
        int turnOrder = random.nextInt(2) + 1;

        switch (turnOrder) {
            case 1:
                return player1;
            case 2:
                return player2;
            default:
                throw new IllegalStateException("Invalid turn order");
        }
    }

    /**
     * Metodo que permite jogar uma ronda. Para isso teremos que identificar primeiro qual o jogador que vai jogar.
     * @param player Jogador que vai comecar a jogar
     * @throws EmptyCollectionException Caso a colecao esteja vazia
     * @throws NonComparableElementException Caso o elemento nao seja comparavel
     */
    public void playRound(Player player) throws EmptyCollectionException, NonComparableElementException {
        int p3, p4;

        if (player.getName().equals(player1.getName())) {

            p3 = player1.moveBot(player1, player2, getMapa().getType());
            if (p3 == 1) {
                setGameStatus(true);
                setWinner(player1);
                return;
            }

            p4 = player2.moveBot(player2,player1, getMapa().getType());
            if (p4 == 1) {
                setGameStatus(true);
                setWinner(player2);
                return;
            }

        } else {

            p4 = player2.moveBot(player2,player1, getMapa().getType());
            if (p4 == 1) {
                setGameStatus(true);
                setWinner(player2);
                return;
            }

            p3 = player1.moveBot(player1,player2, getMapa().getType());
            if (p3 == 1) {
                setGameStatus(true);
                setWinner(player1);
                return;
            }

        }

    }

}
