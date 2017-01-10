package my.util;

public class UnderFlowException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2963887722093325816L;
	public UnderFlowException() {
		// TODO Auto-generated constructor stub
		super();
	}
	public UnderFlowException(String message){
		super(message);
	}
}
