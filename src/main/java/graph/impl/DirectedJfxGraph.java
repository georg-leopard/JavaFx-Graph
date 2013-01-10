package graph.impl;

import graph.model.AcycleBrokenException;
import graph.model.edge.DirectedEdge;
import graph.model.edge.Edge;
import graph.model.vertex.Vertex;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.MultiValueMap;

public class DirectedJfxGraph extends JfxGraph{
	
	protected Map<Integer, MultiValueMap> directedAdjacencyMatrix = 
			new HashMap<Integer, MultiValueMap>();
	
	protected final boolean isAcyclic;
	
	public DirectedJfxGraph(boolean isAcyclic) {
		this.isAcyclic = isAcyclic;
	}

	@Override
	public void addVertex(Vertex vertex) {
		super.addVertex(vertex);
		
		recordInDirectedMatrix(vertex);
	}

	@Override
	public void bindVerteces(Vertex vertexA, Vertex vertexB, int weight)
			throws AcycleBrokenException {
		
		if (isAcyclic && acycleWillBeBroken(vertexA, vertexB)) {
			throw new AcycleBrokenException("Binding vertex: " + vertexA + 
					" to vertex: " + vertexB + " will break acycle condition");
		}
		
		super.bindVerteces(vertexA, vertexB, weight);
		
		recordInDirectedMatrix(vertexA);
	}

	@Override
	public Edge createEdge(Vertex vertexA, Vertex vertexB, int weight) {
		//TODO: handle edge number
		DirectedEdge edge = new DirectedEdge(vertexA.getCenterX(), vertexA.getCenterY(), vertexB.getCenterX(), vertexB.getCenterY(), 0);
		getChildren().addAll(edge, edge.getArrowHead().lineA, edge.getArrowHead().lineB);
		edge.getArrowHead().toBack();
		
		if (showEdgeDescription) {
			getChildren().add(edge.getDescription());
		}
		
		return edge; 
	}
	
	protected boolean acycleWillBeBroken(Vertex vertexA, Vertex vertexB) {
		//TODO implement
		return true;
	}
	
	private void recordInDirectedMatrix(Vertex vertex) {
		MultiValueMap vertexRecord = 
				adjacencyMatrix.get(vertex.getNumber());
		directedAdjacencyMatrix.put(vertex.getNumber(), vertexRecord);
	}
		
}
