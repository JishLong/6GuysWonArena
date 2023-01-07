package resources;

import java.awt.*;

// A collection of important values that are frequently needed in various areas of the codebase
public class Globals
{
    // Rendering specifics
    public static float aspectRatio = 1.5f;
    public static float resolutionScale = 3f;
    public static Rectangle screenBounds = new Rectangle(0, 0, 0, 0);
    public static Rectangle gameBounds = new Rectangle(0, 0, 0, 0);
    public static Rectangle guiBounds = new Rectangle(0, 0, 0, 0);

    // This class is just a collection of useful globals - as such, it shouldn't be instantiated
    private Globals () {}
}
