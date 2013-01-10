package graph.impl;

import graph.model.edge.DirectedWeightedEdge;
import graph.model.edge.Edge;
import graph.model.vertex.Vertex;

public class DirectedWeightedJfxGraph extends DirectedJfxGraph {

	private final WeightedGraphStuff weightedGraphStuff = new WeightedGraphStuff();
	
	public DirectedWeightedJfxGraph(boolean isAcyclicBinder) {
		super(isAcyclicBinder);
	}

	@Override
	public void addVertex(Vertex vertex) {
		weightedGraphStuff.recordVertexWeight(vertex);
		super.addVertex(vertex);
	}

	@Override
	public Edge createEdge(Vertex vertexA, Vertex vertexB, int weight) {
		//TODO handle edge numbers
		DirectedWeightedEdge edge = new DirectedWeightedEdge(vertexA.getCenterX(), vertexA.getCenterY(), 
				vertexB.getCenterX(), vertexB.getCenterY(), 0, weight);
		weightedGraphStuff.recordEdgeWeight(edge.getNumber(), edge.getNodeWeight());
		
		getChildren().addAll(edge, edge.getDescription(), edge.getArrowHead().lineA, edge.getArrowHead().lineB);
		edge.getArrowHead().toBack();
		return edge;
	}
	
	

}
