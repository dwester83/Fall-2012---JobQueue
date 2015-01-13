/* Author: Daniel Wester
 * 
 * Date: 12/20/2012
 * 
 * Course: Comp 2247-01, Fall 2012
 * 
 * Assignment: PGM4
 * 
 * Description: This is the final program which will queue jobs based on their
 * priority. The PriorityQueue will keep track of everything and handle keeping
 * everything sorted the way it needs to. This meets all the requirements asked
 * I used an insert, remove, and top for the PriorityQueue instead of enqueue,
 * dequeue, and first, since in one part of the description is asking for a
 * insert but later asked for enqueue making me unsure which to use. The 
 * insert checks the priority of the job being passed, then it searches
 * for the priority of the jobQueues, once it finds the appropriate jobQueue
 * it'll enqueue the job. Remove will just search the jobQueues and look for 
 * one that isn't empty once it finds one that isn't empty it'll dequeue
 * the job from there. Top does the same thing, but instead of dequeue will
 * get a first from that one.
 */

package jss2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import jss2.exceptions.*;

public class JobGUI extends JFrame  {
	
	JTextArea descriptionJTA, queueJTA, messageJTA;
	JTextField jobIdJTF;
	JLabel priorityJL, jobIdJL, queueJL, totalJL;
	JComboBox<Integer> priorityJCB;
	JButton enqueueJB, dequeueJB, topJB, resetJB, exitJB;
	JPanel northPanel, northPanelLeft, northPanelRight, priorityPanel, 
		jobIdPanel, centerPanel, centerPanelTop, centerPanelRight, southPanel, 
		southPanelTop, southPanelBottom;
	JScrollPane centerJSP;
	
	PriorityQueue priorityQueue = new PriorityQueue();
	
	public JobGUI() {
		
		//Text Area
		descriptionJTA = new JTextArea("This is for the JobQueue thing...");
		descriptionJTA.setLineWrap(true);
		descriptionJTA.setEditable(false);
		descriptionJTA.setBackground(null);
		queueJTA = new JTextArea();
		queueJTA.setEditable(false);
		queueJTA.setFont(new Font("Arial",Font.PLAIN,16));
		queueJTA.setBackground(Color.WHITE);
		queueJTA.setBorder(BorderFactory.createLineBorder(Color.black));
		messageJTA = new JTextArea();
		messageJTA.setLineWrap(true);
		messageJTA.setEditable(false);
		messageJTA.setFont(new Font("Arial",Font.PLAIN,12));
		messageJTA.setBackground(Color.WHITE);
		messageJTA.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Text Field
		jobIdJTF = new JTextField();
		jobIdJTF.setPreferredSize(new Dimension(100, 25));
		jobIdJTF.addActionListener(new InsertButtonHandler());
		
		//Labels
		priorityJL = new JLabel(" Priority");
		jobIdJL = new JLabel(" PID: Must be between 1 and 9999.");
		queueJL = new JLabel(" List of Jobs");
		totalJL = new JLabel("Total Jobs: " + priorityQueue.getTotalJobs() + " ");
		
		//Combo Box
		priorityJCB = new JComboBox<Integer>();
		for (int i = priorityQueue.getMinPriority(); i <= 
				priorityQueue.getMaxPriority(); i++)
			priorityJCB.addItem(i);
		
		//Buttons
		enqueueJB = new JButton ("Insert");
		enqueueJB.addActionListener(new InsertButtonHandler());
		dequeueJB = new JButton ("Remove");
		dequeueJB.addActionListener(new RemoveButtonHandler());
		topJB = new JButton ("Top");
		topJB.addActionListener(new TopButtonHandler());
		resetJB = new JButton ("Reset");
		resetJB.addActionListener(new ResetButtonHandler());
		exitJB = new JButton ("Exit");
		exitJB.addActionListener(new ExitButtonHandler());
		
		//Building the GUI
		northPanel = new JPanel(new BorderLayout());
		northPanelLeft = new JPanel(new BorderLayout());
		northPanelLeft.setPreferredSize(new Dimension(100, 40));
		northPanelRight = new JPanel(new BorderLayout());
		priorityPanel = new JPanel(new BorderLayout());
		jobIdPanel = new JPanel(new BorderLayout());
		centerPanel = new JPanel(new BorderLayout());
		centerPanelTop = new JPanel(new BorderLayout());
		centerPanelRight = new JPanel(new BorderLayout());
		centerPanelRight.setPreferredSize(new Dimension(10, 50));
		centerJSP = new JScrollPane(queueJTA);
		southPanel = new JPanel(new GridLayout (2,1));
		southPanelTop = new JPanel(new GridLayout (2,2));
		southPanelBottom = new JPanel(new GridLayout (1,1));
		
		northPanel.add(descriptionJTA, BorderLayout.NORTH);
		priorityPanel.add(priorityJL, BorderLayout.NORTH);
		priorityPanel.add(priorityJCB, BorderLayout.SOUTH);
		jobIdPanel.add(jobIdJL, BorderLayout.NORTH);
		jobIdPanel.add(jobIdJTF, BorderLayout.SOUTH);
		northPanelLeft.add(priorityPanel, BorderLayout.CENTER);
		northPanelRight.add(jobIdPanel, BorderLayout.CENTER);
		northPanel.add(northPanelLeft, BorderLayout.WEST);
		northPanel.add(northPanelRight, BorderLayout.CENTER);
		centerPanelRight.add(messageJTA, BorderLayout.CENTER);
		centerPanelTop.add(queueJL, BorderLayout.WEST);
		centerPanelTop.add(totalJL, BorderLayout.EAST);
		centerPanel.add(centerPanelTop, BorderLayout.NORTH);
		centerPanel.add(centerJSP, BorderLayout.CENTER);
		centerPanel.add(centerPanelRight, BorderLayout.SOUTH);
		southPanelTop.add(enqueueJB);
		southPanelTop.add(dequeueJB);
		southPanelTop.add(topJB);
		southPanelTop.add(resetJB);
		southPanelBottom.add(exitJB);
		southPanel.add(southPanelTop);
		southPanel.add(southPanelBottom);
		
		//Display the GUI
		setLayout(new BorderLayout());
		setSize(400,550);
		setTitle("Priority Queue");
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}
	
