package View;

import Models.Network;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.Transformer;
import Models.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by andreyprvt on 13.12.15.
 */
public class Visual {
    public static void main(String[] args) {
        // I create the graph in the following...
        Network network = new Network();
        // Layout<V, E>, VisualizationComponent<V,E>
        Layout<Node, Chanel> layout = new CircleLayout(network.getG());

        layout.setSize(new Dimension(600,600));
        VisualizationViewer<Node, Chanel> vv = new VisualizationViewer<Node, Chanel>(layout);
        vv.setPreferredSize(new Dimension(600,600));
        // Show vertex and edge labels
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        Transformer<Node, Paint> vertexColor = new Transformer<Node, Paint>(){
            public Paint transform (Node javaItem){
                return Color.RED;
            }
        };
        vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
        // Create a graph mouse and add it to the visualization component
        PluggableGraphMouse graphMouse = new PluggableGraphMouse();
        graphMouse.add( new PickingGraphMousePlugin<Node,Chanel>() );
        //graphMouse.add( new PopupGraphPlugin() );
        vv.setGraphMouse(graphMouse);

        JFrame frame = new JFrame("Interactive Graph View 1");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }
}