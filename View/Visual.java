package View;

import Controller.PopupGraphPlugin;
import Models.Chanel;
import Models.Network;
import Models.Node;
import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andreyprvt on 13.12.15.
 */
public class Visual {

    private static JFrame frame = new JFrame("Network");
    private static boolean componentFlag;
    private static VisualizationViewer<Node, Chanel> oldNetwork;
    private static Graph<Node, Chanel> graph;


    public static void main(String[] args) {

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenuItem netWork = new JMenuItem("Generate network");
        JMenuItem addNode = new JMenuItem("Add Node");
        JMenuItem addEdge = new JMenuItem("Add Edge");
        JMenuItem sendMessage = new JMenuItem("Send Message");



        netWork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setSize(new Dimension(700, 700));

                if (componentFlag){
                    frame.getContentPane().remove(oldNetwork);
                    Node.count = 0;
                    Chanel.count =0;
                    oldNetwork = new Visual().initNetwork();
                    frame.getContentPane().add(oldNetwork);
                }
                else {
                    componentFlag = true;
                    oldNetwork = new Visual().initNetwork();
                    frame.getContentPane().add(oldNetwork);
                }


                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        addNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (oldNetwork!=null) {
                    graph.addVertex(new Node());
                    oldNetwork.repaint();
                } else {
                    JLabel warning = new JLabel("Generete new network first");
                    frame.add(warning);
                }
            }
        });


        addEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEdgeTable(graph, oldNetwork);
                oldNetwork.repaint();
            }
        });


        file.add(netWork);
        edit.add(addEdge);
        edit.add(addNode);
        menuBar.add(file);
        menuBar.add(edit);
        frame.add(menuBar, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 600));
        frame.pack();
        frame.setVisible(true);
    }


    private VisualizationViewer<Node, Chanel> initNetwork(){

        Network network = new Network();
        // Layout<V, E>, VisualizationComponent<V,E>

        Layout<Node, Chanel> layout = new FRLayout2<Node, Chanel>(network.getG());

        layout.setSize(new Dimension(600,600));
        VisualizationViewer<Node, Chanel> vv = new VisualizationViewer<Node, Chanel>(layout);
        vv.setPreferredSize(new Dimension(600, 600));
        // Show vertex and edge labels
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<Node, Chanel>());

        Transformer<Node, Paint> vertexColor = new Transformer<Node, Paint>(){
            public Paint transform (Node javaItem){
                return Color.RED;
            }
        };
        Transformer<Chanel, Stroke> edgeStroke = new Transformer<Chanel, Stroke>() {
            float dash[] = { 10.0f };
            @Override
            public Stroke transform(Chanel chanel) {
                return chanel.forTransformFont(dash);
            }
        };

        vv.getRenderContext().setEdgeStrokeTransformer(edgeStroke);
        Transformer<Chanel, Paint> edgePaint = new Transformer<Chanel, Paint>() {
            @Override
            public Paint transform(Chanel ch) {
                return ch.forTransformColor();
            }
        };
        vv.getRenderContext().setEdgeDrawPaintTransformer(edgePaint);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
        // Create a graph mouse and add it to the visualization component
        PluggableGraphMouse graphMouse = new PluggableGraphMouse();
        graphMouse.add( new PickingGraphMousePlugin<Node,Chanel>() );
        graphMouse.add( new PopupGraphPlugin() );
        vv.setGraphMouse(graphMouse);
        graph = vv.getGraphLayout().getGraph();
        return vv;
    }
}