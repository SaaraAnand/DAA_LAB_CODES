import java.util.Arrays;
import java.util.Comparator;

class Job {
    char id;
    int deadline, profit;

    public Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobsequencingGreedy {
    public static void printJobSequence(Job[] jobs) {
        // Sort jobs by profit in descending order
        Arrays.sort(jobs, Comparator.comparingInt(o -> o.profit));
        int maxDeadline = Arrays.stream(jobs).mapToInt(job -> job.deadline).max().orElse(0);
        char[] sequence = new char[maxDeadline];
        boolean[] slot = new boolean[maxDeadline];

        // Initialize all slots to be free
        Arrays.fill(slot, false);

        // Fill the slots in reverse order
        for (int i = jobs.length - 1; i >= 0; i--) {
            for (int j = Math.min(maxDeadline - 1, jobs[i].deadline - 1); j >= 0; j--) {
                if (!slot[j]) {
                    slot[j] = true;
                    sequence[j] = jobs[i].id;
                    break;
                }
            }
        }

        // Print the job sequence
        System.out.println("Job Sequence:");
        for (char job : sequence) {
            if (job != 0)
                System.out.print(job + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Job[] jobs = {
                new Job('a', 2, 100),
                new Job('b', 1, 19),
                new Job('c', 2, 27),
                new Job('d', 1, 25),
                new Job('e', 3, 15)
        };

        System.out.println("Given jobs:");
        for (Job job : jobs) {
            System.out.println("Job: " + job.id + ", Deadline: " + job.deadline + ", Profit: " + job.profit);
        }

        System.out.println();

        printJobSequence(jobs);
    }
}

