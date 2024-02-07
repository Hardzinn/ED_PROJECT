package api;

import Estruturas.Lists.ArrayUnorderedList;
import Exceptions.EmptyCollectionException;

import java.util.UUID;

/**
 * Classe que representa um Bot. Esta classe implementa a interface Comparable.
 * Esta classe e responsavel por criar um Bot, definir a sua posicao, destino, algoritmo e se tem a flag.
 * Utilizamos UUID para gerar um identificador unico para cada bot.
 * @author : André Faria
 * @author : Daniela Mendes
 */
public class Bot implements Comparable {

    /**
     * Atributos da classe Bot
     * id - identificador unico do bot
     * positions - lista de posicoes
     * positionsBase - lista de posicoes da base
     * position - posicao atual
     * destination - destino
     * algoritmo - algoritmo a ser utilizado
     * blocked - se esta bloqueado
     * flag - se tem a flag
     */

    private UUID id;
    private ArrayUnorderedList<Integer> positions;
    private ArrayUnorderedList<Integer> positionsBase;
    private int position;
    private int destination;
    private Algoritmo algoritmo;
    private boolean blocked = false;
    private boolean flag;

    /**
     * Construtor da classe Bot
     * @param position Posicao inicial
     * @param destination Destino
     * @param algoritmo Algoritmo a ser utilizado
     * @param flag Se tem a flag
     */

    public Bot(int position, int destination, Algoritmo algoritmo, boolean flag) {
        this.id = UUID.randomUUID();
        this.position = position;
        this.destination = destination;
        this.algoritmo = algoritmo;
        this.flag = flag;
    }

    /**
     * Metodo que retorna o identificador do bot
     * @return id
     */

    public UUID getId() {
        return id;
    }

    /**
     * Metodo que nos permite obter a posicao do bot
     * @return position - posicao do bot
     */
    public int getPosition() {
        return position;
    }

    /**
     * Metodo que nos permite definir a posicao do bot
     * @param position Posicao a definir
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Metodo que nos permite obter o destino do bot
     * @return destino
     */
    public int getDestination() {
        return destination;
    }

    /**
     * Metodo que nos permite definir o destino do bot
     * @param destination Destino a definir
     */
    public void setDestination(int destination) {
        this.destination = destination;
    }

    /**
     * Metodo que nos permite obter as posiçoes do bot.
     * Serve para ver as posicoes que o bot percorreu ate a flag
     * @return algoritmo
     */
    public ArrayUnorderedList<Integer> getPositions() {
        return positions;
    }

    /**
     * Metodo que nos permite definir as posiçoes do bot
     * @param positions Posicoes a definir
     */
    public void setPositions(ArrayUnorderedList<Integer> positions) {
        this.positions = positions;
    }

    /**
     * Metodo que nos permite obter as posicoes ate a base do bot.
     * Serve para ver as posicoes que o bot percorreu ate a base
     * @return positionsBase - posicoes ate a base
     */
    public ArrayUnorderedList<Integer> getPositionsBase() {
        return positionsBase;
    }

    /**
     * Metodo que nos permite definir as posiçoes do bot ate a base
     * @param positionsBase Posicoes a definir
     */
    public void setPositionsBase(ArrayUnorderedList<Integer> positionsBase) {
        this.positionsBase = positionsBase;
    }

    /**
     * Metodo nos permite ver se o bot está bloqueado ou nao.
     * @return blocked - se esta bloqueado
     */
    public boolean isBlocked() {
        return blocked;
    }

    /**
     * Metodo que nos permite definir se o bot esta bloqueado ou nao.
     * @param blocked - se esta bloqueado
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * Metodo que nos permite ver se o bot tem a flag ou nao.
     * @return flag - se tem a flag ou nao
     */
    public boolean hasFlag() {
        return flag;
    }

    /**
     * Metodo que nos permite definir se o bot tem a flag ou nao.
     * @param flag - se tem a flag ou nao
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * Metodo que vai movendo o bot ate chegar a flag. Adicona a posicao atual a lista de posicoes.
     * @return 1 se o bot chegou a flag, 0 se nao chegou
     * @throws EmptyCollectionException Caso a lista de posicoes esteja vazia
     */

    public int move() throws EmptyCollectionException {
        if (!positions.isEmpty()) {
            int nextPosition = positions.removeFirst();
            position = nextPosition;

            positions.addToRear(position);

            System.out.println("ID BOT: " + id);
            System.out.println("POSIÇÕES - " + positions.toString());
            System.out.println("Posição atual " + position);

            if (position == destination) {
                setFlag(true);
                return 1;
            }
        }
        return 0;
    }

    /**
     * Metodo que vai movendo o bot depois de este ter pegado a flag ate a base.
     * Adicona a posicao atual a lista de posicoes.
     * Metodo para o mapa bidirecional
     * @param player Jogador que tem a flag
     * @throws EmptyCollectionException Caso a lista de posicoes esteja vazia
     */
    public void moveToBase(Player player) throws EmptyCollectionException {
        if (!positions.isEmpty()) {
            if (positions.last() == destination) {
                positions.addToFront(positions.removeLast());
            }

            int nextPosition = positions.removeLast();
            position = nextPosition;

            positions.addToFront(position);
            positions.last();

            System.out.println("ID BOT: " + id);
            System.out.println("POSIÇÕES - " + positions.toString());
            System.out.println("Posição atual " + position);

            if (position == player.getPositionFlag()) {
                setBlocked(true);
            }

        }
    }

    /**
     * Metodo que vai movendo o bot depois de este ter pegado a flag ate a base.
     * Adicona a posicao atual a lista de posicoes.
     * Metodo para o mapa unidirecional
     * @param player Jogador que tem a flag
     * @throws EmptyCollectionException Caso a lista de posicoes esteja vazia
     */
    public void moveUniToBase(Player player) throws EmptyCollectionException {
        if (!positionsBase.isEmpty()) {
            if (positionsBase.first() == destination) {
                positionsBase.addToRear(positionsBase.removeFirst());
            }

            int nextPosition = positionsBase.removeFirst();
            position = nextPosition;

            positionsBase.addToRear(position);

            System.out.println("ID BOT: " + id);
            System.out.println("POSIÇÕES - " + positionsBase.toString());
            System.out.println("Posição atual " + position);

            if (position == player.getPositionFlag()) {
                setBlocked(true);
            }
        }
    }

    /**
     * Metodo que nos permite comparar dois bots.
     * Este metodo e necessario para implementar a interface Comparable
     * Nao e usado.
     * @param o Bot a comparar
     * @return 0 se forem iguais
     */
    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
