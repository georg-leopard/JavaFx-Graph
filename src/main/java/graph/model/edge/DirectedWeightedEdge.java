package graph.model.edge;

import graph.model.WeightedNode;
import graph.model.WeightedNodeImpl;

public class DirectedWeightedEdge extends DirectedEdge implements WeightedNode{
	
	private final WeightedNodeImpl weightedNode;
	
	public DirectedWeightedEdge(double startX, double startY, double endX,
			double endY, int number, int weight) {
		super(startX, startY, endX, endY, number);
		weightedNode = new WeightedNodeImpl(weight);	
		
		String text = description.getText();
		text = text + "[" + weight + "]";
		description.setText(text);
	}

	public int getNodeWeight() {
		return weightedNode.getNodeWeight();
	}

	public void setNodeWeight(int weight) {
		weightedNode.setNodeWeight(weight);		
	}

}
