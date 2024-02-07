package Interfaces;

import api.Mapa;

/**
 * Interface que representa um algoritmo. Esta interface e implementada por classes que representam algoritmos
 * que permitem calcular o caminho mais curto entre dois vertices, percorrer o mapa em largura e percorrer a arvore
 * geradora de custo minimo.
 * Esta interface e utilizada para implementar os algoritmos shortestPath, BFS e MST.
 */
public interface IAlgoritmo {

    /**
     * Metodo que nos permite calcular o caminho mais curto entre dois vertices, guardando as posicoes no array dados
     * e percorrendo o iterador iter sempre que este tive um next.
     * @param mapa Mapa a ser percorrido.
     * @param startVertex Vertice inicial.
     * @param lastVertex Ultimo Vertice a percorrer.
     */
     void shortestPath(Mapa mapa, int startVertex, int lastVertex);

    /**
     * Metodo que nos permite calcular o caminho utilizando o método Breadth First Search, percorrendo em largura o mapa
     * e guardando as posicoes no array dados
     * ,percorrendo o iterador iter sempre que este tive um next.
     * @param mapa Mapa a ser percorrido.
     * @param startVertex Vertice inicial.
     */
    void BFS(Mapa mapa, Integer startVertex);

    /**
     * Metodo que nos permite percorrer a arvore geradora de custo minimo, guardando as posicoes no array dados
     * e percorrendo o iterador iter sempre que este tive um next.
     * Utiliza o iterador MST criado em mapa que nos permite percorrer esta arvore.
     * @param mapa Mapa a ser percorrido.
     * @param startVertex Vertice inicial.
     */
    void minimumTree(Mapa mapa, Integer startVertex);
}
