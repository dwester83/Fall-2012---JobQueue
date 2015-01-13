/* Author: Daniel Wester
 * 
 * Date: 12/20/2012
 * 
 * Course: Comp 2247-01, Fall 2012
 * 
 * Assignment: PGM4
 */

package jss2;

import java.util.*;
import jss2.exceptions.*;

public class PriorityQueue extends ArrayOrderedList<JobQueue<Job>> {
	
	final private int STARTING_PRIORITY = 1;
	final private int ENDING_PRIORITY = 10;
	
	/**
	 * Default constructor, will construct 10 jobQueues in the list
	 */
	public PriorityQueue(){
		JobQueue<Job> jobQ;
		for(int i = STARTING_PRIORITY; i <= ENDING_PRIORITY; i++)
			super.add(jobQ = new JobQueue<Job>(i));
	}
	
	/**
	 * Inserts a job into the appropriate jobQueue
	 * @param job job to be inserted into the jobQueue
	 */
	public void insert(Job job){
		JobQueue<Job> jobQ = searchPriority(job.getPriority());
		jobQ.enqueue(job);
	}
	
	/**
	 * Removes a job from the first job it can find
	 * @return job job that is the first one in the list
	 * @throws EmptyCollectionException Empty collection exception
	 */
	public Job remove() throws EmptyCollectionException {
		Iterator<JobQueue<Job>> scan = super.iterator();
		JobQueue<Job> jobQ;
		Job job;
		
		while(scan.hasNext()){
			jobQ = scan.next();
			if (!jobQ.isEmpty()){
				job = jobQ.dequeue();
				return job;
			}
		}
		
		throw new EmptyCollectionException ("queue");
	}
	
	/**
	 * Says the first job in the list
	 * @return job first job in the list
	 * @throws EmptyCollectionException Empty collection exception
	 */
	public Job top() throws EmptyCollectionException {
		Iterator<JobQueue<Job>> scan = super.iterator();
		JobQueue<Job> jobQ;
		Job job;
		
		while(scan.hasNext()){
			jobQ = scan.next();
			if (!jobQ.isEmpty()){
				job = jobQ.first();
				return job;
			}
		}
		
		throw new EmptyCollectionException ("queue");
	}
	
	/**
	 * Searches the list of jobQueues priority and finds the one that matches
	 * @param priority int priority of the JobQueue to find
	 * @return JobQueue the jobQueue that matches
	 */
	public JobQueue<Job> searchPriority(int priority){
		Iterator<JobQueue<Job>> scan = super.iterator();
		JobQueue<Job> jobQ;
		
		while(scan.hasNext()){
			jobQ = scan.next();
			if (priority == jobQ.getPriority())
				return jobQ;
		}
		
		return null;
	}
	
	/**
	 * Gets the minimum priority required
	 * @return int minimum priority
	 */
	public int getMinPriority(){
		return STARTING_PRIORITY;
	}
	
	/**
	 * Gets the maximum priority required
	 * @return int maximum priority
	 */
	public int getMaxPriority(){
		return ENDING_PRIORITY;
	}
	
	/**
	 * Total number of jobs in all the queues
	 * @return int number of jobs in all the queues
	 */
	public int getTotalJobs(){
		Iterator<JobQueue<Job>> scan = super.iterator();
		JobQueue<Job> jobQ;
		int output = 0;
		
		while(scan.hasNext()){
			jobQ = scan.next();
			output = output + jobQ.size();
		}
		
		return output;
	}
	
	/**
	 * toString representation of all the jobs in all the queues
	 * @return String string of all the jobs
	 */
	public String toString(){
		String output = "";
		output = super.toString();
		
		return output;
	}
}