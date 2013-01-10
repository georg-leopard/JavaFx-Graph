package graph.samples;

import graph.impl.DirectedWeightedJfxGraph;
import graph.model.AcycleBrokenException;
import graph.model.vertex.CircleVertex;
import graph.model.vertex.Vertex;
import graph.model.vertex.WeightedVertex;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DirectedWeightedGraphSample extends SimpleGraphSample {

	@Override
	protected void init(Stage primaryStage) {
		
		DirectedWeightedJfxGraph graph = new DirectedWeightedJfxGraph(false); 
		
		final Vertex vertex1 = new WeightedVertex(new CircleVertex(50, 50, 20, Color.rgb(156,216,255), 0), 5);
		final Vertex vertex2 = new WeightedVertex(new CircleVertex(100, 200, 20, Color.rgb(156,216,255), 0), 5);
		final Vertex vertex3 = new WeightedVertex(new CircleVertex(200, 100, 20, Color.rgb(156,216,255), 0), 5);
		
		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addVertex(vertex3);
		
		
		try {
			graph.bindVertexes(vertex1, vertex2);
			graph.bindVertexes(vertex1, vertex3);
		} catch (AcycleBrokenException e) {
			e.printStackTrace();
		}
		
		primaryStage.setScene(new Scene(graph, 500, 500));
	}
	
	public static void main(String[] args) { launch(args); }
	
}
