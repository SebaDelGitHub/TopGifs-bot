package bot;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        JDA jda = JDABuilder.createDefault(dotenv.get("DISCORD_TOKEN"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT).build();

        jda.addEventListener(new GifCounterListener());
        jda.addEventListener(new GifTopViewerListener());


    }
}
