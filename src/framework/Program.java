package framework;

import jobs.JobManager;
import jobs.ManageJobsJob;

// The backbone of the codebase - contains the starting and ending points of the game
public class Program
{
    private static GameLoop gameLoop;

    // This class shouldn't be instantiated, it should essentially be used as a singleton
    private Program () {}

    public static void main (String[] args)
    {
        // Start up the job system so we can have multithreading
        JobManager.getInstance().scheduleAsyncJob(new ManageJobsJob());

        // Create the framework of the game
        Game game = new Game();
        GameScreen gameScreen = new GameScreen(game);
        GameWindow gameWindow = new GameWindow(gameScreen);
        gameLoop = new GameLoop(game, gameScreen);

        // Initialize anything that's needed BEFORE the game starts up
        game.initialize();

        // Start up the game
        gameWindow.open();
        gameLoop.start();

        // Load in the (rest of) the game contents - mainly assets
        game.loadContent();
    }

    public static void shutdown ()
    {
        // Shut down the job system
        JobManager.getInstance().shutdown();

        // Shut down the game - note that the game window already shut itself down, so we don't have to worry about it
        gameLoop.shutdown();
    }
}
