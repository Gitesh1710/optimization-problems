package greedy;

import java.util.Arrays;
import java.util.Comparator;

/*Given an array of jobs where every job has a deadline and associated profit if the job is finished before the deadline. It is also given that every job takes a single unit of time, so the minimum possible deadline for any job is 1. How to maximize total profit if only one job can be scheduled at a time.

Examples: 

Input: Four Jobs with following 
deadlines and profits
JobID  Deadline  Profit
  a      4        20   
  b      1        10
  c      1        40  
  d      1        30
Output: Following is maximum 
profit sequence of jobs
        c, a   


Input:  Five Jobs with following
deadlines and profits
JobID   Deadline  Profit
  a       2        100
  b       1        19
  c       2        27
  d       1        25
  e       3        15
Output: Following is maximum 
profit sequence of jobs
        c, a, e*/

public class JobSequencing {
	public static void main(String[] args) {
		char[] jobIdArray = {'a','b','c','d','e'};
		int[] deadlineArray = {2,1,2,1,3};
		int[] profitArray = {100,19,27,25,15};
		
		getMaximumProfit(jobIdArray, deadlineArray, profitArray);
	}

	private static void getMaximumProfit(char[] jobIdArray, int[] deadlineArray, int[] profitArray) {
		int maxProfit=0;
		int len = jobIdArray.length;
		Job[] jobArray = new Job[len];
		// Mapping input data to job
		for(int i=0; i<len; i++) {
			jobArray[i] = new Job(jobIdArray[i], deadlineArray[i], profitArray[i]);
		}
		
		//Sorting jobs based on decreasing profit
		Arrays.sort(jobArray, new Comparator<Job>() {

			public int compare(Job o1, Job o2) {
				return o2.getProfit()-o1.getProfit();
			}
			
		});
		
		int maxJobDuration = 0;
		
		for(Job j: jobArray) {
			if(j.getDeadline()>maxJobDuration) {
				maxJobDuration = j.getDeadline();
			}
		}
		
		char[] jobSequence = new char[maxJobDuration];
		
		for(Job currentJob: jobArray) {
			for(int k=currentJob.getDeadline()-1; k>=0; k--) {
				if(jobSequence[k]=='\0') {
					jobSequence[k]= currentJob.getJobId();
					maxProfit = maxProfit+currentJob.getProfit();
					break;
				}
			}
		}
		
		System.out.println(maxProfit);
		
		for(char jobName: jobSequence) {
			System.out.print(jobName+" ");
		}
	}
	
	static class Job{
		char jobId;
		int deadline;
		int profit;
		
		Job(char jobId, int deadline, int profit){
			this.jobId = jobId;
			this.deadline = deadline;
			this.profit = profit;
		}
		
		char getJobId() {
			return this.jobId;
		}
		
		int getDeadline() {
			return this.deadline;
		}
		
		int getProfit() {
			return this.profit;
		}
	}
}
