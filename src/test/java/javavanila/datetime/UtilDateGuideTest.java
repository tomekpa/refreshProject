package javavanila.datetime;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

//Java 8 introduced new APIs for Date and Time to address the shortcomings of the older java.util.Date and java.util.Calendar.
//TODO: java.util.Calendar

public class UtilDateGuideTest {
    @Test
    public void shoudldPrintSystemMills() {
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
    }

    @Test
    public void shoudldPrintDate() {
        long timestamp = System.currentTimeMillis();
        Date date = new Date();
        System.out.println(date);
        System.out.println(timestamp);
        System.out.println(date.getTime());
    }

    @Test
    public void isDateSameAsLong() {
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        System.out.println(date);
        System.out.println(timestamp);
        System.out.println(date.getTime());
    }

    @Test
    public void shouldSimpleFormatDate() {
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        System.out.println(simpleDateFormat.format(date));


        System.out.println(simpleDateFormat.format(date));
    }

    @Test
    public void shouldAddOneMinutyByAdd6000() {
        long someInput = 1553035890000L; //2019-03-19 11:51 IN MY CURRENT LOCALE ?
        Date date1 = new Date(someInput);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        long fifeMinutes = 5 * 60 * 1_000;
        Date date2 = new Date(someInput + fifeMinutes);
        System.out.println(simpleDateFormat.format(date1));

        assertThat(simpleDateFormat.format(date1), StringContains.containsString("11:51"));
        assertThat(simpleDateFormat.format(date2), StringContains.containsString("11:56"));
        System.out.println(simpleDateFormat.format(date2));
    }

    @Test
    public void shouldPrintSomeTimeZones() {
        System.out.println("==============================================");
        System.out.println("Default TimeZone:" + TimeZone.getDefault().getDisplayName());
        System.out.println("Default TimeZone:" + TimeZone.getDefault().getID());
        System.out.println("Default TimeZone:" + TimeZone.getDefault().getRawOffset()/1000/60/60);
        System.out.println("==============================================");
        System.out.println("Default TimeZone:" + TimeZone.getTimeZone("Africa/Luanda").getDisplayName());
        System.out.println("Default TimeZone:" + TimeZone.getTimeZone("Africa/Luanda").getID());
        System.out.println("Default TimeZone:" + TimeZone.getTimeZone("Africa/Luanda").getRawOffset()/1000/60/60);
        System.out.println("==============================================");
        System.out.println("Default TimeZone:" + TimeZone.getTimeZone("GMT").getDisplayName());
        System.out.println("Default TimeZone:" + TimeZone.getTimeZone("GMT").getID());
        System.out.println("Default TimeZone:" + TimeZone.getTimeZone("GMT").getRawOffset()/1000/60/60);
        System.out.println("==============================================");
        System.out.println("Default TimeZone:" + TimeZone.getTimeZone("UTC").getDisplayName());
        System.out.println("Default TimeZone:" + TimeZone.getTimeZone("UTC").getID());
        System.out.println("Default TimeZone:" + TimeZone.getTimeZone("UTC").getRawOffset()/1000/60/60);
        System.out.println("==============================================");
        System.out.println("Default TimeZone:" + TimeZone.getTimeZone("US/Hawaii").getDisplayName());
        System.out.println("Default TimeZone:" + TimeZone.getTimeZone("US/Hawaii").getID());
        System.out.println("Default TimeZone:" + TimeZone.getTimeZone("US/Hawaii").getRawOffset()/1000/60/60);
        System.out.println("==============================================");
    }

