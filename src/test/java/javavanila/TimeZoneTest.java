package javavanila;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by tomasz.pasieka on 07.02.2019.
 */
public class TimeZoneTest {

    @Test
    public void gsfdgsdfgsdfg() {

        DateTime dateTime = new DateTime(new Date(234), DateTimeZone.forID("CET"));

//        Date localDate = TimezoneUtil.getLocalizedDateFromUtcDate(date, timeZone);
//        Timestamp localTimestamp = new Timestamp(localDate.getTime());
    }
}