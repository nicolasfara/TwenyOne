package it.goff.np.jsonParsingController;

import com.google.gson.stream.JsonReader;
import org.jetbrains.annotations.NotNull;
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
        this.pathFile = pathFile;
    }

    @Override
    public Stream<String> parseName() {
        try {
            InputStream is = new FileInputStream(new File(pathFile));
            return readJsonStream(is).stream().map(Weappon::getName);
        } catch (IOException io) {
            io.printStackTrace();
        }
        throw new IllegalStateException();
    }

    @Override
    public Stream<String> parseImage() {
        try {
            InputStream is = new FileInputStream(new File(pathFile));
            return readJsonStream(is).stream().map(Weappon::getUrl);
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
    private List<Weappon> readJsonStream(InputStream is) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"))){
            return readMessagesArray(reader);
        }
    }

    @NotNull
    private List<Weappon> readMessagesArray(JsonReader reader) throws IOException {
        List<Weappon> messages = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readMessage(reader));
        }
        reader.endArray();
        return messages;
    }

    @NotNull
    private Weappon readMessage(JsonReader reader) throws IOException {
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
        return new WeapponImpl.WeapponBuilder()
                .name(wName)
                .url(url)
                .build();
    }
}
