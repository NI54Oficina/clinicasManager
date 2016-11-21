package ar.com.clinicasmanager.exception;

public class LockNotGrantedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LockNotGrantedException(String message) {
		super(message);
	}

}
