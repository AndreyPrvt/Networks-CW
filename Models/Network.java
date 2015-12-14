package Models;

/**
 * Created by andreyprvt on 13.12.15.
 */
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

import java.util.ArrayList;
import java.util.Random;

public class Network{


    final int nodeAmount = 28;
    final double averageDegree = 3.5;
    ArrayList<Node> nodes;

    public Graph<Node, Chanel> getG() {
        return g;
    }

    public void setG(Graph<Node, Chanel> g) {
        this.g = g;
    }

    Graph<Node, Chanel> g = new SparseMultigraph<Node,Chanel>();
    private Random random;


    public Network(){
        nodes = new ArrayList<Node>();
        random = new Random();
        for (int i = 0; i < nodeAmount; i++) {
            Node node = new Node();
            nodes.add(node);
        }


        for (int i = 0; i < nodeAmount * averageDegree; i++) {
            int node1 = random.nextInt(nodeAmount);
            int node2 = random.nextInt(nodeAmount);
            if (node1!=node2){
                new Chanel(2.0, g, true, nodes.get(node1), nodes.get(node2));
            }
        }

    }
}