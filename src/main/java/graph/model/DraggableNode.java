package graph.model;

import graph.model.vertex.Vertex;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;


public class DraggableNode extends Group{
		
	private final DragContext dragContext = new DragContext();
	
	private class DragContext {
	    public double mouseAnchorX;
	    public double mouseAnchorY;
	    public double initialCenterX;
	    public double initialCenterY;
	}
	
	public DraggableNode(final Vertex vertex) {
		super(vertex.graphicRepresentaion());
		setMouseHandlers(vertex);
	}

	private void setMouseHandlers(final Vertex vertex) {
		addEventFilter(
				MouseEvent.ANY,
                new EventHandler<MouseEvent>() {
                    public void handle(final MouseEvent mouseEvent) {
                            // disable mouse events for all children
                            mouseEvent.consume();
                    }
                });
		
		addEventFilter(
                MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    public void handle(final MouseEvent mouseEvent) {
                            // remember initial mouse cursor coordinates
                            // and node position
                            dragContext.mouseAnchorX = mouseEvent.getX();
                            dragContext.mouseAnchorY = mouseEvent.getY();
                            dragContext.initialCenterX = vertex.getCenterX();
                            dragContext.initialCenterY = vertex.getCenterY();
                            
                    }
                });
		
		addEventFilter(
                MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {
                    public void handle(final MouseEvent mouseEvent) {
                        // shift node from its initial position by delta
                        // calculated from mouse cursor movement
                    	vertex.setCenterX(dragContext.initialCenterX + mouseEvent.getX() - dragContext.mouseAnchorX);
                    	vertex.setCenterY(dragContext.initialCenterY + mouseEvent.getY() - dragContext.mouseAnchorY);
                    }
                });
	}
	
}
