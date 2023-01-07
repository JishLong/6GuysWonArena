package jobs;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

// Handles the scheduling of submitted jobs using a thread pool
class JobScheduler
{
    private final ScheduledThreadPoolExecutor threadPool;
    private int threadPoolSize;

    // Should only be used within the Jobs package (behind the scenes type shit)
    JobScheduler ()
    {
        threadPoolSize = Runtime.getRuntime().availableProcessors();
        threadPool = new ScheduledThreadPoolExecutor(threadPoolSize);
    }

    synchronized Future<Runnable> scheduleJob (Runnable job)
    {
        // Unchecked cast is fine here since we only ever submit Runnables to the thread pool
        if (!threadPool.isShutdown()) return (Future<Runnable>)threadPool.submit(job);
        else return null;
    }

    // Adjusts the thread pool size periodically based on the current number of cores available to the game
    synchronized void optimizeThreadPool ()
    {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (threadPoolSize != availableProcessors)
        {
            threadPoolSize = availableProcessors;
            threadPool.setCorePoolSize(availableProcessors);
        }
    }

    synchronized void shutdown ()
    {
        threadPool.shutdownNow();
    }
}
