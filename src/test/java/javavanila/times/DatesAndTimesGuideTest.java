package javavanila.times;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.MatcherAssert.assertThat;

public class DatesAndTimesGuideTest {
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
        System.out.println("Default TimeZone:" + TimeZone.getDefault().getDisplayName());
        System.out.println("Default TimeZone:" + TimeZone.getDefault().getID());
        System.out.println("Default TimeZone:" + TimeZone.getDefault().getRawOffset()/1000/60/60);
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

        String someInput = "2019-03-19 11:51";
        SimpleDateFormat currentLocaleDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        Date currentDate = currentLocaleDateFormat.parse(someInput);
        System.out.print("parsed: ");
        System.out.println(currentDate.getTime());

        SimpleDateFormat gmtLocaleDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        gmtLocaleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date gmtDate = gmtLocaleDateFormat.parse(someInput);
        System.out.print("gmtLocaleDateFormat: ");
        System.out.println(gmtDate.getTime());

        SimpleDateFormat printFormatLocal = new SimpleDateFormat("YYYY-MM-dd hh:mm z");
        System.out.println(printFormatLocal.format(currentDate));
        System.out.println(printFormatLocal.format(gmtDate));

        SimpleDateFormat printFormatGmt = new SimpleDateFormat("YYYY-MM-dd hh:mm z");
        printFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println(printFormatGmt.format(currentDate));
        System.out.println(printFormatGmt.format(gmtDate));
    }
}
