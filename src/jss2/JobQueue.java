/* Author: Daniel Wester
 * 
 * Date: 12/20/2012
 * 
 * Course: Comp 2247-01, Fall 2012
 * 
 * Assignment: PGM4
 */

package jss2;

import jss2.exceptions.*;

public class JobQueue<Job> extends LinkedQueue<Job> implements Comparable {
	
	int priority;
	final private int STARTING_PRIORITY = 1;
	final private int ENDING_PRIORITY = 10;
	
	/**
	 * Default JobQueue constructor
	 * @param input int Priority of a jobQueue
	 * @throws InvalidPriorityException Invalid priority exception
	 */
	public JobQueue(int input) throws InvalidPriorityException {
		if (input < STARTING_PRIORITY || input > ENDING_PRIORITY) {
			throw new InvalidPriorityException("Invalid priority: "  +
					input + " only numbers " + STARTING_PRIORITY +
					" to " + ENDING_PRIORITY + " are valid.");
		}
		
		this.priority = input;
	}
	
	/**
	 * Gets a priority of a jobQueue
	 * @return int returns a priority
	 */
	public int getPriority(){
		return priority;
	}
	
	/**
	 * Compares if one jobQueue priority is before another jobQueue.
	 * @return boolean returns if priority is before or after
	 */
	public int compareTo(Object jobQ) {
		JobQueue<Job> otherJobQ = (JobQueue<Job>) jobQ;
	    return this.getPriority() - otherJobQ.getPriority();
	}
	
	/**
	 * A toString representation of the jobQueue
	 * @return String string of a jobQueue
	 */
	public String toString(){
		String output = "";
		
		if (super.isEmpty())
			return output;
		
		output = output + super.toString();
		
		return output;
	}
	
}
