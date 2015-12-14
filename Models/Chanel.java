package Models;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;

/**
 * Created by andreyprvt on 13.12.15.
 */

public class Chanel {

    enum ChanelType {SATELLITE, nonSATELLITE}

    public ChanelType getChanelType() {
        return chanelType;
    }

    public void setChanelType(ChanelType chanelType) {
        this.chanelType = chanelType;
    }

    ChanelType chanelType;
    private double weight = 0;
    private int id;
    private final boolean directed;
    private Node fromNode;
    private Node toNode;
    static int count = 0;


    public Chanel(double weight, Graph<Node, Chanel> graph, boolean directed, Node fromNode, Node toNode) {

        this.id = Chanel.count++;
        this.weight = weight;
        this.directed = directed;
        this.fromNode = fromNode;

        if (this.directed) {
            graph.addEdge(this, fromNode, toNode, EdgeType.DIRECTED);
        } else {
            graph.addEdge(this, fromNode, toNode);
        }
    }

    public String toString() { // Always good for debugging
        return String.valueOf(id);
    }

}


