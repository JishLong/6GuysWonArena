package framework;

import resources.Globals;
import java.awt.*;

// An abstracted version of the game that sits atop the framework - the heart of the game
public class Game
{
    Game ()
    {

    }

    // Initialize anything that's needed BEFORE the game starts up
    void initialize ()
    {

    }

    // Load in the (rest of) the game contents - mainly assets
    void loadContent ()
    {

    }

    // Data updates that occur once every tick
    void update()
    {

    }

    // Rendering that occurs once every tick
    void draw(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.fillRect(Globals.gameBounds.x, Globals.gameBounds.y, Globals.gameBounds.width,
                Globals.gameBounds.height);
        g.setColor(Color.RED);
        g.fillRect(Globals.guiBounds.x, Globals.guiBounds.y, Globals.guiBounds.width,
                Globals.guiBounds.height);
    }
}
