package codewars.kyuEight;

public class ReversedStrings {

    public static String solution(String str) {
        char[] chars = str.toCharArray(); //getBytes uses StringCoding.encode>>Charset.defaultCharset()
        return new String(reverse(chars));
    }

    private static char[] reverse(char[] chars) {

        int firstPos = 0;
        int lastPos = chars.length - 1;
        return swapCharsUntilReversed(chars, firstPos, lastPos);
    }

    private static char[] swapCharsUntilReversed(char[] chars, int firstPosition, int secondPosition) {

        //Recurrency exit condition
        if (firstPosition >= secondPosition) {
            return chars;
        }

        char firstByte = chars[firstPosition];
        chars[firstPosition] = chars[secondPosition];
        chars[secondPosition] = firstByte;

        return swapCharsUntilReversed(chars, ++firstPosition, --secondPosition);
    }
}