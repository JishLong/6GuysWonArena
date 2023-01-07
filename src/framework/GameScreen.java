package framework;

import resources.Globals;

import javax.swing.*;
import java.awt.*;

// Manages the area the game renders to
class GameScreen
{
    private final Game game;
    private final JPanel screen;

    GameScreen (Game game)
    {
        this.game = game;
        screen = new JPanel();

        screen.setVisible(true);
    }

    void draw ()
    {
        // Create a back buffer
        Image bufferImage = screen.getGraphicsConfiguration().createCompatibleImage(
                Globals.screenBounds.width, Globals.screenBounds.height);
        Graphics bufferGraphics = bufferImage.getGraphics();

        // Draw everything to the back buffer
        game.draw(bufferGraphics);

        // Draw the back buffer to the screen and then dispose of it
        bufferGraphics.dispose();
        screen.getGraphics().drawImage(bufferImage, 0, 0, null);
        bufferImage.flush();
    }

    JPanel getScreen ()
    {
        return screen;
    }
}
