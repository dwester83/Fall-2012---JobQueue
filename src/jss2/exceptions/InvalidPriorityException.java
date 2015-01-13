/**
 * InvalidPriorityException represents the situation in which a 
 * priority is invalid. 
 *
 * @author Daniel Wester
 * @version Comp 2247-02 PGM4, 12/20/2012
 */

package jss2.exceptions;

public class InvalidPriorityException extends RuntimeException {
	
	public InvalidPriorityException() {
		super("An error with InvalidPriorityException.");
	}

	public InvalidPriorityException(String errMsg) {
		super(errMsg);
	}
}