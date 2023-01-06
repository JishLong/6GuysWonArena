package Toolbox;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class FileTools
{
    // This class is just a collection of useful methods - as such, it shouldn't be instantiated
    private FileTools () {}

    // Returns a random line from a file as a Unicode string - each line has an equal chance of being picked
    public static String getRandomLineFromFile (String pathname)
    {
        String line = "";
        Random rng = new Random();

        try
        {
            List<String> lines = Files.readAllLines(Paths.get(pathname), StandardCharsets.UTF_8);
            if (!lines.isEmpty()) line = lines.get(rng.nextInt(lines.size()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return line;
    }
}
