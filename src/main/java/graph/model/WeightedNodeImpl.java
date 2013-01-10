package graph.model;

public class WeightedNodeImpl implements WeightedNode{
	private int weight;

	public WeightedNodeImpl(int weight) {
		this.weight = weight;
	}

	public int getNodeWeight() {
		return weight;
	}
	
	public void setNodeWeight(int weight) {
		this.weight = weight;
	}
	
}
