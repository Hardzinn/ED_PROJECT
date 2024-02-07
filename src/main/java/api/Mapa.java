package api;

import Estruturas.Graphs.Network;
import Estruturas.Lists.ArrayUnorderedList;
import Estruturas.Queues.LinkedQueue;
import Exceptions.EmptyCollectionException;
import Interfaces.IMapa;
import enums.MapaType;

import java.io.*;
import java.util.Iterator;
import java.util.Random;

/**
 * Classe que representa um Mapa. Esta classe extende a classe Network e implementa a interface IMapa.
 * Esta classe e responsavel por criar um mapa,adicionar locais e conexoes entre outro metodos que nos permitam
 * obter tanto os vertices do grafos e as suas ligacoes,
 * Tambem exporta e importa um mapa(Tanto Uni direcional como Bi) e metodo para iterar a arvore de custo minimo.
 * @author : André Faria
 * @author : Daniela Mendes
 */
public class Mapa extends Network<Integer> implements IMapa {

    /**
     * Atributos da classe Mapa
     * MAX_DISTANCE - distancia maxima permitida. Valor do enunciado
     * type - tipo de mapa( Uni direcional ou Bi direcional)
     */
    protected static final int MAX_DISTANCE = 15;

    /**
     * Tipo de mapa
     */
    private MapaType type;

    /**
     * Construtor da classe Mapa
     */
    public Mapa() {
        super();
    }


    /**
     * Metodo que retorna o tipo de mapa. Pode ser Uni direcional ou Bi direcional
     * @return Tipo de mapa
     */
    public MapaType getType() {
        return type;
    }

    /**
     * Metodo que define o tipo de mapa. Podemos definir se e Uni direcional ou Bi direcional
     * @param type Tipo de mapa
     */
    public void setType(MapaType type) {
        this.type = type;
    }

    /**
     * Metodo que cria um mapa. Este metodo cria um mapa com um numero de locais(vertices), densidade
     * (se queremos o mapa com muitas ligacoes em %)  e tipo de mapa.
     * @param numeroLocal Numero de locais
     * @param density Densidade
     * @param type Tipo de mapa
     */
    public void createMap(int numeroLocal, int density, MapaType type) {
        this.addLocal(numeroLocal);
        this.addConnection(numeroLocal, density);
        this.type = type;
    }

    /**
     * Metodo para adicionar vertices ao mapa. Apartir do numero de locais, vamos adicionando vertices ao mapa.
     * @param numeroLocal Numero de locais a adicionar
     */
    public void addLocal(int numeroLocal) {
        for(int i = 0; i < numeroLocal; i++) {
            this.addVertex(i);
        }
    }

    /**
     * Metodo para adicionar ligacoes entre os vertices. Este metodo vai adicionar ligacoes entre os vertices
     * apartir da densidade que queremos. Se a densidade for 100% entao todos os vertices estao ligados entre si.
     * @param numVertices Numero de vertices
     * @param density Densidade
     */
    public void addConnection(int numVertices, int density) {
        Random rand = new Random();
        int maxEdges = (int) ((numVertices * (numVertices - 1) / 2) * (density / 100.0));

        for(int i = 0; i < numVertices; i++) {
            for(int j = 0; j < i; j++) {
                if(rand.nextDouble() < density) {
                    int distancia = rand.nextInt(MAX_DISTANCE) + 1;
                    this.addEdge(i, j, distancia);
                    this.addEdge(j, i, distancia);
                }
                if(i >= maxEdges) {
                    return;
                }
            }
        }
    }

    /**
     * Metodo para obter as coordenadas de um vertice atraves da matriz de adjacencia.
     * @param vertice Vertice a obter as coordenadas
     * @return Coordenadas do vertice
     */
    public double[] getCoords(int vertice){
        return this.getAdjMatrix()[vertice];
    }

    /**
     * Metodo para obter a matriz de adjacencia
     * @return Matriz de adjacencia
     */
    public double[][] getAdjMatrix(){
        return this.adjMatrix;
    }

    /**
     * Metodo para verificar se existe uma ligacao entre dois vertices.
     * Este metodo e necessario para implementar a interface IMapa. Mas nao esta a ser usado em nenhum metodo
     * @param source Vertice de origem
     * @param target Vertice de destino
     * @return True se existe ligacao, False se nao existe
     */
    public boolean edgeExists(int source, int target) {
        return adjMatrix[source][target] != Double.POSITIVE_INFINITY;
    }


    /**
     * Metodo para obter os vertices do mapa. Iteramos o numero de vertices num ArrayUnorderedList
     * e retornamos os vertices todos
     * @return Lista de vertices
     */
    public Iterable<Integer> getVertices() {
        ArrayUnorderedList<Integer> vertices = new ArrayUnorderedList<>();
        for (int i = 0; i < numVertices; i++) {
            vertices.addToRear(i);
        }
        return vertices;
    }

