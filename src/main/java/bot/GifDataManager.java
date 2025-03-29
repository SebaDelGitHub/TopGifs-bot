package bot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GifDataManager {
    private final File jsonFile;
    private final Gson gson;

    public GifDataManager(String fileName, String serverName, String serverId) {
        Dotenv dotenv = Dotenv.load();
        String saveUrl = dotenv.get("JSON_FILE_PATH");

        this.jsonFile = new File(saveUrl + fileName);
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        if (!jsonFile.exists()) {
            try (FileWriter writer = new FileWriter(jsonFile)) {
                Map <String, Object> data = new HashMap<>();
                data.put("serverName", serverName);
                data.put("serverId", serverId);
                data.put("gifs", new HashMap<String, Integer>());
                gson.toJson(data, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addGif(String gifUrl) {
        try {
            Map<String, Object> data = loadJson();
            Map<String, Double> gifs = (Map<String, Double>) data.get("gifs");
            gifs.put(gifUrl, gifs.getOrDefault(gifUrl, 0.0) + 1);

            try (FileWriter writer = new FileWriter(jsonFile)) {
                gson.toJson(data, writer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Loader JSON
    private Map<String, Object> loadJson() throws IOException {
        try {
            Reader reader = new FileReader(jsonFile);
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();

            return gson.fromJson(reader, type);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Map.of();
    }
}
