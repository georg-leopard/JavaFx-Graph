package graph.samples;

import graph.model.DraggableNode;
import graph.model.vertex.CircleVertex;
import graph.model.vertex.Vertex;
import graph.model.vertex.WeightedVertex;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class GraphSample extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
	
	private void init(Stage primaryStage) {
		final Group graph = new Group();
		primaryStage.setScene(new Scene(graph, 200, 200));
		
		final Vertex vertex1 = new WeightedVertex(new CircleVertex(50, 50, 20, Color.rgb(156,216,255), 0), 5);
		final Vertex vertex2 = new WeightedVertex(new CircleVertex(100, 100, 20, Color.rgb(156,216,255), 0), 5);
		
//		final Vertex vertex1 = new WeightedVertex(new ArcVertex(50.0f, 50.0f, 25.0f, 25.0f, 45.0f, 250.0f), 0);
//		final Vertex vertex2 = new WeightedVertex(new ArcVertex(100.0f, 100.0f, 25.0f, 25.0f, 45.0f, 350.0f), 0);
				
		final Line line = new Line(vertex1.getCenterX(), vertex1.getCenterY(),
				vertex2.getCenterX(), vertex2.getCenterY());
				
		line.setFill(null);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);

        line.startXProperty().bind(vertex1.centerXProperty()); 
        line.startYProperty().bind(vertex1.centerYProperty());
        line.endXProperty().bind(vertex2.centerXProperty());
        line.endYProperty().bind(vertex2.centerYProperty());
        
        // wrap vertex to draggable node just before adding to the scene
		DraggableNode draggableCircle1 = new DraggableNode(vertex1);
		DraggableNode draggableCircle2 = new DraggableNode(vertex2);
		graph.getChildren().addAll(draggableCircle1, draggableCircle2, line);
		line.toBack();
		drawArrowHead(graph, line.startXProperty(), line.startYProperty(),
				 line.endXProperty(), line.endYProperty());
	}
	 
	private void  drawArrowHead(Group root, DoubleProperty lineStartXProperty, DoubleProperty lineStartYProperty,
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
		Line line1 = new Line();
		NumberBinding startX = Bindings.subtract(lineEndXProperty, arrowStartXLocal);
		NumberBinding startY = Bindings.add(lineEndYProperty, arrowStartYLocal);
		line1.startXProperty().bind(startX);
		line1.startYProperty().bind(startY);
		line1.endXProperty().bind(Bindings.subtract(startX, arrowX1Local)); // shift to center of polar coordinate system (end of line)
		line1.endYProperty().bind(Bindings.add(startY, arrowY1Local)); // shift to center of polar coordinate system (end of line)
		
		Line line2 = new Line();
		line2.startXProperty().bind(startX);
		line2.startYProperty().bind(startY);
		line2.endXProperty().bind(Bindings.subtract(startX, arrowX2Local)); // shift to center of polar coordinate system (end of line)
		line2.endYProperty().bind(Bindings.add(startY, arrowY2Local)); // shift to center of polar coordinate system (end of line)
		
		root.getChildren().addAll(line1, line2);
	}
	
	public static void main(String[] args) { launch(args); }
}

