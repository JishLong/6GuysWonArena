package toolbox;

import resources.Globals;

// A collection of useful methods relating to rendering for use across the whole codebase
public class RenderTools
{
    /* Calculates and sets the gui bounds by inscribing and centering a rectangle with width to height ratio
       [Globals.aspectRatio] inside a rectangle with the width and height of the screen
     */
    public static void calculateGuiBounds ()
    {
        int screenArea = Globals.screenBounds.width * Globals.screenBounds.height;
        float screenAspectRatio = (float)Globals.screenBounds.width / Globals.screenBounds.height;
        float guiBoundsAreaFactor = Math.max(Globals.aspectRatio, screenAspectRatio) /
                Math.min(Globals.aspectRatio, screenAspectRatio);
        float guiBoundsArea = screenArea / guiBoundsAreaFactor;

        // We round the width and height
        int guiBoundsHeight = (int)(Math.sqrt(guiBoundsArea / Globals.aspectRatio) + 0.5);
        int guiBoundsWidth = (int)(Globals.aspectRatio * Globals.guiBounds.height + 0.5);

        Globals.guiBounds = RectangleTools.centerRectangles(Globals.screenBounds, guiBoundsWidth, guiBoundsHeight);
    }

    /* Calculates and sets the game bounds by circumscribing and centering a rectangle with width to height ratio
       [Globals.aspectRatio] outside a rectangle with the width and height of the screen
     */
    public static void calculateGameBounds ()
    {
        float screenAspectRatio = (float)Globals.screenBounds.width / Globals.screenBounds.height;
        float gameBoundsAreaFactor = Math.max(Globals.aspectRatio, screenAspectRatio) /
                Math.min(Globals.aspectRatio, screenAspectRatio);
        float gameBoundsArea = Globals.screenBounds.height * Globals.screenBounds.height * screenAspectRatio *
                gameBoundsAreaFactor;

        // We round the width and height
        int gameBoundsHeight = (int)(Math.sqrt(gameBoundsArea / Globals.aspectRatio) + 0.5);
        int gameBoundsWidth = (int)(Globals.aspectRatio * Globals.gameBounds.height + 0.5);
        Globals.gameBounds = RectangleTools.centerRectangles(Globals.screenBounds, gameBoundsWidth, gameBoundsHeight);
    }

    // This class is just a collection of useful methods - as such, it shouldn't be instantiated
    private RenderTools () {}
}
