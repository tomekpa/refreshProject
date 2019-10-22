package codewars.kyuEight;

public class RemoveFirstAndLastCharacter {

    public static String remove(String str) {

        char[] input = str.toCharArray();
        char[] output = new char[input.length - 2];

        //* @param      src      the source array.
        //* @param      srcPos   starting position in the source array.
        //* @param      dest     the destination array.
        //* @param      destPos  starting position in the destination data.
        //* @param      length   the number of array elements to be copied.
        System.arraycopy(input, 1, output, 0, input.length - 2);
        return new String(output);
    }
}
