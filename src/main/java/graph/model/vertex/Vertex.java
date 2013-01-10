package graph.model.vertex;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Label;

/**
 * Interface for vertexes in the graph.
 * From visual perspective it is required to know vertex's center coordinates.
 * Vertex's center coordinates are used to bind edges further.
 * 
 * TODO: there is translate mechanism in javafx that may be used instead.
 * It may be a pain to calculate center coordinates for rectangles or custom shapes.
 * 
 * @author Denis
 *
 */
public interface Vertex {

	public int getNumber();
	
	public double getCenterX();
	public double getCenterY();
	public void setCenterX(double centerX);
	public void setCenterY(double centerY);
	
	public DoubleProperty centerXProperty();
	public DoubleProperty centerYProperty();
	
	public Label graphicRepresentaion();
}
