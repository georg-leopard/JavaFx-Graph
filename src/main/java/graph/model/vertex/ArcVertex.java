package graph.model.vertex;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.shape.Arc;

public class ArcVertex implements Vertex {
	
	private final Arc arc;
	
	private final int number;
	
	public ArcVertex(double centerX, double centerY, double radiusX, double radiusY, double startAngle, double length, int number) {
		arc = new Arc(centerX, centerY, radiusX, radiusY, startAngle, length);
		this.number = number;
	}
	public double getCenterX() {
		return arc.getCenterX();
	}

	public double getCenterY() {
		return arc.getCenterY();
	}

	public void setCenterX(double centerX) {
		arc.setCenterX(centerX);
	}

	public void setCenterY(double centerY) {
		arc.setCenterY(getCenterX());
	}

	public DoubleProperty centerXProperty() {
		return arc.centerXProperty();
	}

	public DoubleProperty centerYProperty() {
		return arc.centerYProperty();
	}

	
	public Label graphicRepresentaion() {
		throw new UnsupportedOperationException("not supported yet");
	}
	public int getNumber() {
		// TODO Auto-generated method stub
		return number;
	}

}
