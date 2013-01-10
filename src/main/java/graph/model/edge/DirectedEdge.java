package graph.model.edge;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Line;

public class DirectedEdge extends Edge {
	
	private final ArrowHead arrowHead = new ArrowHead();
	
	public class ArrowHead {
		public Line lineA = new Line();
		public Line lineB = new Line();
		
		public void toBack() {
			lineA.toBack();
			lineB.toBack();
		}
	}
	
	public DirectedEdge(double startX, double startY, double endX, double endY,
			int number) {
		super(startX, startY, endX, endY, number);
		createArrowHead(startXProperty(), startYProperty(),
				endXProperty(), endYProperty());
	}

	private void  createArrowHead(DoubleProperty lineStartXProperty, DoubleProperty lineStartYProperty,
			DoubleProperty lineEndXProperty, DoubleProperty lineEndYProperty) {

		NumberBinding lineXPropety = Bindings.subtract(lineEndXProperty, lineStartXProperty); //x1
		NumberBinding lineYPropety = Bindings.subtract(lineEndYProperty, lineStartYProperty); //y1
		
		double xAxis = 1; //magic number of x2
		double yAxis = 0; // y2
		
		// skew product: x1y2 - x2y1
		final NumberBinding vectorSkewProduct = Bindings.subtract(Bindings.multiply(lineXPropety, yAxis), Bindings.multiply(lineYPropety, xAxis));
		//inner product: x1x2 + y1y2
		final NumberBinding vectorInnerProduct = Bindings.add(Bindings.multiply(lineXPropety, xAxis), Bindings.multiply(lineYPropety, yAxis));
		// TODO: try to replace vector position in product formula to 
		// direct polar coordinate system the same way as xy outer coordinate system.
		final NumberBinding angle = new DoubleBinding() {
			{
				super.bind(vectorSkewProduct, vectorInnerProduct);
			}
			@Override
			protected double computeValue() {
				// calculate polar angle
				return Math.atan2(vectorSkewProduct.getValue().doubleValue(), vectorInnerProduct.getValue().doubleValue());
			}
		};
		
		int arrowLength = 10;
		final NumberBinding angle1 = Bindings.subtract(angle,Math.toRadians(15));
		final NumberBinding angle2 = Bindings.add(angle,Math.toRadians(15));
		//arrow has to be a simple triangle, first vertex - is line end
		//arrow second vertex
		NumberBinding arrowX1Local =  new DoubleBinding() {
			{
				super.bind(angle1);
			}
			@Override
			protected double computeValue() {
				return Math.cos(angle1.getValue().doubleValue()); // from polar coordinates
			}
		}.multiply(arrowLength);
		
		NumberBinding arrowY1Local =  new DoubleBinding() {
			{
				super.bind(angle1);
			}
			@Override
			protected double computeValue() {
				return Math.sin(angle1.getValue().doubleValue()); // from polar coordinates
			}
		}.multiply(arrowLength);
		
		NumberBinding arrowX2Local =  new DoubleBinding() {
			{
				super.bind(angle2);
			}
			@Override
			protected double computeValue() {
				return Math.cos(angle2.getValue().doubleValue()); // from polar coordinates
			}
		}.multiply(arrowLength);
		
		NumberBinding arrowY2Local =  new DoubleBinding() {
			{
				super.bind(angle2);
			}
			@Override
			protected double computeValue() {
				return Math.sin(angle2.getValue().doubleValue()); // from polar coordinates
			}
		}.multiply(arrowLength);
		
		NumberBinding arrowStartXLocal = new DoubleBinding() {
			{
				super.bind(angle);
			}
			@Override
			protected double computeValue() {
				return Math.cos(angle.doubleValue());
			}
		}.multiply(20);
		NumberBinding arrowStartYLocal = new DoubleBinding() {
			{
				super.bind(angle);
			}
			@Override
			protected double computeValue() {
				return Math.sin(angle.doubleValue());
			}
		}.multiply(20);
		NumberBinding startX = Bindings.subtract(lineEndXProperty, arrowStartXLocal);
		NumberBinding startY = Bindings.add(lineEndYProperty, arrowStartYLocal);
		
		arrowHead.lineA.startXProperty().bind(startX);
		arrowHead.lineA.startYProperty().bind(startY);
		arrowHead.lineA.endXProperty().bind(Bindings.subtract(startX, arrowX1Local)); // shift to center of polar coordinate system (end of line)
		arrowHead.lineA.endYProperty().bind(Bindings.add(startY, arrowY1Local)); // shift to center of polar coordinate system (end of line)
		
		arrowHead.lineB.startXProperty().bind(startX);
		arrowHead.lineB.startYProperty().bind(startY);
		arrowHead.lineB.endXProperty().bind(Bindings.subtract(startX, arrowX2Local)); // shift to center of polar coordinate system (end of line)
		arrowHead.lineB.endYProperty().bind(Bindings.add(startY, arrowY2Local)); // shift to center of polar coordinate system (end of line)

	}

	public ArrowHead getArrowHead() {
		return arrowHead;
	}
	
}
