package javavanila.times;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        long someInput = 1553035890000L; //2019-03-20 12:20
        Date date1 = new Date(someInput);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        long fifeMinutes = 5 * 60 * 1_000;
        Date date2 = new Date(someInput + fifeMinutes);
        System.out.println(simpleDateFormat.format(date1));

        assertThat(simpleDateFormat.format(date1), StringContains.containsString("11:51"));
        assertThat(simpleDateFormat.format(date2), StringContains.containsString("11:56"));
        System.out.println(simpleDateFormat.format(date2));
        date2.setTime(date2.getTime()+60000L);
        System.out.println(simpleDateFormat.format(date2));
        assertThat(simpleDateFormat.format(date2), StringContains.containsString("11:57"));

    }
}
