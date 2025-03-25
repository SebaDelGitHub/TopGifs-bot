package bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GifCounterListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        try {
            String messageSent = event.getMessage().getContentRaw();

            Pattern gifUrlPattern = Pattern.compile("(https?://(?:media\\.)?(?:tenor\\.com|giphy\\.com|giphy\\.media)\\S+)");
            Matcher matcher = gifUrlPattern.matcher(messageSent);

            String serverId = event.getGuild().getId();
            String serverName = event.getGuild().getName();
            String fileName = serverId + "_gif_data.json";

            GifDataManager dataManager = new GifDataManager(fileName, serverName, serverId);

            while (matcher.find()) {
                String gifUrl = matcher.group(1);
                dataManager.addGif(gifUrl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
