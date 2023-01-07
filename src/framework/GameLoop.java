package framework;

import jobs.JobManager;

// The game loop - performs data updates and rendering once every tick
class GameLoop
{
    // The amount of game ticks that will ideally occur every real-life second (the FPS)
    private static final int TICKS_PER_SECOND = 60;

    private final Game game;
    private final GameScreen gameScreen;

    private boolean isShutdown;

    GameLoop (Game game, GameScreen gameScreen)
    {
        this.game = game;
        this.gameScreen = gameScreen;

        isShutdown = false;
    }

    // The actual game loop
    void start ()
    {
        // Ideal amount of time (in ns) per game tick
        double timePerTick = 1000000000.0 / TICKS_PER_SECOND;

        // Reference to the last time recorded
        long previousTime = System.nanoTime();

        // How much total has passed (in ms)
        long millisRecorded = System.currentTimeMillis();
        // How many (ideal) non-recorded ticks have occurred between since the last time recorded
        double ticksToRecord = 0;
        // The number of recorded ticks that have occurred in the current second
        int ticksRecorded = 0;

        while (!isShutdown)
        {
            // Record the current time
            long currentTime = System.nanoTime();

            // Determine how many new (ideal) ticks have passed
            ticksToRecord += (currentTime - previousTime) / timePerTick;
            previousTime = currentTime;

            // Handle data and rendering for each non-recorded tick, and record the tick
            while (ticksToRecord >= 1)
            {
                game.update();
                while (!JobManager.getInstance().syncJobsComplete())
                {
                    // Wait for all synchronous jobs to complete before rendering
                }
                gameScreen.draw();

                ticksToRecord--;
                ticksRecorded++;
            }

            // If it's been over a second, reset the tick count and record the second that has passed
            if (System.currentTimeMillis() - millisRecorded > 1000)
            {
                millisRecorded += 1000;
                System.out.println("FPS: " + ticksRecorded);
                ticksRecorded = 0;
            }
        }
    }

    void shutdown ()
    {
        isShutdown = true;
    }
}