	//Insert Button Handler.
	private class InsertButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			int priority, jobId;
			
			try {
			priority = (int) priorityJCB.getSelectedItem();
			jobId = Integer.parseInt(jobIdJTF.getText());
			Job job = new Job(priority, jobId);
			priorityQueue.insert(job);
			
			queueJTA.setText(priorityQueue.toString());
			messageJTA.setText(job.toString() + "Has been added to the queue.");
			totalJL.setText("Total Jobs: " + priorityQueue.getTotalJobs() + " ");
			} catch(NumberFormatException nfe) {
				messageJTA.setText("Must be a valid number please try again.");
			} catch (JobDataException jde){
				messageJTA.setText("Must be a valid number please try again.");
			}
		}
	}
	
	//Remove Button Handler.
	private class RemoveButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			try {
			Job job = priorityQueue.remove();
			
			queueJTA.setText(priorityQueue.toString());
			messageJTA.setText(job.toString() + "Has been removed from the queue.");
			totalJL.setText("Total Jobs: " + priorityQueue.getTotalJobs() + " ");
			} catch (EmptyCollectionException ece){
				messageJTA.setText("There is nothing to remove from the queue.");
			}
		}
	}
	
	//Top Button
	private class TopButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			try {
			Job job = priorityQueue.top();
			
			messageJTA.setText(job.toString() + "Is the next in the queue.");
			} catch (EmptyCollectionException ece){
				messageJTA.setText("There is nothing to remove from the queue.");
			}
		}
	}
	
	//Reset Button
	private class ResetButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			priorityQueue = new PriorityQueue();
			messageJTA.setText("");
			queueJTA.setText(priorityQueue.toString());
		}
	}
	
	//Exit Button
	private class ExitButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			System.exit(0);
		}
	}
	
	public static void main(String[] args){
		new JobGUI();
	}
}
