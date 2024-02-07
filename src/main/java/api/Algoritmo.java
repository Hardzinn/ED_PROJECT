package api;

import Estruturas.Lists.ArrayUnorderedList;
import Exceptions.VertexNotFoundException;
import Interfaces.IAlgoritmo;

import java.util.Iterator;

/**
 * Classe de implementacao dos algoritmos. Esta classe implementa a interface IAlgoritmo.
 * Esta classe e responsavel pelos algoritmos de caminho mais curto, arvore minima e BFS.
 * @author : André Faria
 * @author : Daniela Mendes
 */

public class Algoritmo implements IAlgoritmo {

    /**
     * Atributos da classe Algoritmo
     * positions - lista de posicoes.
     * iter - iterador para percorrer a lista de posicoes
     */
    private ArrayUnorderedList<Integer> positions;

    private Iterator iter;

    /**
     * Construtor da classe Algoritmo
     */
    public Algoritmo() {
        this.positions = new ArrayUnorderedList<>();
    }

    /**
     * Metodo que retorna as posicoes da lista percorrida
     * @return posiçoes
     */
    public ArrayUnorderedList<Integer> getPositions() {
        return positions;
    }

    /**
     * Metodo que define as posicoes
     * @param positions Posicoes a definir
     */
    public void setPositions(ArrayUnorderedList<Integer> positions) {
        this.positions = positions;
    }

    /**
     * Metodo que nos permite calcular o caminho mais curto entre dois vertices, guardando as posicoes no array dados
     * e percorrendo o iterador iter sempre que este tive um next.
     * @param mapa Mapa a ser percorrido.
     * @param startVertex Vertice inicial.
     * @param lastVertex Ultimo Vertice a percorrer.
     */
    public void shortestPath(Mapa mapa, int startVertex, int lastVertex) {
        try {
            ArrayUnorderedList<Integer> dados= new ArrayUnorderedList<>();

            iter = mapa.iteratorShortestPath(startVertex, lastVertex);

            if (!iter.hasNext()) {
                System.out.println("Iterator is empty");
            }

            while (iter.hasNext()) {
                dados.addToRear((Integer) iter.next());
                positions=dados;
            }

            setPositions(positions);

        } catch (VertexNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que nos permite calcular o caminho utilizando o método Breadth First Search, percorrendo em largura o mapa
     * e guardando as posicoes no array dados
     * ,percorrendo o iterador iter sempre que este tive um next.
     * @param mapa Mapa a ser percorrido.
     * @param startVertex Vertice inicial.
     */
    public void BFS(Mapa mapa, Integer startVertex) {
        try {

            ArrayUnorderedList<Integer> dados= new ArrayUnorderedList<>();
            iter = mapa.iteratorBFS(startVertex);

            if (!iter.hasNext()) {
                System.out.println("Iterator is empty");
            }

            while (iter.hasNext()) {
                dados.addToRear((Integer) iter.next());
                positions=dados;
            }

            setPositions(positions);


        } catch (VertexNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que nos permite percorrer a arvore geradora de custo minimo, guardando as posicoes no array dados
     * e percorrendo o iterador iter sempre que este tive um next.
     * Utiliza o iterador MST criado em mapa que nos permite percorrer esta arvore.
     * @param mapa Mapa a ser percorrido.
     * @param startVertex Vertice inicial.
     */
    public void minimumTree(Mapa mapa, Integer startVertex) {
        ArrayUnorderedList<Integer> dados= new ArrayUnorderedList<>();
        iter = mapa.iteratorMST(mapa, startVertex);

        while (iter.hasNext()) {
            dados.addToRear((Integer) iter.next());
            positions=dados;
        }

        setPositions(positions);
    }
}
