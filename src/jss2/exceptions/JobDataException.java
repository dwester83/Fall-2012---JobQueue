/**
 * JobDataException represents the situation in which a Job ID
 * is invalid. 
 *
 * @author Daniel Wester
 * @version Comp 2247-02 PGM4, 12/20/2012
 */

package jss2.exceptions;

public class JobDataException extends RuntimeException {
	
	public JobDataException() {
		super("An error with JobDataException.");
	}

	public JobDataException(String errMsg) {
		super(errMsg);
	}
}