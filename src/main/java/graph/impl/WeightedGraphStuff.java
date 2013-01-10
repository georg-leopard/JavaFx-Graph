package graph.impl;

import graph.model.vertex.Vertex;
import graph.model.vertex.WeightedVertex;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WeightedGraphStuff {
	
	private final Map<Integer, Integer> weightVertexTable = new HashMap<Integer, Integer>();
	private final Map<Integer, Integer> weightEdgeTable = new HashMap<Integer, Integer>();
	
	public void recordVertexWeight(Vertex vertex) {
		if ( !WeightedVertex.class.isInstance(vertex)) {
			//TODO try to paramatrize graph class instead, to have compile time exception.
			throw new IllegalArgumentException("only vertexes with weight are allowed");
		}
		weightVertexTable.put(vertex.getNumber(), ((WeightedVertex)vertex).getNodeWeight());
	}

	public void recordEdgeWeight(int number, int weight) {
		weightEdgeTable.put(number, weight);
	}
	
	public Map<Integer, Integer> getWeightVertexTable() {
		return Collections.unmodifiableMap(weightVertexTable);
	}

	public Map<Integer, Integer> getWeightEdgeTable() {
		return Collections.unmodifiableMap(weightEdgeTable);
	}
	
	
}
