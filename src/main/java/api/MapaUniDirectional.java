package api;

import Interfaces.IMapaUniDirectional;

import java.util.Random;

/**
 * Classe que representa um mapa unidirecional.
 * Esta classe extende a classe Mapa e implementa a interface IMapaUniDirectional.
 * @author : André Faria
 * @author : Daniela Mendes
 */
public class MapaUniDirectional extends Mapa implements IMapaUniDirectional {

    /**
     * Construtor da classe MapaUniDirectional
     */
    public MapaUniDirectional() {
        super();
    }

    /**
     * Metodo que adiciona uma conexao entre dois vertices.
     * E necessario criar este metodo e dar overridade de mapa, pois e preciso garantir que a conexao e unidirecional.
     * Em vez de dividirmos por 2 como no mapa bidirecional, nao precisamos dividir pelas 2 arestas.
     * @param numVertices Numero de vertices
     * @param density Densidade
     */
    @Override
    public void addConnection(int numVertices, int density) {
        Random rand = new Random();
        int maxEdges = (int) ((numVertices * (numVertices - 1)) * (density / 100.0));
        int edgeCount = 0;

        while (edgeCount < maxEdges) {
            int i = rand.nextInt(numVertices);
            int j = rand.nextInt(numVertices);
            if(i != j && adjMatrix[i][j] == Double.POSITIVE_INFINITY) {
                int distancia = rand.nextInt(MAX_DISTANCE) + 1;
                adjMatrix[i][j] = distancia;
                edgeCount++;
            }
        }
    }

    /**
     * Metodo para imprimir o mapa unidirecional. Imprime a matriz de adjacencia e as conexoes para visualizar em String.
     * @return String com o mapa unidirecional.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("MapaUniDirectional Adjacency Matrix:\n     ");
        for (int i = 0; i < numVertices; i++) {
            result.append(String.format("%5d", i));
        }
        result.append("\n");
        for (int i = 0; i < numVertices; i++) {
            result.append(String.format("%4d ", i));
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] != Double.POSITIVE_INFINITY) {
                    result.append(String.format("%5.1f", adjMatrix[i][j]));
                } else {
                    result.append("  N/A");
                }
            }
            result.append("\n");
        }
        result.append("\nConnections:\n");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] != Double.POSITIVE_INFINITY) {
                    result.append("Vertex ").append(i).append(" is connected to Vertex ").append(j);
                    result.append(" with weight ").append(adjMatrix[i][j]).append("\n");
                }
            }
        }
        return result.toString();
    }
}
