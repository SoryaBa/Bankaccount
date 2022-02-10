package bankaccount.exaltit.exception;

public class ExceptionIllegalOperation extends IllegalArgumentException{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	public ExceptionIllegalOperation(String message) {
		super(message);
	}

}
