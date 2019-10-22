package codewars.kyuEight;

public class Alternatingcase {

    public static String toAlternativeString(String string) {
        char[] chars = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            chars[i] = alternateCase(string.charAt(i));
        }

//        string.chars()
//                .map( c->c)
//                .collect()


        return new String(chars);
    }

    private static char alternateCase(char input) {
        if (Character.isUpperCase(input)) {
            return Character.toLowerCase(input);
        } else {
            return Character.toUpperCase(input);
        }
    }
}
