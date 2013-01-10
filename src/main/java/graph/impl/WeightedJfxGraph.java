package graph.impl;

import graph.model.edge.Edge;
import graph.model.edge.WeightedEdge;
import graph.model.vertex.Vertex;

public class WeightedJfxGraph extends JfxGraph{

	private final WeightedGraphStuff weightedGraphStuff = new WeightedGraphStuff();
	
	@Override
	public void addVertex(Vertex vertex) {
		weightedGraphStuff.recordVertexWeight(vertex);
		super.addVertex(vertex);
	}

	@Override
	public Edge createEdge(Vertex vertexA, Vertex vertexB, int weight) {
		//TODO handle edge number
		WeightedEdge edge = new WeightedEdge(vertexA.getCenterX(), vertexA.getCenterY(), vertexB.getCenterX(), vertexB.getCenterY(), weight, 0);
		weightedGraphStuff.recordEdgeWeight(edge.getNumber(), edge.getNodeWeight());
		getChildren().addAll(edge, edge.getDescription());
		return edge;
	}
	
}
