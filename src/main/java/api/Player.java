package api;

import Estruturas.Lists.ArrayOrderedList;
import Exceptions.EmptyCollectionException;
import Interfaces.IPlayer;
import enums.MapaType;

/**
 * Classe que representa um jogador. Esta classe implementa a interface IPlayer.
 * Esta classe e responsavel por criar um jogador, obter o nome do jogado e bots.
 * @author : André Faria
 * @author : Daniela Mendes
 */
public class Player implements IPlayer {

    /**
     * Atributos da classe Player
     * name - nome do jogador
     * numPlayer - numero do jogador
     * numBots - numero de bots
     * botIndex - indice do bot
     * flag - flag do jogador
     * bots - lista de bots
     */
    private String name;
    private int numBots;
    private int botIndex;
    private Flag flag;
    private ArrayOrderedList<Bot> bots;

    /**
     * Construtor da classe Player
     * @param name Nome do jogador
     */
    public Player(String name) {
        this.name = name;
        this.bots = new ArrayOrderedList<Bot>();
        this.botIndex = 0;
    }

    /**
     * Metodo que retorna o nome do jogador
     * @return nome do jogador
     */
    public String getName() {
        return name;
    }

    /**
     * Metodo que define o nome do jogador
     * @param name Nome do jogador
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metodo que retorna a flag do jogador
     * @return flag do jogador
     */
    public Flag getFlag() {
        return flag;
    }

    /**
     * Metodo que define a flag do jogador
     * @param flag Flag do jogador
     */
    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    /**
     * Metodo que retorna a lista de bots
     * @return lista de bots
     */
    public ArrayOrderedList<Bot> getBots() {
        return bots;
    }

    /**
     * Metodo que define uma lista de bots para o jogador.
     * @param bots Lista de bots
     */
    public void setBots(ArrayOrderedList<Bot> bots) {
        this.bots = bots;
    }

    /**
     * Metodo que retorna o numero de bots
     * @return numero de bots
     */
    public int getNumBots() {
        return numBots;
    }

    /**
     * Metodo que define o numero de bots
     * @param numBots Numero de bots
     */
    public void setNumBots(int numBots) {
        this.numBots = numBots;
    }

    /**
     * Metodo que retorna a posição da flag
     * @return posicao da flag
     */
    public int getPositionFlag() {
        return flag.getPositionFlag();
    }


    /**
     * Metodo que nos permite comparar a posicao dos bots e verificar se estes tem a flag.
     * Apos isso caso tenham a flag e estejam na mesma posicao, a flag e devolvida a base.
     * @param player1 player 1
     * @param player2 player 2
     */
    public void checkBotHaveFlagIsInSamePos(Player player1, Player player2) {
        if (player1.getBots() == null || player2.getBots() == null) {
            throw new IllegalArgumentException("Bots list cannot be null");
        }

        for (int i = 0; i < player1.getNumBots(); i++) {
            for (int j = 0; j < player2.getNumBots(); j++) {
                Bot bot1 = player1.getBots().get(i);
                Bot bot2 = player2.getBots().get(j);

                if (bot1.getPosition() == bot2.getPosition() && bot1.hasFlag() && bot2.hasFlag()) {
                    System.out.println("Bots with flags cant have the same position. \n");
                    System.out.println("Flag returned to base. \n");
                    System.out.println(bot1.getId() + " and " + bot2.getId() + " are in the same position");
                    bot1.setFlag(false);
                    bot2.setFlag(false);

                    if (player1.getFlag() != null) {
                        player1.getFlag().setPositionFlag(player1.getFlag().getPositionBase());
                    }

                    if (player2.getFlag() != null) {
                        player2.getFlag().setPositionFlag(player2.getFlag().getPositionBase());
                    }
                }
            }
        }
    }

