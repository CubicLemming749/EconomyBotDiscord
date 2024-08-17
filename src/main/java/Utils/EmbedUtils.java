package Utils;

import it.auties.named.annotation.Option;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class EmbedUtils {
    public static void createEmbed(EmbedBuilder embed, String titleName, Color color, String descriptionText, @Option String author, @Option String imageUrl, @Option String thumbnailUrl){
        embed.setTitle(titleName);
        embed.setColor(color);
        embed.setDescription(descriptionText);
        embed.setAuthor(author);
        embed.setThumbnail(thumbnailUrl);
        embed.setImage(imageUrl);
    }
}
