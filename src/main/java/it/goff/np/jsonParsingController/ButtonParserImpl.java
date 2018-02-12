package it.goff.np.jsonParsingController;

import com.google.gson.stream.JsonReader;
import com.sun.istack.internal.NotNull;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ButtonParserImpl implements ButtonParser {

    private final String pathFile;

    /**
     * Constructor for this class.
     * @param pathFile Require the path for the JSON file
     */
    public ButtonParserImpl(@NotNull final String pathFile) {
        if(! new File(pathFile).exists()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to find " + pathFile + " file.", ButtonType.CLOSE);
            alert.showAndWait();
            System.exit(1);
        }
        this.pathFile = pathFile;
    }

    @Override
    public Stream<String> parseName() {
        try {
            InputStream is = new FileInputStream(new File(pathFile));
            return readJsonStream(is).stream().map(Weapon::getName);
        } catch (IOException io) {
            io.printStackTrace();
        }
        throw new IllegalStateException();
    }

    @Override
    public Stream<String> parseImage() {
        try {
            InputStream is = new FileInputStream(new File(pathFile));
            return readJsonStream(is).stream().map(Weapon::getUrl);
        } catch (IOException io) {
            io.printStackTrace();
        }
        throw new IllegalStateException();
    }

    @Override
    public int size() {
        try {
            InputStream is = new FileInputStream(new File(pathFile));
            return readJsonStream(is).size();
        } catch (IOException io) {
            io.printStackTrace();
        }
        throw new IllegalStateException();
    }

    @NotNull
    private List<Weapon> readJsonStream(InputStream is) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"))){
            return readMessagesArray(reader);
        }
    }

    @NotNull
    private List<Weapon> readMessagesArray(JsonReader reader) throws IOException {
        List<Weapon> messages = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readMessage(reader));
        }
        reader.endArray();
        return messages;
    }

    @NotNull
    private Weapon readMessage(JsonReader reader) throws IOException {
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
