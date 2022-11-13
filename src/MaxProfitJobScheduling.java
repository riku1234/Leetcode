import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class MaxProfitJobScheduling {

    class Job {
        int startTime;
        int endTime;
        int profit;

        Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }

        int getStartTime() {
            return startTime;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }

        jobs.sort(Comparator.comparing(Job::getStartTime));

        int len = jobs.size();
        int[] maxProfit = new int[len];
        maxProfit[len - 1] = jobs.get(len - 1).profit;

        for (int i = startTime.length - 2; i >= 0; i--) {
            int nextIndex = findNextIndex(jobs, i);
            int nextProfit = nextIndex < len ? maxProfit[nextIndex] : 0;
            maxProfit[i] = Math.max(maxProfit[i+1], jobs.get(i).profit + nextProfit);
        }

        return maxProfit[0];
    }

    private static int findNextIndex(List<Job> sortedJobs, int index) {
        Job job = sortedJobs.get(index);
        for (int i = index+1; i < sortedJobs.size(); i++) {
            if (sortedJobs.get(i).startTime >= job.endTime) {
                return i;
            }
        }

        return sortedJobs.size();
    }

    public static void main(String[] args) {
        MaxProfitJobScheduling obj = new MaxProfitJobScheduling();
        int[] startTime = {1,1,1};
        int[] endTime = {2,3,4};
        int[] profit = {5,6,4};
        System.out.println(obj.jobScheduling(startTime, endTime, profit));
    }
}