    @Test
    public void shouldCheckIfDateIsInGMT() throws ParseException {
        System.out.println("==============================================");
        String someInput = "2019-03-19 11:51";
        SimpleDateFormat currentLocaleDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        Date currentDate = currentLocaleDateFormat.parse(someInput);
        System.out.println("OPERATION: Parse using default locale");
        System.out.println("INPUT:" + someInput);
        System.out.println("OUTPUT:" + currentDate.getTime());
        System.out.println("==============================================");

        SimpleDateFormat gmtLocaleDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        gmtLocaleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date gmtDate = gmtLocaleDateFormat.parse(someInput);
        System.out.println("OPERATION: Parse using GMT locale");
        System.out.println("INPUT:" + someInput);
        System.out.println("OUTPUT:" + gmtDate.getTime());
        System.out.println("==============================================");

        SimpleDateFormat printFormatLocal = new SimpleDateFormat("YYYY-MM-dd hh:mm z");
        SimpleDateFormat printFormatGmt = new SimpleDateFormat("YYYY-MM-dd hh:mm z");
        printFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));

        System.out.println("OPERATION: currentDate formatted as default locale");
        System.out.println("INPUT:" + printFormatLocal.format(currentDate));
        System.out.println("==============================================");
        System.out.println("OPERATION: currentDate formatted as GMT locale");
        System.out.println("INPUT:" + printFormatGmt.format(currentDate));
        System.out.println("==============================================");
        System.out.println("OPERATION: gmtDate formatted as default locale");
        System.out.println("INPUT:" + printFormatLocal.format(gmtDate));
        System.out.println("==============================================");
        System.out.println("OPERATION: gmtDate formatted as GMT locale");
        System.out.println("INPUT:" + printFormatGmt.format(gmtDate));
        System.out.println("==============================================");
    }


    /*
     * INFO:
     * date with +1h is parsed as +1 locale and this generate timestamp ONE
     * date with +0h is parsed as +0 locale and this generate timestamp ZERO
     * Timestamps==long of ONE and ZERO are equal because timestamp is
     * Timezone neutral
     * CONCLUSION: parse date gives different result in different timezone
     */
    @Test
    public void shouldParseAsSameMills() throws ParseException {
        String willBeParsedAsPlusOne = "2019-03-19 11:51";
        String willBeParsedAsPlusZero = "2019-03-19 10:51";

        SimpleDateFormat formatInPlusOneLocale = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        formatInPlusOneLocale.setTimeZone(TimeZone.getTimeZone("Africa/Luanda"));

        SimpleDateFormat formatInPlusZeroLocale = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        formatInPlusZeroLocale.setTimeZone(TimeZone.getTimeZone("GMT"));

        long plusOneMills = formatInPlusOneLocale.parse(willBeParsedAsPlusOne).getTime();
        long plusZeroMills = formatInPlusZeroLocale.parse(willBeParsedAsPlusZero).getTime();

        assertEquals(plusOneMills, plusZeroMills);
    }

    @Test
    public void shouldSuccesfullyParseAndCompareInSameZone() throws ParseException {

        String parseableToDate = "2019-03-19 11:51";

        SimpleDateFormat formatInPlusOneLocale = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        formatInPlusOneLocale.setTimeZone(TimeZone.getTimeZone("Africa/Luanda"));

        SimpleDateFormat formatInPlusZeroLocale = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        formatInPlusZeroLocale.setTimeZone(TimeZone.getTimeZone("GMT"));

        long plusOneMills = formatInPlusOneLocale.parse(parseableToDate).getTime();
        long plusZeroMills = formatInPlusZeroLocale.parse(parseableToDate).getTime();

        System.out.println(plusOneMills);
        System.out.println(plusZeroMills);
        System.out.println(new Date(plusOneMills)); //To String in default locae = +1

        String plusOneAsDateString = formatInPlusOneLocale.format(plusOneMills); //new Date(plusOneMills) give same result
        String plusZeroAsDateString = formatInPlusZeroLocale.format(plusZeroMills);

        assertEquals(parseableToDate, plusOneAsDateString);
        assertEquals(parseableToDate, plusZeroAsDateString);
//TODO: WTF ?
//        Expected :2019-03-19 11:51
//        Actual   :2019-12-31 11:51
    }
}
