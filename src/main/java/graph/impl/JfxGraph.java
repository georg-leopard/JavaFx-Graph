package graph.impl;

import graph.model.AcycleBrokenException;
import graph.model.DraggableNode;
import graph.model.edge.Edge;
import graph.model.vertex.Vertex;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;

import org.apache.commons.collections.map.MultiValueMap;


public class JfxGraph extends Group {
	
	protected final Map<Integer, MultiValueMap> adjacencyMatrix = new HashMap<Integer, MultiValueMap>();
	
	protected boolean showEdgeDescription = false;
	
	public JfxGraph() {}
	
	//TODO: paramatrize this class with vertex type?
	public void addVertex(Vertex vertex) {
		MultiValueMap vertexRecord = new MultiValueMap();
		adjacencyMatrix.put(vertex.getNumber(), vertexRecord);
		
		getChildren().add(new DraggableNode(vertex));
	}
	
	public void bindVertexes(Vertex vertexA, Vertex vertexB) throws AcycleBrokenException {
		bindVerteces(vertexA, vertexB, 0);
	}
	
	public void bindVerteces(Vertex vertexA, Vertex vertexB, int weight) throws AcycleBrokenException {
		Edge edge = createEdge(vertexA, vertexB, weight);
		
		edge.startXProperty().bind(vertexA.centerXProperty()); 
		edge.startYProperty().bind(vertexA.centerYProperty());
		edge.endXProperty().bind(vertexB.centerXProperty());
		edge.endYProperty().bind(vertexB.centerYProperty());
		edge.toBack();
		
		recordBinding(vertexA, vertexB, edge);
	}

	private void recordBinding(Vertex vertexA, Vertex vertexB, Edge edge) {
		MultiValueMap vertexRecordA = adjacencyMatrix.get(vertexA.getNumber());
		vertexRecordA.put(vertexB.getNumber(), edge.getNumber());
		//adjacency matrix is a symmetric matrix
		MultiValueMap vertexRecordB = adjacencyMatrix.get(vertexB.getNumber());
		vertexRecordB.put(vertexA.getNumber(), edge.getNumber());
	}

	public Edge createEdge(Vertex vertexA, Vertex vertexB, int weight) {
		//TODO: how do we handle edge number
		Edge edge = new Edge(vertexA.getCenterX(), vertexA.getCenterY(), vertexB.getCenterX(), vertexB.getCenterY(), 0);
		getChildren().add(edge);
		
		if (showEdgeDescription) {
			getChildren().add(edge.getDescription());
		}
		return edge;
	}

	public void setShowEdgeDescription(boolean showEdgeDescription) {
		this.showEdgeDescription = showEdgeDescription;
	}
	
}
