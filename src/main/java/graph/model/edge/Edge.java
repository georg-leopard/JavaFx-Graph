package graph.model.edge;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge extends Line {
	
	protected final int number;
	protected final Label description;
	
	public Edge(double startX, double startY, double endX, double endY, int number) {
		super(startX, startY, endX, endY);
		this.number = number;
		
		setStroke(Color.BLACK);
        setStrokeWidth(2);
        
		description = new Label(String.valueOf(number));
		description.layoutXProperty().bind(Bindings.divide(Bindings.add( endXProperty(), startXProperty()), 2));
	    description.layoutYProperty().bind(Bindings.divide(Bindings.add( endYProperty(), startYProperty()), 2));
	}
	
	public int getNumber() {
		return number;
	}

	public Label getDescription() {
		return description;
	}

	
}
