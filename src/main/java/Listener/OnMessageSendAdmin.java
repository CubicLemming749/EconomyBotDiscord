package Listener;

import Utils.CoinsUtils;
import Utils.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Mentions;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

import static net.dv8tion.jda.api.Permission.MANAGE_SERVER;

public class OnMessageSendAdmin extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        Message msg = e.getMessage();
        MessageChannel channel = e.getChannel();
        String[] args = e.getMessage().getContentRaw().split(" ");
        if (args.length < 2 || !args[0].equalsIgnoreCase("!ecoadmin")) {
            return;
        }
        else if(args[0].equalsIgnoreCase("!ecoadmin") && !msg.getMember().hasPermission(MANAGE_SERVER)){
            EmbedBuilder embed = new EmbedBuilder();
            String title = "¡SIN PERMISOS SUFICIENTES! <:error:1274363040476958831>";
            String description = "Necesitas el permiso "+"manage server"+" para poder hacer esto.";
            String author = "COMANDOS DE ADMINISTRADOR DE ECONOMIA";
            EmbedUtils.createEmbed(embed, title, Color.RED, description, author, null, null);
            channel.sendMessageEmbeds(embed.build()).queue();
            return;
        }

        if (args[1].equalsIgnoreCase("add")) {
            if (args.length < 4) {
                channel.sendMessage("¡ERROR! Debes mencionar un usuario y especificar una cantidad.").queue();
                return;
            }

            Mentions mention = msg.getMentions();
            if (mention.getUsers().isEmpty()) {
                channel.sendMessage("¡ERROR! Debes mencionar a un usuario válido.").queue();
                return;
            }

            User user = mention.getUsers().get(0);
            String userID = user.getId();
            int amount;
            try {
                amount = Integer.parseInt(args[3]);
            } catch (NumberFormatException ex) {
                channel.sendMessage("¡ERROR! Debes introducir un número válido.").queue();
                return;
            }

            CoinsUtils.addCoins(userID, amount);
            channel.sendMessage("Se han añadido " + amount + " monedas a " + user.getAsMention()).queue();

        } else if (args[1].equalsIgnoreCase("reset")) {
            if (args.length < 2) {
                channel.sendMessage("¡ERROR! Debes mencionar un usuario.").queue();
                return;
            }

            Mentions mention = msg.getMentions();
            if (mention.getUsers().isEmpty()) {
                channel.sendMessage("¡ERROR! Debes mencionar a un usuario válido.").queue();
                return;
            }

            User user = mention.getUsers().get(0);
            String userID = user.getId();
            if (CoinsUtils.getCoins(userID) > 0) {
                CoinsUtils.resetCoins(userID);
                channel.sendMessage("Cantidad de dinero reiniciada para el usuario " + user.getAsMention()).queue();
            } else {
                channel.sendMessage("Este usuario ya tiene la cantidad de dinero establecida en 0.").queue();
            }
        }
    }
}
