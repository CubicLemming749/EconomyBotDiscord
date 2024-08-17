package Utils;

import Data.DataLoader;

public class CoinsUtils {
    private static DataLoader loader;

    public static void setLoader(DataLoader dataLoader) {
        loader = dataLoader;
    }
    public static int getCoins(String userID){
        return loader.userMap.getOrDefault(userID, 0);
    }

    public static void addCoins(String userID, int amount){
        int currentCoins = getCoins(userID);
        int newAmount = currentCoins + amount;
        loader.userMap.put(userID, newAmount);
        loader.saveToYaml();
    }
    public static void removeCoins(String userID, int amount){
        int currentCoins = getCoins(userID);
        int newAmount = currentCoins - amount;
        loader.userMap.put(userID, newAmount);
        loader.saveToYaml();
    }

    public static void resetCoins(String userID){
        loader.userMap.put(userID, 0);
        loader.saveToYaml();
    }
}
