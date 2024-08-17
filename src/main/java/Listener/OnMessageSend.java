package Listener;

import Data.DataLoader;
import Utils.CoinsUtils;
import Utils.EmbedUtils;
import Utils.GeneralUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Mentions;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class OnMessageSend extends ListenerAdapter {

    public static int cooldownTime = 5000;

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        Message msg = e.getMessage();
        MessageChannel channel = e.getChannel();
        String[] args = e.getMessage().getContentRaw().split(" ");
        if(args.length < 2 || !args[0].equalsIgnoreCase("!eco")){
            return;
        }
        if (args[1].equalsIgnoreCase("balance")) {
            String userID = msg.getAuthor().getId();
            int balance = CoinsUtils.getCoins(userID); // Obteniendo el balance del usuario
            channel.sendMessage("Tu balance es de: " + balance + " monedas").queue();
        }else if(args[1].equalsIgnoreCase("work")){
            int amount = ThreadLocalRandom.current().nextInt(50, 500);
            int descriptionSelect = ThreadLocalRandom.current().nextInt(0, 5);
            String userID = msg.getAuthor().getId();
            CoinsUtils.addCoins(userID, amount);
            EmbedBuilder embed = new EmbedBuilder();
            String title = "Trabajo realizado con exito <:success:1274363222862073888>";
            String description = GeneralUtils.selectDesc(descriptionSelect)+" "+amount;
            String author = msg.getAuthor().getName();
            String thumbnail = msg.getAuthor().getAvatarUrl();
            String url = GeneralUtils.selectImage(descriptionSelect);
            EmbedUtils.createEmbed(embed, title, Color.GREEN, description, author, url, thumbnail);
            channel.sendMessageEmbeds(embed.build()).queue();
        }else if(args[1].equalsIgnoreCase("rob")){
            Mentions mention = msg.getMentions();
            if (mention.getUsers().size() < 1) {
                channel.sendMessage("¡ERROR! Debes mencionar al menos a un usuario para poder robar.").queue();
                return;
            }
            User target = mention.getUsers().get(0);
            String targetId = target.getId();
            String userId = msg.getAuthor().getId();
            int targetCoins = CoinsUtils.getCoins(targetId);

            if(targetCoins == 0){
                EmbedBuilder embed = new EmbedBuilder();
                String title = "¡Este usuario no tiene dinero suficiente! <:error:1274363040476958831>";
                String description = target.getName() + " tiene 0 monedas, así que no puedes robarle nada.";
                EmbedUtils.createEmbed(embed, title, Color.RED, description, "ECONOMY BOT TEST", null, msg.getAuthor().getAvatarUrl());
                channel.sendMessageEmbeds(embed.build()).queue();
                return;
            }
            else if(target == msg.getAuthor()){
                EmbedBuilder embed = new EmbedBuilder();
                String title = "¡No te puedes robar a ti mismo! <:error:1274363040476958831>";
                String description = "No se necesita explicacion para esto...";
                EmbedUtils.createEmbed(embed, title, Color.RED, description, "ECONOMY BOT TEST", null, msg.getAuthor().getAvatarUrl());
                channel.sendMessageEmbeds(embed.build()).queue();
                return;
            }

            int robAmount = ThreadLocalRandom.current().nextInt(Math.max(-targetCoins, -500), targetCoins + 1);
            if (robAmount > 0) {
                CoinsUtils.removeCoins(targetId, robAmount);
                CoinsUtils.addCoins(userId, robAmount);
                EmbedBuilder embed = new EmbedBuilder();
                String title = "Robo realizado con éxito <:success:1274363222862073888>";
                String description = "Robaste a " + target.getName() + " la cantidad de: " + robAmount + " monedas.";
                EmbedUtils.createEmbed(embed, title, Color.GREEN, description, "ECONOMY BOT TEST", null, msg.getAuthor().getAvatarUrl());
                channel.sendMessageEmbeds(embed.build()).queue();
            } else {
                CoinsUtils.removeCoins(userId, -robAmount);
                EmbedBuilder embed = new EmbedBuilder();
                String title = "¡TE ATRAPARON ROBANDO! <:error:1274363040476958831>";
                String description = "Intentaste robarle a: " + target.getName() + ". Pero fuiste atrapado y te quitaron: " + -robAmount + " monedas.";
                EmbedUtils.createEmbed(embed, title, Color.RED, description, "ECONOMY BOT TEST", null, msg.getAuthor().getAvatarUrl());
                channel.sendMessageEmbeds(embed.build()).queue();
            }
        }
    }
}


