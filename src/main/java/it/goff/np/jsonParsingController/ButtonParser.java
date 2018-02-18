package it.goff.np.jsonParsingController;

import java.util.stream.Stream;

/**
 * Interface for parsing JSON file to build a dynamic structure of Button in the GUI.
 */
public interface ButtonParser {
    /**
     * This method return a Stream with the all name of the button.
     * @return A stream with name for each button.
     */
    Stream<String> parseName();

    /**
     * This method return a string with the path of the image for each Button.
     * @return A stream with path to image for each button.
     */
    Stream<String> parseImage();

    /**
     *
     * @return The size of the stream.
     */
    int size();
}
