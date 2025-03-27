package bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.util.*;

public class GifTopViewerListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.equalsIgnoreCase("$topgifs")) {
            try {
                String serverId = event.getGuild().getId();
                String fileName = serverId + "_gif_data.json";
                File file = new File("src/main/java/data/" + fileName);

                if (!file.exists()) {
                    event.getChannel().sendMessage("There is no GIF data for this server..").queue();
                    return;
                }

                Map<String, Integer> gifMap = getGifUsageMap(file);

                List<Map.Entry<String, Integer>> sortedGifs = gifMap.entrySet()
                        .stream()
                        .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                        .limit(10)
                        .toList();

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("🎉 Top 10 most used GIFs 🎉");
                embed.setColor(0x00A2E8);

                int rank = 1;
                for (Map.Entry<String, Integer> entry : sortedGifs) {
                    embed.addField("#" + rank,  entry.getKey() + "  **-" + entry.getValue() + " uses**", false);
                    rank++;
                }

                event.getChannel().sendMessageEmbeds(embed.build()).queue();

            } catch (Exception e) {
                event.getChannel().sendMessage("There was an error getting the top GIFs.").queue();
                e.printStackTrace();
            }
        }
    }

    private Map<String, Integer> getGifUsageMap(File file) {
        try {
            FileReader reader = new FileReader(file);
            StringBuilder jsonContent = new StringBuilder();
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine());
            }
            scanner.close();
            reader.close();

            JSONObject jsonObject = new JSONObject(jsonContent.toString());
            JSONObject gifsObject = jsonObject.getJSONObject("gifs");

            Map<String, Integer> gifMap = new HashMap<>();
            for (String key : gifsObject.keySet()) {
                gifMap.put(key, gifsObject.getInt(key));
            }

            return gifMap;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