    /**
     * Metodo para obter os vizinhos de um vertice. Iteramos a matriz de adjacencia e adicionamos os vizinhos
     * a um ArrayUnorderedList. Verificamos tambem se o existe algum vertice com POSITIVE_INFINITY
     * @param vertex Vertice a obter os vizinhos
     * @return Lista de vizinhos
     */
    public Iterable<Integer> getNeighbors(int vertex) {

        ArrayUnorderedList<Integer> neighbors = new ArrayUnorderedList<>();
        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[vertex][i] != Double.POSITIVE_INFINITY) {
                neighbors.addToRear(i);
            }
        }
        return neighbors;
    }

    /**
     * Metodo para obter numero de vertices de um mapa.
     * @return Numero de vertices
     */
    public int getNumVertices(){
        return numVertices;
    }

    /**
     * Metodo para export um mapa. Este metodo exporta um mapa para um ficheiro txt.
     * No mapa exportamos o numero de vertices, o tipo de mapa e a matriz de adjacencia.
     * @param mapa a ser exportado
     * @param filename Nome do ficheiro que queremos criar
     */
    public void exportMap(Mapa mapa, String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(mapa.getNumVertices());
            printWriter.println(mapa.getType());
            double[][] adjMatrix = mapa.getAdjMatrix();
            for (int i = 0; i < mapa.getNumVertices(); i++) {
                for (int j = 0; j < mapa.getNumVertices(); j++) {
                    printWriter.print(adjMatrix[i][j] + " ");
                }
                printWriter.println();
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para importar um mapa. Este metodo importa um mapa apartir de um ficheiro txt.
     * No mapa importamos o numero de vertices, o tipo de mapa e a matriz de adjacencia.
     * @param filename Nome do ficheiro que queremos importar
     * @return Mapa importado
     */
    public Mapa importMap(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            int numVertices = Integer.parseInt(br.readLine());
            String typeString = br.readLine();
            String typeName = typeString.replaceAll("\\s+", "_").toUpperCase();
            setType(MapaType.valueOf(typeName));
            Mapa mapa = new Mapa();
            mapa.setType(MapaType.valueOf(typeName));
            for (int i = 0; i < numVertices; i++) {
                mapa.addVertex(i);
            }
            for (int i = 0; i < numVertices; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < numVertices; j++) {
                    double weight = Double.parseDouble(line[j]);
                    if (weight != Double.POSITIVE_INFINITY) {
                        mapa.addEdge(i, j, weight);
                    }
                }
            }
            return mapa;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para iterar a arvore de custo minimo. Metodo importante para que podes iterar a arvore geradora de custo
     * minimo apartir de um vertice inicial. Este metodo e importante para os algoritmos utilizados na classe Bot.
     * @param mapa Mapa a ser iterado
     * @param startVertex Vertice inicial
     * @return Iterador da arvore de custo minimo
     */
    public Iterator<Integer> iteratorMST(Mapa mapa, Integer startVertex) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();
        if (mapa.getNumVertices() == 0) {
            return resultList.iterator();
        }
        boolean[] visited = new boolean[mapa.getNumVertices()];

        for(int i = 0; i < mapa.getNumVertices(); i++) {
            visited[i] = false;
        }

        double[][] adjMatrix = mapa.getAdjMatrix();

        queue.enqueue(startVertex);
        visited[startVertex] = true;

        while (!queue.isEmpty()) {
            int vertex = -1;
            try {
                vertex = queue.dequeue();
            } catch (EmptyCollectionException e) {
                System.out.println(e.getMessage());
            }

            resultList.addToRear(vertex);

            for (int i = 0; i < mapa.getNumVertices(); i++) {
                if (adjMatrix[vertex][i] != Double.POSITIVE_INFINITY && !visited[i]) {
                    queue.enqueue(i);
                    visited[i] = true;
                }
            }
        }

        return resultList.iterator();
    }

    /**
     * Metodo toString para imprimir o mapa. Este metodo imprime a matriz de adjacencia e as ligacoes entre os vertices.
     * @return String com a matriz de adjacencia e as ligacoes entre os vertices.
     */

    @Override
    public String toString() {
        String result = "Mapa Adjacency Matrix:\n     ";

        // Column headers for the matrix
        for (int i = 0; i < numVertices; i++) {
            result += String.format("%5d", i);
        }
        result += "\n";

        // Matrix rows
        for (int i = 0; i < numVertices; i++) {
            result += String.format("%4d ", i);
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] == Double.POSITIVE_INFINITY) {
                    result += "  N/A";
                } else {
                    result += String.format("%5.1f", adjMatrix[i][j]);
                }
            }
            result += "\n";
        }

        result += "\nConnections:\n";

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] != Double.POSITIVE_INFINITY) {
                    result += "Vertex " + i + " is connected to Vertex " + j;
                    result += " with weight " + adjMatrix[i][j] + "\n";
                }
            }
        }

        return result;
    }

}
