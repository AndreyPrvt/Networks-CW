package Models;

/**
 * Created by andreyprvt on 13.12.15.
 */

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Network{


    final int nodeAmount = 28;
    final double averageDegree = 3.5;
    ArrayList<Node> nodes;
    static private int satelliteAmount = 3;
    double[] weights = {2.0, 3.0, 4.0, 5.0};
    ArrayList<Integer> satelliteChanelIndex;
    HashMap<Integer, Integer> usedNodes;

    public Graph<Node, Chanel> getG() {
        return this.g;
    }

    public void setG(Graph<Node, Chanel> g) {
        this.g = g;
    }

    Graph<Node, Chanel> g = new SparseMultigraph<Node,Chanel>();
    private Random random;


    private boolean isUniq(int node1, int node2){

        for(Map.Entry<Integer, Integer> entry: usedNodes.entrySet()){
            if (entry.getKey().equals(node1) && entry.getValue().equals(node2)){
                return false;
            }
            if (entry.getKey().equals(node2) && entry.getValue().equals(node1))
                return false;
        }

        return true;
    }

    public Network() {
        nodes = new ArrayList<Node>();
        random = new Random();
        satelliteChanelIndex = new ArrayList<Integer>();
        usedNodes = new HashMap<Integer, Integer>();
        for (int i = 0; i < nodeAmount; i++) {
            Node node = new Node();
            nodes.add(node);
        }

        for (int i = 0; i < satelliteAmount; i++) {
            satelliteChanelIndex.add(random.nextInt(nodeAmount));
        }


        for (int i = 0; i < nodeAmount * averageDegree / 2; i++) {
            int node1 = random.nextInt(nodeAmount);
            int node2 = random.nextInt(nodeAmount);
            if ((node1 != node2)&& (isUniq(node1, node2))){
                //System.out.println(node1 + " " + node2);
                usedNodes.put(node1,node2);
                if (satelliteChanelIndex.contains(i)) {
                    new Chanel(weights[random.nextInt(weights.length)], g, random.nextBoolean(), true, nodes.get(node1), nodes.get(node2));
                    //System.out.println("satellite");
                }
                    else
                    new Chanel(weights[random.nextInt(weights.length)], g, random.nextBoolean(), false, nodes.get(node1), nodes.get(node2));
            }
        }

    }
}