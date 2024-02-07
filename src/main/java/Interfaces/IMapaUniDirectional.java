package Interfaces;

/**
 * Interface que representa um mapa unidirecional. Esta interface e utilizada
 * para implementar um mapa unidirecional, permitindo adicionar uma conexao entre dois vertices e imprimir o mapa unidirecional.
 * Extende a interface IMapa para garantir que temos os metodos necessarios para criar um mapa.
 */
public interface IMapaUniDirectional extends IMapa{

    /**
     * Metodo que adiciona uma conexao entre dois vertices.
     * E necessario criar este metodo e dar overridade de mapa, pois e preciso garantir que a conexao e unidirecional.
     * Em vez de dividirmos por 2 como no mapa bidirecional, nao precisamos dividir pelas 2 arestas.
     * @param numVertices Numero de vertices
     * @param density Densidade
     */
    public void addConnection(int numVertices, int density);

    /**
     * Metodo para imprimir o mapa unidirecional. Imprime a matriz de adjacencia e as conexoes para visualizar em String.
     * @return String com o mapa unidirecional.
     */
    public String toString();


}
