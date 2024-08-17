package Data;

import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class DataLoader {
    public static HashMap<String, Integer> userMap;
    static Yaml file;
    public DataLoader() {
        this.userMap = new HashMap<>();
        file = new Yaml();
        loadFromYaml();
    }

    public static void saveToYaml(){
        try (FileWriter writer = new FileWriter("C:\\Users\\mauri\\IdeaProjects\\DiscordEconomyBotTest\\src\\main\\resources\\data.yml")) {
            file.dump(userMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadFromYaml() {
        try (FileReader reader = new FileReader("C:\\Users\\mauri\\IdeaProjects\\DiscordEconomyBotTest\\src\\main\\resources\\data.yml")) {
            userMap = file.load(reader);
            if (userMap == null) {
                userMap = new HashMap<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> getMap() {
        return userMap;
    }
}
