package Jobs;

// The "master" job - manages all other jobs as long as the JobManager hasn't yet been shutdown
public class ManageJobsJob implements Runnable
{
    private final JobManager jobManager;

    public ManageJobsJob ()
    {
        jobManager = JobManager.getInstance();
    }

    public void run ()
    {
        while (!jobManager.isShutdown()) jobManager.manageJobs();
    }
}
