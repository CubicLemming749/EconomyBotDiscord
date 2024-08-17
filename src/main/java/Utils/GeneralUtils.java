package Utils;

import java.util.ArrayList;

public class GeneralUtils {
    public static ArrayList<String> descriptionList = new ArrayList<String>();
    public static ArrayList<String> imageList = new ArrayList<String>();
    public static String selectDesc(int number){
        descriptionList.add(0, "Trabajaste creando peluches pirata de Skibidi Toilet. Ganaste:");
        descriptionList.add(1, "Trabajaste como un moderador de The Murder Mansion. Ganaste:");
        descriptionList.add(2, "Te contrataron como minero. Ganaste:");
        descriptionList.add(3, "Te contrataron para asesinar a trump, esta vez si salio bien. Ganaste:");
        descriptionList.add(4, "Test XD. Ganaste:");
        descriptionList.add(5, "No se que poner waza. Ganaste:");
        return descriptionList.get(number);
    }

    public static String selectImage(int number){
        imageList.add(0, "https://http2.mlstatic.com/D_NQ_NP_988092-CBT70828486039_082023-O.webp:");
        imageList.add(1, "https://media.discordapp.net/attachments/1122011030436974602/1274194082432024596/image.png?ex=66c15d36&is=66c00bb6&hm=b99d23c2d66548bbc436c8dd7050aa980efba938d1988e6db75cb4ef2204ba48&=&format=webp&quality=lossless&width=542&height=165");
        imageList.add(2, "https://i.ytimg.com/vi/Q3F3jvtZQd4/maxresdefault.jpg");
        imageList.add(3, "https://i.ytimg.com/vi/if2ob-nZ3lM/maxresdefault.jpg");
        imageList.add(4, "https://i.imgflip.com/8p2d8f.jpg");
        imageList.add(5, "https://cdn.discordapp.com/attachments/1122011030436974602/1274195347102765087/Screenshot_2024-07-18_220042.png?ex=66c15e64&is=66c00ce4&hm=552f85e17041b02068c07ddd08396006fd5483dd1c9f0432606bc211f3d2dd42&");
        return imageList.get(number);
    }
}
