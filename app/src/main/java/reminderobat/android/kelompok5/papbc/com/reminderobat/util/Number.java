package reminderobat.android.kelompok5.papbc.com.reminderobat.util;

import java.util.Random;

/**
 * Created by arifinfrds on 12/14/17.
 */

public class Number {

    public static int getRandomNumberBetween(int min, int max) {
        Random foo = new Random();
        int randomNumber = foo.nextInt(max - min) + min;
        if (randomNumber == min) {
            // Since the random number is between the min and max values, simply add 1
            return min + 1;
        } else {
            return randomNumber;
        }
    }
}
