package codewars.beta;

public class PalindromeChecker {
    public boolean isPalindrome(String str) {

        if (str == null) {
            return false;
        }

        String cleanStr = removeMeaninglessChars(str);
        int halfLengthRounded = cleanStr.length() / 2;
        int halfLengthIndex = halfLengthRounded - 1;

        boolean isPalindrome = true;
        for (int i = 0; i <= halfLengthIndex; i++) {
            char charAtIndexFromBeginning = Character.toLowerCase(cleanStr.charAt(i));
            char charAtIndexFromEnd = Character.toLowerCase(cleanStr.charAt(cleanStr.length() -1 - i));
            if (charAtIndexFromBeginning != charAtIndexFromEnd) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    private String removeMeaninglessChars(String str) {
//        return str.replaceAll("[ ,'?!`;\\.]+", "");
        return str.replaceAll("\\W", "");
    }
}