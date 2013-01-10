package graph.samples;

import graph.impl.JfxGraph;
import graph.model.AcycleBrokenException;
import graph.model.vertex.CircleVertex;
import graph.model.vertex.Vertex;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SimpleGraphSample extends Application{
	
	protected void init(Stage primaryStage) {
		JfxGraph graph = new JfxGraph();
		final Vertex vertex1 = new CircleVertex(50, 50, 20, Color.rgb(156,216,255), 0);
		final Vertex vertex2 = new CircleVertex(100, 100, 20, Color.rgb(156,216,255), 0);
		
		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		
		//uncomment to show edge numbers 
//		graph.setShowEdgeDescription(true);
		
		try {
			graph.bindVertexes(vertex1, vertex2);
		} catch (AcycleBrokenException e) {
			e.printStackTrace();
		}
		
		primaryStage.setScene(new Scene(graph, 500, 500));
	}
	
	public static void main(String[] args) { launch(args); }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
}
