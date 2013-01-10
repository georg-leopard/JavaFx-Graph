package graph.model;

public class AcycleBrokenException extends Exception {

	public AcycleBrokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public AcycleBrokenException(String message) {
		super(message);
	}

}
