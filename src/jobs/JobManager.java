package jobs;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

/* Manages all jobs that run on threads separate to the main game thread

   Asynchronous jobs - meant to run on their own, completely independent of the main game loop
   Synchronous jobs - meant to run in cooperation with the main game loop - if a synchronous job is scheduled, the game
   loop will not advance to the next tick until it has completed
 */
public class JobManager
{
    // Single point of access for the entire codebase
    private static JobManager instance;

    private final JobScheduler jobScheduler;
    private final List<Future<Runnable>> incompleteSyncJobs;
    private boolean isShutdown;

    private JobManager ()
    {
        jobScheduler = new JobScheduler();
        incompleteSyncJobs = new LinkedList<>();
        isShutdown = false;
    }

    public Future<Runnable> scheduleAsyncJob (Runnable job)
    {
        return jobScheduler.scheduleJob(job);
    }

    public synchronized Future<Runnable> scheduleSyncJob (Runnable job)
    {
        Future<Runnable> jobStatus = jobScheduler.scheduleJob(job);
        incompleteSyncJobs.add(jobStatus);
        return jobStatus;
    }

    // Returns true if all scheduled synchronous jobs have completed, and false otherwise
    public boolean syncJobsComplete ()
    {
        return incompleteSyncJobs.isEmpty();
    }

    public void shutdown ()
    {
        isShutdown = true;
        jobScheduler.shutdown();
    }

    // Checks on all the synchronous jobs that haven't yet been completed and attempts to optimize the system
    synchronized void manageJobs ()
    {
        for (int i = incompleteSyncJobs.size() - 1; i >= 0; i--)
        {
            if (incompleteSyncJobs.get(i).isDone()) incompleteSyncJobs.remove(i);
        }

        jobScheduler.optimizeThreadPool();
    }

    boolean isShutdown ()
    {
        return isShutdown;
    }

    public synchronized static JobManager getInstance()
    {
        if (instance == null) instance = new JobManager();
        return instance;
    }
}
