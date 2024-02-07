package Interfaces;

import api.Mapa;
import enums.MapaType;

import java.util.Iterator;

/**
 * Interface que representa o mapa. Esta interface e responsavel por criar um mapa, adicionar vertices e ligacoes,
 * exportar e importar um mapa apartir de um ficheiro txt e iterar a arvore de custo minimo.
 */
public interface IMapa {

    /**
     * Metodo que cria um mapa. Este metodo cria um mapa com um numero de locais(vertices), densidade
     * (se queremos o mapa com muitas ligacoes em %)  e tipo de mapa.
     * @param numeroLocal Numero de locais
     * @param density Densidade
     * @param type Tipo de mapa
     */
    void createMap(int numeroLocal, int density, MapaType type);

    /**
     * Metodo para adicionar vertices ao mapa. Apartir do numero de locais, vamos adicionando vertices ao mapa.
     * @param numeroLocal Numero de locais a adicionar
     */
    void addLocal(int numeroLocal);

    /**
     * Metodo para adicionar ligacoes entre os vertices. Este metodo vai adicionar ligacoes entre os vertices
     * apartir da densidade que queremos. Se a densidade for 100% entao todos os vertices estao ligados entre si.
     * @param numVertices Numero de vertices
     * @param density Densidade
     */
    void addConnection(int numVertices, int density);

    /**
     * Metodo para export um mapa. Este metodo exporta um mapa para um ficheiro txt.
     * No mapa exportamos o numero de vertices, o tipo de mapa e a matriz de adjacencia.
     * @param mapa a ser exportado
     * @param filename Nome do ficheiro que queremos criar
     */
    void exportMap(Mapa mapa, String filename);

    /**
     * Metodo para importar um mapa. Este metodo importa um mapa apartir de um ficheiro txt.
     * No mapa importamos o numero de vertices, o tipo de mapa e a matriz de adjacencia.
     * @param filename Nome do ficheiro que queremos importar
     * @return Mapa importado
     */
    Mapa importMap(String filename);

    /**
     * Metodo para iterar a arvore de custo minimo. Metodo importante para que podes iterar a arvore geradora de custo
     * minimo apartir de um vertice inicial. Este metodo e importante para os algoritmos utilizados na classe Bot.
     * @param mapa Mapa a ser iterado
     * @param startVertex Vertice inicial
     * @return Iterador da arvore de custo minimo
     */
    Iterator<Integer> iteratorMST(Mapa mapa, Integer startVertex);

    /**
     * Metodo toString para imprimir o mapa. Este metodo imprime a matriz de adjacencia e as ligacoes entre os vertices.
     * @return String com a matriz de adjacencia e as ligacoes entre os vertices.
     */
    String toString();
}
