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

public class Job implements Comparable {
	
	final private int STARTING_JOBID = 1;
	final private int ENDING_JOBID = 9999;
	final private int STARTING_PRIORITY = 1;
	final private int ENDING_PRIORITY = 10;
	private int priority;
	private int jobId;
	
	/**
	 * The default constructor, validates a jobId and priority.
	 * @param inputPriority int Priority of a job
	 * @param inputJobId int JobID of a job
	 * @throws InvalidPriorityException invalid priority exception
	 * @throws JobDataException job data exception
	 */
	public Job(int inputPriority,int inputJobId) throws 
						InvalidPriorityException,  JobDataException {
		if (inputJobId < STARTING_JOBID || inputJobId > ENDING_JOBID) {
			throw new JobDataException("Invalid jobID: " + inputJobId + 
					" only numbers " + STARTING_JOBID + " to " + ENDING_JOBID +
					" are valid.");
		}
		
		if (inputPriority < STARTING_PRIORITY || inputPriority > ENDING_PRIORITY) {
			throw new InvalidPriorityException("Invalid priority: "  +
					inputPriority + " only numbers " + STARTING_PRIORITY +
					" to " + ENDING_PRIORITY + " are valid.");
		}
		
		this.jobId = inputJobId;
		this.priority = inputPriority;
	}
	
	/**
	 * Gets a job ID
	 * @return int returns a job ID
	 */
	public int getJobId() {
		return jobId;
	}
	
	/**
	 * Gets a job Priority
	 * @return int returns a priority
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * Checks if a job is equal to another job
	 * @return boolean returns if jobs are equal to each other
	 */
	public boolean equals(Object job) {
		if ((job != null) && (this.getClass() == job.getClass())){
			Job otherJob = (Job) job;
			return (otherJob.getJobId() == this.getJobId() && 
					otherJob.getPriority() == this.getPriority());
		}
		return false;
	}
	
	/**
	 * Compares if one jobs priority is before another job.
	 * @return boolean returns if priority is before or after
	 */
	public int compareTo(Object job) {
		Job otherJob = (Job) job;
	    return this.getPriority() - otherJob.getPriority();
	}
	
	/**
	 * A toString representation of the job
	 * @return String string of a job
	 */
	public String toString() {
		String output = String.format("Priority: " + priority + "\nPID%04d%n", 
				getJobId());
		return output;
	}
	
}
