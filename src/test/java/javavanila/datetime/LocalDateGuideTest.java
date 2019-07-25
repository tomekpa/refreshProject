package javavanila.datetime;

import org.junit.Test;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
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

    //https://stackoverflow.com/questions/40075780/java-best-practice-for-date-manipulation-storage-for-geographically-diverse-user

    @Test
    public void defaultJava8DateApiZones() {

        //INFO Z means UTC = ZuluTime

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
        System.out.println("== ZonedDateTime at " + zoneId1 + " stored as UTC");
        ZonedDateTime zone1asUtc = zonedDateTime1.withZoneSameInstant(zoneId1);
        System.out.println(zone1asUtc);
        System.out.println(zone1asUtc.getOffset());

        System.out.println("== ZonedDateTime at " + zoneId2);
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(localDateTime, zoneId2);
        System.out.println(zonedDateTime2);
        System.out.println(zonedDateTime2.getOffset());

        System.out.println("== ZonedDateTime at " + zoneId2 + " stored as UTC");
        ZonedDateTime zone2asUtc = zonedDateTime1.withZoneSameInstant(zoneId2);
        System.out.println(zone2asUtc);
        System.out.println(zone2asUtc.getOffset());

        //INFO: We say that LocalDateTime we are talking about is in ZoneId thus we have ZonedDateTime

        System.out.println("== ZonedDateTime at UTC:" + ZoneOffset.UTC);
        ZonedDateTime zonedDateTime1AsUTC = ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
        System.out.println(zonedDateTime1AsUTC);
        System.out.println(zonedDateTime1AsUTC.getOffset());

        System.out.println("== ZonedDateTime from UTC in zone1:" + zoneId1);
        ZonedDateTime zonedDateTime1AsUTCasZone1 = zonedDateTime1AsUTC.withZoneSameInstant(zoneId1);
        System.out.println(zonedDateTime1AsUTCasZone1);
        System.out.println(zonedDateTime1AsUTCasZone1.getOffset());

        System.out.println("== ZonedDateTime from UTC in zone2:" + zoneId2);
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

    //TODO: sql.Date
    @Test
    public void defaultJava8Instant() {
        Instant instant = Instant.now();
        System.out.println(instant);

        long epochSecond = instant.getEpochSecond();
        System.out.println(epochSecond);

        Date date = new Date(epochSecond * 1000);
        System.out.println(date);
        System.out.println(date.getTime());

//        LocalDate localDate = LocalDate.from(instant); //Java9

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC); //Java9
        System.out.println(localDateTime.toLocalDate());

        //ISO 8601 format contains Z at the end, meanint UTC+0

        //If the time is in UTC, add a Z directly after the time without a space.
        //Z is the zone designator for the zero UTC offset.
        //Sometimes referred to as "Zulu time" However the ACP 121 standard that defines the list of military time zones makes
        //no mention of UTC and derives the "Zulu time" from the Greenwich Mean Time[27]
        System.out.println(Instant.now().toString());
    }

    @Test
    public void defaultJava8UtilDateInstantConversions() {
        //https://stackoverflow.com/questions/26142864/how-to-get-utc0-date-in-java-8
        Instant instant = Instant.now();

        Date dateFromInstant = Date.from(instant);
        Instant InstantFromDate = dateFromInstant.toInstant();

        System.out.println(instant);
        System.out.println(InstantFromDate);
        System.out.println(dateFromInstant);

    }

    @Test
    public void defaultJava8OffsetDateTime() {
        //Instant has limitations such as no formatting options for generating strings in alternate formats
        Instant instant = Instant.now();
        OffsetDateTime odt1 = instant.atOffset(ZoneOffset.UTC);
        OffsetDateTime odt2 = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime odt3 = OffsetDateTime.now(ZoneId.of("Europe/Warsaw"));
        System.out.println(DateTimeFormatter.RFC_1123_DATE_TIME.format(odt1));
        System.out.println(DateTimeFormatter.RFC_1123_DATE_TIME.format(odt2));
        System.out.println(DateTimeFormatter.RFC_1123_DATE_TIME.format(odt3));
    }

    @Test
    public void defaultJava8HandlingDaylightSavingTimeDSTChange() throws ParseException {

//        2018-03-25T00:55
//        2018-03-25T00:55Z
//        2018-03-25T01:55+01:00[Europe/Warsaw]
//        2018-03-25T01:56+01:00[Europe/Warsaw]
//        2018-03-25T01:57+01:00[Europe/Warsaw]
//        2018-03-25T01:58+01:00[Europe/Warsaw]
//        2018-03-25T01:59+01:00[Europe/Warsaw]
//        2018-03-25T03:00+02:00[Europe/Warsaw]
//        2018-03-25T03:01+02:00[Europe/Warsaw]
//        2018-03-25T03:02+02:00[Europe/Warsaw]
//        2018-03-25T03:03+02:00[Europe/Warsaw]
//        2018-03-25T03:04+02:00[Europe/Warsaw]

        LocalDateTime localDateTimeBeforeDST = LocalDateTime.of(2018, 3, 25, 0, 55);
        System.out.println(localDateTimeBeforeDST.toString());


        ZonedDateTime utcTime = ZonedDateTime.of(localDateTimeBeforeDST, ZoneOffset.UTC);
        System.out.println(utcTime);

        ZoneId zoneId = ZoneId.of("Europe/Warsaw");
        ZonedDateTime warsawTime = utcTime.withZoneSameInstant(zoneId);
        System.out.println(warsawTime);

        for (int i = 1; i < 10; i++) {
            ZonedDateTime newWarsawTime = warsawTime.plusMinutes(i);
            System.out.println(newWarsawTime);
        }
    }
}
