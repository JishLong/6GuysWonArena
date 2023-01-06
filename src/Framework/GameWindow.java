package Framework;

import Toolbox.FileTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

// Manages the game window the game runs in
class GameWindow extends WindowAdapter
{
    private final JFrame window;

    GameWindow (GameScreen gameScreen)
    {
        window = new JFrame();

        window.setVisible(false);
        window.add(gameScreen.getScreen());

        initialize();
    }

    void open ()
    {
        window.setVisible(true);
    }

    private void initialize ()
    {
        // Sizing and resizing
        window.setUndecorated(false);
        window.setSize(500, 500);
        window.setResizable(true);
        window.addComponentListener(RESIZE_OPERATION);

        // Position and focus
        window.setLocationRelativeTo(null);
        window.setFocusable(true);

        // Closing the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.addWindowListener(CLOSE_OPERATION);

        // Cosmetics - splash text and (invisible) mouse cursor
        window.setTitle(FileTools.getRandomLineFromFile("resources/windowSplashMessages.txt"));
        window.setCursor(window.getToolkit().createCustomCursor(new BufferedImage( 1, 1,
                        BufferedImage.TYPE_INT_ARGB ), new Point(), null));
    }

    // Called when the game window is closed
    private static final WindowListener CLOSE_OPERATION = new WindowAdapter()
    {
        @Override
        public void windowClosing(WindowEvent e)
        {
            Program.shutdown();
        }
    };

    // Called every time the game window is resized
    private static final ComponentListener RESIZE_OPERATION = new ComponentAdapter()
    {
        public void componentResized(ComponentEvent componentEvent)
        {

        }
    };
}
