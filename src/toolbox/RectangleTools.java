package toolbox;

import java.awt.*;

// A collection of useful methods relating to rectangles for use across the codebase
public class RectangleTools
{
    // Returns a rectangle of width [width] and height [height] that has been centered on the rectangle [base]
    public static Rectangle centerRectangles (Rectangle base, int width, int height)
    {
        return new Rectangle(base.x + base.width / 2 - width / 2, base.y + base.height / 2 - height / 2,
                width, height);
    }

    // This class is just a collection of useful methods - as such, it shouldn't be instantiated
    private RectangleTools () {}
}
