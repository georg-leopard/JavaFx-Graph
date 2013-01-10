package graph.model.vertex;

import graph.model.WeightedNodeImpl;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Label;

public class WeightedVertex extends WeightedNodeImpl implements Vertex {
	
	private final Vertex vertex;
	
	public WeightedVertex(Vertex vertex, int weight) {
		super(weight);
		this.vertex = vertex;
	}

	public double getCenterX() {
		return vertex.getCenterX();
	}

	public double getCenterY() {
		return vertex.getCenterY();
	}
	
	public void setCenterX(double centerX) {
		vertex.setCenterX(centerX);
	}
	
	public void setCenterY(double centerY) {
		vertex.setCenterY(centerY);
	}

	public DoubleProperty centerXProperty() {
		return vertex.centerXProperty();
	}

	public DoubleProperty centerYProperty() {
		return vertex.centerYProperty();
	}

	public Label graphicRepresentaion() {
		Label graphicRepresentation = vertex.graphicRepresentaion();
		String text = graphicRepresentation.getText();
		text = text + "[" + this.getNodeWeight() + "]";
		graphicRepresentation.setText(text);
		return graphicRepresentation;
	}

	public int getNumber() {
		return vertex.getNumber();
	}
	
}
