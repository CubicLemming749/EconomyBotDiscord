import Data.DataLoader;
import Listener.OnMessageSend;
import Listener.OnMessageSendAdmin;
import Utils.CoinsUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {
        JDA bot = JDABuilder.createDefault("TOKEN_SECRET").enableIntents(GatewayIntent.MESSAGE_CONTENT).
                setActivity(Activity.playing("Jugando a ser ministro de economia")).addEventListeners(new OnMessageSend(), new OnMessageSendAdmin()).build();
        DataLoader loader = new DataLoader();
        loader.loadFromYaml();
        CoinsUtils.setLoader(loader);
    }
}
