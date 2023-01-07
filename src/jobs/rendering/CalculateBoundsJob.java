package jobs.rendering;

import toolbox.RenderTools;

public class CalculateBoundsJob implements Runnable
{
    public void run ()
    {
        /* We calculate the bounds twice for the case of maximizing the window - sometimes, the calculations are done
           so quickly that the window is still resizing by the time they're complete. So, we do it twice
         */
        for (int i = 0; i < 2; i++)
        {
            RenderTools.calculateGuiBounds();
            RenderTools.calculateGameBounds();
        }
    }
}
