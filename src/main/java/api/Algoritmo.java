package api;

import Estruturas.Lists.ArrayUnorderedList;
import Exceptions.VertexNotFoundException;

import java.util.Iterator;

public class Algoritmo {

    private ArrayUnorderedList<Integer> positions;

    private Iterator iter;

    public Algoritmo() {
        this.positions = new ArrayUnorderedList<>();
    }

    public ArrayUnorderedList<Integer> getPositions() {
        return positions;
    }

    public void setPositions(ArrayUnorderedList<Integer> positions) {
        this.positions = positions;
    }


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
