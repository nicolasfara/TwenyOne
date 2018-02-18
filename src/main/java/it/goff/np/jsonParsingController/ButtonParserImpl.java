package it.goff.np.jsonParsingController;

import com.google.gson.stream.JsonReader;
import com.sun.istack.internal.NotNull;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Class with method for parsing JSON files.
 */
public class ButtonParserImpl implements ButtonParser {

    private InputStream inputStream;

    /**
     * Constructor for this class.
     * @param pathFile Require the path for the JSON file.
     */
    public ButtonParserImpl(@NotNull final String pathFile) {
        if (!new File(pathFile).exists()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to find " + pathFile + " file.", ButtonType.CLOSE);
            alert.showAndWait();
            System.exit(1);
        }
        try {
            inputStream = new FileInputStream(new File(pathFile));
        } catch (IOException ex) {
            Alert readErrorAlert = new Alert(Alert.AlertType.ERROR, "IOException on read " + pathFile, ButtonType.CLOSE);
            readErrorAlert.showAndWait();
            System.exit(1);
        }
    }

    /**
     *
     * @return a stream with all name for each button.
     */
    @Override
    public Stream<String> parseName() {
        return readStream(Weapon::getName);
    }

    /**
     *
     * @return a stream with all img reference.
     */
    @Override
    public Stream<String> parseImage() {
        return readStream(Weapon::getUrl);
    }

    /**
     *
     * @return the size of the json array.
     */
    @Override
    public int size() {
        try {
            return readJsonStream(inputStream).size();
        } catch (IOException io) {
            io.printStackTrace();
        }
        throw new IllegalStateException();
    }

     private Stream<String> readStream(final Function<? super Weapon, ? extends String> func) {
         try {
             return readJsonStream(inputStream).stream().map(func);
         } catch (IOException io) {
             io.printStackTrace();
         }
         throw new IllegalStateException();
    }

    @NotNull
    private List<Weapon> readJsonStream(final InputStream is) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"))) {
            return readMessagesArray(reader);
        }
    }

    @NotNull
    private List<Weapon> readMessagesArray(final JsonReader reader) throws IOException {
        List<Weapon> messages = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readMessage(reader));
        }
        reader.endArray();
        return messages;
    }

    @NotNull
    private Weapon readMessage(final JsonReader reader) throws IOException {
        String wName = null;
        String url = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                wName = reader.nextString();
            } else if (name.equals("url_img")) {
                url = reader.nextString();
            }
        }
        reader.endObject();

        return new WeaponImpl.WeaponBuilder()
                .name(wName)
                .url(url)
                .build();
    }
}
