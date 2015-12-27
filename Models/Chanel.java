package Models;

import edu.uci.ics.jung.graph.Graph;

import java.awt.*;

/**
 * Created by andreyprvt on 13.12.15.
 */

public class Chanel {

    private final boolean satellite;
    private final boolean duplex;
    private double weight = 0;
    private int id;
    private Node fromNode;
    private Node toNode;
    public static int count = 0;


    public Chanel(double weight, Graph<Node, Chanel> graph, boolean duplex, boolean chanelType, Node fromNode, Node toNode) {

        this.id = Chanel.count++;
        this.weight = weight;
        this.duplex = duplex;
        this.fromNode = fromNode;
        this.satellite =chanelType;
        graph.addEdge(this, fromNode, toNode);

    }


    public boolean isDuplex(){
        return duplex;
    }


    public boolean isSatellite(){
        return satellite;
    }


    public Paint forTransformColor() {
        if (isSatellite())
            return Color.RED;
        return Color.BLACK;
    }



    public Stroke forTransformFont(float[] dash){
        if (isDuplex())
            return new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);;
        return new BasicStroke();
    }


    public String toString() { // Always good for debugging
        return String.valueOf(weight);
    }

}


