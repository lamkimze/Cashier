package Item;

import java.util.Random;

public class Utils {

    public static int nextID(){
        Random random = new Random();
        int low = 000000000;
        int high = 999999999;
        return (random.nextInt(high - low) + low);
    }
}
