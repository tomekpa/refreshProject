package codewars.kyuEight;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlternatingcaseTest {

    @Test
    public void fixedTests() {
        assertEquals("HELLO WORLD", Alternatingcase.toAlternativeString("hello world"));
//        assertEquals("hello world", Alternatingcase.toAlternativeString("HELLO WORLD"));
//        assertEquals("HELLO world", Alternatingcase.toAlternativeString("hello WORLD"));
//        assertEquals("hEllO wOrld", Alternatingcase.toAlternativeString("HeLLo WoRLD"));
//        assertEquals("Hello World", Alternatingcase.toAlternativeString(Alternatingcase.toAlternativeString("Hello World")));
//        assertEquals("12345", Alternatingcase.toAlternativeString("12345"));
//        assertEquals("1A2B3C4D5E", Alternatingcase.toAlternativeString("1a2b3c4d5e"));
//        assertEquals("sTRINGuTILS.TOaLTERNATINGcASE", Alternatingcase.toAlternativeString("Alternatingcase.toAlternatingCase"));
    }

//    @Test
//    public void kataTitleTests() {
//        assertEquals("ALTerNAtiNG CaSe", Alternatingcase.toAlternativeString("altERnaTIng cAsE"));
//        assertEquals("altERnaTIng cAsE", Alternatingcase.toAlternativeString("ALTerNAtiNG CaSe"));
//        assertEquals("ALTerNAtiNG CaSe <=> altERnaTIng cAsE", Alternatingcase.toAlternativeString("altERnaTIng cAsE <=> ALTerNAtiNG CaSe"));
//        assertEquals("altERnaTIng cAsE <=> ALTerNAtiNG CaSe", Alternatingcase.toAlternativeString("ALTerNAtiNG CaSe <=> altERnaTIng cAsE"));
//    }

    @Test
    public void whatIsCodePoint() {

        int i = Character.toCodePoint( '\ud83c', '\udf09');
        System.out.println("i:"+i);

        i = Character.toCodePoint( ' ', '\u2209');
        System.out.println("i:"+i);

        StringBuilder sb = new StringBuilder();

        sb.appendCodePoint(66);
        sb.appendCodePoint(66);
        sb.appendCodePoint(66);
        sb.appendCodePoint(97);
        sb.appendCodePoint(97);
        sb.appendCodePoint(97);
        sb.appendCodePoint(45);
        sb.appendCodePoint(12345);
        sb.appendCodePoint(22345);

        System.out.println("s:"+sb.toString());


        String s = "BBB";
        System.out.println(Character.codePointAt(s, 0));



    }
}