    /**
     * Metodo para verificar se o bot a mover tem flag e entra na mesma posicao que um bot inimigo sem flag.
     * Se isso acontecer a flag e devolvida a base.
     * @param player1 player 1
     * @param player2 player 2
     */
    public void checkFlagReturnToBase(Player player1, Player player2, int location) {
        for (int i = 0; i < player2.getNumBots(); i++) {
            Bot bot = player2.getBots().get(i);
            if (bot.getPosition() == location && bot.hasFlag()) {
                System.out.println("Bot " + bot.getId() + " from " + player2.getName() + " has returned the flag to base.");
                player1.getFlag().setPositionFlag(player1.getFlag().getPositionBase());
            }
        }
    }


    /**
     * Metodo que nos permite mover os bots . Quando captura a flag o bot retorna a base.
     * @param player1 player 1
     * @param player2 player 2
     * @return 1 se o bot chegou a base, 0 se nao chegou
     * @throws EmptyCollectionException Caso a lista de posicoes esteja vazia
     */
    public int moveBot(Player player1, Player player2, MapaType type) throws EmptyCollectionException {
        for (int i = 0; i < numBots; i++) {
            Bot currentBot = bots.get(i);

            System.out.println("\n" + player1.getName());
            if (!currentBot.hasFlag()) {
                bots.get(i).move();
            }else
            {
                flag.capture(player1);
                System.out.println("\nBASE: " + player1.getName() + " has captured the flag." +
                        "\nReturning to the Base\n");
                if (type == MapaType.MAPA_UNIDIRECIONAL) {
                    return moveBotToBaseUnidirecional(player1, player2);
                } else {
                    return moveBotToBase(player1, player2);
                }

            }

        }
        return 0;
    }

    /**
     * Metodo que nos permite mover os bots para a base. Quando captura a flag o bot retorna a base.
     * Utilizamos os metodos que verificam as posicoes dos bots e se estes tem a flag para devolver a flag a base.
     * Metodo para mapa bidirecional
     * @param player1 player 1
     * @param player2 player 2
     * @return 1 se o bot chegou a base, 0 se nao chegou
     * @throws EmptyCollectionException Caso a lista de posicoes esteja vazia
     */
    public int moveBotToBase(Player player1, Player player2) throws EmptyCollectionException {
        for (int i = 0; i < numBots; i++) {
            Bot currentBot = bots.get(botIndex);

            System.out.println(player1.getName());

            currentBot.moveToBase(player1);

            botIndex = (botIndex + 1) % bots.size();

            checkFlagReturnToBase(player1, player2, currentBot.getPosition());

            if(currentBot.hasFlag()){
                checkBotHaveFlagIsInSamePos(player1, player2);
            }

            if (currentBot.isBlocked()) {
                flag.capturetoBase(player1);
                return 1;
            }
        }
        return 0;
    }


    /**
     * Metodo que nos permite mover os bots para a base. Quando captura a flag o bot retorna a base.
     * Utilizamos os metodos que verificam as posicoes dos bots e se estes tem a flag para devolver a flag a base.
     * Metodo para mapa unidirecional
     * @param player1 player 1
     * @param player2 player 2
     * @return 1 se o bot chegou a base, 0 se nao chegou
     * @throws EmptyCollectionException Caso a lista de posicoes esteja vazia
     */
    public int moveBotToBaseUnidirecional(Player player1,  Player player2) throws EmptyCollectionException {
        for (int i = 0; i < numBots; i++) {
            Bot currentBot = bots.get(botIndex);

            System.out.println(player1.getName());

            currentBot.moveUniToBase(player1);

            botIndex = (botIndex + 1) % bots.size();

            checkFlagReturnToBase(player1, player2, currentBot.getPosition());

            if(currentBot.hasFlag()){
                checkBotHaveFlagIsInSamePos(player1, player2);
            }

            if (currentBot.isBlocked()) {
                flag.capturetoBase(player1);
                return 1;
            }
        }
        return 0;
    }

}
