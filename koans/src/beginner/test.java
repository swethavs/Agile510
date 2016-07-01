package beginner;

import java.text.MessageFormat;
import java.util.MissingFormatArgumentException;

import static com.sandwich.util.Assert.assertEquals;
import static com.sandwich.util.Assert.fail;

/**
 * Created by Vijay on 6/30/2016.
 */
public class test {
    public static void main(String[] args){
        String str = "I AM a number ONE!";
        System.out.println(str.substring(0));
        System.out.println(str.substring(1));
        System.out.println(str.substring(5));
        System.out.println(str.substring(14, 17));
        System.out.println(str.substring(7, str.length()));
        System.out.println(String.format("%s %s %s", "a", "b", "c", "d"));
        System.out.println(MessageFormat.format("{0} {1} {0}", "a"));
        System.out.println(3 / 2);
        try {
           throw new MissingFormatArgumentException(String.format("%s %s %s", "a", "b"));
        } catch (MissingFormatArgumentException e) {
            e.getMessage();
            e.getClass();
        }
    }
}
