package javavanila.datetime;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

//https://www.baeldung.com/java-8-date-time-intro

public class LocalDateGuideTest {
    @Test
    public void defaultJava8DateApiDefaultFormats() {
        //These classes are mainly used when timezone are not required to be explicitly specified in the context
        //LocalDate This class does not store or represent a time or time-zone
        //It cannot represent an instant on the time-line without additional information such as an offset or time-zone.
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.MAX;
        LocalDateTime localDateTime = localDate.atTime(LocalTime.MAX);

        System.out.println(localDate);
        System.out.println(localDate.getDayOfWeek());

        System.out.println(localTime);
        System.out.println(localDateTime);

        LocalDate localDate1 = LocalDate.of(2019, 12, 31);
        LocalDate localDate2 = LocalDate.of(2019, 12, 31);
        System.out.println("Same LocalDate can be compared with \"==\" operator: " + (localDate1 == localDate2));

    }

    @Test
    public void allZones() {
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        allZoneIds.stream().sorted().forEach(System.out::println);
    }

    @Test
    public void defaultJava8DateApiZones() {

        //ZoneId specifies a time zone identifier and provides rules for converting between an Instant and a LocalDateTime.
        //ZoneOffset specifies a time zone offset from Greenwich/UTC time.

        ZoneId zoneId1 = ZoneId.of("Europe/Warsaw");
        ZoneId zoneId2 = ZoneId.of("Australia/Sydney");
        System.out.println("Zone1:" + zoneId1);
        System.out.println("Zone2:" + zoneId2);

        System.out.println("== LocalDateTime and NOON");
        LocalDateTime localDateTime = LocalDate.of(2019, 1, 1).atTime(LocalTime.NOON);
        System.out.println(localDateTime);

        System.out.println("== ZonedDateTime at " + zoneId1);
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(localDateTime, zoneId1);
        System.out.println(zonedDateTime1);
        System.out.println(zonedDateTime1.getOffset());

        System.out.println("== ZonedDateTime at " + zoneId2);
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(localDateTime, zoneId2);
        System.out.println(zonedDateTime2);
        System.out.println(zonedDateTime2.getOffset());

        //INFO: We say that LocalDateTime we are talking about is in ZoneId thus we have ZonedDateTime

        System.out.println("== ZonedDateTime at UTC:" + ZoneOffset.UTC);
        ZonedDateTime zonedDateTime1AsUTC = ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
        System.out.println(zonedDateTime1AsUTC);
        System.out.println(zonedDateTime1AsUTC.getOffset());

        System.out.println("== ZonedDateTime from UTC in zone1:" + zoneId1);
        ZonedDateTime zonedDateTime1AsUTCasZone1 = zonedDateTime1AsUTC.withZoneSameInstant(zoneId1);
        System.out.println(zonedDateTime1AsUTCasZone1);
        System.out.println(zonedDateTime1AsUTCasZone1.getOffset());

        System.out.println("== ZonedDateTime from UTC in zone2:" + zoneId1);
        ZonedDateTime zonedDateTime1AsUTCasZone2 = zonedDateTime1AsUTC.withZoneSameInstant(zoneId2);
        System.out.println(zonedDateTime1AsUTCasZone2);
        System.out.println(zonedDateTime1AsUTCasZone2.getOffset());
    }

    @Test
    public void defaultJava8Periods() {
        Period period = Period.ofDays(5);
        Duration duration = Duration.ofMinutes(10);

        LocalDateTime localDateTime = LocalDate.of(2019, 1, 1).atStartOfDay();
        LocalDateTime inTenMinutes = localDateTime.plus(duration);
        LocalDateTime inFifeDays = localDateTime.plus(period);

        System.out.println(localDateTime);
        System.out.println(inTenMinutes);
        System.out.println(inFifeDays);

        System.out.println("Minutes1:" + Duration.between(localDateTime, inTenMinutes).getSeconds() / 60);
        System.out.println("Minutes2:" + Duration.between(localDateTime, inTenMinutes).get(ChronoUnit.SECONDS) / 60);
        System.out.println("Minutes3:" + ChronoUnit.MINUTES.between(localDateTime, inTenMinutes));
        System.out.println("Days1:" + Period.between(localDateTime.toLocalDate(), inFifeDays.toLocalDate()).getDays());
        System.out.println("Days2:" + ChronoUnit.DAYS.between(localDateTime.toLocalDate(), inFifeDays.toLocalDate()));
    }

    @Test
    public void defaultJava8Instant() {
        Instant instant = Instant.now();
        System.out.println(instant);

        int nano = instant.getNano();
        System.out.println(nano);

        Date date = new Date(nano);
        System.out.println(date);
        System.out.println(date.getTime());

    }
}
